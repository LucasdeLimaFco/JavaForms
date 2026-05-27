/**
 * BACK-END / BANCO = Matheus Godoy.
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {
    private static final String URL = "jdbc:mysql://localhost:3306/cadastro_etec";
    private static final String USUARIO = "root";
    private static final String SENHA = "";

    public static Connection conectar() throws Exception {
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }
}
