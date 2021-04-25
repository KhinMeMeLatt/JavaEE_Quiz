<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header.html"></jsp:include>

<form action="quiz" method="POST">
	<input type="hidden" name="type" value="ANSWER">
	<img src="${ initParam['logo'] }" class="logo mx-auto">
	<c:forEach var="quiz" items="${ quizList }">
		<div class="w-1/2 h-1/2 bg-white mt-10 mx-auto items-center rounded-lg p-5">
			<h2 class="text-bold">${ quiz.question }</h2>
			<div class="flex items-center">
				<input type="radio" name="${ quiz.quizId }" value="1"><span class="pl-5">${ quiz.option1 }</span>
			</div>
			<div class="flex items-center">
				<input type="radio" name="${ quiz.quizId }" value="2"><span class="pl-5">${ quiz.option2 }</span>
			</div>
			<div class="flex items-center">
				<input type="radio" name="${ quiz.quizId }" value="3"><span class="pl-5">${ quiz.option3 }</span>
			</div>
			<div class="flex items-center">
				<input type="radio" name="${ quiz.quizId }" value="4"><span class="pl-5">${ quiz.option4 }</span>
			</div>
		</div>	
	</c:forEach>
	<div class="flex justify-center">
		<input type="submit" class="btn bg-blue-500 my-10" value="Submit all answers">
	</div>
</form>
<jsp:include page="footer.html"></jsp:include>