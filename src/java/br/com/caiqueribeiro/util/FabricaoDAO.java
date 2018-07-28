package br.com.caiqueribeiro.util;

import br.com.caiqueribeiro.contato.ContatoDAO;
import br.com.caiqueribeiro.contato.ContatoDAOMySQL;
import br.com.caiqueribeiro.contato.ContatoDAOOracle;
import br.com.caiqueribeiro.contato.ContatoDAOPostgres;



/**
 * Esta classe implementa um 'Abstract Factory' de DAOs.
 * @author Leandro Luque.
 */
public class FabricaoDAO {

    public static ContatoDAO getContatoDAO() {

        // A lógica de verificação de SGBD não é adequada.
        // Trata-se apenas de um exemplo.
        try {
            BancoDadosOracle.getConexao().close();
            return new ContatoDAOOracle();
        } catch (Exception e) {
            try {
                BancoDadosPostgres.getConexao().close();
                return new ContatoDAOPostgres();
            } catch (Exception e1) {
                try {
                    BancoDadosMySQL.getConexao().close();
                return new ContatoDAOMySQL();
                } catch(Exception e2) {
                        
                       
                 }
             }
        }
        return null;
    }
}
