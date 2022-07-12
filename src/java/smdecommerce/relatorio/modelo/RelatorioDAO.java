/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smdecommerce.relatorio.modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
    public List<Relatorio> listarRelatorios() throws Exception {
        List<Relatorio> relatorios = new ArrayList<>();
        String SQLQuery = "SELECT id_relatorio, movimentacao, quantidade, id_produto_relatorio, data_hora_venda FROM relatorio";
        preparedStatement = dbconnection.getConnection().prepareStatement(SQLQuery);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Relatorio relatorio = new Relatorio();

            relatorio.setId(resultSet.getInt("id_relatorio"));
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
