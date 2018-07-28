<%@page import="java.util.List"%>
<%@page import="br.com.caiqueribeiro.contato.Contato"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset='UTF-8' />
        <title>Contatos</title>
    </head>
    <body>
        <h1>Contatos</h1>
        <a href='/EstudoArquiteturaWeb/admin/inserir.jsp'>Inserir</a>
        <form action='ExcluirContato'>
            <input type='submit' name='excluir' value=' Excluir ' />
            <table border='1'>
                <thead>
                    <tr>
                        <th></th><th>Nome</th><th>Telefone</th><th>E-mail</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        List<Contato> contatos = (List<Contato>) request.getAttribute("contatos"); // Object.
                        if (null != contatos) {
                            for (Contato contato : contatos) {
                                out.println("<tr>");
                                out.println("<td><input type='checkbox' name='id' value='" + contato.getId() + "' /></td>");
                                out.println("<td><a href='PreAtualizarContato?id=" + contato.getId() + "'>" + contato.getNome() + "</a></td>");
                                out.println("<td>" + contato.getTelefone() + "</td>");
                                out.println("<td>" + contato.getEmail() + "</td>");
                                out.println("</tr>");
                            }
                        } else {
                    %>
                    <tr><td colspan="4"><a href="/EstudoArquiteturaWeb/Contatos">Clique aqui para ver os dados.</a></td></tr>
                    <%
                        } // Fim dor if(null != contatos)
                    %>
                </tbody>
            </table>
        </form>
    </body>
</html>