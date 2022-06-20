/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package smdecommerce.usuario.controle;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import smdecommerce.usuario.modelo.Usuario;
import smdecommerce.usuario.modelo.UsuarioDAO;

/**
 *
 * @author Bruno
 */

public class DeletarClienteServlet extends HttpServlet {

     @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /* entrada */
        String id = request.getParameter("id");
        boolean administrador = false;
        /* processamento */
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        boolean deletou;
        String mensagem;
        try {
            usuarioDAO.deletarUsuario(Integer.parseInt(id));
            deletou = true;
            mensagem = "Cliente deltado com sucesso";
        } catch (Exception ex) {
            deletou = false;
            mensagem = ex.getMessage();
        }
        /* saída */
        /* Linhas utilizadas para permitir CORS - Início */
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
        response.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
        response.addHeader("Access-Control-Max-Age", "1728000");
        /* Linhas utilizadas para permitir CORS - Fim */
        
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("{");
            if (deletou) {
                out.println("\"sucesso\": true");
            } else {
                out.println("\"sucesso\": false");
                out.println("\"error\": " + mensagem);
            }
            out.println("}");
        }
    }
}
