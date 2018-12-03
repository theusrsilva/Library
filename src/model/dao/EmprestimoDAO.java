/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.bean.Estoque;
import model.bean.Livro;
import model.bean.Usuario;

/**
 *
 * @author Lohan
 */
public class EmprestimoDAO {

    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    private Connection con = null;

    public EmprestimoDAO() {
        con = ConnectionFactory.getConnection();
    }

    public void criaPedidoEmprestimo(String cpf, List<Livro> livros) {
        Usuario usuario = usuarioDAO.findByCpf(cpf);
        if (usuario != null) {
            Connection con = ConnectionFactory.getConnection();
            PreparedStatement stmt = null;
            ResultSet ultimoId = null;
            
            EstoqueDAO estoqueDAO = new EstoqueDAO();
            
            Connection con2 = ConnectionFactory.getConnection();
            PreparedStatement stmt2 = null;   
            
            Date dataEmprestimo = new Date(System.currentTimeMillis());
            Date dataPrevista = geraDataPrevista();
            

            try {
                stmt = con.prepareStatement("INSERT INTO emprestimo (id_usuario, data_emprestimo, data_devolucao, data_prevista, status_emprestimo, status_devolucao) VALUES (?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
                stmt.setInt(1, usuario.getId_usuario());
                stmt.setDate(2,dataEmprestimo);
                stmt.setObject(3, null);
                stmt.setDate(4, dataPrevista);     
                stmt.setString(5, "PENDENTE");
                stmt.setBoolean(6, false);
                
                
                stmt.executeUpdate();
                ultimoId = stmt.getGeneratedKeys();
                int idEmprestimo = 0;
                if (ultimoId.next()) {
                    idEmprestimo = ultimoId.getInt(1);
                }
                for (Livro livro : livros) {                 
                    stmt2 = con.prepareStatement("INSERT INTO emprestimo_livro (id_emprestimo,id_livro) VALUES(?,?) ");
                    stmt2.setInt(1, idEmprestimo);
                    stmt2.setInt(2, livro.getId_livro());
                    stmt2.executeUpdate();
                    
                    estoqueDAO.atualizaQtdLivro(livro.getIsbn(), idEmprestimo);
                    
                }
                
                
                
                
                
 

            } catch (SQLException ex) {
                Logger.getLogger(EmprestimoDAO.class.getName()).log(Level.SEVERE, null, ex);

            } finally {
                ConnectionFactory.closeConnection(con, stmt, ultimoId);
                ConnectionFactory.closeConnection(con2, stmt2);
            }
        }
    }
    
    public List<Livro> findLivrosEmpretadosPorCpf(String cpf){
        List<Livro> livrosEmprestados = new ArrayList<>();
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt=con.prepareStatement("SELECT * FROM emprestimo e "
                    + "INNER JOIN emprestimo_livro el ON(e.id_emprestimo = el.id_emprestimo) "
                    + "INNER JOIN livro l ON (el.id_livro = l.id_livro) "
                    + "INNER JOIN usuario u ON (u.id_usuario = e.id_usuario) WHERE u.cpf = ? ");
            stmt.setString(1, cpf);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Livro livro = new Livro();
                livro.setId_livro(rs.getInt("id_livro"));
                livro.setTitulo(rs.getString("titulo"));
                livro.setAno(rs.getInt("ano"));
                livro.setAutor(rs.getString("autor"));
                livro.setIsbn(rs.getString("isbn"));
                livrosEmprestados.add(livro);
                
            }
            
            
            
            } catch (SQLException ex) {
            Logger.getLogger(LivroDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return livrosEmprestados;
    }
    
        
    
    
//    private String geraDataFormatadaInicial(){
//        DateFormat  dateFormatDMY = new SimpleDateFormat("dd/MM/yyyy");
//        java.util.Date datainicial = new java.util.Date();
//        String dataFormatadaInicial = dateFormatDMY.format(new java.util.Date());
//        return dataFormatadaInicial;
//    }
//    
   private java.sql.Date geraDataPrevista(){
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, 7);
        java.sql.Date dataPrevista = new java.sql.Date(c.getTimeInMillis());
        return dataPrevista;
   }
}
