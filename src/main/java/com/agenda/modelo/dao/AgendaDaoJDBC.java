package com.agenda.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.agenda.modelo.Agenda;

public class AgendaDaoJDBC implements AgendaDAO {

	private Connection conexao;

	public AgendaDaoJDBC(Connection conexao) {
		this.conexao = conexao;
	}

	@Override
	public void inserir(Agenda obj) {
		PreparedStatement ps = null;
		String inserir = "INSERT INTO contatos (nome, fone, email) VALUES (?, ?, ?)";
		try {
			ps = conexao.prepareStatement(inserir);
			ps.setString(1, obj.getNome());
			ps.setString(2, obj.getFone());
			ps.setString(3, obj.getEmail());

			int cadastrado = ps.executeUpdate();
			System.out.println("Contato cadastrado: " + cadastrado);

		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			Conexao.fecharConexaoStatement(ps);
		}
	}

	@Override
	public void deletar(Agenda contato) {

		PreparedStatement ps = null;
		String deletar = "DELETE FROM contatos WHERE id=?";
		try {
			ps = conexao.prepareStatement(deletar);
			ps.setString(1, contato.getIdcon());
			int deletado = ps.executeUpdate();
			System.out.println("Contato deletado: " + deletado);
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			Conexao.fecharConexaoStatement(ps);
		}
	}

	@Override
	public List<Agenda> buscarLista() {

		PreparedStatement ps = null;
		ResultSet rs = null;
		String listar = "SELECT * FROM contatos ORDER BY nome";
		List<Agenda> lista = new ArrayList<>();

		try {
			ps = conexao.prepareStatement(listar);
			rs = ps.executeQuery();

			while (rs.next()) {
				Agenda contatos = new Agenda();
				contatos.setIdcon(rs.getString("idcon"));
				contatos.setNome(rs.getString("nome"));
				contatos.setFone(rs.getString("fone"));
				contatos.setEmail(rs.getString("email"));
				lista.add(contatos);
			}

		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			Conexao.fecharConexaoStatement(ps);
			Conexao.fecharConexaoResultSet(rs);

		}
		return lista;
	}

	@Override
	public void mostarContato(Agenda contato) {

		PreparedStatement ps = null;
		ResultSet rs = null;
		String exibir = "SELECT * FROM contatos WHERE id=?";
		try {
			ps = conexao.prepareStatement(exibir);
			ps.setString(1, contato.getIdcon());
			rs = ps.executeQuery();
			while (rs.next()) {
				contato.setIdcon(rs.getString("idcon"));
				contato.setNome(rs.getString("nome"));
				contato.setFone(rs.getString("fone"));
				contato.setEmail(rs.getString("email"));
			}
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			Conexao.fecharConexaoStatement(ps);
			Conexao.fecharConexaoResultSet(rs);

		}
	}

	@Override
	public void editar(Agenda contato) {
		PreparedStatement ps = null;
		String atualizar = "UPDATE contatos SET nome=?, fone=?, email=? WHERE idcon=? ";
		try {
			ps = conexao.prepareStatement(atualizar);
			ps.setString(1, contato.getNome());
			ps.setString(2, contato.getFone());
			ps.setString(3, contato.getEmail());
			ps.setString(4, contato.getIdcon());

			int atualizado = ps.executeUpdate();
			System.out.println("Contato atualizado: " + atualizado);
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			Conexao.fecharConexaoStatement(ps);
		}
	}
}
