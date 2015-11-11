
package br.edu.ifnmg.arinos.systemvendas.dao;
import br.edu.ifnmg.arinos.systemvendas.entidade.Pessoa;
import br.edu.ifnmg.arinos.systemvendas.util.FabricaConexao;
import br.edu.ifnmg.arinos.systemvendas.util.StringUtil;
import br.edu.ifnmg.arinos.systemvendas.util.excecao.ErroPessoa;
import br.edu.ifnmg.arinos.systemvendas.util.excecao.ErroSistema;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PessoaDAO {
    public void salvar(Pessoa entidade) throws ErroPessoa, ErroSistema {
        try {
            if (StringUtil.ehVazio(entidade.getNome())) {
                throw new ErroPessoa("Informe o Nome!");
            }
            if (StringUtil.ehVazio(entidade.getCpf())) {
                throw new ErroPessoa("Informe o CPF!");
            }
            if (StringUtil.ehVazio(entidade.getRua())) {
                throw new ErroPessoa("Informe a sua rua!");
            }
            if (StringUtil.ehVazio(entidade.getNumero())) {
                throw new ErroPessoa("Informe o numero da sua rua!");
            }
            if (StringUtil.ehVazio(entidade.getComplemento())) {
                throw new ErroPessoa("Informe um complemento!");
            }
            if (StringUtil.ehVazio(entidade.getBairro())) {
                throw new ErroPessoa("Informe o seu bairro!");
            }
            if (StringUtil.ehVazio(entidade.getCep())) {
                throw new ErroPessoa("Informe o seu cep!");
            }
            
            PreparedStatement ps;
            if (entidade.getId() == null) {
                ps = FabricaConexao.getConexao().prepareStatement("insert into pessoa (nome, cpf, rua, numero, complemento, bairro, cep) values (?,?,?,?,?,?,?)");
            } else {
                ps = FabricaConexao.getConexao().prepareStatement("update usuario set nome = ?, cpf = ?, rua = ?, numero = ?, complemento = ? bairro = ? cep = ? where id = ?");
                ps.setInt(4, (int) entidade.getId());
            }
            ps.setString(1, entidade.getNome());
            ps.setString(2, entidade.getCpf());
            ps.setString(3, entidade.getRua());
            ps.setString(4, entidade.getNumero());
            ps.setString(5, entidade.getComplemento());
            ps.setString(6, entidade.getBairro());
            ps.setString(7, entidade.getCep());
            
            ps.execute();
        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao tentar salvar pessoa! " + ex.getMessage());
        }
    }

    public void deletar(Pessoa entidade) throws ErroPessoa, ErroSistema {
        
        try {
            PreparedStatement ps;
            ps = FabricaConexao.getConexao().prepareStatement("delete from pessoa where id = ?");
            ps.setInt(1, (int) entidade.getId());
            ps.execute();
        } catch (SQLException ex) {
            throw new ErroPessoa("Erro ao tentar deletar! "+ex.getMessage());
        }
    }

    public List<Pessoa> listar() throws ErroPessoa, ErroSistema {
        List<Pessoa> entidades = new ArrayList<>();
        try {
            PreparedStatement ps;
            ps = FabricaConexao.getConexao().prepareStatement("select * from pessoa");
            ResultSet resultado = ps.executeQuery();
            while(resultado.next()){
                Pessoa entidade = new Pessoa();
                entidade.setId(resultado.getInt("id"));
                entidade.setNome(resultado.getString("nome"));
                entidade.setRua(resultado.getString("rua"));
                entidade.setNumero(resultado.getString("numero"));
                entidade.setComplemento(resultado.getString("complemento"));
                entidade.setBairro(resultado.getString("bairro"));
                entidade.setCep(resultado.getString("cep"));
                entidades.add(entidade);
            }
            return entidades;
        } catch (SQLException ex) {
            throw new ErroPessoa("Erro ao tentar buscar! "+ex.getMessage());
        }
    }

}

 

