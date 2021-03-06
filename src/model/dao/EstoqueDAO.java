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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.bean.Estoque;
import model.bean.Livro;

/**
 *
 * @author Rocha
 */
public class EstoqueDAO {
    private Connection con=null;

    public EstoqueDAO() {
        con=ConnectionFactory.getConnection();
        
    }
    
    public boolean save(Estoque estoque){
        String sql = "INSERT INTO estoque (id_livro,quantidade) VALUES (?,?)";
        
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, estoque.getLivro().getId_livro());
            stmt.setInt(2,estoque.getQuantidade());
            stmt.executeUpdate();
            
            //Livro livro = new Livro();
            
            return true;
                    } catch (SQLException ex) {
            System.err.println("Erro: "+ex);
            return false;
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public void atualizaQtdLivro(String isbn, int qtd){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        LivroDAO livroDAO = new LivroDAO();
        Livro livro = livroDAO.findLivroByIsbn(isbn);
        try {
            stmt = con.prepareStatement("UPDATE estoque SET quantidade = ? WHERE id_livro = ?");
            stmt.setInt(1,qtd);
            stmt.setInt(2,livro.getId_livro());
            
            stmt.executeUpdate();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar!"+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }   
    }
    
    public int retornaQtdLivro(String isbn){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        LivroDAO livroDAO = new LivroDAO();
        Livro livro = livroDAO.findLivroByIsbn(isbn);
        
        int quantidade = 0;
        
        try {
            stmt = con.prepareStatement("SELECT quantidade FROM estoque WHERE id_livro = ?");
            stmt.setInt(1,livro.getId_livro());
            rs = stmt.executeQuery();
            while(rs.next()){
                quantidade = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }   
        return quantidade;
    }
    
}
