/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

/**
 *
 * @author Rocha
 */
public class Estoque {
    private int id_estoque;
    private Livro livro;
    private int quantidade;

    public Estoque(int quantidade) {
        this.quantidade = quantidade;
    }

    public Estoque() {
    }

    public int getId_estoque() {
        return id_estoque;
    }

    public void setId_estoque(int id_estoque) {
        this.id_estoque = id_estoque;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro id_livro) {
        this.livro = id_livro;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    
    
}
