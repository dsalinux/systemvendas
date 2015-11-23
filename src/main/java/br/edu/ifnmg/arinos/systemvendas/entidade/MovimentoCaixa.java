package br.edu.ifnmg.arinos.systemvendas.entidade;

import java.util.Date;
import java.util.Objects;


public class MovimentoCaixa {

    private Integer id;
    private String descricao;
    private String tipo_movimento;
    private String valor;
    private Date data;

    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipo_movimento() {
        return tipo_movimento;
    }

    public void setTipo_movimento(String tipo_movimento) {
        this.tipo_movimento = tipo_movimento;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public Date getData() {
        Date data = null;
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }



    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.id);
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
        final MovimentoCaixa other = (MovimentoCaixa) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
}
    