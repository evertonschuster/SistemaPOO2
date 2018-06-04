package br.edu.udc.sistemas.poo2.infra;

import java.sql.Connection;
import java.sql.DriverManager;

// Classe Singleton - Design Patterns
// Garante uma �nica inst�ncia de uma classe em toda aplica��o
// 1. Atributo privado est�tico para guardar a inst�ncia
// 2. Contrutor privado
// 3. M�todo p�blico est�tico para pegar a inst�ncia
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
		this.password = "123";
		this.host = "localhost";
		this.port = "5432";
		this.databaseName = "sistemapoo2";
		this.connect();
	}

	private void connect() throws Exception {
		// import din�mico
		Class.forName(this.driverClassName);
		String url = "jdbc:postgresql://" + this.host + ":" + this.port + "/" + this.databaseName;
		this.connection = DriverManager.getConnection(url, this.userName, this.password);
		this.connection.setAutoCommit(false);
	}

	public Connection getConnection() {
		return this.connection;
	}

}