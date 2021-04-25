package edu.quiz.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import edu.quiz.dao.AccountDataUtils;
import edu.quiz.domain.Account;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/account")
public class AccountController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private AccountDataUtils accountDataUtils;
	
	@Resource(name = "jdbc/quiz")
	private DataSource dataSource;
	
	private String name, password, mode, role;
	
	@Override
	public void init() throws ServletException {
		accountDataUtils = new AccountDataUtils(dataSource);
	}
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.name = request.getParameter("name");
		this.password = request.getParameter("password");
		this.mode = request.getParameter("mode");
		this.role = request.getParameter("role");
		
		try {
			if(this.mode.equals("login")) {
				if(accountDataUtils.login(new Account(name, password, role))) {
					if(role.equals("teacher")) {
						response.sendRedirect("create-quiz.jsp");
					}else {
						response.sendRedirect("quiz");
					}
					
				}else {
					RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
					request.setAttribute("message", "User name and password do not match!");
					dispatcher.forward(request, response);
				}
			} else {
				signUp(request, response);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void signUp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		String email = request.getParameter("email");
		
		accountDataUtils.signUp(new Account(name, password, email, role));
		response.sendRedirect("login.jsp?role="+role);
	}

}
