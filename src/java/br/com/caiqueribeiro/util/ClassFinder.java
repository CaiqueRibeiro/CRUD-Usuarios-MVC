package br.com.caiqueribeiro.util;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * Searches for all classes in project and return an array of them
 * @author Caique
 */
public class ClassFinder {
    
    public static Class[] findClasses(String packageName) throws IOException, ClassNotFoundException {
        
        String path = packageName.replace('.', '/');  
        
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        
        Enumeration<URL> resources = classLoader.getResources(path);
        
        List<File> folders = new ArrayList<>();
        
        while(resources.hasMoreElements()) {
            URL url = resources.nextElement();
            folders.add(new File(url.getFile()));
        }
        
        ArrayList<Class> classes = new ArrayList<>();
        
        for(File folder : folders) {
            classes.addAll(classSearch(folder, packageName));
        }
        
        return classes.toArray(new Class[classes.size()]);                
    }
    
    
    public static List<Class> classSearch(File folder, String packageName) throws ClassNotFoundException {
        
        List<Class> classes = new ArrayList<>();
        
        if (!folder.exists()) {
            return classes;
        }
        
        File[] files = folder.listFiles();
        
        for(File file : files) {
            
            if(file.isDirectory()) {               
                classes.addAll(classSearch(file, packageName + "." + file.getName())); 
            } else if (file.getName().endsWith(".class")) {
                classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
            }
        } // end of for(File file : files)
        
        return classes;
    } // end of method
    
} // end of class
