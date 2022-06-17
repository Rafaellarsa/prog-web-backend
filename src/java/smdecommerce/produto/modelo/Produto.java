/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package smdecommerce.produto.modelo;

import java.io.Serializable;

/**
 *
 * @author SilvaVan
 */
public class Produto implements Serializable{
    
    private Integer id;
    private String nome;
    private String descricao;
    private Double preco;
    private String foto;
    private Integer qntde;
    private Integer id_categoria;

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return the preco
     */
    public Double getPreco() {
        return preco;
    }

    /**
     * @param preco the preco to set
     */
    public void setPreco(Double preco) {
        this.preco = preco;
    }

    /**
     * @return the foto
     */
    public String getFoto() {
        return foto;
    }

    /**
     * @param foto the foto to set
     */
    public void setFoto(String foto) {
        this.foto = foto;
    }

    /**
     * @return the qntde
     */
    public Integer getQntde() {
        return qntde;
    }

    /**
     * @param qntde the qntde to set
     */
    public void setQntde(Integer qntde) {
        this.qntde = qntde;
    }

    /**
     * @return the id_categoria
     */
    public Integer getId_categoria() {
        return id_categoria;
    }

    /**
     * @param id_categoria the id_categoria to set
     */
    public void setId_categoria(Integer id_categoria) {
        this.id_categoria = id_categoria;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String toJSON() {
        String json = "";
        json += "{";
            json += "\"id\":" + id + ", ";
            json += "\"nome\":\"" + nome + "\", ";
            json += "\"descricao\":\"" + descricao + "\", ";
            json += "\"preco\":" + preco + ", ";
            json += "\"foto\":" + foto + ", ";
            json += "\"id_categoria\":" + id_categoria + ", ";
            json += "\"quantidade\":" + qntde;
        json += "}";
        return json;
    }
    
}
