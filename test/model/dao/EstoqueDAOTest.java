/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import model.bean.Estoque;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Rocha
 */
public class EstoqueDAOTest {
    
    public EstoqueDAOTest() {
    }

    @Test
    public void inserir() {
        Estoque est = new Estoque(2);
        EstoqueDAO dao = new EstoqueDAO();
        
        if(dao.save(est)){
            System.out.println("salvo!");
        }else{
            fail("Falha ao salvar!");
        }
    }
    
}
