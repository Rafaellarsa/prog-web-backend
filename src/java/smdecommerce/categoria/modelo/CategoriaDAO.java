package smdecommerce.categoria.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import smdecommerce.usuario.modelo.Usuario;

public class CategoriaDAO {

    /**
     * Método utilizado para obter um usuário pelo seu identificador
     *
     * @param id
     * @return
     * @throws Exception
     */
    public Usuario obter(int id) throws Exception {
        Usuario categoria = null;
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/smdecommerce", "postgres", "ufc123");
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, nome FROM categoria WHERE id = ?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            categoria = new Usuario();
            categoria.setId(resultSet.getInt("id"));
            categoria.setNome(resultSet.getString("nome"));
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        if (categoria == null) {
            throw new Exception("Categoria não encontrada");
        }
        return categoria;
    }

    public Usuario obter(String login) throws Exception {
        Usuario categoria = null;
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/smdecommerce", "postgres", "ufc123");
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, nome FROM categoria WHERE login = ?");
        preparedStatement.setString(1, login);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            categoria = new Usuario();
            categoria.setId(resultSet.getInt("id"));
            categoria.setNome(resultSet.getString("nome"));
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        if (categoria == null) {
            throw new Exception("Usuário não encontrado");
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
    public void inserir(String nome) throws Exception {
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/smdecommerce", "postgres", "ufc123");
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO categoria (nome) VALUES (?)");
        preparedStatement.setString(1, nome);
        int resultado = preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
        if (resultado != 1) {
            throw new Exception("Não foi possível inserir o categoria");
        }
    }
}
