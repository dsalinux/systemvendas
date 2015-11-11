package br.edu.ifnmg.arinos.systemvendas.telas;

import br.edu.ifnmg.arinos.systemvendas.dao.VendaDAO;
import br.edu.ifnmg.arinos.systemvendas.entidade.Venda;
import br.edu.ifnmg.arinos.systemvendas.util.CrudTela;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author danilo
 */
@ManagedBean
@SessionScoped
public class VendaTela extends CrudTela<Venda, VendaDAO>{

    private VendaDAO vendaDAO = new VendaDAO();
    
    @Override
    public Venda novaEntidade() {
        return new Venda();
    }

    @Override
    public VendaDAO getDAO() {
        return vendaDAO;
    }

    
}
