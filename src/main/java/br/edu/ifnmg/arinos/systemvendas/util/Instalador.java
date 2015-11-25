
package br.edu.ifnmg.arinos.systemvendas.util;

import br.edu.ifnmg.arinos.systemvendas.util.excecao.ErroSistema;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 *
 * @author danilo
 */
public class Instalador implements ServletContextListener {

    public static final String CREATE_DATABASE = "CREATE DATABASE IF NOT EXISTS `system-vendas`";
    public static final String[] CREATE_TABLES = new String[]{
        //Criando table usuario
        "CREATE TABLE IF NOT EXISTS `system-vendas`.`usuario` (`id` INT(11) NOT NULL AUTO_INCREMENT,\n"
        + "  `nome` VARCHAR(100) NOT NULL,\n"
        + "  `login` VARCHAR(200) NOT NULL,\n"
        + "  `senha` VARCHAR(100) NOT NULL,\n"
        + "  PRIMARY KEY (`id`))\n"
        + "ENGINE = InnoDB\n"
        + "DEFAULT CHARACTER SET = utf8\n"
        + "COLLATE = utf8_general_ci",
        //Criando table categoria
        "CREATE TABLE IF NOT EXISTS `system-vendas`.`categoria` (\n"
        + "  `id` INT(11) NOT NULL AUTO_INCREMENT,\n"
        + "  `nome` VARCHAR(60) NULL DEFAULT NULL,\n"
        + "  `categoria_id` INT(11) NULL DEFAULT NULL,\n"
        + "  PRIMARY KEY (`id`),\n"
        + "  INDEX `fk_categoria_categoria_idx` (`categoria_id` ASC),\n"
        + "  CONSTRAINT `fk_categoria_categoria`\n"
        + "    FOREIGN KEY (`categoria_id`)\n"
        + "    REFERENCES `system-vendas`.`categoria` (`id`)\n"
        + "    ON DELETE NO ACTION\n"
        + "    ON UPDATE NO ACTION)\n"
        + "ENGINE = InnoDB\n"
        + "DEFAULT CHARACTER SET = utf8\n"
        + "COLLATE = utf8_general_ci",
        //Criando table produto
        "CREATE TABLE IF NOT EXISTS `system-vendas`.`produto` (\n"
        + "  `id` INT(11) NOT NULL AUTO_INCREMENT,\n"
        + "  `nome` VARCHAR(60) NOT NULL,\n"
        + "  `descricao` VARCHAR(300) NOT NULL,\n"
        + "  `preco` DECIMAL(9,2) NOT NULL,\n"
        + "  `estoque_atual` DECIMAL(5,2) NOT NULL DEFAULT 0,\n"
        + "  `estoque_minimo` DECIMAL(5,2) NOT NULL DEFAULT 0,\n"
        + "  `categoria_id` INT(11) NOT NULL,\n"
        + "  `unidade` VARCHAR(30) NOT NULL,\n"
        + "  PRIMARY KEY (`id`),\n"
        + "  INDEX `fk_produto_categoria_idx` (`categoria_id` ASC),\n"
        + "  CONSTRAINT `fk_produto_categoria`\n"
        + "    FOREIGN KEY (`categoria_id`)\n"
        + "    REFERENCES `system-vendas`.`categoria` (`id`)\n"
        + "    ON DELETE NO ACTION\n"
        + "    ON UPDATE NO ACTION)\n"
        + "ENGINE = InnoDB\n"
        + "DEFAULT CHARACTER SET = utf8\n"
        + "COLLATE = utf8_general_ci",
        //Criando table tipo_pessoa
        "CREATE TABLE IF NOT EXISTS `system-vendas`.`tipo_pessoa` (\n"
        + "  `id` INT(11) NOT NULL,\n"
        + "  `nome` VARCHAR(45) NOT NULL,\n"
        + "  PRIMARY KEY (`id`))\n"
        + "ENGINE = InnoDB\n"
        + "DEFAULT CHARACTER SET = utf8\n"
        + "COLLATE = utf8_general_ci",
        "CREATE TABLE IF NOT EXISTS `system-vendas`.`cidade` (\n"
        + "  `id` INT(11) NOT NULL AUTO_INCREMENT,\n"
        + "  `nome` VARCHAR(100) NOT NULL,\n"
        + "  `uf` CHAR(2) NOT NULL,\n"
        + "  `pais` VARCHAR(60) NOT NULL,\n"
        + "  PRIMARY KEY (`id`))\n"
        + "ENGINE = InnoDB\n"
        + "DEFAULT CHARACTER SET = utf8\n"
        + "COLLATE = utf8_general_ci",
        //Criando table pessoa
        "CREATE TABLE IF NOT EXISTS `system-vendas`.`pessoa` (\n"
        + "  `id` INT(11) NOT NULL AUTO_INCREMENT,\n"
        + "  `nome` VARCHAR(150) NOT NULL,\n"
        + "  `cpf_cnpj` VARCHAR(18) NOT NULL,\n"
        + "  `rua` VARCHAR(100) NOT NULL,\n"
        + "  `numero` INT(11) NULL DEFAULT NULL,\n"
        + "  `complemento` VARCHAR(50) NULL DEFAULT NULL,\n"
        + "  `bairo` VARCHAR(100) NOT NULL,\n"
        + "  `cep` CHAR(10) NOT NULL,\n"
        + "  `cidade_id` INT(11) NOT NULL,\n"
        + "  `tipo_pessoa_id` INT(11) NOT NULL,\n"
        + "  `data_desativacao` DATETIME NULL,\n"
        + "  PRIMARY KEY (`id`),\n"
        + "  INDEX `fk_pessoa_tipo_pessoa_idx` (`tipo_pessoa_id` ASC),\n"
        + "  INDEX `fk_pessoa_cidade_idx` (`cidade_id` ASC),\n"
        + "  CONSTRAINT `fk_pessoa_cidade`\n"
        + "    FOREIGN KEY (`cidade_id`)\n"
        + "    REFERENCES `system-vendas`.`cidade` (`id`)\n"
        + "    ON DELETE NO ACTION\n"
        + "    ON UPDATE NO ACTION,\n"
        + "  CONSTRAINT `fk_pessoa_tipo_pessoa`\n"
        + "    FOREIGN KEY (`tipo_pessoa_id`)\n"
        + "    REFERENCES `system-vendas`.`tipo_pessoa` (`id`)\n"
        + "    ON DELETE NO ACTION\n"
        + "    ON UPDATE NO ACTION)\n"
        + "ENGINE = InnoDB\n"
        + "DEFAULT CHARACTER SET = utf8\n"
        + "COLLATE = utf8_general_ci",
        //Criando table venda
        "CREATE TABLE IF NOT EXISTS `system-vendas`.`venda` (\n"
        + "  `id` INT(11) NOT NULL AUTO_INCREMENT,\n"
        + "  `data` DATETIME NOT NULL,\n"
        + "  `observacao` VARCHAR(200) NULL DEFAULT NULL,\n"
        + "  `valor_total` DECIMAL(9,2) NULL DEFAULT NULL,\n"
        + "  `cliente_id` INT(11) NOT NULL,\n"
        + "  `usuario_id` INT(11) NOT NULL,\n"
        + "  PRIMARY KEY (`id`),\n"
        + "  INDEX `fk_pedido_pessoa_idx` (`cliente_id` ASC),\n"
        + "  INDEX `fk_pedido_usuario_idx` (`usuario_id` ASC),\n"
        + "  CONSTRAINT `fk_pedido_pessoa`\n"
        + "    FOREIGN KEY (`cliente_id`)\n"
        + "    REFERENCES `system-vendas`.`pessoa` (`id`)\n"
        + "    ON DELETE NO ACTION\n"
        + "    ON UPDATE NO ACTION,\n"
        + "  CONSTRAINT `fk_pedido_usuario`\n"
        + "    FOREIGN KEY (`usuario_id`)\n"
        + "    REFERENCES `system-vendas`.`usuario` (`id`)\n"
        + "    ON DELETE NO ACTION\n"
        + "    ON UPDATE NO ACTION)\n"
        + "ENGINE = InnoDB\n"
        + "DEFAULT CHARACTER SET = utf8\n"
        + "COLLATE = utf8_general_ci",
        //Criando table item_venda
        "CREATE TABLE IF NOT EXISTS `system-vendas`.`item_venda` (\n"
        + "  `id_pedido` INT(11) NOT NULL,\n"
        + "  `id_produto` INT(11) NOT NULL,\n"
        + "  `preco` DECIMAL(9,2) NULL DEFAULT NULL,\n"
        + "  `quantidade` DECIMAL(5,2) NULL DEFAULT NULL,\n"
        + "  PRIMARY KEY (`id_pedido`, `id_produto`),\n"
        + "  INDEX `fk_item_pedido_produto_idx` (`id_produto` ASC),\n"
        + "  CONSTRAINT `fk_item_pedido_produto`\n"
        + "    FOREIGN KEY (`id_produto`)\n"
        + "    REFERENCES `system-vendas`.`produto` (`id`)\n"
        + "    ON DELETE NO ACTION\n"
        + "    ON UPDATE NO ACTION,\n"
        + "  CONSTRAINT `fk_item_pedido_pedido`\n"
        + "    FOREIGN KEY (`id_pedido`)\n"
        + "    REFERENCES `system-vendas`.`venda` (`id`)\n"
        + "    ON DELETE NO ACTION\n"
        + "    ON UPDATE NO ACTION)\n"
        + "ENGINE = InnoDB\n"
        + "DEFAULT CHARACTER SET = utf8\n"
        + "COLLATE = utf8_general_ci",
        //Criando table movimento_estoque
        "CREATE TABLE IF NOT EXISTS `system-vendas`.`movimento_estoque` (\n"
        + "  `id` INT(11) NOT NULL AUTO_INCREMENT,\n"
        + "  `data_movimento` DATETIME NOT NULL,\n"
        + "  `produto_id` INT(11) NOT NULL,\n"
        + "  `tipo_movimento` VARCHAR(45) NOT NULL,\n"
        + "  `quantidade` DECIMAL(12,4) NULL DEFAULT NULL,\n"
        + "  PRIMARY KEY (`id`),\n"
        + "  INDEX `fk_movimento_estoque_produto_idx` (`produto_id` ASC),\n"
        + "  CONSTRAINT `fk_movimento_estoque_produto`\n"
        + "    FOREIGN KEY (`produto_id`)\n"
        + "    REFERENCES `system-vendas`.`produto` (`id`)\n"
        + "    ON DELETE NO ACTION\n"
        + "    ON UPDATE NO ACTION)\n"
        + "ENGINE = InnoDB\n"
        + "DEFAULT CHARACTER SET = utf8\n"
        + "COLLATE = utf8_general_ci",
        //Criando table conta
        "CREATE TABLE IF NOT EXISTS `system-vendas`.`conta` (\n"
        + "  `id` INT(11) NOT NULL AUTO_INCREMENT,\n"
        + "  `nome` VARCHAR(80) NOT NULL,\n"
        + "  PRIMARY KEY (`id`))\n"
        + "ENGINE = InnoDB\n"
        + "DEFAULT CHARACTER SET = utf8\n"
        + "COLLATE = utf8_general_ci",
        //Criando table movimento_caixa
        "CREATE TABLE IF NOT EXISTS `system-vendas`.`movimento_caixa` (\n"
        + "  `id` INT(11) NOT NULL AUTO_INCREMENT,\n"
        + "  `descricao` VARCHAR(200) NOT NULL,\n"
        + "  `tipo_movimento` VARCHAR(45) NOT NULL,\n"
        + "  `valor` VARCHAR(45) NOT NULL,\n"
        + "  `data` DATETIME NOT NULL,\n"
        + "  `conta_id` INT(11) NOT NULL,\n"
        + "  `venda_id` INT(11) NULL DEFAULT NULL,\n"
        + "  PRIMARY KEY (`id`),\n"
        + "  INDEX `fk_movimento_caixa_venda_idx` (`venda_id` ASC),\n"
        + "  INDEX `fk_movimento_caixa_conta_idx` (`conta_id` ASC),\n"
        + "  CONSTRAINT `fk_movimento_caixa_venda`\n"
        + "    FOREIGN KEY (`venda_id`)\n"
        + "    REFERENCES `system-vendas`.`venda` (`id`)\n"
        + "    ON DELETE NO ACTION\n"
        + "    ON UPDATE NO ACTION,\n"
        + "  CONSTRAINT `fk_movimento_caixa_conta`\n"
        + "    FOREIGN KEY (`conta_id`)\n"
        + "    REFERENCES `system-vendas`.`conta` (`id`)\n"
        + "    ON DELETE NO ACTION\n"
        + "    ON UPDATE NO ACTION)\n"
        + "ENGINE = InnoDB\n"
        + "DEFAULT CHARACTER SET = utf8\n"
        + "COLLATE = utf8_general_ci"
    };

    public void verificarBancoDeDados() throws ErroSistema {
        try {
            PreparedStatement psCheck = FabricaConexao.getConexao(false).prepareStatement("show databases like 'system-vendas'");
            boolean jaExiste = psCheck.executeQuery().next();
            if (!jaExiste) {
                System.out.println("Banco de dados será criado!");
                PreparedStatement psCriar = FabricaConexao.getConexao(false).prepareStatement(CREATE_DATABASE);
                psCriar.executeUpdate();
                Statement s = FabricaConexao.getConexao().createStatement();
                System.out.println("Criando " + CREATE_TABLES.length + " tabelas!");
                for (int i = 0; i < CREATE_TABLES.length; i++) {
                    System.out.println("Criando tabela " + i);
                    s.execute(CREATE_TABLES[i]);
                    System.out.println("Tabela " + i + " criada!");
                }
                FabricaConexao.fecharConexao();
                PreparedStatement psUsuario = FabricaConexao.getConexao().prepareStatement("insert into usuario (nome, login, senha) values (?,?,?)");
                psUsuario.setString(1, "Admin");
                psUsuario.setString(2, "admin");
                psUsuario.setString(3, "123");
                psUsuario.execute();
                System.out.println("Banco de dados criado com sucess!");
            }
            FabricaConexao.fecharConexao();
        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao criar o banco de dados! " + ex.getMessage());
        }
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            System.out.println("Verificando a criação do banco de dados!");
            verificarBancoDeDados();
        } catch (ErroSistema ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

}
