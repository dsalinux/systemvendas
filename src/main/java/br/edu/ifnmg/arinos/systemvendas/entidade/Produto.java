/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.arinos.systemvendas.entidade;

import java.util.Objects;

/**
 *
 * @author ifnmg
 */
public class Produto {
    private Integer id;
    private String nome;
    private String descricao;
    private float preco;
    private float estoque_atual;
    private float estoque_minimo;
    private String unidade;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public float getEstoque_atual() {
        return estoque_atual;
    }

    public void setEstoque_atual(float estoque_atual) {
        this.estoque_atual = estoque_atual;
    }

    public float getEstoque_minimo() {
        return estoque_minimo;
    }

    public void setEstoque_minimo(float estoque_minimo) {
        this.estoque_minimo = estoque_minimo;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.id);
        hash = 59 * hash + Objects.hashCode(this.nome);
        hash = 59 * hash + Objects.hashCode(this.descricao);
        hash = 59 * hash + Float.floatToIntBits(this.preco);
        hash = 59 * hash + Float.floatToIntBits(this.estoque_atual);
        hash = 59 * hash + Float.floatToIntBits(this.estoque_minimo);
        hash = 59 * hash + Objects.hashCode(this.unidade);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Produto other = (Produto) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.descricao, other.descricao)) {
            return false;
        }
        if (Float.floatToIntBits(this.preco) != Float.floatToIntBits(other.preco)) {
            return false;
        }
        if (Float.floatToIntBits(this.estoque_atual) != Float.floatToIntBits(other.estoque_atual)) {
            return false;
        }
        if (Float.floatToIntBits(this.estoque_minimo) != Float.floatToIntBits(other.estoque_minimo)) {
            return false;
        }
        if (!Objects.equals(this.unidade, other.unidade)) {
            return false;
        }
        return true;
    }

    
}
