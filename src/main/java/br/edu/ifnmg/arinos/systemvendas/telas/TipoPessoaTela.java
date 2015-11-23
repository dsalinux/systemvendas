package br.edu.ifnmg.arinos.systemvendas.telas;

import br.edu.ifnmg.arinos.systemvendas.dao.TipoPessoaDAO;
import br.edu.ifnmg.arinos.systemvendas.entidade.TipoPessoa;
import br.edu.ifnmg.arinos.systemvendas.util.CrudTela;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author danilo
 */
@ManagedBean
@SessionScoped
public class TipoPessoaTela extends CrudTela<TipoPessoa, TipoPessoaDAO>{

    private TipoPessoaDAO tipoPessoaDAO = new TipoPessoaDAO();
    
    @Override
    public TipoPessoa novaEntidade() {
        return new TipoPessoa();
    }

    @Override
    public TipoPessoaDAO getDAO() {
        return tipoPessoaDAO;
    }

    
}
