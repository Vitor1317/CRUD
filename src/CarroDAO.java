package src;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarroDAO {
    private ConnectionFactory connectionFactory;

    public CarroDAO() {
        this.connectionFactory = new ConnectionFactory();
    }

    public int createCarro(Carro carro) {
        String sql = "INSERT INTO carro (id, nome, valor) VALUES(?, ?, ?)";

        try {
            Connection connection = connectionFactory.connectionDB();

            PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            stmt.setInt(1, carro.getId());
            stmt.setString(2, carro.getNome());
            stmt.setFloat(3, carro.getValor());
            stmt.executeUpdate();

            ResultSet generateKeys = stmt.getGeneratedKeys();

            if(generateKeys.next()) {
                return generateKeys.getInt(1);
            }
        } catch (SQLException error) {
            System.err.println("Erro Create: " + error.getMessage());
        }

        return -1;
    }

    public Carro readCarro(int id) {
        String sql = "SELECT * FROM carros WHERE ID =?";

        try {
            Connection connection = connectionFactory.connectionDB();

            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()) {
                return new Carro(rs.getInt("id"), rs.getString("nome"), rs.getFloat("valor"));
            }

        }catch (SQLException error) {
            System.err.println("Erro Read: " + error.getMessage());
        }

        return null;
    }

    public List<Carro> readAllCarro() {
        List<Carro> carroList = new ArrayList<>();
        String sql = "SELECT * FROM carro";

        try {
            Connection connection = connectionFactory.connectionDB();

            Statement stmt = connection.createStatement();

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                carroList.add(new Carro(rs.getInt("id"), rs.getString("nome"), rs.getFloat("valor")));
            }

        }catch (SQLException error ) {
            System.err.println("Erro Read All:" + error.getMessage());
        }

        return carroList;
    }

    public boolean updateCarro(Carro carro) {
        String sql = "UPDATE carro SET nome =?, valor=? WHERE id =?";

        try {

            Connection connection = connectionFactory.connectionDB();
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, carro.getNome());
            stmt.setFloat(1, carro.getValor());
            stmt.setInt(3, carro.getId());

            int rowsAffected = stmt.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException error) {
            System.err.println("Erro update: " + error.getMessage());
        }

        return false;
    }

    public boolean deleteCarro(int id) {
        String sql = "DELETE FROM carro id=?";
        try {
            Connection connection = connectionFactory.connectionDB();
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();

            return rowsAffected > 0;
        } catch(SQLException error) {
            System.err.println("Error delete: " + error.getMessage());
        }

        return false;
    }
}
