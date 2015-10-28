package br.edu.ifnmg.arinos.systemvendas.util;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Danilo
 */
public class JsfUtil implements Serializable {

    enum EstadoTela {

        EDITANDO,
        PESQUISANDO,
        INSERINDO
    }

    private EstadoTela estado = EstadoTela.PESQUISANDO;

    public boolean ehPesquisando() {
        return EstadoTela.PESQUISANDO.equals(estado);
    }

    public boolean ehEditando() {
        return EstadoTela.EDITANDO.equals(estado);
    }

    public boolean ehInserindo() {
        return EstadoTela.INSERINDO.equals(estado);
    }

    public void alterarParaPesquisando() {
        estado = EstadoTela.PESQUISANDO;
    }

    public void alterarParaEditando() {
        estado = EstadoTela.EDITANDO;
    }

    public void alterarParaInserindo() {
        estado = EstadoTela.INSERINDO;
    }

    public void addMensagem(String mensagem, String titulo, FacesMessage.Severity tipo) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(tipo, titulo, mensagem));
    }

    public void addMensagem(String mensagem) {
        addMensagem(mensagem, "Info", FacesMessage.SEVERITY_INFO);
    }

    public void addMensagemAviso(String mensagem) {
        addMensagem(mensagem, "Aviso", FacesMessage.SEVERITY_WARN);
    }
    public void addMensagemAviso(Exception ex) {
        addMensagemAviso(ex.getMessage());
    }

    public void addMensagemErro(String mensagem) {
        addMensagem(mensagem, "Erro", FacesMessage.SEVERITY_ERROR);
    }
    public void addMensagemErro(Exception ex) {
        addMensagemErro(ex.getMessage());
    }
}
