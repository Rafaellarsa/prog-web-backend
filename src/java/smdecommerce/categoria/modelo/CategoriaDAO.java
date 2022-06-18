package smdecommerce.categoria.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;

public class CategoriaDAO {

    /**
     * Método utilizado para obter um usuário pelo seu identificador
     *
     * @param id
     * @return
     * @throws Exception
     */
    public Categoria obter(int id) throws Exception {
        Categoria categoria = null;
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/smdecommerce", "postgres", "ufc123");
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, descricao_categoria FROM categoria WHERE id = ?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            categoria = new Categoria();
            categoria.setId(resultSet.getInt("id"));
            categoria.setDescricaoCategoria(resultSet.getString("descricao_categoria"));
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
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
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/smdecommerce", "postgres", "ufc123");
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO categoria (id_categoria, descricao_categoria) VALUES (?, ?)");
        preparedStatement.setInt(1, new Random().nextInt(100000));
        preparedStatement.setString(2, descricaoCategoria);
        int resultado = preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
        if (resultado != 1) {
            throw new Exception("Não foi possível inserir o categoria");
        }
    }
}
