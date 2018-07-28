package br.com.caiqueribeiro.web;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Caique
 */
public class RequestResult {
    
    private ResponseType returnType = ResponseType.SAME_REQUEST;   
    private String relativePath;
    private Map<String, Object> data = new HashMap<>();
    
    public void addData(String nome, Object valor) {
        this.data.put(nome, valor);
    }
    
    public void setData(Map<String, Object> dados) {
        this.data = dados;
    }

    public Object getData(String nome) {
        return this.data.get(nome);
    }
    
    public Object getData() {
        return this.data;
    }

    public ResponseType getReturnType() {
        return returnType;
    }

    public void setReturnType(ResponseType returnType) {
        this.returnType = returnType;
    }

    public String getRelativePath() {
        return relativePath;
    }

    public void setRelativePath(String relativePath) {
        this.relativePath = relativePath;
    }
}
