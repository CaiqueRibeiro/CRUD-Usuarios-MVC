package br.com.caiqueribeiro.contato;

import br.com.caiqueribeiro.excecao.ExcecaoAcessoDados;
import java.util.List;

public interface ContatoDAO {

    void alterar(Contato contato) throws ExcecaoAcessoDados;

    Contato consultarPorId(int id) throws ExcecaoAcessoDados;

    List<Contato> consultarTodos() throws ExcecaoAcessoDados;

    void excluir(int id) throws ExcecaoAcessoDados;

    void inserir(Contato contato) throws ExcecaoAcessoDados;

}
