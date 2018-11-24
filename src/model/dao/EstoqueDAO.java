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
import model.bean.Estoque;

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
        String sql = "INSERT INTO estoque (quantidade) VALUES (?)";
        
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1,estoque.getQuantidade());
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
