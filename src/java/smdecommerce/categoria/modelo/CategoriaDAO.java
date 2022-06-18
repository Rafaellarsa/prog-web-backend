package smdecommerce.categoria.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;
import smdecommerce.application.DatabaseConnection;

public class CategoriaDAO {
    
    
    DatabaseConnection dbconnection = null;
    PreparedStatement preparedStatement = null;

    public CategoriaDAO() {
        dbconnection = new DatabaseConnection();
    }

    /**
     * Método utilizado para obter um usuário pelo seu identificador
     *
     * @param id
     * @return
     * @throws Exception
     */
    public Categoria obter(int id) throws Exception {
        Categoria categoria = null;
        
        String SQLQuery = "SELECT id, descricao_categoria FROM categoria WHERE id = ?";
        preparedStatement = dbconnection.getConnection().prepareStatement(SQLQuery);
        
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            categoria = new Categoria();
            categoria.setId(resultSet.getInt("id"));
            categoria.setDescricaoCategoria(resultSet.getString("descricao_categoria"));
        }
        resultSet.close();
        preparedStatement.close();
        dbconnection.closeConnection();
        if (categoria == null) {
            throw new Exception("Categoria não encontrada");
        }
        return categoria;
    }

    /**
     * Método utilizado para inserir um novo usuário
     *
     * @param nome
     * @param endereco
     * @param email
     * @param login
     * @param senha
     * @param administrador
     * @throws Exception
     */
    public void inserir(String descricaoCategoria) throws Exception {
        Class.forName("org.postgresql.Driver");
        String SQLQuery = "INSERT INTO categoria (id_categoria, descricao_categoria) VALUES (?, ?)";
        preparedStatement = dbconnection.getConnection().prepareStatement(SQLQuery);
        preparedStatement.setInt(1, new Random().nextInt(100000));
        preparedStatement.setString(2, descricaoCategoria);
        int resultado = preparedStatement.executeUpdate();
        preparedStatement.close();
        dbconnection.closeConnection();
        if (resultado != 1) {
            throw new Exception("Não foi possível inserir o categoria");
        }
    }
}
