<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header.html"></jsp:include>
<input type="button" value="Create New Quiz" class="bg-green-300 p-3 m-3 hover:bg-green-400" onclick="window.location.href='create-quiz.jsp'">
<table class="border-separate border border-green-800 m-3 bg-white">
	<thead>
		<tr>
			<th class="border border-green-600">Question</th>
			<th class="border border-green-600">Option1</th>
			<th class="border border-green-600">Option2</th>
			<th class="border border-green-600">Option3</th>
			<th class="border border-green-600">Option4</th>
			<th class="border border-green-600">Correct Answer</th>
			<th class="border border-green-600">Mark(s)</th>
			<th colspan="2" class="border border-green-600">Action</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="q" items="${ quizList }">
			<c:url var="updateQuiz" value="quiz">
				<c:param name="mode" value="UPDATE"></c:param>
				<c:param name="quizId" value="${ q.quizId }"></c:param>
			</c:url>
			<c:url var="deleteQuiz" value="quiz">
				<c:param name="mode" value="DELETE"></c:param>
				<c:param name="quizId" value="${ q.quizId }"></c:param>
			</c:url>
			<tr>
				<td class="border border-green-600">${ q.question }</td>
				<td class="border border-green-600">${ q.option1 }</td>
				<td class="border border-green-600">${ q.option2 }</td>
				<td class="border border-green-600">${ q.option3 }</td>
				<td class="border border-green-600">${ q.option4 }</td>
				<td class="border border-green-600">${ q.answer }</td>
				<td class="border border-green-600">${ q.quizMarks }</td>
				<td class="border border-green-600"><a href="${ updateQuiz }">Update</a></td>
				<td class="border border-green-600"><a href="${ deleteQuiz }">Delete</a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<jsp:include page="footer.html"></jsp:include>