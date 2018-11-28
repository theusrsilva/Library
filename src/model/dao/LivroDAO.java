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
import java.util.List;
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
    public void create(Livro livro, int quantidade){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt =null;
        ResultSet ultimoId = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO livro (autor,isbn,titulo,ano,id_estoque) VALUES (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
         
            stmt.setString(1,livro.getAutor());
            stmt.setString(2,livro.getIsbn());
            stmt.setString(3,livro.getTitulo());
            stmt.setInt(4,livro.getAno());
            
            Estoque estoque = new Estoque();
            estoque.setId_estoque(1);
            livro.setEstoque(estoque);
            
            stmt.setInt(5, estoque.getId_estoque());
            stmt.executeUpdate();
            
            
            ultimoId = stmt.getGeneratedKeys();
            int id = 0;
            if(ultimoId.next()){
                id = ultimoId.getInt(1);
            }
            
            livro.setId_livro(id);
            estoque.setLivro(livro);
            estoque.setQuantidade(quantidade);
            
            EstoqueDAO estoqueDAO = new EstoqueDAO();
            estoqueDAO.save(estoque);
            
           
            JOptionPane.showMessageDialog(null, "Livro cadastrado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar!"+ex);
        }finally{
            ConnectionFactory.closeConnection(con,stmt);
           
        }
        
        //this.adicionarLivro(p.getIsbn());
        
        
    }
    public void updateLivro(String isbn, int qtd){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        Livro livro = findLivroByIsbn(isbn);
        try {
            stmt = con.prepareStatement("UPDATE estoque SET quantidade = ? WHERE id_livro = ?");
            stmt.setInt(1,qtd);
            stmt.setInt(2,livro.getId_livro());
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar!"+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
         
            
        
        
        
        
        
        
        
        
    }
    
    public Livro findLivroByIsbn(String isbn){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Livro livro = new Livro();
        try {
            stmt = con.prepareStatement("SELECT *FROM livro INNER JOIN estoque ON (livro.id_livro = estoque.id_livro) WHERE isbn = ? ");
            stmt.setString(1, isbn);
            rs = stmt.executeQuery();
            while(rs.next()){
                livro.setAno(rs.getInt("ano"));
                livro.setAutor(rs.getString("autor"));
                livro.setIsbn(isbn);
                livro.setTitulo(rs.getString("titulo"));
                livro.setId_livro(rs.getInt("id_livro"));
                //livro.setEstoque(rs.getInt("id_estoque"));
                
                
                
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(LivroDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt,rs);
        }
        return livro;
    }
    
//    public void adicionarLivro(String isbn){
//        EstoqueDAO estoqueDAO = new EstoqueDAO();
//        
//        Livro livro = this.findLivroByIsbn(isbn);
//        Estoque estoque = new Estoque();
//        estoque.setId_estoque(1);
//        estoque.setLivro(livro);
//        estoque.setQuantidade(1);
//        estoqueDAO.save(estoque);
//    }
    public List<Livro> findAll(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Livro> livros= new ArrayList<>();
        
        
        try {
            stmt=con.prepareStatement("SELECT * FROM livro INNER JOIN estoque ON (livro.id_livro = estoque.id_livro)");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Livro livro = new Livro();
                Estoque estoque = new Estoque();
                
                estoque.setId_estoque(rs.getInt("id_estoque"));
                
                
                
                livro.setId_livro(rs.getInt("id_livro"));
                livro.setTitulo(rs.getString("titulo"));
                livro.setAno(rs.getInt("ano"));
                livro.setAutor(rs.getString("autor"));
                livro.setIsbn(rs.getString("isbn"));
                estoque.setLivro(livro);
                livro.setEstoque(estoque);
                estoque.setQuantidade(rs.getInt("quantidade"));
                livros.add(livro);
                
            }
            
            
            
            } catch (SQLException ex) {
            Logger.getLogger(LivroDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return livros;
    }
     
    
}
