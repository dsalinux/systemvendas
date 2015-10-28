package br.edu.ifnmg.arinos.systemvendas.telas;

import br.edu.ifnmg.arinos.systemvendas.dao.UsuarioDAO;
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
public class UsuarioTela extends CrudTela<Usuario, UsuarioDAO>{

    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    
    @Override
    public Usuario novaEntidade() {
        return new Usuario();
    }

    @Override
    public UsuarioDAO getDAO() {
        return usuarioDAO;
    }

    
}
