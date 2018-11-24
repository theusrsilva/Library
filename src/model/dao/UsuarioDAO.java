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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.bean.Usuario;

/**
 *
 * @author Rocha
 */
public class UsuarioDAO {


        public boolean checkLogin(String cpf, String senha_hash){
            Connection con = ConnectionFactory.getConnection();
            PreparedStatement stmt = null;
            ResultSet rs=null;
            boolean check = false;
            
            
            try{
                stmt = con.prepareStatement("SELECT * FROM usuario WHERE cpf = ? and senha_hash = md5(?) ");
                stmt.setString(1, cpf);
                stmt.setString(2, senha_hash);
                rs = stmt.executeQuery();
                
                if(rs.next()){
                    check=true;
                }
                }catch (SQLException ex){
                        Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE,null,ex);
                        } finally{
                           ConnectionFactory.closeConnection(con, stmt, rs);
                    
                    
                    
                }
            return check;
            }
            
        public void create(Usuario u){
            Connection con = ConnectionFactory.getConnection();
            PreparedStatement stmt = null;
            
            
            try {
            stmt = con.prepareStatement("INSERT INTO usuario (cpf,nome,telefone,email,senha_hash) VALUES (?,?,?,?,md5(?))");
            stmt.setString(1,u.getCpf());
            stmt.setString(2,u.getNome());
            stmt.setString(3,u.getTelefone());
            stmt.setString(4,u.getEmail());
            stmt.setString(5,u.getSenha_hash());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null,"Cadastro realizado com sucesso!");
            
        
        
        
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao cadastrar!"+ex);
            
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
            
        }

}
