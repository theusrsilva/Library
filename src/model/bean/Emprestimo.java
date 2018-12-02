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
    
    
    public Emprestimo(Date dataEmpresimo, Date dataDevolucao, Date dataPrevista, boolean status) {
        this.dataEmpresimo = dataEmpresimo;
        this.dataDevolucao = dataDevolucao;
        this.dataPrevista = dataPrevista;
        this.status_devolucao = status;
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
    }
    
    
    
}
