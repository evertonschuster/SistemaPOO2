package br.edu.udc.sistemas.poo2.infra;

import java.sql.Connection;
import java.sql.DriverManager;

// Classe Singleton - Design Patterns
// Garante uma única instância de uma classe em toda aplicação
// 1. Atributo privado estático para guardar a instância
// 2. Contrutor privado
// 3. Método público estático para pegar a instância
public class Database {
	private static Database database = null;

	// JDBC - JAVA DATABASE CONNECTION
	// API - APPLICATION INTERFACE
	// Define interfaces para que os fabricantes de banco de dados
	// implementem as bibliotecas para o java conversar

	private Connection connection;
	private String host;
	private String port;
	private String userName;
	private String password;
	private String databaseName;
	private String driverClassName;

	public static Database getInstance() throws Exception {
		if (Database.database == null) {
			Database.database = new Database();
		}
		return Database.database;
	}

	private Database() throws Exception {
		this.driverClassName = "org.postgresql.Driver";
		this.userName = "postgres";
		this.password = "postgres";
		this.host = "localhost";
		this.port = "5432";
		this.databaseName = "sistemapoo2";
		this.connect();
	}

	private void connect() throws Exception {
		// import dinâmico
		Class.forName(this.driverClassName);
		String url = "jdbc:postgresql://" + this.host + ":" + this.port + "/" + this.databaseName;
		this.connection = DriverManager.getConnection(url, this.userName, this.password);
		this.connection.setAutoCommit(false);
	}

	public Connection getConnection() {
		return this.connection;
	}

}