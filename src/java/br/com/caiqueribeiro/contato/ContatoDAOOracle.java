package br.com.caiqueribeiro.contato;

import br.com.caiqueribeiro.excecao.ExcecaoAcessoDados;
import br.com.caiqueribeiro.util.BancoDadosOracle;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContatoDAOOracle implements ContatoDAO {

    @Override
    public void inserir(Contato contato) throws ExcecaoAcessoDados {
        try {
            // Abre uma conexao com o banco.
            Connection conexao = BancoDadosOracle.getConexao();

            String instrucaoSQL = "insert into contato(telefone, nome, email) values(?, ?, ?)";
            PreparedStatement declaracao = conexao.prepareStatement(instrucaoSQL);
            declaracao.setString(1, contato.getTelefone());
            declaracao.setString(2, contato.getNome());
            declaracao.setString(3, contato.getEmail());
            declaracao.execute();

            // Fecha a conexao.
            conexao.close();
        } catch (ClassNotFoundException erro) {
            erro.printStackTrace();
            throw new ExcecaoAcessoDados("Houve um problema de configuração blá blá blá");
        } catch (SQLException erro) {
            erro.printStackTrace();
            throw new ExcecaoAcessoDados("Houve um problema de conectividade blá blá blá");
        }

    }

    @Override
    public void excluir(int id) throws ExcecaoAcessoDados {
        try {
            // Abre uma conexao com o banco.
            Connection conexao = BancoDadosOracle.getConexao();

            String instrucaoSQL = "delete from contato where id=?";
            PreparedStatement declaracao = conexao.prepareStatement(instrucaoSQL);
            declaracao.setInt(1, id);
            declaracao.execute();

            // Fecha a conexao.
            conexao.close();
        } catch (ClassNotFoundException erro) {
            erro.printStackTrace();
            throw new ExcecaoAcessoDados("Houve um problema de configuração blá blá blá");
        } catch (SQLException erro) {
            erro.printStackTrace();
            throw new ExcecaoAcessoDados("Houve um problema de conectividade blá blá blá");
        }
    }

    @Override
    public void alterar(Contato contato) throws ExcecaoAcessoDados {
        try {
            // Abre uma conexao com o banco.
            Connection conexao = BancoDadosOracle.getConexao();

            String instrucaoSQL = "update contato set telefone=?, nome=?, email=? where id=?";
            PreparedStatement declaracao = conexao.prepareStatement(instrucaoSQL);
            declaracao.setString(1, contato.getTelefone());
            declaracao.setString(2, contato.getNome());
            declaracao.setString(3, contato.getEmail());
            declaracao.setInt(4, Integer.valueOf(contato.getId()));
            declaracao.execute();

            // Fecha a conexao.
            conexao.close();
        } catch (ClassNotFoundException erro) {
            erro.printStackTrace();
            throw new ExcecaoAcessoDados("Houve um problema de configuração blá blá blá");
        } catch (SQLException erro) {
            erro.printStackTrace();
            throw new ExcecaoAcessoDados("Houve um problema de conectividade blá blá blá");
        }
    }

    @Override
    public Contato consultarPorId(int id) throws ExcecaoAcessoDados {
        Contato contato = null;
        try {
            // Abre uma conexao com o banco.
            Connection conexao = BancoDadosOracle.getConexao();

            String instrucaoSQL = "select id, nome, telefone, email from contato where id=" + id;
            PreparedStatement declaracao = conexao.prepareStatement(instrucaoSQL);
            ResultSet resultado = declaracao.executeQuery();
            if (resultado.next()) {
                contato = new Contato();
                contato.setId(resultado.getInt("id"));
                contato.setNome(resultado.getString("nome"));
                contato.setTelefone(resultado.getString("telefone"));
                contato.setEmail(resultado.getString("email"));
            }

            // Fecha a conexao.
            conexao.close();
        } catch (ClassNotFoundException erro) {
            erro.printStackTrace();
            throw new ExcecaoAcessoDados("Houve um problema de configuração blá blá blá");
        } catch (SQLException erro) {
            erro.printStackTrace();
            throw new ExcecaoAcessoDados("Houve um problema de conectividade blá blá blá");
        }
        return contato;
    }

    @Override
    public List<Contato> consultarTodos() throws ExcecaoAcessoDados {
        List<Contato> contatos = null;
        try {
            // Abre uma conexao com o banco.
            Connection conexao = BancoDadosOracle.getConexao();

            String instrucaoSQL = "select id, nome, telefone, email from contato";
            PreparedStatement declaracao = conexao.prepareStatement(instrucaoSQL);
            ResultSet resultado = declaracao.executeQuery();
            contatos = new ArrayList<>();
            while (resultado.next()) {
                Contato contato = new Contato();
                contato.setId(resultado.getInt("id"));
                contato.setNome(resultado.getString("nome"));
                contato.setTelefone(resultado.getString("telefone"));
                contato.setEmail(resultado.getString("email"));
                contatos.add(contato);
            }

            // Fecha a conexao.
            conexao.close();
        } catch (ClassNotFoundException erro) {
            erro.printStackTrace();
            throw new ExcecaoAcessoDados("Houve um problema de configuração blá blá blá");
        } catch (SQLException erro) {
            erro.printStackTrace();
            throw new ExcecaoAcessoDados("Houve um problema de conectividade blá blá blá");
        }
        return contatos;
    }

}
