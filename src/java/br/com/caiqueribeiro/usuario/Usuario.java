/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.caiqueribeiro.usuario;

import java.util.Date;

public class Usuario {

    private int id;
    private String usuario;
    private String senha;
    private int tentativas;
    // São utilizados para a recuperação da senha.
    // A chave é um hash que vai no e-mail enviado ao
    // usuário e a validade é a data de validade deste
    // hash.pg
    private String chave;
    private Date validade;
    // Define o nível de acesso do usuário.
    // É a implementação mais simples das 3 apresentadas 
    // em aula.
    private String papel;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setTentativasIncorretas(int tentativasIncorretas) {
        this.tentativas = tentativasIncorretas;
    }

    public int getTentativasIncorretas() {
        return tentativas;
    }

    public String getChave() {
        return chave;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }

    public Date getValidade() {
        return validade;
    }

    public void setValidade(Date validade) {
        this.validade = validade;
    }

    public String getPapel() {
        return papel;
    }

    public void setPapel(String papel) {
        this.papel = papel;
    }

}
