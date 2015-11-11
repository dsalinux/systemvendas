package br.edu.ifnmg.arinos.systemvendas.telas;

import br.edu.ifnmg.arinos.systemvendas.dao.ContaDAO;
import br.edu.ifnmg.arinos.systemvendas.entidade.Conta;
import br.edu.ifnmg.arinos.systemvendas.util.CrudTela;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author danilo
 */
@ManagedBean
@SessionScoped
public class ContaTela extends CrudTela<Conta, ContaDAO>{

    private ContaDAO usuarioDAO = new ContaDAO();
    
    @Override
    public Conta novaEntidade() {
        return new Conta();
    }

    @Override
    public ContaDAO getDAO() {
        return usuarioDAO;
    }

    
}
