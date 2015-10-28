package br.edu.ifnmg.arinos.systemvendas.dao;

import br.edu.ifnmg.arinos.systemvendas.entidade.Cidade;
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
public class CidadeDAO implements GenericoDAO<Cidade> {

    @Override
    public void salvar(Cidade entidade) throws ErroNegocio, ErroSistema {
        try {
            if (StringUtil.ehVazio(entidade.getNome())) {
                throw new ErroNegocio("Informe o Nome!");
            }
            if (StringUtil.ehVazio(entidade.getUf())) {
                throw new ErroNegocio("Informe o Estado!");
            }
            if (StringUtil.ehVazio(entidade.getPais())) {
                throw new ErroNegocio("Informe o Pais!");
            }
            PreparedStatement ps;
            if (entidade.getId() == null) {
                ps = FabricaConexao.getConexao().prepareStatement("insert into cidade (nome, uf, pais) values (?,?,?)");
            } else {
                ps = FabricaConexao.getConexao().prepareStatement("update cidade set nome = ?, uf = ?, pais = ? where id = ?");
                ps.setInt(4, entidade.getId());
            }
            ps.setString(1, entidade.getNome());
            ps.setString(2, entidade.getUf());
            ps.setString(3, entidade.getPais());
            ps.execute();
        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao tentar salvar usuário! " + ex.getMessage());
        }
    }

    @Override
    public void deletar(Cidade entidade) throws ErroNegocio, ErroSistema {
        try {
            PreparedStatement ps;
            ps = FabricaConexao.getConexao().prepareStatement("delete from cidade where id = ?");
            ps.setInt(1, entidade.getId());
            ps.execute();
        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao tentar deletar! "+ex.getMessage());
        }
    }

    @Override
    public List<Cidade> listar() throws ErroNegocio, ErroSistema {
        List<Cidade> entidades = new ArrayList<Cidade>();
        try {
            PreparedStatement ps;
            ps = FabricaConexao.getConexao().prepareStatement("select * from cidade");
            ResultSet resultado = ps.executeQuery();
            while(resultado.next()){
                Cidade entidade = new Cidade();
                entidade.setId(resultado.getInt("id"));
                entidade.setNome(resultado.getString("nome"));
                entidade.setUf(resultado.getString("uf"));
                entidade.setPais(resultado.getString("pais"));
                entidades.add(entidade);
            }
            return entidades;
        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao tentar buscar! "+ex.getMessage());
        }
    }

}
