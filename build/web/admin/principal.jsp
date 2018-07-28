<%@page import="br.com.caiqueribeiro.usuario.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Menu Principal</title>
    </head>
    <body>
        <h1>Menu Principal</h1>
        <%

            Usuario usuarioLogado = ((Usuario) request.getAttribute("usuarioAutenticado"));

        %>
        Seja bem-vindo(a) <%= usuarioLogado.getUsuario()%>.
        Seu papel é: <%= usuarioLogado.getPapel()%>.
        <p>Você pode acessar:</p>
        <ul>
            <li><a href="/EstudoArquiteturaWeb/Contatos">Consultar Contatos</a></li>
            <li><a href="/EstudoArquiteturaWeb/admin/inserir.jsp">Inserir Contato</a></li>
        </ul>
    </body>
</html>
