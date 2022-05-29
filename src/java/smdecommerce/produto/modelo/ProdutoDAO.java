/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package smdecommerce.produto.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author SilvaVan
 */
public class ProdutoDAO {
   
    @SuppressWarnings("ConvertToTryWithResources")
    public boolean cadastrarProduto (int id, String desc, double preco, String foto, int qtde, int id_categoria) throws Exception{
        boolean status;
        
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/smdecommerce", "postgres", "ufc123");
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO produto (id_produto, descricao_produto, preco_produto, foto_produto, quantidade_produto, id_categoria_produto) VALUES (?, ?, ?, ?, ?, ?)");
        
        preparedStatement.setInt(1, id);
        preparedStatement.setString(2, desc);
        preparedStatement.setDouble(3, preco);
        preparedStatement.setString(4, foto);
        preparedStatement.setInt(5, qtde);
        preparedStatement.setInt(6, id_categoria);
        
        int resultado = preparedStatement.executeUpdate();
        
        preparedStatement.close();
        connection.close();
        
        if (resultado != 1) {
            status = false;
        }else{
            status = true;
        }
        
        return status;
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
        Class.forName("org.postgresql.Driver");
        
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/smdecommerce", "postgres", "ufc123");
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT id_produto, descricao_produto, preco_produto, foto_produto, quantidade_produto, id_categoria_produto FROM produto WHERE id_produto = ?");
        preparedStatement.setInt(1, id);
        
        ResultSet resultSet = preparedStatement.executeQuery();
        
        while (resultSet.next()) {
            produto = new Produto();
            produto.setId(resultSet.getInt("id_produto"));
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
        connection.close();
        if (produto == null) {
            throw new Exception("Produto não encontrado");
        }
        return produto;
    }

    @SuppressWarnings("ConvertToTryWithResources")
    public List<Produto> listarProdutosEstoque() throws Exception {
        List<Produto> produtos = new ArrayList<>();
        Class.forName("org.postgresql.Driver");
        
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/smdecommerce", "postgres", "ufc123");
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT id_produto, descricao_produto, preco_produto, foto_produto, quantidade_produto, id_categoria_produto FROM produto WHERE quantidade_produto > 0");
        ResultSet resultSet = preparedStatement.executeQuery();
        
        while (resultSet.next()) {
            Produto produto = new Produto();

            produto.setId(resultSet.getInt("id_produto"));
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
        connection.close();
        return produtos;
    }

    @SuppressWarnings("ConvertToTryWithResources")
    public List<Produto> listarProdutos() throws Exception {
        List<Produto> produtos = new ArrayList<>();
        Class.forName("org.postgresql.Driver");
        
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/smdecommerce", "postgres", "ufc123");
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT id_produto, descricao_produto, preco_produto, foto_produto, quantidade_produto, id_categoria_produto FROM produto");
        ResultSet resultSet = preparedStatement.executeQuery();
        
        while (resultSet.next()) {
            Produto produto = new Produto();

            produto.setId(resultSet.getInt("id_produto"));
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
        connection.close();
        return produtos;
    }

}
