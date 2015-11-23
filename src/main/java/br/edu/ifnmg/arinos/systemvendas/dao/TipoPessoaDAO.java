package br.edu.ifnmg.arinos.systemvendas.dao;

import br.edu.ifnmg.arinos.systemvendas.entidade.TipoPessoa;
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
public class TipoPessoaDAO implements GenericoDAO<TipoPessoa> {

    @Override
    public void salvar(TipoPessoa entidade) throws ErroNegocio, ErroSistema {
        try {
            if (StringUtil.ehVazio(entidade.getNome())) {
                throw new ErroNegocio("Informe o Nome!");
            }
            PreparedStatement ps;
            if (entidade.getId() == null) {
                ps = FabricaConexao.getConexao().prepareStatement("insert into tipo_pessoa (nome) values (?)");
            } else {
                ps = FabricaConexao.getConexao().prepareStatement("update tipo_pessoa set nome = ? where id = ?");
                ps.setInt(4, entidade.getId());
            }
            ps.setString(1, entidade.getNome());
            ps.execute();
        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao tentar salvar tipo pessoa! " + ex.getMessage());
        }
    }

    @Override
    public void deletar(TipoPessoa entidade) throws ErroNegocio, ErroSistema {
        try {
            PreparedStatement ps;
            ps = FabricaConexao.getConexao().prepareStatement("delete from tipo_pessoa where id = ?");
            ps.setInt(1, entidade.getId());
            ps.execute();
        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao tentar deletar! "+ex.getMessage());
        }
    }

    @Override
    public List<TipoPessoa> listar() throws ErroNegocio, ErroSistema {
        List<TipoPessoa> entidades = new ArrayList<TipoPessoa>();
        try {
            PreparedStatement ps;
            ps = FabricaConexao.getConexao().prepareStatement("select * from tipo_pessoa");
            ResultSet resultado = ps.executeQuery();
            while(resultado.next()){
                TipoPessoa entidade = new TipoPessoa();
                entidade.setId(resultado.getInt("id"));
                entidade.setNome(resultado.getString("nome"));
                entidades.add(entidade);
            }
            return entidades;
        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao tentar buscar! "+ex.getMessage());
        }
    }

}
