/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package smdecommerce.vendas.modelo;

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
public class VendasDao {
    
    //cadastra uma nova venda
    public boolean cadastrarVenda (int id_venda, String data_hora_venda, int id_cliente_venda, int id_produto_venda) throws Exception{
        boolean status;
        
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/smdecommerce", "postgres", "ufc123");
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO venda (id_venda, data_hora_venda, id_cliente_venda, id_produto_venda) VALUES (?, ?, ?, ?)");
        
        preparedStatement.setInt(1, id_venda);
        preparedStatement.setString(2, data_hora_venda);
        preparedStatement.setInt(3, id_cliente_venda);
        preparedStatement.setInt(4, id_produto_venda);
        
        
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
    
    //consulta as compras/vendas feitas por usuario pesquisado
    public List<Vendas> consultarVenda(int id) throws Exception {
        List<Vendas> listavenda = new ArrayList<>();
        Class.forName("org.postgresql.Driver");
        
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/smdecommerce", "postgres", "ufc123");
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT id_venda, data_hora_venda, id_cliente_venda, id_produto_venda FROM venda WHERE id_cliente_venda = ?");
        preparedStatement.setInt(1, id);
        
        ResultSet resultSet = preparedStatement.executeQuery();
        
        while (resultSet.next()) {
            Vendas vendas = new Vendas();
            
            vendas.setId_venda(resultSet.getInt("Id_venda"));
            vendas.setData_hora_venda(resultSet.getString("Data_hora_venda"));
            vendas.setId_cliente_venda(resultSet.getInt("Id_cliente_venda"));
            vendas.setId_produto_venda(resultSet.getInt("Id_produto_venda"));
            
            listavenda.add(vendas);
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        if (listavenda.isEmpty()) {
            throw new Exception("nenhuma venda foi efetuada pora este usuário");
        }
        return listavenda;
    }

}
