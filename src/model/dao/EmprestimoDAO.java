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
import model.bean.Emprestimo;
import model.bean.Estoque;
import model.bean.Livro;
import model.bean.PedidoEmprestimoDTO;
import model.bean.Usuario;

/**
 *
 * @author Lohan
 */
public class EmprestimoDAO {

    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    private Connection con = null;
    
    private List<Livro> livrosDevolvidos = new ArrayList<>();
    private EstoqueDAO estoqueDAO = new EstoqueDAO();

    public EmprestimoDAO() {
        con = ConnectionFactory.getConnection();
    }

    public void criaPedidoEmprestimo(String cpf, List<Livro> livros) {
        Usuario usuario = usuarioDAO.findByCpf(cpf);
        if (usuario != null) {
            Connection con = ConnectionFactory.getConnection();
            PreparedStatement stmt = null;
            ResultSet ultimoId = null;
            
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
                    
                    int quantidade = this.estoqueDAO.retornaQtdLivro(livro.getIsbn());
                    estoqueDAO.atualizaQtdLivro(livro.getIsbn(), quantidade - 1);
                    
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
                    + "INNER JOIN usuario u ON (u.id_usuario = e.id_usuario) WHERE u.cpf = ? AND e.status_emprestimo != ? AND e.status_devolucao = ?");
            stmt.setString(1, cpf);
            stmt.setString(2, "RECUSADO");
            stmt.setBoolean(3, false);
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
    
    public boolean usuarioJaTemEmprestimo(String cpf){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        boolean jaTemEmprestimo = false;
        try {
            stmt = con.prepareStatement("SELECT e.status_devolucao FROM emprestimo e INNER JOIN usuario u ON (u.id_usuario = e.id_usuario)  WHERE u.cpf = ?");
            stmt.setString(1, cpf);
            rs = stmt.executeQuery();
            while (rs.next()) {
                if (rs.getBoolean(1) == false){
                    jaTemEmprestimo = true;
                }

            }

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);

        }
        return jaTemEmprestimo;
    }
    
    public void devolveEmprestimo(String cpf){
            Connection con = ConnectionFactory.getConnection();
            PreparedStatement stmt = null;
                      
            try {
                this.livrosDevolvidos = findLivrosEmpretadosPorCpf(cpf);
                for( Livro livro : livrosDevolvidos){
                    int quantidade = this.estoqueDAO.retornaQtdLivro(livro.getIsbn());
                    estoqueDAO.atualizaQtdLivro(livro.getIsbn(), quantidade + 1);
                }
                
                stmt = con.prepareStatement("UPDATE emprestimo e INNER JOIN usuario u ON(e.id_usuario = u.id_usuario) SET e.data_devolucao = ? , e.status_devolucaO = ? WHERE u.cpf = ? ");
                stmt.setDate(1,new Date(System.currentTimeMillis()) );
                stmt.setBoolean(2,true);
                stmt.setString(3, cpf);
                stmt.executeUpdate();
 
                
            } catch (SQLException ex) {
                Logger.getLogger(EmprestimoDAO.class.getName()).log(Level.SEVERE, null, ex);

            } finally {
                ConnectionFactory.closeConnection(con, stmt);
            } 
    }
    
    public void recusaEmprestimo(String cpf){
            Connection con = ConnectionFactory.getConnection();
            PreparedStatement stmt = null;
                      
            try {
                this.livrosDevolvidos = findLivrosEmpretadosPorCpf(cpf);
                for( Livro livro : livrosDevolvidos){
                    int quantidade = this.estoqueDAO.retornaQtdLivro(livro.getIsbn());
                    this.estoqueDAO.atualizaQtdLivro(livro.getIsbn(), quantidade + 1);
                }
                
                stmt = con.prepareStatement("UPDATE emprestimo e INNER JOIN usuario u ON(e.id_usuario = u.id_usuario) SET e.status_emprestimo = ? , e.status_devolucaO = ? WHERE u.cpf = ? ");
                stmt.setString(1, "RECUSADO" );
                stmt.setBoolean(2,true);
                stmt.setString(3, cpf);
                stmt.executeUpdate();
                
                
                JOptionPane.showMessageDialog(null, "Pedido recusado com sucesso!");
            } catch (SQLException ex) {
                Logger.getLogger(EmprestimoDAO.class.getName()).log(Level.SEVERE, null, ex);

            } finally {
                ConnectionFactory.closeConnection(con, stmt);
            } 
    }
    
    public void aceitaEmprestimo(String cpf){
            Connection con = ConnectionFactory.getConnection();
            PreparedStatement stmt = null;
            
            Date dataEmprestimo = new Date(System.currentTimeMillis());
            Date dataPrevista = geraDataPrevista();
                      
            try {
                stmt = con.prepareStatement("UPDATE emprestimo e INNER JOIN usuario u ON(e.id_usuario = u.id_usuario) SET e.data_emprestimo = ?, e.data_prevista = ?, e.status_emprestimo = ? WHERE u.cpf = ? ");
                stmt.setDate (1,dataEmprestimo);
                stmt.setDate(2,dataPrevista);
                stmt.setString(3, "APROVADO" );
                stmt.setString(4, cpf);
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Pedido aceito com sucesso");
                
            } catch (SQLException ex) {
                Logger.getLogger(EmprestimoDAO.class.getName()).log(Level.SEVERE, null, ex);

            } finally {
                ConnectionFactory.closeConnection(con, stmt);
            } 
    }
    
    
    public Emprestimo getInfosEmprestimo(String cpf){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        Emprestimo emprestimo = new Emprestimo();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM emprestimo e INNER JOIN usuario u ON (u.id_usuario = e.id_usuario) WHERE u.cpf = ? AND e.status_emprestimo = ? AND e.status_devolucao = ?");
            stmt.setString(1, cpf);
            stmt.setString(2, "APROVADO");
            stmt.setBoolean(3, false);
            rs = stmt.executeQuery();
            while (rs.next()) {
                
                emprestimo.setDataEmpresimo(rs.getDate("data_emprestimo"));
                emprestimo.setDataDevolucao(rs.getDate("data_devolucao"));
                emprestimo.setDataPrevista(rs.getDate("data_prevista"));
                emprestimo.setStatus_emprestimo(rs.getString("status_emprestimo"));
                emprestimo.setStatus_devolucao(rs.getBoolean("status_devolucao"));
                emprestimo.setId_emprestimo(rs.getInt("id_emprestimo"));
                
              
            }

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);

        }
        return emprestimo;
    }
    
     
    public List<PedidoEmprestimoDTO> getPedidosPendentes(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<PedidoEmprestimoDTO> pedidosPendentes = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM emprestimo e INNER JOIN usuario u ON (u.id_usuario = e.id_usuario) WHERE e.status_emprestimo = ?");
            stmt.setString(1, "PENDENTE");
            rs = stmt.executeQuery();
            while (rs.next()) {
                PedidoEmprestimoDTO pedidoEmprestimoDTO = new PedidoEmprestimoDTO();
                pedidoEmprestimoDTO.setDataDoPedido(rs.getDate("data_emprestimo"));
                pedidoEmprestimoDTO.setCpf(rs.getString("cpf"));
                pedidoEmprestimoDTO.setLivrosPedidos(this.findLivrosEmpretadosPorCpf(rs.getString("cpf")));
                pedidoEmprestimoDTO.setNomeUsuario(rs.getString("nome"));
                pedidosPendentes.add(pedidoEmprestimoDTO);
                
              
            }
            
            

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);

        }
        return pedidosPendentes;
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
