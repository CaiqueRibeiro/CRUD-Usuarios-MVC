
import br.com.caiqueribeiro.util.ClassFinder;
import java.io.IOException;
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
public class TesteServicosWeb {
    
    public static void main(String[] args) {
        
        Class[] classes;
        try {
            classes = ClassFinder.findClasses("br.com.caiqueribeiro");
            
        for(Class classe : classes) {
            System.out.println("Classe: " + classe.getName());
        }
            
            
            
        } catch (IOException ex) {
            Logger.getLogger(TesteServicosWeb.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TesteServicosWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
