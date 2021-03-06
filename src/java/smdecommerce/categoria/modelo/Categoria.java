package smdecommerce.categoria.modelo;

import java.io.Serializable;

public class Categoria implements Serializable {
    
    private Integer id;
    private String descricaoCategoria;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricaoCategoria() {
        return descricaoCategoria;
    }

    public void setDescricaoCategoria(String descricaoCategoria) {
        this.descricaoCategoria = descricaoCategoria;
    }
    
        public String toJSON() {
        String json = "";
        json += "{";
            json += "\"id\":" + id + ", ";
            json += "\"descricaoCategoria\":\"" + descricaoCategoria + "\"";
        json += "}";
        return json;
    }
}
