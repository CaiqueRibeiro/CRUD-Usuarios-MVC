package br.com.caiqueribeiro.usuario;

import br.com.caiqueribeiro.excecao.ExcecaoAcessoDados;
import br.com.caiqueribeiro.excecao.ExcecaoLimiteTentativas;
import br.com.caiqueribeiro.util.BancoDadosMySQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

public class UsuarioDAO {

    public void inserir(Usuario usuario) throws ExcecaoAcessoDados {
        try {
            
            // Abre uma conexao com o banco.
            Connection conexao = BancoDadosMySQL.getConexao();

            String instrucaoSQL = "insert into usuario(usuario, senha, tentativas, papel) values(?, ?, 0, ?)";
            PreparedStatement declaracao = conexao.prepareStatement(instrucaoSQL);
            declaracao.setString(1, usuario.getUsuario());
            declaracao.setString(2, usuario.getSenha());
            declaracao.setString(3, usuario.getPapel());
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

    public void excluir(int id) throws ExcecaoAcessoDados {
        try {
            // Abre uma conexao com o banco.
            Connection conexao = BancoDadosMySQL.getConexao();

            String instrucaoSQL = "delete from usuario where id=?";

            PreparedStatement sql = conexao.prepareStatement(instrucaoSQL);
            sql.setInt(1, id);
            sql.execute();

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

    public void alterar(Usuario usuario) throws ExcecaoAcessoDados {
        try {
            // Abre uma conexao com o banco.
            Connection conexao = BancoDadosMySQL.getConexao();

            String instrucaoSQL = "update usuario set usuario=?, senha=?, tentativas=?, papel=? where id=?";
            PreparedStatement declaracao = conexao.prepareStatement(instrucaoSQL);
            declaracao.setString(1, usuario.getUsuario());
            declaracao.setString(2, usuario.getSenha());
            declaracao.setInt(3, usuario.getTentativasIncorretas());
            declaracao.setInt(4, usuario.getId());
            declaracao.setString(5, usuario.getPapel());
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

    public void zerarTentativas(int id) throws ExcecaoAcessoDados {
        try {
            // Abre uma conexao com o banco.
            Connection conexao = BancoDadosMySQL.getConexao();

            String instrucaoSQL = "update usuario set tentativas=0 where id=?";
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

    public void incrementarTentativas(int id) throws ExcecaoAcessoDados {
        try {
            // Abre uma conexao com o banco.
            Connection conexao = BancoDadosMySQL.getConexao();

            String instrucaoSQL = "update usuario set tentativas=tentativas+1 where id=?";
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

    public Usuario consultarPorId(int id) throws ExcecaoAcessoDados {
        Usuario usuario = null;
        try {
            // Abre uma conexao com o banco.
            Connection conexao = BancoDadosMySQL.getConexao();

            String instrucaoSQL = "select id, usuario, senha, tentativas, papel from usuario where id=" + id;
            PreparedStatement declaracao = conexao.prepareStatement(instrucaoSQL);
            ResultSet resultado = declaracao.executeQuery();
            if (resultado.next()) {
                usuario = new Usuario();
                usuario.setId(resultado.getInt("id"));
                usuario.setUsuario(resultado.getString("usuario"));
                usuario.setSenha(resultado.getString("senha"));
                usuario.setTentativasIncorretas(resultado.getInt("tentativas"));
                usuario.setPapel(resultado.getString("papel"));
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
        return usuario;
    }

    public void esqueciASenha(Usuario usuario) throws ExcecaoAcessoDados {
        try {
            // Abre uma conexao com o banco.
            Connection conexao = BancoDadosMySQL.getConexao();

            String instrucaoSQL = "update usuario set chave=?, validade=? where usuario=?";
            PreparedStatement declaracao = conexao.prepareStatement(instrucaoSQL);
            usuario.setChave(UUID.randomUUID().toString());
            declaracao.setString(1, usuario.getChave());

            Calendar calendario = Calendar.getInstance();
            calendario.add(Calendar.HOUR, 1);
            usuario.setValidade(calendario.getTime());

            //long horaAtual = System.currentTimeMillis();
            //horaAtual += 1000*60*60;
            declaracao.setDate(2, new java.sql.Date(usuario.getValidade().getTime()));
            declaracao.setString(3, usuario.getUsuario());
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

    public Usuario consultarPorChave(String chave) throws ExcecaoAcessoDados {
        Usuario usuario = null;
        try {
            // Abre uma conexao com o banco.
            Connection conexao = BancoDadosMySQL.getConexao();

            String instrucaoSQL = "select id, usuario, validade from usuario where chave=?";
            PreparedStatement declaracao = conexao.prepareStatement(instrucaoSQL);
            declaracao.setString(1, chave);
            ResultSet resultado = declaracao.executeQuery();
            if (resultado.next()) {
                usuario = new Usuario();
                usuario.setId(resultado.getInt("id"));
                usuario.setUsuario(resultado.getString("usuario"));
                usuario.setValidade(resultado.getDate("validade"));
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
        return usuario;
    }

    public Usuario autenticar(Usuario usuario) throws ExcecaoAcessoDados, ExcecaoLimiteTentativas {
        Usuario encontrado = null;
        try {
            // Abre uma conexao com o banco.
            Connection conexao = BancoDadosMySQL.getConexao();

            String instrucaoSQL = "select id, usuario, senha, tentativas, papel from usuario where usuario=?";
            PreparedStatement declaracao = conexao.prepareStatement(instrucaoSQL);
            declaracao.setString(1, usuario.getUsuario());
            System.out.println(usuario.getUsuario());
            ResultSet resultado = declaracao.executeQuery();
            if (resultado.next()) {
                // Verificar se ultrapassou o limite de tentativas.
                if (resultado.getInt("tentativas") > 3) {
                    throw new ExcecaoLimiteTentativas();
                } else if (usuario.getSenha().equals(resultado.getString("senha"))) {
                    encontrado = new Usuario();
                    encontrado.setId(resultado.getInt("id"));
                    encontrado.setUsuario(resultado.getString("usuario"));
                    encontrado.setSenha(resultado.getString("senha"));
                    encontrado.setTentativasIncorretas(resultado.getInt("tentativas"));
                    encontrado.setPapel(resultado.getString("papel"));
                    // Zerar as tentativas no banco, já que o usuário acessou.
                    zerarTentativas(resultado.getInt("id"));
                } else {
                    // Incrementar em 1 as tentativas incorretas.
                    incrementarTentativas(resultado.getInt("id"));
                }
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
        return encontrado;
    }

    public List<Usuario> consultarTodos() throws ExcecaoAcessoDados {
        List<Usuario> usuarios = null;
        try {
            // Abre uma conexao com o banco.
            Connection conexao = BancoDadosMySQL.getConexao();

            String instrucaoSQL = "select id, usuario, senha, tentativas, papel from usuario";
            PreparedStatement declaracao = conexao.prepareStatement(instrucaoSQL);
            ResultSet resultado = declaracao.executeQuery();
            usuarios = new ArrayList<>();
            while (resultado.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(resultado.getInt("id"));
                usuario.setUsuario(resultado.getString("usuario"));
                usuario.setSenha(resultado.getString("senha"));
                usuario.setTentativasIncorretas(resultado.getInt("tentativas"));
                usuario.setPapel(resultado.getString("papel"));
                usuarios.add(usuario);
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
        return usuarios;
    }

}
