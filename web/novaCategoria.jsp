<%-- 
    Document   : novaCategoria
    Created on : 17/06/2022, 23:40:04
    Author     : ivalm
--%>
<%@page import="smdecommerce.usuario.modelo.Usuario"%>
<%
    Usuario usuario = (Usuario) session.getAttribute("currentUser");
    if (usuario == null) {
        request.setAttribute("mensagem", "Para ter acesso a esta página, você deve estar logado como admin.");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
        requestDispatcher.forward(request, response);
    } else {
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
         <h1>SMD E-commerce</h1>
         <hr/>
         <h3>Cadastrar nova categoria</h3>
         <form action="Categoria" method="post">
             <input type="text" name="descricaoCategoria" placeholder="Entre com o nome da categoria" /><br/>
             <input type="submit" value="Cadastrar" />
         </form>
         <a href="index.jsp">Voltar</a>
     </body>
</html>
<%
    }
%>