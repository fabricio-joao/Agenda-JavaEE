package com.agenda.modelo;

public class Agenda {
	
	private String Idcon;
	private String nome;
	private String fone;
	private String email;
	
	public Agenda() {

	}

	public Agenda(String idcon, String nome, String fone, String email) {
		
		Idcon = idcon;
		this.nome = nome;
		this.fone = fone;
		this.email = email;
	}

	public String getIdcon() {
		return Idcon;
	}

	public void setIdcon(String idcon) {
		Idcon = idcon;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getFone() {
		return fone;
	}

	public void setFone(String fone) {
		this.fone = fone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Agenda [Idcon=" + Idcon + ", nome=" + nome + ", fone=" + fone + ", email=" + email + "]";
	}	
}
