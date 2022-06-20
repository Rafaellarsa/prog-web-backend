package smdecommerce.usuario.controle;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import smdecommerce.usuario.modelo.UsuarioDAO;

/**
 *
 * @author Leonardo Oliveira Moreira
 * 
 * Classe que implementa a ação de inserir um novo usuário do tipo cliente
 */
public class AtualizarClienteServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /* entrada */
        request.setCharacterEncoding("UTF-8");
        String nome = request.getParameter("nome");
        String endereco = request.getParameter("endereco");
        String email = request.getParameter("email");
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        String id = request.getParameter("id");
        boolean administrador = false;
        /* processamento */
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        boolean atualizou;
        String mensagem;
        try {
            usuarioDAO.atualizarUsuario(nome, endereco, email, login, senha, Integer.parseInt(id));
            atualizou = true;
            mensagem = "Cliente atualizado com sucesso";
        } catch (Exception ex) {
            atualizou = false;
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
