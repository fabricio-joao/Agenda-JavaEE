package com.agenda.modelo.dao;

import java.util.List;

import com.agenda.modelo.Agenda;

public interface AgendaDAO {

	void inserir(Agenda obj);

	void mostarContato(Agenda id);

	void deletar(Agenda id);

	void editar(Agenda id);

	List<Agenda> buscarLista();
}
