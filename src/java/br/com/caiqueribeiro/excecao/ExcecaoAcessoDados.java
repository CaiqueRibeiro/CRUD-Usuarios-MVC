/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.caiqueribeiro.excecao;

public class ExcecaoAcessoDados extends Exception {

    public ExcecaoAcessoDados(String message) {
        super(message);
    }

    public ExcecaoAcessoDados(String message, Throwable cause) {
        super(message, cause);
    }

}