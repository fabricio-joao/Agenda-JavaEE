package com.agenda.modelo.dao;

public class FabricaDAO {

	public static AgendaDAO criarAgendaDAO() {
		return new AgendaDaoJDBC(Conexao.conectar());
	}
}
