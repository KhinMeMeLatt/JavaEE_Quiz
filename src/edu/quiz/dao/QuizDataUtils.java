package edu.quiz.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import edu.quiz.domain.Quiz;

public class QuizDataUtils {

	private DataSource dataSource;
	
	private Connection connection;
	
	private Statement statement;
	
	private PreparedStatement preparedStatement;
	
	private ResultSet resultSet;

	public QuizDataUtils(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public void createQuiz(Quiz quiz) throws SQLException {
		connection = dataSource.getConnection();
		
		preparedStatement = connection.prepareStatement("INSERT INTO QUIZ "+"(question, option1, option2, option3, option4, answer, quizMarks)"
				+" VALUES(?, ?, ?, ?, ?, ?, ?)");
		
		preparedStatement.setString(1, quiz.getQuestion());
		preparedStatement.setString(2, quiz.getOption1());
		preparedStatement.setString(3, quiz.getOption2());
		preparedStatement.setString(4, quiz.getOption3());
		preparedStatement.setString(5, quiz.getOption4());
		preparedStatement.setInt(6, quiz.getAnswer());
		preparedStatement.setInt(7, quiz.getQuizMarks());
		
		preparedStatement.execute();
		
		connection.close();
	}
	
	public List<Quiz> getQuiz() throws SQLException {
		List<Quiz> quizList = new ArrayList<Quiz>();
		
		connection = dataSource.getConnection();
		
		statement = connection.createStatement();
		
		resultSet = statement.executeQuery("select * from quiz order by rand()");
		
		while(resultSet.next()) {
			quizList.add(new Quiz(resultSet.getInt("quizId"),
					resultSet.getString("question"),
					resultSet.getString("option1"),
					resultSet.getString("option2"),
					resultSet.getString("option3"),
					resultSet.getString("option4"),
					resultSet.getInt("answer"),
					resultSet.getInt("quizMarks")
					));
		}
		
		connection.close();
		
		return quizList;
	}
	
	public Quiz getOneQuiz(int quizId) throws SQLException {
		Quiz quiz = null;
		
		connection = dataSource.getConnection();
		
		statement = connection.createStatement();
		
		resultSet = statement.executeQuery("select * from quiz where quizId = "+quizId);
		
		while(resultSet.next()) {
			quiz = new Quiz(resultSet.getInt("quizId"),
					resultSet.getString("question"), 
					resultSet.getString("option1"), 
					resultSet.getString("option2"), 
					resultSet.getString("option3"), 
					resultSet.getString("option4"), 
					resultSet.getInt("answer"), 
					resultSet.getInt("quizMarks"));
		}
		
		connection.close();
		
		return quiz;
	}
	
	public List<Quiz> showAllQuizzes() throws SQLException {
		List<Quiz> quizList = new ArrayList<Quiz>();
		
		connection = dataSource.getConnection();
		
		statement = connection.createStatement();
		
		resultSet = statement.executeQuery("select * from quiz");
		
		while(resultSet.next()) {
			quizList.add(new Quiz(resultSet.getInt("quizId"),
					resultSet.getString("question"),
					resultSet.getString("option1"),
					resultSet.getString("option2"),
					resultSet.getString("option3"),
					resultSet.getString("option4"),
					resultSet.getInt("answer"),
					resultSet.getInt("quizMarks")
					));
		}
		
		connection.close();
		
		return quizList;
	}
	
	public void updateQuiz(Quiz quiz) throws SQLException {
		connection = dataSource.getConnection();
		
		preparedStatement = connection.prepareStatement("UPDATE quiz SET "
							+"question = ?,"
							+"option1 = ?,"
							+"option2 = ?,"
							+"option3 = ?,"
							+"option4 = ?,"
							+"answer = ?,"
							+"quizMarks = ? WHERE quizId = ?");
		preparedStatement.setString(1, quiz.getQuestion());
		preparedStatement.setString(2, quiz.getOption1());
		preparedStatement.setString(3, quiz.getOption2());
		preparedStatement.setString(4, quiz.getOption3());
		preparedStatement.setString(5, quiz.getOption4());
		preparedStatement.setInt(6, quiz.getAnswer());
		preparedStatement.setInt(7, quiz.getQuizMarks());
		preparedStatement.setInt(8, quiz.getQuizId());
		
		preparedStatement.execute();
		connection.close();
	}
	
	public void deleteQuiz(int quizId) throws SQLException {
		connection = dataSource.getConnection();
		
		preparedStatement = connection.prepareStatement("delete from quiz where quizId = ?");
		preparedStatement.setInt(1, quizId);
		
		preparedStatement.execute();
		connection.close();
	}
	
	public int checkAnswer(HashMap<Integer, Integer> answerSheet) throws SQLException {
		connection = dataSource.getConnection();
		
		statement = connection.createStatement();
		
		resultSet = statement.executeQuery("select * from quiz");
		
		int marks = 0;
		
		while(resultSet.next()) {
			if(answerSheet.containsKey(resultSet.getInt("quizId"))) {
				if(answerSheet.get(resultSet.getInt("quizId")) == resultSet.getInt("answer")) {
					marks += resultSet.getInt("quizMarks");
				}
			}
		}
		
		connection.close();
		
		return marks;
	}
	
	public int getTotalMarks() throws SQLException {
		connection = dataSource.getConnection();
		
		statement = connection.createStatement();
		
		resultSet = statement.executeQuery("select sum(quizMarks) as total from quiz");
		
		resultSet.next();
		
		return resultSet.getInt("total");
	}
}
