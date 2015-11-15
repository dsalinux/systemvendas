package br.edu.ifnmg.arinos.systemvendas.dao;


import br.edu.ifnmg.arinos.systemvendas.entidade.Venda;
import br.edu.ifnmg.arinos.systemvendas.util.FabricaConexao;
import br.edu.ifnmg.arinos.systemvendas.util.StringUtil;
import br.edu.ifnmg.arinos.systemvendas.util.excecao.ErroNegocio;
import br.edu.ifnmg.arinos.systemvendas.util.excecao.ErroSistema;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author danilo
 */
public class VendaDAO implements GenericoDAO<Venda> {

    @Override
    public void salvar(Venda entidade) throws ErroNegocio, ErroSistema {
        try {
            if (entidade.getData() == null) {
                throw new ErroNegocio("Informe a data!");
            }
            PreparedStatement ps;
            if (entidade.getId() == null) {
                ps = FabricaConexao.getConexao().prepareStatement("insert into venda (data, observacao, valor_total) values (?,?,?)");
            } else {
                ps = FabricaConexao.getConexao().prepareStatement("update venda set data = ?, observacao = ?, valor_total = ? where id = ?");
                ps.setInt(4, entidade.getId());
            }
            ps.setDate(1, new java.sql.Date(entidade.getData().getTime()));
            ps.setString(2, entidade.getObservacao());
            ps.setFloat(3, entidade.getValor_total());
            ps.execute();
        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao tentar salvar Venda! " + ex.getMessage());
        }
    }

    @Override
    public void deletar(Venda entidade) throws ErroNegocio, ErroSistema {
        try {
            PreparedStatement ps;
            ps = FabricaConexao.getConexao().prepareStatement("delete from venda where id = ?");
            ps.setInt(1, entidade.getId());
            ps.execute();
        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao tentar deletar! "+ex.getMessage());
        }
    }

    @Override
    public List<Venda> listar() throws ErroNegocio, ErroSistema {
        List<Venda> entidades = new ArrayList<Venda>();
        try {
            PreparedStatement ps;
            ps = FabricaConexao.getConexao().prepareStatement("select * from venda");
            ResultSet resultado = ps.executeQuery();
            while(resultado.next()){
                Venda entidade = new Venda();
                entidade.setId(resultado.getInt("id"));
                entidade.setData(resultado.getDate("data"));
                entidade.setObservacao(resultado.getString("observacao"));
                entidade.setValor_total(resultado.getFloat("valor_total"));
                entidades.add(entidade);
            }
            return entidades;
        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao tentar buscar! "+ex.getMessage());
        }
    }
}
