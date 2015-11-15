package br.edu.ifnmg.arinos.systemvendas.telas;

import br.edu.ifnmg.arinos.systemvendas.dao.ProdutoDAO;
import br.edu.ifnmg.arinos.systemvendas.entidade.Produto;
import br.edu.ifnmg.arinos.systemvendas.util.CrudTela;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author danilo
 */
@ManagedBean
@SessionScoped
public class ProdutoTela extends CrudTela<Produto, ProdutoDAO>{

    private final ProdutoDAO produtoDAO = new ProdutoDAO();
    
    @Override
    public Produto novaEntidade() {
        return new Produto();
    }

    @Override
    public ProdutoDAO getDAO() {
        return produtoDAO;
    }

    
}
