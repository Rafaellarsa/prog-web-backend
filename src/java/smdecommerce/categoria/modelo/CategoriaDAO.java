package smdecommerce.categoria.modelo;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
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
        
        String SQLQuery = "SELECT id, descricao_categoria FROM categoria WHERE id_categoria = ?";
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
        String SQLQuery = "INSERT INTO categoria (id_categoria, descricao_categoria) VALUES (?, ?)";
        preparedStatement = dbconnection.getConnection().prepareStatement(SQLQuery);
        preparedStatement.setInt(1, new Random().nextInt(100000));
        preparedStatement.setString(2, descricaoCategoria);
        int resultado = preparedStatement.executeUpdate();
        preparedStatement.close();
        dbconnection.closeConnection();
        if (resultado != 1) {
            throw new Exception("Não foi possível inserir a categoria");
        }
    }
    
    @SuppressWarnings("ConvertToTryWithResources")
    public List<Categoria> listarCategorias() throws Exception {
        List<Categoria> categorias = new ArrayList<>();
        String SQLQuery = "SELECT id_categoria, descricao_categoria FROM categoria";
        preparedStatement = dbconnection.getConnection().prepareStatement(SQLQuery);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Categoria categoria = new Categoria();

            categoria.setId(resultSet.getInt("id_categoria"));
            categoria.setDescricaoCategoria(resultSet.getString("descricao_categoria"));

            categorias.add(categoria);
        }
        resultSet.close();
        preparedStatement.close();
        dbconnection.closeConnection();
        return categorias;
    }
    
    public void remover(int id) throws Exception {
        String SQLQuery = "DELETE FROM categoria WHERE id_categoria = ?";
        preparedStatement = dbconnection.getConnection().prepareStatement(SQLQuery);
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        dbconnection.closeConnection();
    }
    
    public void alterar(String old, String descricaoCategoria) throws Exception {
        String SQLQuery = "UPDATE categoria SET descricao_categoria = ? WHERE descricao_categoria = ?";
        preparedStatement = dbconnection.getConnection().prepareStatement(SQLQuery);
        preparedStatement.setString(1, descricaoCategoria);
        preparedStatement.setString(2, old);
        int resultado = preparedStatement.executeUpdate();
        preparedStatement.close();
        dbconnection.closeConnection();
    }
}
