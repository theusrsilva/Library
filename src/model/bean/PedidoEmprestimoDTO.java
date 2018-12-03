/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Lohan
 */
public class PedidoEmprestimoDTO {
    private String cpf;
    private String nomeUsuario;
    private List<Livro> livrosPedidos;
    private Date dataDoPedido;
    
    public PedidoEmprestimoDTO(){
        
    }

    /**
     * @return the nomeUsuario
     */
    public String getNomeUsuario() {
        return nomeUsuario;
    }

    /**
     * @param nomeUsuario the nomeUsuario to set
     */
    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    /**
     * @return the livrosPedidos
     */
    public List<Livro> getLivrosPedidos() {
        return livrosPedidos;
    }

    /**
     * @param livrosPedidos the livrosPedidos to set
     */
    public void setLivrosPedidos(List<Livro> livrosPedidos) {
        this.livrosPedidos = livrosPedidos;
    }

    /**
     * @return the dataDoPedido
     */
    public Date getDataDoPedido() {
        return dataDoPedido;
    }

    /**
     * @param dataDoPedido the dataDoPedido to set
     */
    public void setDataDoPedido(Date dataDoPedido) {
        this.dataDoPedido = dataDoPedido;
    }

    /**
     * @return the cpf
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * @param cpf the cpf to set
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
    
}
