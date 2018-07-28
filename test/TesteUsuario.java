
import br.com.caiqueribeiro.excecao.ExcecaoAcessoDados;
import br.com.caiqueribeiro.usuario.UsuarioService;
import br.com.caiqueribeiro.usuario.Usuario;
import br.com.caiqueribeiro.usuario.UsuarioDAO;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Caique
 */
public class TesteUsuario {
    
    public static void main(String[] args) {
        UsuarioService servico = new UsuarioService();
      
     //   UsuarioDAO dao = new UsuarioDAO();
        
        try {
            
            Usuario usuario = servico.consultarPorId(1);
            System.out.println("Nome: " + usuario.getUsuario());
            
        } catch (ExcecaoAcessoDados ex) {
            Logger.getLogger(TesteUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//
    
}
