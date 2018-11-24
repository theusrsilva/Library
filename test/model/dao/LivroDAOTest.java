/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import model.bean.Estoque;
import model.bean.Livro;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Rocha
 */
public class LivroDAOTest {
    
    public LivroDAOTest() {
    }

    @Test
    public void inserir() {
        Estoque estoque = new Estoque(2);
        
        
        Livro livro = new Livro();
        livro.setAno(1996);
        livro.setAutor("machado");
        livro.setIsbn("1234567898912");
        livro.setTitulo("alguem de alguem");
        livro.setId_estoque(estoque);
        
        LivroDAO dao = new LivroDAO();
        
        if(dao.save(livro)){
            System.out.println("salvo!");
        }else{
            fail("falhou!");
        }
        
        
       
        
        
    }
    
}
