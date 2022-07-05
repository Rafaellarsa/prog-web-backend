/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smdecommerce.categoria.controle;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import smdecommerce.categoria.modelo.CategoriaDAO;
//import smdecommerce.categoria.modelo.Categoria;
/**
 *
 * @author ivalm
 */
public class AtualizarCategoriaServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        /* entrada */
        int id = Integer.parseInt(request.getParameter("id"));
        String descricaoCategoria = request.getParameter("descricao_categoria");
        /* processamento */
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        boolean sucesso = false;
        String mensagem = null;
        try {
            categoriaDAO.alterar(id, descricaoCategoria);
            sucesso = true;
        } catch (Exception ex) {
            sucesso = false;
            mensagem = ex.getMessage();
        }
        
        /* saída */
       /* Linhas utilizadas para permitir CORS - Início */
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Headers", "Authorization");
        response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");;
        response.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
        response.addHeader("Access-Control-Max-Age", "1728000");
        /* Linhas utilizadas para permitir CORS - Fim */

        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("{");
            if (sucesso) {
                out.println("\"sucesso\": true,");
                out.println("\"categoria\": \"" + descricaoCategoria + "\",");
            } else {
                out.println("\"sucesso\": false,");
                out.println("\"error\": \"" + mensagem + "\"");
            }
            out.println("}");
        }
    }
}
