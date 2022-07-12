package smdecommerce.carrinho.modelo;

import smdecommerce.categoria.modelo.*;
import java.io.Serializable;
import java.util.ArrayList;
import smdecommerce.produto.modelo.Produto;

public class Carrinho implements Serializable {
    
    private Integer id;
    private Integer id_cliente;
    private ArrayList<Integer> produtos;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public Integer getIdCliente() {
        return id_cliente;
    }

    public void setIdCliente(Integer id_c) {
        this.id_cliente = id_c;
    }

    public ArrayList<Integer> getProdutos() {
        return produtos;
    }

    public void setProdutos(ArrayList<Integer> p) {
        this.produtos = p;
    }
    
        public String toJSON() {
        String json = "";
        json += "{";
            json += "\"id\":" + id + ", ";
            for(int x = 0; x < produtos.size(); x++){
                json += "\"produto_\" " + String.valueOf(x) + ":" + String.valueOf(produtos.get(x)) + ", ";
            }
            json += "\"id_cliente\":\"" + id_cliente + "\"";
        json += "}";
        return json;
    }
}
