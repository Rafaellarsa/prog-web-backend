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
public class DeletarProdutoServlet {

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id_produto = Integer.parseInt(request.getParameter("id_produto"));

        ProdutoDAO produtoDao = new ProdutoDAO();

        boolean deletou = false;
        String mensagem = null;

        try {
            produtoDao.deletarProduto(id_produto);
            deletou = true;
            mensagem = "Produto deletado com sucesso";
        } catch (Exception ex) {
            deletou = false;
            mensagem = ex.getMessage();
        }

//Transforma a msg em json
        String json = "";
        json += "{";
        json += "\"atualizou\":" + deletou + ", ";
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
