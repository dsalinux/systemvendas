package br.edu.ifnmg.arinos.systemvendas.dao;

import br.edu.ifnmg.arinos.systemvendas.entidade.Usuario;
import br.edu.ifnmg.arinos.systemvendas.util.FabricaConexao;
import br.edu.ifnmg.arinos.systemvendas.util.StringUtil;
import br.edu.ifnmg.arinos.systemvendas.util.excecao.ErroNegocio;
import br.edu.ifnmg.arinos.systemvendas.util.excecao.ErroSistema;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author danilo
 */
public class UsuarioDAO implements GenericoDAO<Usuario> {

    @Override
    public void salvar(Usuario entidade) throws ErroNegocio, ErroSistema {
        try {
            if (StringUtil.ehVazio(entidade.getNome())) {
                throw new ErroNegocio("Informe o Nome!");
            }
            if (StringUtil.ehVazio(entidade.getLogin())) {
                throw new ErroNegocio("Informe o Login!");
            }
            if (StringUtil.ehVazio(entidade.getSenha())) {
                throw new ErroNegocio("Informe o Senha!");
            }
            PreparedStatement ps;
            if (entidade.getId() == null) {
                ps = FabricaConexao.getConexao().prepareStatement("insert into usuario (nome, login, senha) values (?,?,?)");
            } else {
                ps = FabricaConexao.getConexao().prepareStatement("update usuario set nome = ?, login = ?, senha = ? where id = ?");
                ps.setInt(4, entidade.getId());
            }
            ps.setString(1, entidade.getNome());
            ps.setString(2, entidade.getLogin());
            ps.setString(3, entidade.getSenha());
            ps.execute();
        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao tentar salvar usuário! " + ex.getMessage());
        }
    }

    @Override
    public void deletar(Usuario entidade) throws ErroNegocio, ErroSistema {
        try {
            PreparedStatement ps;
            ps = FabricaConexao.getConexao().prepareStatement("delete from usuario where id = ?");
            ps.setInt(1, entidade.getId());
            ps.execute();
        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao tentar deletar! "+ex.getMessage());
        }
    }

    @Override
    public List<Usuario> listar() throws ErroNegocio, ErroSistema {
        List<Usuario> entidades = new ArrayList<Usuario>();
        try {
            PreparedStatement ps;
            ps = FabricaConexao.getConexao().prepareStatement("select * from usuario");
            ResultSet resultado = ps.executeQuery();
            while(resultado.next()){
                Usuario entidade = new Usuario();
                entidade.setId(resultado.getInt("id"));
                entidade.setNome(resultado.getString("nome"));
                entidade.setLogin(resultado.getString("login"));
                entidade.setSenha(resultado.getString("senha"));
                entidades.add(entidade);
            }
            return entidades;
        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao tentar buscar! "+ex.getMessage());
        }
    }
    
    public Usuario buscarUsuario(String login, String senha) throws ErroSistema{
         Usuario entidade = new Usuario();
        try {
            PreparedStatement ps;
            ps = FabricaConexao.getConexao().prepareStatement("select * from usuario where login = ? and senha = ?");
            ps.setString(1, login);
            ps.setString(2, senha);
            ResultSet resultado = ps.executeQuery();
            if(resultado.next()){
                entidade.setId(resultado.getInt("id"));
                entidade.setNome(resultado.getString("nome"));
                entidade.setLogin(resultado.getString("login"));
                entidade.setSenha(resultado.getString("senha"));
            }
            return entidade;
        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao tentar buscar! "+ex.getMessage());
        }
    }

}
