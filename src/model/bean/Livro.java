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
public class Livro {
    private int id_livro;
    private Estoque id_estoque;
    private String autor;
    private String isbn;
    private String titulo;
    private int ano;
    
    public Livro(String autor, String isbn, String titulo, int ano) {
        this.autor = autor;
        this.isbn = isbn;
        this.titulo = titulo;
        this.ano = ano;
    }

    public Livro() {
    }
    
    public int getId_livro() {
        return id_livro;
    }

    public void setId_livro(int id_livro) {
        this.id_livro = id_livro;
    }

    

    public Estoque getId_estoque() {
        return id_estoque;
    }

    public void setId_estoque(Estoque id_estoque) {
        this.id_estoque = id_estoque;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }
    
    
}
