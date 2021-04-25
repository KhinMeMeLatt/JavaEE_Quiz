package edu.quiz.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import edu.quiz.domain.Account;

public class AccountDataUtils {

	private DataSource dataSource;
	
	private Connection connection;
	
	private PreparedStatement preparedStatement;
	
	private ResultSet resultSet;

	public AccountDataUtils(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public boolean login(Account account) throws SQLException {
		boolean isValid = false;
		connection = dataSource.getConnection();
		
		preparedStatement = connection.prepareStatement("SELECT * FROM "+account.getRole()+" WHERE name=? AND password=?");
		preparedStatement.setString(1, account.getName());
		preparedStatement.setString(2, account.getPassword());

		resultSet = preparedStatement.executeQuery();
		while(resultSet.next()) {
			isValid= true;
		}
		
		connection.close();
		
		return isValid;
	}
	
	public void signUp(Account account) throws SQLException {
		connection = dataSource.getConnection();
		
		preparedStatement = connection.prepareStatement("INSERT INTO "+account.getRole()+"(name, password, email)"
				+" VALUES(?, ?, ?)");
		preparedStatement.setString(1, account.getName());
		preparedStatement.setString(2, account.getPassword());
		preparedStatement.setString(3, account.getEmail());
		
		preparedStatement.execute();
		
		connection.close();
	}
}
