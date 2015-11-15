package br.edu.ifnmg.arinos.systemvendas.dao;


import br.edu.ifnmg.arinos.systemvendas.entidade.Produto;
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
public class ProdutoDAO implements GenericoDAO<Produto> {

    @Override
    public void salvar(Produto entidade) throws ErroNegocio, ErroSistema {
        try {
            if (StringUtil.ehVazio(entidade.getNome())) {
                throw new ErroNegocio("Informe o Nome!");
            }
            if (StringUtil.ehVazio(entidade.getDescricao())) {
                throw new ErroNegocio("Informe a Descrição!");
            }
            if (entidade.getPreco() == 0) {
                throw new ErroNegocio("Informe o Preço!");
            }
           if (entidade.getEstoque_atual() == 0) {
                throw new ErroNegocio("Informe o Estoque Atual");
            }
            if (entidade.getEstoque_minimo() == 0) {
                throw new ErroNegocio("Informe o Estoque minimo");
            }
            if (StringUtil.ehVazio(entidade.getUnidade())) {
                throw new ErroNegocio("Informe a Unidade!");
            }
            
            PreparedStatement ps;
            if (entidade.getId() == null) {
                ps = FabricaConexao.getConexao().prepareStatement("insert into produto (nome, descricao, preco, estoque_atual, estoque_minimo, unidade) values (?,?,?,?,?,?,?)");
            } else {
                ps = FabricaConexao.getConexao().prepareStatement("update produto set nome = ?, descricao = ?, preco = ?, estoque_atual = ?, estoque_minimo = ?, unidade = ? where id = ?");
                ps.setInt(7, entidade.getId());
            }
            ps.setString(1, entidade.getNome());
            ps.setString(2, entidade.getDescricao());
            ps.setFloat(3, entidade.getPreco());
            ps.setFloat(4, entidade.getEstoque_atual());
            ps.setFloat(5, entidade.getEstoque_minimo());
            ps.setString(3, entidade.getUnidade());
            
            ps.execute();
        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao tentar salvar Produto! " + ex.getMessage());
        }
    }

    @Override
    public void deletar(Produto entidade) throws ErroNegocio, ErroSistema {
        try {
            PreparedStatement ps;
            ps = FabricaConexao.getConexao().prepareStatement("delete from produto where id = ?");
            ps.setInt(1, entidade.getId());
            ps.execute();
        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao tentar deletar! "+ex.getMessage());
        }
    }

    @Override
    public List<Produto> listar() throws ErroNegocio, ErroSistema {
        List<Produto> entidades = new ArrayList<>();
        try {
            PreparedStatement ps;
            ps = FabricaConexao.getConexao().prepareStatement("select * from produto");
            ResultSet resultado = ps.executeQuery();
            while(resultado.next()){
                Produto entidade = new Produto();
                entidade.setId(resultado.getInt("id"));
                entidade.setNome(resultado.getString("nome"));
                entidade.setDescricao(resultado.getString("descricao"));
                entidade.setPreco(resultado.getFloat("preco"));
                entidade.setEstoque_atual(resultado.getFloat("estoque_atual"));
                entidade.setEstoque_minimo(resultado.getFloat("estoque_minimo"));
                entidade.setUnidade(resultado.getString("Unidade"));
                entidades.add(entidade);
            }
            return entidades;
        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao tentar buscar! "+ex.getMessage());
        }
    }
}
