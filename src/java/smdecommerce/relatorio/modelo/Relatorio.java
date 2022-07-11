/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smdecommerce.relatorio.modelo;

import java.io.Serializable;

/**
 *
 * @author ivalm
 */
public class Relatorio implements Serializable{
    
    private Integer id;
    private String movimentacao;
    private Integer quantidade;
    private Integer id_produto;
    private String data_hora_venda;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMovimentacao() {
        return movimentacao;
    }

    public void setMovimentacao(String movimentacao) {
        this.movimentacao = movimentacao;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Integer getId_produto() {
        return id_produto;
    }

    public void setId_produto(Integer id_produto) {
        this.id_produto = id_produto;
    }

    public String getData_hora_venda() {
        return data_hora_venda;
    }

    public void setData_hora_venda(String data_hora_venda) {
        this.data_hora_venda = data_hora_venda;
    }

    public String toJSON() {
        String json = "";
        json += "{";
            json += "\"id\":" + id + ", ";
            json += "\"movimentacao\":\"" + movimentacao + "\", ";
            json += "\"quantidade\":\"" + quantidade + "\", ";
            json += "\"id_produto\":" + id_produto + ", ";
            json += "\"data_hora_venda\":" + data_hora_venda;
        json += "}";
        return json;
    }
    
}
