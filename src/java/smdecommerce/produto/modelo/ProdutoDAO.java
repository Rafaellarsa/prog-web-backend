/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package smdecommerce.produto.modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import smdecommerce.application.DatabaseConnection;

/**
 *
 * @author SilvaVan
 */
public class ProdutoDAO {

    DatabaseConnection dbconnection = null;
    PreparedStatement preparedStatement = null;

    public ProdutoDAO() {
        dbconnection = new DatabaseConnection();
    }

    @SuppressWarnings("ConvertToTryWithResources")
    public void cadastrarProduto(String nome, String desc, double preco, String foto, int qtde, int id_categoria) throws Exception {

        String SQLQuery = "INSERT INTO produto (id_produto, nome_produto, descricao_produto, preco_produto, foto_produto, quantidade_produto, id_categoria_produto) VALUES (?, ?, ?, ?, ?, ?)";
        preparedStatement = dbconnection.getConnection().prepareStatement(SQLQuery);

        preparedStatement.setInt(1, new Random().nextInt(100000));
        preparedStatement.setString(2, nome);
        preparedStatement.setString(3, desc);
        preparedStatement.setDouble(4, preco);
        preparedStatement.setString(5, foto);
        preparedStatement.setInt(6, qtde);
        preparedStatement.setInt(7, id_categoria);

        int resultado = preparedStatement.executeUpdate();

        preparedStatement.close();
        dbconnection.closeConnection();

        if (resultado != 1) {
            throw new Exception("Erro ao cadastrar o produto");
        }
    }

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    @SuppressWarnings("ConvertToTryWithResources")
    public Produto consultarProduto(int id) throws Exception {
        Produto produto = null;
        String SQLQuery = "SELECT id_produto, nome_produto, descricao_produto, preco_produto, foto_produto, quantidade_produto, id_categoria_produto FROM produto WHERE id_produto = ?";
        preparedStatement = dbconnection.getConnection().prepareStatement(SQLQuery);
        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            produto = new Produto();
            produto.setId(resultSet.getInt("id_produto"));
            produto.setNome(resultSet.getString("nome_produto"));
            produto.setDescricao(resultSet.getString("descricao_produto"));
            produto.setPreco(resultSet.getDouble("preco_produto"));
            produto.setFoto(resultSet.getString("foto_produto"));
            produto.setQntde(resultSet.getInt("quantidade_produto"));
            produto.setId_categoria(resultSet.getInt("id_categoria_produto"));

            if (resultSet.wasNull()) {
                produto.setFoto(null);
            }
        }
        resultSet.close();
        preparedStatement.close();
        dbconnection.closeConnection();
        if (produto == null) {
            throw new Exception("Produto não encontrado");
        }
        return produto;
    }

    public List<Produto> consultarProdutoPorNome(String nome) throws Exception {
        List<Produto> produtos = new ArrayList<>();
        String SQLQuery = "SELECT id_produto, nome_produto, descricao_produto, preco_produto, foto_produto, quantidade_produto, id_categoria_produto FROM produto LIKE %?%";
        preparedStatement = dbconnection.getConnection().prepareStatement(SQLQuery);
        preparedStatement.setString(1, nome);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Produto produto = new Produto();

            produto.setId(resultSet.getInt("id_produto"));
            produto.setNome(resultSet.getString("nome_produto"));
            produto.setDescricao(resultSet.getString("descricao_produto"));
            produto.setPreco(resultSet.getDouble("preco_produto"));
            produto.setFoto(resultSet.getString("foto_produto"));
            produto.setQntde(resultSet.getInt("quantidade_produto"));
            produto.setId_categoria(resultSet.getInt("id_categoria_produto"));

            if (resultSet.wasNull()) {
                produto.setFoto(null);
            }
            produtos.add(produto);
        }
        resultSet.close();
        preparedStatement.close();
        dbconnection.closeConnection();
        return produtos;
    }

    @SuppressWarnings("ConvertToTryWithResources")
    public List<Produto> listarProdutosEstoque() throws Exception {
        List<Produto> produtos = new ArrayList<>();

        String SQLQuery = "SELECT id_produto, nome_produto, descricao_produto, preco_produto, foto_produto, quantidade_produto, id_categoria_produto FROM produto WHERE quantidade_produto > 0";
        preparedStatement = dbconnection.getConnection().prepareStatement(SQLQuery);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Produto produto = new Produto();

            produto.setId(resultSet.getInt("id_produto"));
            produto.setNome(resultSet.getString("nome_produto"));
            produto.setDescricao(resultSet.getString("descricao_produto"));
            produto.setPreco(resultSet.getDouble("preco_produto"));
            produto.setFoto(resultSet.getString("foto_produto"));
            produto.setQntde(resultSet.getInt("quantidade_produto"));
            produto.setId_categoria(resultSet.getInt("id_categoria_produto"));

            if (resultSet.wasNull()) {
                produto.setFoto(null);
            }
            produtos.add(produto);
        }
        resultSet.close();
        preparedStatement.close();
        dbconnection.closeConnection();
        return produtos;
    }

    /**
     *
     * @return Lista de objetos do tipo Produtos independente da disponibilidade
     * do estoque
     * @throws Exception
     */
    @SuppressWarnings("ConvertToTryWithResources")
    public List<Produto> listarProdutos() throws Exception {
        List<Produto> produtos = new ArrayList<>();
        String SQLQuery = "SELECT id_produto, nome_produto, descricao_produto, preco_produto, foto_produto, quantidade_produto, id_categoria_produto FROM produto";
        preparedStatement = dbconnection.getConnection().prepareStatement(SQLQuery);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Produto produto = new Produto();

            produto.setId(resultSet.getInt("id_produto"));
            produto.setDescricao(resultSet.getString("nome_produto"));
            produto.setDescricao(resultSet.getString("descricao_produto"));
            produto.setPreco(resultSet.getDouble("preco_produto"));
            produto.setFoto(resultSet.getString("foto_produto"));
            produto.setQntde(resultSet.getInt("quantidade_produto"));
            produto.setId_categoria(resultSet.getInt("id_categoria_produto"));

            if (resultSet.wasNull()) {
                produto.setFoto(null);
            }
            produtos.add(produto);
        }
        resultSet.close();
        preparedStatement.close();
        dbconnection.closeConnection();
        return produtos;
    }

    public String deletarProduto(int id) throws Exception {
        String msg = "";
        try {
            String SQLQuery = "DELETE FROM produto WHERE id_produto = ?";
            preparedStatement = dbconnection.getConnection().prepareStatement(SQLQuery);
            preparedStatement.setInt(1, id);

            int result = preparedStatement.executeUpdate();

            preparedStatement.close();
            dbconnection.closeConnection();

            msg = result + " produto foi excluído";

        } catch (Exception e) {
            e.printStackTrace();
        }
        return msg;
    }

    public String atualizarProduto(String nome, String desc, double preco, String foto, int qtde, int id_categoria, int id_produto) throws Exception {
        String msg = "";
        try {
            String SQLQuery = "UPDATE produto SET nome_produto = ?, descricao_produto = ?, preco_produto = ?, foto_produto = ?, quantidade_produto = ?, id_categoria_produto = ? WHERE id_produto = ?";
            preparedStatement = dbconnection.getConnection().prepareStatement(SQLQuery);

            preparedStatement.setString(1, nome);
            preparedStatement.setString(2, desc);
            preparedStatement.setDouble(3, preco);
            preparedStatement.setString(4, foto);
            preparedStatement.setInt(5, qtde);
            preparedStatement.setInt(6, id_categoria);
            preparedStatement.setInt(7, id_produto);

            int resultado = preparedStatement.executeUpdate();
            msg = resultado + " produtos atualizados.";
            
            preparedStatement.close();
            dbconnection.closeConnection();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return msg;
    }

}
