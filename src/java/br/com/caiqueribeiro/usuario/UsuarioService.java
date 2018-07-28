
package br.com.caiqueribeiro.usuario;

import br.com.caiqueribeiro.excecao.ExcecaoAcessoDados;
import br.com.caiqueribeiro.excecao.ExcecaoLimiteTentativas;

public class UsuarioService {
    
    public Usuario login(Usuario usuario) throws ExcecaoAcessoDados, ExcecaoLimiteTentativas {
        UsuarioDAO dao = new UsuarioDAO();
        Usuario doBanco = dao.autenticar(usuario);
        if(null == doBanco) {
            return doBanco;
        } else {
            return null;
        }
    }
    
    public Usuario consultarPorId(int id) throws ExcecaoAcessoDados {
        
        UsuarioDAO dao = new UsuarioDAO();
        Usuario doBanco = dao.consultarPorId(id);
        System.out.println("NOME no service: " + doBanco.getUsuario());
        
        if(null != doBanco) {
            return doBanco;
        } else {
            return null;
        }
        
    }
    
}
