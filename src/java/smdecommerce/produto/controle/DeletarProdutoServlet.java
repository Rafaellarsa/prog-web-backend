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
public class DeletarProdutoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id_produto = request.getParameter("id");

        ProdutoDAO produtoDao = new ProdutoDAO();

        boolean deletou;
        String mensagem;

        try {
            produtoDao.deletarProduto(Integer.parseInt(id_produto));
            deletou = true;
            mensagem = "Produto deletado com sucesso";
        } catch (Exception ex) {
            deletou = false;
            mensagem = ex.getMessage();
        }

        //Conf cors 
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
        response.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
        response.addHeader("Access-Control-Max-Age", "1728000");

        //Envio JSON
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("{");
            if (deletou) {
                out.println("\"sucesso\": true,");
                out.println("\"produto\": \"" + id_produto + "\",");
            } else {
                out.println("\"sucesso\": false,");
                out.println("\"produto\": \"" + id_produto + "\",");
                out.println("\"error\": \"" + mensagem + "\"");
            }
            out.println("}");
        }
    }
}
