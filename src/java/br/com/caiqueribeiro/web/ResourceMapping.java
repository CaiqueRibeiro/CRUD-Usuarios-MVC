/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.caiqueribeiro.web;

import java.lang.reflect.Method;

/**
 *
 * @author Caique
 */
public class ResourceMapping {

    private String caminho;
    private Class classe;
    private Method metodo;
    private MetodoHTTP[] metodosHTTP;

    public ResourceMapping() {
    }

    public ResourceMapping(String caminho, Class classe, Method metodo, MetodoHTTP[] metodosHTTP) {
        this.caminho = caminho;
        this.classe = classe;
        this.metodo = metodo;
        this.metodosHTTP = metodosHTTP;
    }

    public String getCaminho() {
        return caminho;
    }

    public void setCaminho(String caminho) {
        this.caminho = caminho;
    }

    public Class getClasse() {
        return classe;
    }

    public void setClasse(Class classe) {
        this.classe = classe;
    }

    public Method getMetodo() {
        return metodo;
    }

    public void setMetodo(Method metodo) {
        this.metodo = metodo;
    }

    public MetodoHTTP[] getMetodosHTTP() {
        return metodosHTTP;
    }

    public void setMetodosHTTP(MetodoHTTP[] metodosHTTP) {
        this.metodosHTTP = metodosHTTP;
    }

    @Override
    public String toString() {
        return "MapeamentoRecurso{" + "caminho=" + caminho + ", classe=" + classe + ", metodo=" + metodo + ", metodosHTTP=" + metodosHTTP + '}';
    }

}

