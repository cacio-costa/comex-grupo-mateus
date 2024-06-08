package br.com.alura.comex.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Produto {
    private Long id;

    private String nome;
    private String descricao;

    private double preco;
    private List<Categoria> categorias = new ArrayList<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public List<Categoria> getCategorias() {
        return Collections.unmodifiableList(categorias);
    }

    public void adicionaCategoria(Categoria categoria) {
        // verifica se a categoria já foi adicionada com base no id
        for (Categoria categoriaDaLista : categorias) {
            if (categoriaDaLista.getId().equals(categoria.getId())) {
                return;
            }
        }

        this.categorias.add(categoria);
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", preco=" + preco +
                ", categorias=" + categorias +
                '}';
    }
}