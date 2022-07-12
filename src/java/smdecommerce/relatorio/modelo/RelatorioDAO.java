/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smdecommerce.relatorio.modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import smdecommerce.application.DatabaseConnection;

/**
 *
 * @author ivalm
 */
public class RelatorioDAO {
    
    DatabaseConnection dbconnection = null;
    PreparedStatement preparedStatement = null;

    public RelatorioDAO() {
        dbconnection = new DatabaseConnection();
    }
    
    @SuppressWarnings("ConvertToTryWithResources")
    public void inserirRelatorio(String nome_produto, String movimentacao, int quantidade) throws Exception {
        LocalDateTime now = LocalDateTime.now();
        
        String SQLQuery = "INSERT INTO relatorio (nome_produto, movimentacao, quantidade, data_hora_venda) VALUES (?, ?, ?, ?)";
        preparedStatement = dbconnection.getConnection().prepareStatement(SQLQuery);

        preparedStatement.setString(1, nome_produto);
        preparedStatement.setString(2, movimentacao);
        preparedStatement.setInt(3, quantidade);
        preparedStatement.setString(4, now.toString());

        int resultado = preparedStatement.executeUpdate();

        preparedStatement.close();
        dbconnection.closeConnection();

        if (resultado != 1) {
            throw new Exception("Erro ao cadastrar o relatorio");
        }
    }
    
    @SuppressWarnings("ConvertToTryWithResources")
    public List<Relatorio> listarRelatorios() throws Exception {
        List<Relatorio> relatorios = new ArrayList<>();
        String SQLQuery = "SELECT id_relatorio, nome_produto, movimentacao, quantidade, id_produto_relatorio, data_hora_venda FROM relatorio";
        preparedStatement = dbconnection.getConnection().prepareStatement(SQLQuery);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Relatorio relatorio = new Relatorio();

            relatorio.setId(resultSet.getInt("id_relatorio"));
            relatorio.setNome_produto(resultSet.getString("nome_produto"));
            relatorio.setMovimentacao(resultSet.getString("movimentacao"));
            relatorio.setQuantidade(resultSet.getInt("quantidade"));
            relatorio.setId_produto(resultSet.getInt("id_produto_relatorio"));
            relatorio.setData_hora_venda(""+resultSet.getTime("data_hora_venda"));

            relatorios.add(relatorio);
        }
        resultSet.close();
        preparedStatement.close();
        dbconnection.closeConnection();
        return relatorios;
    }

    
}
