package smdecommerce.usuario.controle;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import smdecommerce.usuario.modelo.Usuario;
import smdecommerce.usuario.modelo.UsuarioDAO;

/**
 *
 * @author Leonardo Oliveira Moreira
 *
 * Classe que implementa a ação de login de um usuário do tipo cliente
 */
public class LoginClienteServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /* entrada */
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        Boolean isAdmin = false;
        /* processamento */
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        boolean sucesso = false;
        String mensagem = null;
        try {
            Usuario usuario = usuarioDAO.obter(login);
            if (usuario.getSenha().equals(senha)) {
                sucesso = true;
                isAdmin = usuario.getAdministrador();
                HttpSession session = request.getSession(true);
                session.setAttribute("currentUser", usuario);
                session.setAttribute("admin", isAdmin);
                response.sendRedirect("principalCliente.jsp");
            } else {
                response.sendRedirect("invalidLogin.jsp");
            }
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
//        if (sucesso && isAdmin) {
//                RequestDispatcher requestDispatcher = request
//                        .getRequestDispatcher("/novaCategoria.jsp");
//                requestDispatcher.forward(request, response);
//            } else {
//
//                RequestDispatcher requestDispatcher = request
//                        .getRequestDispatcher("/invalidLogin.jsp");
//                requestDispatcher.forward(request, response);
//
//            }
//        try (PrintWriter out = response.getWriter()) {
//            out.println("{");
//            if (sucesso) {
//                out.println("\"sucesso\": true,");
//                out.println("\"login\": \"" + login + "\",");
//                out.println("\"isAdmin\": " + isAdmin);
//            } else {
//                out.println("\"sucesso\": false,");
//                out.println("\"error\": \"" + mensagem + "\"");
//            }
//            out.println("}");
//        }
    }

}
