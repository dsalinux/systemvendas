
package br.edu.ifnmg.arinos.systemvendas.telas;

import br.edu.ifnmg.arinos.systemvendas.dao.PessoaDAO;
import br.edu.ifnmg.arinos.systemvendas.dao.UsuarioDAO;
import br.edu.ifnmg.arinos.systemvendas.entidade.Pessoa;
import br.edu.ifnmg.arinos.systemvendas.entidade.Usuario;

/**
 *
 * @author ifnmg
 */
public class PessoaTela {
     private PessoaDAO pessoaDAO = new PessoaDAO();
    
    
    public Pessoa novaEntidade() {
        return new Pessoa();
    }

  
    public PessoaDAO getDAO() {
        return pessoaDAO;
    }

    
}


