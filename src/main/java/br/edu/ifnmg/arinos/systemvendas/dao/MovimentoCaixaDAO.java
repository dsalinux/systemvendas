package br.edu.ifnmg.arinos.systemvendas.dao;

import br.edu.ifnmg.arinos.systemvendas.entidade.MovimentoCaixa;
import br.edu.ifnmg.arinos.systemvendas.util.FabricaConexao;
import br.edu.ifnmg.arinos.systemvendas.util.StringUtil;
import br.edu.ifnmg.arinos.systemvendas.util.excecao.ErroNegocio;
import br.edu.ifnmg.arinos.systemvendas.util.excecao.ErroSistema;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovimentoCaixaDAO implements GenericoDAO<MovimentoCaixa> {

    @Override
    public void salvar(MovimentoCaixa entidade) throws ErroNegocio, ErroSistema {
        try {
            if (StringUtil.ehVazio(entidade.getDescricao())) {
                throw new ErroNegocio("Descrição !");
            }
            if (StringUtil.ehVazio(entidade.getTipo_movimento())) {
                throw new ErroNegocio("Qual o tipo do movimento?");
            }
            if (StringUtil.ehVazio(entidade.getValor())) {
                throw new ErroNegocio("Informe o Valor!");
            }
            if (entidade.getData() == null) {
                throw new ErroNegocio("Informe a data!");
            } else {
            }
            
            PreparedStatement ps;
            if (entidade.getId() == null) {
                ps = FabricaConexao.getConexao().prepareStatement("insert into movimento_caixa (descricao,tipo_movimento,valor, data) values (?,?,?,?)");
            } else {
                ps = FabricaConexao.getConexao().prepareStatement("update movimento_caixa set descricao = ?, tipo_movimento = ?, valor = ?, data =? where id = ?");
                ps.setInt(4, entidade.getId());
            }
            ps.setString(1, entidade.getDescricao());
            ps.setString(2, entidade.getTipo_movimento());
            ps.setString(3, entidade.getValor());
            ps.setDate(4, new java.sql.Date(entidade.getData().getTime()));
            ps.execute();
        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao tentar salvar Movimento Caixa! " + ex.getMessage());
        }
    }

    @Override
    public void deletar(MovimentoCaixa entidade) throws ErroNegocio, ErroSistema {
        try {
            PreparedStatement ps;
            ps = FabricaConexao.getConexao().prepareStatement("delete from movimento_caixa where id = ?");
            ps.setInt(1, entidade.getId());
            ps.execute();
        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao tentar deletar! "+ex.getMessage());
        }
    }

    @Override
    public List<MovimentoCaixa> listar() throws ErroNegocio, ErroSistema {
        List<MovimentoCaixa> entidades = new ArrayList<MovimentoCaixa>();
        try {
            PreparedStatement ps;
            ps = FabricaConexao.getConexao().prepareStatement("select * from movimento_caixa");
            ResultSet resultado = ps.executeQuery();
            while(resultado.next()){
                MovimentoCaixa entidade = new MovimentoCaixa();
                entidade.setId(resultado.getInt("id"));
                entidade.setDescricao(resultado.getString("descricao"));
                entidade.setTipo_movimento(resultado.getString("tipo_movimento"));
                entidade.setValor(resultado.getString("valor"));
                entidade.setData(resultado.getDate("data"));
                entidades.add(entidade);
            }
            return entidades;
        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao tentar buscar! "+ex.getMessage());
        }
    }

}
