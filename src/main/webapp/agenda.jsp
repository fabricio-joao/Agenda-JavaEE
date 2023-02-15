<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.agenda.modelo.Agenda"%>
<%@ page import="java.util.List"%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="icon" href="img/favicon3.png">
<link rel="stylesheet" href="style.css">
<title>Agenda de Contato</title>
</head>
<%
List<Agenda> lista = (List<Agenda>) request.getAttribute("contatos");
%>
<body>
	<h1>Novo Contato</h1>
	<a href="novocontato.html" class="Botao1">Criar Novo Contato</a>
	<a href="reportar" class="Botao2">Relatorio</a>
	<table id="tabela">
		<thead>
			<tr>
				<th>Id</th>
				<th>Nome</th>
				<th>Fone</th>
				<th>Email</th>
				<th>Opções</th>
			</tr>
		</thead>
		<tbody>
			<%
			for (Agenda a : lista) {
			%>
			<tr>
				<td><%=a.getIdcon()%></td>
				<td><%=a.getNome()%></td>
				<td><%=a.getFone()%></td>
				<td><%=a.getEmail()%></td>
				<td><a href="selecionar?idcon=<%=a.getIdcon()%>" class="Botao1">Editar</a>
					<a href="javascript:deletar(<%=a.getIdcon()%>)" class="Botao2">Deletar</a></td>
			</tr>
			<%}%>
		</tbody>
	</table>
	<script src="scripts/confirmador.js"></script>
</body>
</html>