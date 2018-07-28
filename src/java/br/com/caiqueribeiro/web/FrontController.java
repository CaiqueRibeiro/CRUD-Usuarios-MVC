
package br.com.caiqueribeiro.web;

import br.com.caiqueribeiro.util.ClassFinder;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.com.caiqueribeiro.util.Conversor;

@WebServlet(name = "FrontController", urlPatterns = {"/FrontController"})
public class FrontController extends HttpServlet {
    

    private Map<String, ResourceMapping> resourceMapping;
    private Map<Class, Object> conversorList;
    private final String NOT_FOUND = "_____nenhum_____";
    
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        
        // métodos futuros
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        

    }
    
    private void findServices() throws ServletException{
        
        try {
            Class[] classes = ClassFinder.findClasses("br.com.caiqueribeiro");
            
            resourceMapping = new HashMap<>();
            
            for(Class classe : classes) {
                
                RequestMapping mapaServicos;
                
                if(null != (mapaServicos = (RequestMapping) classe.getDeclaredAnnotation(RequestMapping.class))) {
                    String caminhoBase = mapaServicos.url();
                    if(!caminhoBase.endsWith("/")) {
                        caminhoBase += "/";
                    }
                    
                    Method[] metodos = classe.getDeclaredMethods();
                    
                    for(Method metodo : metodos) {
                        
                        RequestMethod mapaMetodos;
                        
                        if(null != (mapaMetodos = (RequestMethod) metodo.getDeclaredAnnotation(RequestMethod.class))) {
                            
                            if(RequestResult.class == metodo.getReturnType()) {
                                
                                String caminhoRelativo = caminhoBase;
                                
                                
                                if (mapaMetodos.url().startsWith("/")) {
                                    // Descarta a "/" inicial.
                                    caminhoRelativo += mapaMetodos.url().substring(1);
                                } else {
                                    caminhoRelativo += mapaMetodos.url();
                                }
                                
                                if(caminhoRelativo.endsWith("/")) {
                                    caminhoRelativo = caminhoRelativo.substring(0, caminhoRelativo.length() -1);
                                }
                                
                                ResourceMapping mapeamento = new ResourceMapping(caminhoRelativo, classe, metodo, mapaMetodos.metodo());
                            
                                resourceMapping.put(caminhoRelativo, mapeamento);
                            } else {
                                System.out.println("Method " + metodo.getName() + " from class " + classe.getName() + " doesn't return a RequestResult, so it's being ignored.");
                            }   
                        }     
                    } 
                } 
            }
            
                      
            
        } catch (IOException | ClassNotFoundException ex) {
            throw new ServletException("Error in the try/catch.");
        } 
        
    }
    
    
    public void findConversors() throws ServletException{
        try {
            Class[] classes = ClassFinder.findClasses("br.com.caiqueribeiro");
            
            conversorList = new HashMap<>();
            
            for(Class classe : classes) {
                Conversor conversor;
                
                if(null != (conversor = (Conversor) classe.getDeclaredAnnotation(Conversor.class))) {
                    Method[] methods = classe.getDeclaredMethods();
                    
                    boolean toString = false;
                    boolean fromString = false;
                    
                    for(Method method : methods) {
                        if(method.getName().equals("deString")) {
                            if(method.getParameterTypes()[0] == String.class && method.getParameterTypes().length == 1
                                    && Void.class != method.getReturnType()) {
                                fromString = true;
                            } else {
                                System.out.println("Method has to receive a String and return any stuff");
                            }
                        } else if(method.getName().equals("paraString")) {
                            if(method.getParameterTypes()[0] == String.class && method.getParameterTypes().length == 1) {
                                toString = true;
                            } else {
                                System.out.println("Method has to receive something and return a String");
                            }
                        }
                    }
                    
                    if(fromString && toString) {
                        try {
                            this.conversorList.put(classe.getDeclaredMethod("paraString", String.class).getReturnType(), classe.newInstance());
                        } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    } else {
                        System.out.println("WARNING: A classe de conversor " + classe.getName() + " shall have valid deString and paraString methods");
                    }
                }
            }
        } catch (Exception e) {
        }
    }
    
    
    

}
