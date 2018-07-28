<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt">
    <head>
        <title>Agenda de Contatos | Controle de Acesso</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="/Agenda/css/login.css" />
    </head>
    <body>

        <div class="conteiner-login">
            <h1>Controle de Acesso</h1>
            <%
                if (null != request.getAttribute("mensagem")) {
                    out.println(request.getAttribute("mensagem"));
                }
            %>
            <form method="POST" action="/EstudoArquiteturaWeb/AutenticarUsuario">
                <input type="text" name="usuario" placeholder="Nome de usuário" />
                <input type="password" name="senha" placeholder="Senha" />
                <input type="checkbox" name="lembrarme" value="true" /> Lembrar-me
                <input type="submit" name="acessar" value=" Acessar "/>
            </form>
            <div class="clearfix"></div>
        </div>
    </body>
</html>