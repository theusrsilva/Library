/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

import java.sql.Date;

/**
 *
 * @author Lohan
 */
public class Emprestimo {

    private int id_emprestimo;
    private Date dataEmprestimo;
    private Date dataDevolucao;
    private Date dataPrevista;
    private boolean status_devolucao;
    private String status_emprestimo;
    
    
    public Emprestimo(Date dataEmpresimo, Date dataDevolucao, Date dataPrevista, boolean status_devolucao, String status_emprestimo) {
        this.dataEmprestimo = dataEmpresimo;
        this.dataDevolucao = dataDevolucao;
        this.dataPrevista = dataPrevista;
        this.status_devolucao = status_devolucao;
        this.status_emprestimo = status_emprestimo;

    }
    
    public Emprestimo(){
        
    }
    
    public int getId_emprestimo() {
        return id_emprestimo;
    }

    

    public void setId_emprestimo(int id_emprestimo) {
        this.id_emprestimo = id_emprestimo;
    }

 
    public Date getDataEmprestimo() {
        return dataEmprestimo;
    }

   
    public void setDataEmprestimo(Date dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }


    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

 
    public Date getDataPrevista() {
        return dataPrevista;
    }

 
    public void setDataPrevista(Date dataPrevista) {
        this.dataPrevista = dataPrevista;
    }

    public boolean isStatus_devolucao() {
        return status_devolucao;
    }

    public void setStatus_devolucao(boolean status) {
        this.status_devolucao = status;
    }
    
    public String getStatus_emprestimo() {
        return status_emprestimo;
    }

 
    public void setStatus_emprestimo(String status_emprestimo) {
        this.status_emprestimo = status_emprestimo;
    }
    
    
    
}
