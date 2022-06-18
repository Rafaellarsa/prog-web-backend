<%-- 
    Document   : novaCategoria
    Created on : 17/06/2022, 23:40:04
    Author     : ivalm
--%>

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
         <h3>Cadastrar Novo Cliente</h3>
         <form action="Categoria" method="post">
             <input type="text" name="descricaoCategoria" placeholder="Entre com o nome da categoria" /><br/>
             <input type="submit" value="Cadastrar" />
         </form>
         <a href="index.jsp">Voltar</a>
     </body>
</html>
