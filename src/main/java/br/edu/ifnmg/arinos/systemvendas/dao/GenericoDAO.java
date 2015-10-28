package br.edu.ifnmg.arinos.systemvendas.dao;

import br.edu.ifnmg.arinos.systemvendas.util.excecao.ErroNegocio;
import br.edu.ifnmg.arinos.systemvendas.util.excecao.ErroSistema;
import java.util.List;

/**
 *
 * @author danilo
 */
public interface GenericoDAO<E> {
 
    public void salvar(E entidade) throws ErroNegocio, ErroSistema;
    
    public void deletar(E entidade) throws ErroNegocio, ErroSistema;
    
    public List<E> listar() throws ErroNegocio, ErroSistema;
    
}
