/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import com.mysql.jdbc.Statement;
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
public class LivroDAO {
    public void create(Livro p){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt =null;
      
        
        try {
            stmt = con.prepareStatement("INSERT INTO livro (autor,isbn,titulo,ano,id_estoque) VALUES (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
         
            stmt.setString(1,p.getAutor());
            stmt.setString(2,p.getIsbn());
            stmt.setString(3,p.getTitulo());
            stmt.setInt(4,p.getAno());
            Estoque estoque = new Estoque();
            estoque.setId_estoque(1);
            p.setEstoque(estoque);
            stmt.setInt(5, estoque.getId_estoque());
            
            
         
            
     
           
            
            stmt.executeUpdate();
            
            ResultSet ultimoId = stmt.getGeneratedKeys();
            int id = 0;
            if(ultimoId.next()){
                id = ultimoId.getInt(1);
            }
            System.out.println(p.getId_livro());
            p.setId_livro(id);
            estoque.setLivro(p);
            EstoqueDAO estoqueDAO = new EstoqueDAO();
            estoqueDAO.save(estoque);
            
           
            JOptionPane.showMessageDialog(null, "Livro cadastrado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar!"+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
           
        }
        
        //this.adicionarLivro(p.getIsbn());
        
        
    }
    
    public Livro findLivroByIsbn(String isbn){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        Livro livro = new Livro();
        try {
            stmt = con.prepareStatement("SELECT * FROM livro WHERE isbn = ?");
            stmt.setString(1, isbn);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                livro.setAno(rs.getInt("ano"));
                livro.setAutor(rs.getString("autor"));
                livro.setIsbn(isbn);
                livro.setTitulo(rs.getString("titulo"));
                //livro.setEstoque(rs.getInt("id_estoque"));
                
                
                
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(LivroDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return livro;
    }
    
    public void adicionarLivro(String isbn){
        EstoqueDAO estoqueDAO = new EstoqueDAO();
        
        Livro livro = this.findLivroByIsbn(isbn);
        Estoque estoque = new Estoque();
        estoque.setId_estoque(1);
        estoque.setLivro(livro);
        estoque.setQuantidade(1);
        estoqueDAO.save(estoque);
    }
     
    
}
