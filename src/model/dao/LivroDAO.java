/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.bean.Livro;

/**
 *
 * @author Rocha
 */
public class LivroDAO {
    public boolean save(Livro l){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt =null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO livro (autor,isbn,titulo,ano,id_estoque) VALUES (?,?,?,?,?)");
            stmt.setString(1,l.getAutor());
            stmt.setString(2,l.getIsbn());
            stmt.setString(3, l.getTitulo());
            stmt.setInt(4,l.getAno());
            stmt.setInt(5,l.getId_estoque().getId_estoque());
            stmt.executeUpdate();
            return true;
            
        
        
        
        } catch (SQLException ex) {
            System.err.println("Erro: "+ex);
            return false;
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        
        
        
    }
}
