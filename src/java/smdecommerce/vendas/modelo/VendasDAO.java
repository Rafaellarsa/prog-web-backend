package smdecommerce.vendas.modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.postgresql.shaded.com.ongres.scram.common.util.Preconditions;
import smdecommerce.application.DatabaseConnection;

public class VendasDAO {

    DatabaseConnection dbconnection = null;
    PreparedStatement preparedStatement = null;

    public VendasDAO() {
        dbconnection = new DatabaseConnection();
    }

    /* Lista compras feitas pelo cliente através do id do cliente */
        public List<Vendas> listarVendas() throws Exception {
        List<Vendas> vendasList = new ArrayList<>();
        String SQLQuery = "SELECT * FROM venda";
        preparedStatement = dbconnection.getConnection().prepareStatement(SQLQuery);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Vendas venda = new Vendas();

            venda.setId_venda(resultSet.getInt("id_venda"));
            venda.setId_cliente_venda(resultSet.getInt("id_cliente_venda"));
            venda.setId_produto_venda(resultSet.getInt("id_produto_venda"));
            venda.setData_hora_venda(resultSet.getString("data_hora_venda"));

            vendasList.add(venda);
        }
        resultSet.close();
        preparedStatement.close();
        dbconnection.closeConnection();
        return vendasList;
    }
    
        public List<Vendas> listarVendasPorCliente(int clientID) throws Exception {
        List<Vendas> vendasList = new ArrayList<>();
        String SQLQuery = "SELECT * FROM venda WHERE id_cliente_venda =" + String.valueOf(clientID);
        preparedStatement = dbconnection.getConnection().prepareStatement(SQLQuery);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Vendas venda = new Vendas();

            venda.setId_venda(resultSet.getInt("id_venda"));
            venda.setId_cliente_venda(resultSet.getInt("id_cliente_venda"));
            venda.setId_produto_venda(resultSet.getInt("id_produto_venda"));
            venda.setData_hora_venda(resultSet.getString("data_hora_venda"));

            vendasList.add(venda);
        }
        resultSet.close();
        preparedStatement.close();
        dbconnection.closeConnection();
        return vendasList;
    }

    //tentei fazer um count aqui
    /*public Integer consultarVendasPorCliente(int clientID) throws Exception {

    String SQLQuery = "SELECT COUNT(id_venda) FROM venda WHERE id_cliente_venda =" + String.valueOf(clientID);
        preparedStatement = dbconnection.getConnection().prepareStatement(SQLQuery);
        ResultSet resultSet = preparedStatement.executeQuery();

        Preconditions.checkArgument(resultSet.next(), "Result set is empty");
        return resultSet.getInt("size");
    }*/
    public Integer consultarVendasPorCliente(int clientID) throws Exception {
        int contador = 0;
        String SQLQuery = "SELECT COUNT(id_venda) FROM venda WHERE id_cliente_venda =" + String.valueOf(clientID);
        preparedStatement = dbconnection.getConnection().prepareStatement(SQLQuery);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            contador = resultSet.getInt(1);
        }

        resultSet.close();
        preparedStatement.close();
        dbconnection.closeConnection();

        return contador;
    }

    public void cadastrarVenda(String data_e_hora, int id_cliente_venda, int id_produto_venda) throws Exception {

        String SQLQuery = "INSERT INTO venda (data_e_hora, id_cliente_venda, id_produto_venda) VALUES (?, ?, ?)";
        preparedStatement = dbconnection.getConnection().prepareStatement(SQLQuery);

        preparedStatement.setString(1, data_e_hora);
        preparedStatement.setInt(2, id_cliente_venda);
        preparedStatement.setInt(3, id_produto_venda);

        int resultado = preparedStatement.executeUpdate();

        preparedStatement.close();
        dbconnection.closeConnection();

        if (resultado != 1) {
            throw new Exception("Erro ao registrar a operação de venda");
        }
    }

    public void deletarOperacao(int id) throws Exception {
        String SQLQuery = "DELETE FROM venda WHERE id_venda = ?";
        preparedStatement = dbconnection.getConnection().prepareStatement(SQLQuery);
        preparedStatement.setInt(1, id);

        int resultado = preparedStatement.executeUpdate();

        preparedStatement.close();
        dbconnection.closeConnection();

        if (resultado != 1) {
            throw new Exception("Erro ao deletar operação de venda");
        }
    }

}
