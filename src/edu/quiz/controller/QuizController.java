package edu.quiz.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import edu.quiz.dao.QuizDataUtils;
import edu.quiz.domain.Quiz;

/**
 * Servlet implementation class QuizController
 */
@WebServlet("/quiz")
public class QuizController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private QuizDataUtils quizDataUtils;
	
	@Resource(name = "jdbc/quiz")
	private DataSource dataSource;
       
	private List<Quiz> quizList = null;
	private RequestDispatcher dispatcher = null;
	private String question, option1, option2, option3, option4;
	private int answer, quizMarks;
	
	@Override
	public void init() throws ServletException {
		quizDataUtils = new QuizDataUtils(dataSource);
	}
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuizController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mode = request.getParameter("mode");
		if(mode == null) {
			mode = "LIST";
		}
		switch(mode) {
		case "UPDATE":
			showSelectedQuiz(request, response);
			break;
		case "DELETE":
			deleteQuiz(request, response);
			break;
		case "LIST":
			try {
				if(request.getParameter("role") == null) {
					quizList= quizDataUtils.getQuiz();
					dispatcher = request.getRequestDispatcher("quiz-form.jsp");
					request.setAttribute("quizList", quizList);
					dispatcher.forward(request, response);
				}else {
					showQuizzes(request, response);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void showQuizzes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		quizList = quizDataUtils.showAllQuizzes();
		dispatcher = request.getRequestDispatcher("show-quiz.jsp");
		request.setAttribute("quizList", quizList);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		switch(type) {
		case "QUESTION":
			question = request.getParameter("question");
			option1 = request.getParameter("option1");
			option2 = request.getParameter("option2");
			option3 = request.getParameter("option3");
			option4 = request.getParameter("option4");
			answer = Integer.parseInt(request.getParameter("answer"));
			quizMarks = Integer.parseInt(request.getParameter("quizMarks"));
			if(request.getParameter("mode").equals("UPDATE")) {
				updateQuiz(request, response);
			}else {
				createQuestion(request, response);
			}
			break;
		default:
			List<String> params = Collections.list(request.getParameterNames());//Get param name
			HashMap<Integer, Integer> answerSheet = new HashMap<Integer, Integer>();
			for (String p: params)
			{
				if(!p.equals("type")) {
					answerSheet.put(Integer.parseInt(p), Integer.parseInt(request.getParameter(p)));
				}
			}
			try {
				int marks = quizDataUtils.checkAnswer(answerSheet);
				int total = quizDataUtils.getTotalMarks();
				dispatcher = request.getRequestDispatcher("result.jsp");
				
				request.setAttribute("marks", marks);
				request.setAttribute("total", total);
				
				dispatcher.forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void createQuestion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			quizDataUtils.createQuiz(new Quiz(question, option1, option2, option3, option4, answer, quizMarks));
			PrintWriter out = response.getWriter();
			out.print("<script>alert('successfully created new quiz!');</script>");
			response.sendRedirect("create-quiz.jsp");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void answerQuiz(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	private void showSelectedQuiz(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int quizId = Integer.parseInt(request.getParameter("quizId"));
		try {
			Quiz quiz = quizDataUtils.getOneQuiz(quizId);
			dispatcher = request.getRequestDispatcher("create-quiz.jsp");
			request.setAttribute("quiz", quiz);
			request.setAttribute("mode", "UPDATE");
			dispatcher.forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void updateQuiz(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int quizId = Integer.parseInt(request.getParameter("quizId"));
		try {
			quizDataUtils.updateQuiz(new Quiz(quizId, question, option1, option2, option3, option4, answer, quizMarks));
			PrintWriter out = response.getWriter();
			out.print("<script>alert('successfully updated!');</script>");
			showQuizzes(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void deleteQuiz(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int quizId = Integer.parseInt(request.getParameter("quizId"));
		try {
			quizDataUtils.deleteQuiz(quizId);
			showQuizzes(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
