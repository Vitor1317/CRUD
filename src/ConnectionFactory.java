package src;

import java.sql.*;

public class ConnectionFactory {
    public Connection connectionDB() {
        Connection connection = null;
        
        try {
            String url = "jdbc:mysql://localhost:3307/loja?useSSL=false";
            String user = "root";
            String password = "";

            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        return connection;
    }
}