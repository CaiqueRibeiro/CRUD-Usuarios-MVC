/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.caiqueribeiro.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Caique
 */
public class BancoDadosMySQL {
    
        public static Connection getConexao() throws ClassNotFoundException, SQLException {
        // Define um driver de conexao com o banco.
        Class.forName("com.mysql.jdbc.Driver");
        // Abre uma conexao com o banco.
        Connection conexao;
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/contato", "user", "pass");
    }
    
}

