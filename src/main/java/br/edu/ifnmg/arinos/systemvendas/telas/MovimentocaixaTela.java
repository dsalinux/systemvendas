package br.edu.ifnmg.arinos.systemvendas.telas;

import br.edu.ifnmg.arinos.systemvendas.dao.MovimentoCaixaDAO;
import br.edu.ifnmg.arinos.systemvendas.dao.UsuarioDAO;
import br.edu.ifnmg.arinos.systemvendas.entidade.MovimentoCaixa;
import br.edu.ifnmg.arinos.systemvendas.entidade.Usuario;
import br.edu.ifnmg.arinos.systemvendas.util.CrudTela;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author danilo
 */
@ManagedBean
@SessionScoped
public class MovimentocaixaTela extends CrudTela<MovimentoCaixa, MovimentoCaixaDAO>{

    private MovimentoCaixaDAO movimentocaixaDAO = new MovimentoCaixaDAO();
    
    @Override
    public MovimentoCaixa novaEntidade() {
        return new MovimentoCaixa();
    }

    @Override
    public MovimentoCaixaDAO getDAO() {
        return movimentocaixaDAO;
    }

    
}
