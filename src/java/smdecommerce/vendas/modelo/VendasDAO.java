package smdecommerce.vendas.modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.postgresql.shaded.com.ongres.scram.common.util.Preconditions;
import smdecommerce.application.DatabaseConnection;
import smdecommerce.produto.modelo.Produto;
import smdecommerce.produto.modelo.ProdutoDAO;

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

    public void cadastrarVenda(int id_cliente_venda, ArrayList<String> ids_produto_venda) throws Exception {
        
        ProdutoDAO produtos = new ProdutoDAO();
        
        Map<String, Integer> neededItems = new HashMap<String, Integer>();
        
        for(int i = 0; i < ids_produto_venda.size(); i++){
            if(!neededItems.containsKey(ids_produto_venda.get(i).toString())){
                neededItems.put(ids_produto_venda.get(i).toString(), 1);
            }
            else{
                neededItems.put(ids_produto_venda.get(i).toString(), neededItems.get(ids_produto_venda.get(i).toString()) + 1);
            }
        }
        
        
        for(String key: neededItems.keySet()){
            Integer value = neededItems.get(key);
            Produto p = produtos.consultarProduto(Integer.parseInt(key));
            
            if(p.getQntde() < value){
                throw new Exception("Sem estoque");
            }
            else{
                produtos.atualizarProduto(p.getNome(), p.getDescricao(), p.getPreco(), p.getFoto(), p.getQntde() - value, p.getId_categoria(), p.getId());
            }
        }
        
        
        String now = java.time.ZonedDateTime.now().toString();
        
        int resultado = 0;
        
        for(int i = 0; i < ids_produto_venda.size(); i++){
            
            Produto prod = produtos.consultarProduto(Integer.parseInt(ids_produto_venda.get(i)));
            
            int estoque = prod.getQntde();
            
            
            String SQLQuery = "INSERT INTO venda (data_hora_venda, id_cliente_venda, id_produto_venda) VALUES (?, ?, ?)";
            preparedStatement = dbconnection.getConnection().prepareStatement(SQLQuery);
            
            preparedStatement.setString(1, now);
            preparedStatement.setInt(2, id_cliente_venda);
            preparedStatement.setInt(3, Integer.parseInt(ids_produto_venda.get(i)));
            
            resultado = preparedStatement.executeUpdate();
            
            preparedStatement.close();
            dbconnection.closeConnection();
            
            if (resultado != 1) {
                throw new Exception("Erro ao registrar a operação de venda");
            }
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
