/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.bean.Livro;

/**
 *
 * @author Lohan
 */
public class EmprestimoDAO {
    private Connection con=null;
    private List<List<Livro>> pedidosEmprestimo = new ArrayList<>();
    private List<Date> dataEmprestimo = new ArrayList<Date>();
   
    public EmprestimoDAO(){
        con=ConnectionFactory.getConnection();
    }
    
    public void addPedidoEmprestimo(List<Livro> livros){
        this.pedidosEmprestimo.add(livros);
        this.dataEmprestimo.add(new Date());
    }
    
    public void removePedidoEmprestimo(List<Livro> livros){
        int posDoLivro = this.pedidosEmprestimo.indexOf(livros);
        this.pedidosEmprestimo.remove(livros);
        this.dataEmprestimo.remove(posDoLivro);
    }
    
    
    public List<List<Livro>> getPedidosEmprestimo (){
        return this.pedidosEmprestimo;
    }
    
    public List<Date> getDataEmprestimo (){
        return this.dataEmprestimo;
    }
    
}
