<%-- 
    Document   : novoCliente
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
        <hr/>
        <h3>Cadastrar Novo Cliente</h3>
        <form action="NovoCliente" method="post">
            <input type="text" name="nome" placeholder="Entre com seu nome" /><br/>
            <input type="text" name="endereco" placeholder="Entre com seu endereco" /><br/>
            <input type="text" name="email" placeholder="Entre com seu e-mail" /><br/>
            <input type="text" name="login" placeholder="Entre com seu login" /><br/>
            <input type="password" name="senha" placeholder="Entre com sua senha" /><br/>
            <input type="submit" value="Cadastrar" />
        </form>
        <a href="index.jsp">Voltar</a>
    </body>
</html>
