package br.edu.ifnmg.arinos.systemvendas.util;

import br.edu.ifnmg.arinos.systemvendas.dao.GenericoDAO;
import br.edu.ifnmg.arinos.systemvendas.util.excecao.ErroNegocio;
import br.edu.ifnmg.arinos.systemvendas.util.excecao.ErroSistema;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;

/**
 *
 * @author danilo
 * @param <E> Classe de Entidade
 * @param <D> Classe DAO
 */
public abstract class CrudTela<E, D extends GenericoDAO> extends JsfUtil {

    private E entidade;
    private List<E> listaComEntidades;

    @PostConstruct
    public void iniciar() {
        entidade = novaEntidade();
        listaComEntidades = new ArrayList<>();
        alterarParaPesquisando();
    }

    public void novo() {
        entidade = novaEntidade();
        alterarParaInserindo();
    }

    public void salvar() {
        try {
            getDAO().salvar(entidade);
            entidade = novaEntidade();
            alterarParaPesquisando();
            addMensagem("Salvo com sucesso!");
        } catch (ErroNegocio ex) {
            addMensagemAviso(ex);
        } catch (ErroSistema ex) {
            addMensagemErro(ex);
        }
    }

    public void deletar(E entidade) {
        try {
            getDAO().deletar(entidade);
            if(listaComEntidades.contains(entidade)){
                listaComEntidades.remove(entidade);
            }
            addMensagem("Deletado com sucesso!");
        } catch (ErroNegocio ex) {
            addMensagemAviso(ex);
        } catch (ErroSistema ex) {
            addMensagemErro(ex);
        }
    }

    public void editar(E entidade) {
        this.entidade = entidade;
        listaComEntidades.remove(entidade);
        alterarParaEditando();
    }

    public void listar() {
        if (!ehPesquisando()) {
            alterarParaPesquisando();
        } else {
            try {
                listaComEntidades = new ArrayList<>();
                listaComEntidades = getDAO().listar();
                if(listaComEntidades == null || listaComEntidades.isEmpty()){
                    addMensagem("Nenhum dado cadastrado!");
                }
            } catch (ErroNegocio ex) {
                addMensagemAviso(ex.getMessage());
            } catch (ErroSistema ex) {
                addMensagemErro(ex);
            }
        }
    }

    public E getEntidade() {
        return entidade;
    }

    public void setEntidade(E entidade) {
        this.entidade = entidade;
    }

    public List<E> getListaComEntidades() {
        return listaComEntidades;
    }

    public void setListaComEntidades(List<E> listaComEntidades) {
        this.listaComEntidades = listaComEntidades;
    }

    public abstract E novaEntidade();

    public abstract D getDAO();

}
