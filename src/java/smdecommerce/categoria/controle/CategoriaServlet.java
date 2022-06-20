/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smdecommerce.categoria.controle;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class CategoriaServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        /* entrada */
        String descricaoCategoria = request.getParameter("descricaoCategoria");
        Boolean isAdmin = false;
        /* processamento */
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        boolean sucesso = false;
        String mensagem = null;
         ResultSet rs = null;
        try {
            rs = categoriaDAO.getAll();
            sucesso = true;
        } catch (Exception ex) {
            sucesso = false;
            mensagem = ex.getMessage();
        }
        /* saída */
       /* Linhas utilizadas para permitir CORS - Início */
        response.addHeader("Access-Control-Allow-Origin", "*");
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
                out.println("categorias:");
                while(rs.next()){
                    out.println(rs.getString("id_categoria")+ ":" + rs.getString("descricao_categoria") + ",");
                }
                out.println("\"isAdmin\": " + isAdmin);
            } else {
                out.println("\"sucesso\": false,");
                out.println("\"error\": \"" + mensagem + "\"");
            }
            out.println("}");
        } catch (SQLException ex) {
            Logger.getLogger(CategoriaServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
