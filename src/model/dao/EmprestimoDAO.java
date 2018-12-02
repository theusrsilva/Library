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
import javax.swing.JOptionPane;
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
            Connection con2 = ConnectionFactory.getConnection();
            PreparedStatement stmt2 = null;
            ResultSet ultimoId = null;
            String dataEmprestimo = geraDataFormatadaInicial();
            String dataPrevista = geraDataFormatadaDevolucao();

            try {
                stmt = con.prepareStatement("INSERT INTO emprestimo (id_usuario, data_emprestimo, data_devolucao, data_prevista, status_emprestimo, status_devolucao) VALUES (?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
                stmt.setInt(1, usuario.getId_usuario());
                stmt.setDate(2, java.sql.Date.valueOf(dataEmprestimo));
                stmt.setObject(3, null);
                stmt.setDate(4, java.sql.Date.valueOf(dataPrevista));     
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
                    
                }
                
                JOptionPane.showMessageDialog(null, "Pedido criado com sucesso!");

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Falha ao criar o pedido!" + ex);

            } finally {
                ConnectionFactory.closeConnection(con, stmt, ultimoId);
                ConnectionFactory.closeConnection(con2, stmt2);
            }
        }

    }
    
    private String geraDataFormatadaInicial(){
        DateFormat  dateFormatDMY = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date datainicial = new java.util.Date();
        String dataFormatadaInicial = dateFormatDMY.format(new java.util.Date());
        return dataFormatadaInicial;
    }
    
   private String geraDataFormatadaDevolucao(){
        Calendar c = Calendar.getInstance();
        DateFormat  dateFormatDMY = new SimpleDateFormat("dd/MM/yyyy");
        c.add(Calendar.DATE, 7);
        java.util.Date dataDevolucao = c.getTime();
        String dataFormatadaDevolucao = dateFormatDMY.format(dataDevolucao);
        return dataFormatadaDevolucao;
   }
}
