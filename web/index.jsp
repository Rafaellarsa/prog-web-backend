<%-- 
    Document   : index
    Author     : leoomoreira
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SMD E-commerce</title>
    </head>
    <body>
        <h1>SMD E-commerce</h1>
        <% if (request.getAttribute("mensagem") != null) { %>
        <hr/>
        <div><%= request.getAttribute("mensagem") %></div>
        <% } %>
        <hr/>
        <h3>Identificação do Cliente</h3>
        <form action="LoginCliente" method="post">
            <input type="text" name="login" placeholder="Entre com seu login de cliente" /><br/>
            <input type="password" name="senha" placeholder="Entre com sua senha de cliente" /><br/>
            <input type="submit" value="Entrar" />
        </form>
        <a href="novoCliente.jsp">Novo Cliente</a>
    </body>
</html>
