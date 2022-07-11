/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package smdecommerce.vendas.modelo;

import java.io.Serializable;

/**
 *
 * @author SilvaVan
 */
public class Vendas implements Serializable{
    
    private Integer id_venda;
    private String data_hora_venda;
    private Integer id_cliente_venda;
    private Integer id_produto_venda; 

    /**
     * @return the data_hora_venda
     */
    public String getData_hora_venda() {
        return data_hora_venda;
    }

    /**
     * @param data_hora_venda the data_hora_venda to set
     */
    public void setData_hora_venda(String data_hora_venda) {
        this.data_hora_venda = data_hora_venda;
    }

    /**
     * @return the id_cliente_venda
     */
    public Integer getId_cliente_venda() {
        return id_cliente_venda;
    }

    /**
     * @param id_cliente_venda the id_cliente_venda to set
     */
    public void setId_cliente_venda(Integer id_cliente_venda) {
        this.id_cliente_venda = id_cliente_venda;
    }

    /**
     * @return the id_produto_venda
     */
    public Integer getId_produto_venda() {
        return id_produto_venda;
    }

    /**
     * @param id_produto_venda the id_produto_venda to set
     */
    public void setId_produto_venda(Integer id_produto_venda) {
        this.id_produto_venda = id_produto_venda;
    }

    /**
     * @return the id_venda
     */
    public Integer getId_venda() {
        return id_venda;
    }

    /**
     * @param id_venda the id_venda to set
     */
    public void setId_venda(Integer id_venda) {
        this.id_venda = id_venda;
    }
    
    public String toJSON() {
        String json = "";
        json += "{";
            json += "\"id_venda\":" + id_venda + ", ";
            json += "\"id_cliente_venda\":" + id_cliente_venda + ", ";
            json += "\"id_produto_venda\":" + id_produto_venda + ", ";
            json += "\"data_hora_venda\":\"" + data_hora_venda + "\"";
        json += "}";
        return json;
    }
}
