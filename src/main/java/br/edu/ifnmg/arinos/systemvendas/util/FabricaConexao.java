package br.edu.ifnmg.arinos.systemvendas.util;

import br.edu.ifnmg.arinos.systemvendas.util.excecao.ErroSistema;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author danilo
 */
public class FabricaConexao {

    private static Connection conexao;
    private static final String USUARIO = "root";
    private static final String SENHA = "root";
    private static final String URL_CONEXAO = "jdbc:mysql://localhost/";
    private static final String BANCO = "system-vendas";

    public static Connection getConexao() throws ErroSistema {
        return getConexao(true);
    }
    public static Connection getConexao(boolean selecionaDatabase) throws ErroSistema {
        try {
            if (conexao == null || conexao.isClosed()) {
                Class.forName("com.mysql.jdbc.Driver");
                String url = URL_CONEXAO;
                if(selecionaDatabase){
                    url+= BANCO;
                }
                conexao = DriverManager.getConnection(url, USUARIO, SENHA);
            }
        } catch (ClassNotFoundException ex) {
            throw new ErroSistema("Drive do banco de dados não encontrado!");
        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao conectar no banco!");
        }
        return conexao;
    }

    public static void fecharConexao() throws ErroSistema {
        try {
            if (conexao != null && !conexao.isClosed()) {
                conexao.close();
            }
        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao fechar conexão com o banco!");
        }
    }
}
