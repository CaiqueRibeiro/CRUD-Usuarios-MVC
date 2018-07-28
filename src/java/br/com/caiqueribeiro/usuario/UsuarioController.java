/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.caiqueribeiro.usuario;

import br.com.caiqueribeiro.web.RequestMethod;
import br.com.caiqueribeiro.excecao.ExcecaoAcessoDados;
import br.com.caiqueribeiro.excecao.ExcecaoLimiteTentativas;
import br.com.caiqueribeiro.web.MetodoHTTP;
import br.com.caiqueribeiro.web.RequestMapping;
import br.com.caiqueribeiro.web.RequestParameter;
import br.com.caiqueribeiro.web.RequestResult;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.Cookie;

@RequestMapping(url="/usuario")
public class UsuarioController {
    
    UsuarioService service = new UsuarioService();
    
    @RequestMethod(url = "/login.jsp", metodo = MetodoHTTP.GET)
    public boolean login(String usuario, String senha) {
        
       if(null != usuario) {
           try {
               
               Usuario doBanco = service.login(usuario);
               if(null != doBanco) {
                   dados.getRequest().getSession().setAttribute("usarioAutenticado", doBanco);
                   
                   if(null != dados.getRequest().getParameter("lembrarme")) {
                        Cookie cookieLembrar = new Cookie("lembrar", String.valueOf(doBanco.getId()));
                        cookieLembrar.setMaxAge(60*60);
                        dados.getResponse().addCookie(cookieLembrar);
                   }
                   
                   return true;
               }
               
               return false;
               
           } catch (ExcecaoAcessoDados ex) {
               Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
           } catch (ExcecaoLimiteTentativas ex) {
               Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
           }
       }
       
       return false;
    }
    
    
    @RequestMethod(url = "/consultar-id.jsp")
    public RequestResult consultarId(@RequestParameter(nome = "id") int id) {
        
        RequestResult resultado = new RequestResult();
        
        try {
            
            resultado.addData("usuario", service.consultarPorId(id));
            
        } catch (ExcecaoAcessoDados ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        resultado.setRelativePath("/cliente-consultar.jsp");
        
        return resultado;
        
    }
    
}
