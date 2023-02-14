package com.agenda.modelo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexao {
	private static String driver = "com.mysql.cj.jdbc.Driver";
	private static String url = "jdbc:mysql://localhost:3306/dbagenda";
	private static String user = "novo";
	private static String password = "dF7* Pm *2r";

	private static Connection conexao = null;

	public static Connection conectar() {
		if (conexao == null) {
			try {
				Class.forName(driver);
				conexao = DriverManager.getConnection(url, user, password);
				System.out.println("Conectado com sucesso");
			} catch (SQLException e) {
				System.out.println(e);
			} catch (ClassNotFoundException e) {
				System.out.println("nao conexao");
			}
		}
		
		return conexao;
	}

	public static void fecharConexao() {
		if(conexao != null) {
			try {
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void fecharConexaoStatement(Statement st) {
		if(st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void fecharConexaoResultSet(ResultSet rs) {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
}
