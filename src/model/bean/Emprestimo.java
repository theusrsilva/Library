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
    private Date dataEmpresimo;
    private Date dataDevolucao;
    private Date dataPrevista;
    private boolean status_devolucao;
    private String status_emprestimo;
    
    
    public Emprestimo(Date dataEmpresimo, Date dataDevolucao, Date dataPrevista, boolean status_devolucao, String status_emprestimo) {
        this.dataEmpresimo = dataEmpresimo;
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

 
    public Date getDataEmpresimo() {
        return dataEmpresimo;
    }

   
    public void setDataEmpresimo(Date dataEmpresimo) {
        this.dataEmpresimo = dataEmpresimo;
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

    public boolean isStatus() {
        return status_devolucao;
    }

    public void setStatus(boolean status) {
        this.status_devolucao = status;
<<<<<<< HEAD
    }
    
    public String getStatus_emprestimo() {
        return status_emprestimo;
    }

 
    public void setStatus_emprestimo(String status_emprestimo) {
        this.status_emprestimo = status_emprestimo;
=======
>>>>>>> 213b2f3b4ed648f0c3ff8e3ee5cfbb67c502ac15
    }
    
    
    
}
