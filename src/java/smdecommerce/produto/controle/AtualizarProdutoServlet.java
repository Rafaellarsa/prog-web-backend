/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package smdecommerce.produto.controle;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import smdecommerce.produto.modelo.ProdutoDAO;

/**
 *
 * @author SilvaVan
 */
public class AtualizarProdutoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        
        int id_produto = Integer.parseInt(request.getParameter("id"));
        String nome = request.getParameter("nome");
        String desc = request.getParameter("descricao");
        double preco = Double.parseDouble(request.getParameter("preco"));
        String foto = request.getParameter("foto");
        int qntde = Integer.parseInt(request.getParameter("qntde"));
        int id_categoria = Integer.parseInt(request.getParameter("id_categoria"));

        //ProdutoDao produtoDao = new ProdutoDao();
        ProdutoDAO produtoDao = new ProdutoDAO();

        String mensagem;
        boolean atualizou = false;

        try {
            produtoDao.atualizarProduto(nome, desc, preco, foto, qntde, id_categoria, id_produto);
            atualizou = true;
            mensagem = "Produto atualizado com sucesso";

        } catch (Exception ex) {
            mensagem = ex.getMessage();
        }

        //Conf cors 
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
        response.addHeader("Access-Control-Allow-Headers", "*");
        response.addHeader("Access-Control-Max-Age", "1728000");
        
        //Envio JSON
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("{");
            if (atualizou) {
                out.println("\"sucesso\": true");
            } else {
                out.println("\"sucesso\": false");
                out.println("\"error\": " + mensagem);
            }
            out.println("\"nome\": " + nome);
            out.println("}");
        }
    }
}
