package smdecommerce.usuario.modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;
import smdecommerce.application.DatabaseConnection;

/**
 *
 * @author Leonardo Oliveira Moreira
 *
 * Classe que implementa o padrão DAO para a entidade usuário
 */
public class UsuarioDAO {
    
    DatabaseConnection dbconnection = null;
    PreparedStatement preparedStatement = null;

    public UsuarioDAO() {
        dbconnection = new DatabaseConnection();
    }
    
    

    /**
     * Método utilizado para obter um usuário pelo seu identificador
     *
     * @param id
     * @return
     * @throws Exception
     */
    public Usuario obter(int id) throws Exception {
        Usuario usuario = null;
        String SQLQuery = "SELECT id_usuario, nome_usuario, endereco_usuario, email_usuario, login_usuario, senha_usuario, adm = ?";
        preparedStatement = dbconnection.getConnection().prepareStatement(SQLQuery);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            usuario = new Usuario();
            usuario.setId(resultSet.getInt("id"));
            usuario.setNome(resultSet.getString("nome"));
            usuario.setEndereco(resultSet.getString("endereco"));
            usuario.setEmail(resultSet.getString("email"));
            usuario.setLogin(resultSet.getString("email"));
            usuario.setSenha(resultSet.getString("senha"));
            usuario.setAdministrador(resultSet.getBoolean("administrador"));
        }
        resultSet.close();
        preparedStatement.close();
        dbconnection.closeConnection();
        if (usuario == null) {
            throw new Exception("Usuário não encontrado");
        }
        return usuario;
    }

    /**
     * Método utilizado para obter um usuário pelo seu login
     *
     * @param login
     * @return
     * @throws Exception
     */
    public Usuario obter(String login) throws Exception {
        Usuario usuario = null;
        String SQLQuery = "SELECT id_usuario, nome_usuario, endereco_usuario, email_usuario, login_usuario, senha_usuario, adm  FROM usuario WHERE login_usuario = ?";
        preparedStatement = dbconnection.getConnection().prepareStatement(SQLQuery);
        preparedStatement.setString(1, login);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            usuario = new Usuario();
            usuario.setId(resultSet.getInt("id_usuario"));
            usuario.setNome(resultSet.getString("nome_usuario"));
            usuario.setEndereco(resultSet.getString("endereco_usuario"));
            usuario.setEmail(resultSet.getString("email_usuario"));
            usuario.setLogin(resultSet.getString("login_usuario"));
            usuario.setSenha(resultSet.getString("senha_usuario"));
            usuario.setAdministrador(resultSet.getBoolean("adm"));
        }
        resultSet.close();
        preparedStatement.close();
        dbconnection.closeConnection();
        if (usuario == null) {
            throw new Exception("Usuário não encontrado");
        }
        return usuario;
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
    public void inserir(String nome, String endereco, String email, String login, String senha, boolean administrador) throws Exception {
        Class.forName("org.postgresql.Driver");
        String SQLQuery = "INSERT INTO usuario (id_usuario, nome_usuario, endereco_usuario, email_usuario, login_usuario, senha_usuario, adm) VALUES (?, ?, ?, ?, ?, ?, ?)";
        preparedStatement = dbconnection.getConnection().prepareStatement(SQLQuery);
        preparedStatement.setInt(1, new Random().nextInt(100000));
        preparedStatement.setString(2, nome);
        preparedStatement.setString(3, endereco);
        preparedStatement.setString(4, email);
        preparedStatement.setString(5, login);
        preparedStatement.setString(6, senha);
        preparedStatement.setBoolean(7, administrador);
        
        int resultado = preparedStatement.executeUpdate();
        preparedStatement.close();
        dbconnection.closeConnection();
        
        if (resultado != 1) {
            throw new Exception("Não foi possível inserir o usuário");
        }
    
    }
}
