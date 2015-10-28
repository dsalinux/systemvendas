package br.edu.ifnmg.arinos.systemvendas.telas;

import br.edu.ifnmg.arinos.systemvendas.dao.CidadeDAO;
import br.edu.ifnmg.arinos.systemvendas.entidade.Cidade;
import br.edu.ifnmg.arinos.systemvendas.util.CrudTela;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author danilo
 */
@ManagedBean
@SessionScoped
public class CidadeTela extends CrudTela<Cidade, CidadeDAO>{

    private CidadeDAO cidadeDAO = new CidadeDAO();
    
    @Override
    public Cidade novaEntidade() {
        return new Cidade();
    }

    @Override
    public CidadeDAO getDAO() {
        return cidadeDAO;
    }
    
}
