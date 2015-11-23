
package br.edu.ifnmg.arinos.systemvendas.telas;

import br.edu.ifnmg.arinos.systemvendas.dao.PessoaDAO;
import br.edu.ifnmg.arinos.systemvendas.entidade.Pessoa;
import br.edu.ifnmg.arinos.systemvendas.util.CrudTela;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author ifnmg
 */
@SessionScoped
@ManagedBean
public class PessoaTela extends CrudTela<Pessoa, PessoaDAO>{
    private final PessoaDAO pessoaDAO = new PessoaDAO();

    @Override
    public Pessoa novaEntidade() {
        return new Pessoa();
    }

    @Override
    public PessoaDAO getDAO() {
        return pessoaDAO;
    }
}


