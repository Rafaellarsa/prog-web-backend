/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package smdecommerce.produto.controle;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import smdecommerce.produto.modelo.ProdutoDAO;

/**
 *
 * @author SilvaVan
 */
public class AtualizaProdutoServlet {
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
        String nome = request.getParameter("nome");
        String desc = request.getParameter("descricao");
        double preco = Double.parseDouble(request.getParameter("preco"));
        String foto = request.getParameter("foto");
        int qntde = Integer.parseInt(request.getParameter("qntde"));
        int id_categoria = Integer.parseInt(request.getParameter("id_categoria"));
        int id_produto = Integer.parseInt(request.getParameter("id_produto"));
        
        //ProdutoDao produtoDao = new ProdutoDao();
        ProdutoDAO produtoDao = new ProdutoDAO();
        
        String mensagem = null;
        boolean atualizou = false;
        
        try {
            atualizou = true;
            mensagem = produtoDao.atualizarProduto(nome, desc, preco, foto, qntde, id_categoria, id_produto);
        } catch (Exception ex) {
            mensagem = ex.getMessage();
        }
        
        //Transforma a msg em json
        String json = "";
        json += "{";
            json += "\"atualizou\":" + atualizou + ", ";
            json += "\"mensagem\":" + mensagem;
        json += "}";

        //Conf cors 
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
        response.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
        response.addHeader("Access-Control-Max-Age", "1728000");
        
        //Envio JSON
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        
        // primeiro faz funcionar, depois faz direito
        out.print("[");
        out.print(json);
        out.print("]");

    }

}
