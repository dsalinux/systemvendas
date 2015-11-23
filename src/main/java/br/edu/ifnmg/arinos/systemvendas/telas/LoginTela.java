package br.edu.ifnmg.arinos.systemvendas.telas;

import br.edu.ifnmg.arinos.systemvendas.dao.UsuarioDAO;
import br.edu.ifnmg.arinos.systemvendas.entidade.Usuario;
import br.edu.ifnmg.arinos.systemvendas.util.JsfUtil;
import br.edu.ifnmg.arinos.systemvendas.util.StringUtil;
import br.edu.ifnmg.arinos.systemvendas.util.excecao.ErroSistema;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

@SessionScoped
@ManagedBean
public class LoginTela extends JsfUtil {
    
    private UsuarioDAO dao = new UsuarioDAO();
    
    private Usuario usuarioLogado = null;
    
    private String login;
    private String senha;
    
    public void logar(){
        try {
            if(StringUtil.ehVazio(login)){
                addMensagemAviso("Informe o usuário!");
                return;
            }
            if(StringUtil.ehVazio(login)){
                addMensagemAviso("Informe a senha!");
                return;
            }
            usuarioLogado = dao.buscarUsuario(login, senha);
            addMensagemAviso("Usuário ou senha inválidos!");
        } catch (ErroSistema ex) {
            addMensagemAviso(ex);
        }
    }
    public void deslogar(){
        usuarioLogado = null;
    }
    public void redirecionarLogado(){
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("index.jsf");
        } catch (IOException ex) {
            addMensagemErro("Erro ao redirecionar!");
            ex.printStackTrace();
        }
    }
    public void redirecionarTelaLogin(){
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("login.jsf");
        } catch (IOException ex) {
            addMensagemErro("Erro ao redirecionar!");
            ex.printStackTrace();
        }
    }
    
    public boolean estaLogado(){
        return usuarioLogado != null;
    }
    
    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    
    
}
