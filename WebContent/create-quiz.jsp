<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="header.html"></jsp:include>
<form id="createQuiz" action="quiz" method="POST">
	<input type="hidden" name="type" value="QUESTION">
	<input type="hidden" name="mode" value="${ mode }">
	<input type="hidden" name="quizId" value="${ quiz.quizId }">
	<img src="${ initParam['logo'] }" class="logo mx-auto mt-6">
	<div class="grid grid-cols-2 w-1/2 h-1/2 bg-white space-y-6 p-5 mx-auto my-20 items-center rounded-lg">
		<label>Question:</label>
		<textarea name="question" required>${ quiz.question }</textarea>
		<label>Option1:</label>
		<textarea name="option1"required>${ quiz.option1 }</textarea>
		<label>Option2:</label>
		<textarea name="option2" required>${ quiz.option2 }</textarea>
		<label>Option3:</label>
		<textarea name="option3" required>${ quiz.option3 }</textarea>
		<label>Option4:</label>
		<textarea name="option4" required>${ quiz.option4 }</textarea>
		<label>Correct answer:</label>
		<select name="answer">
			<option value="1" ${ quiz.answer == '1' ? 'selected': '' }>1</option>
			<option value="2" ${ quiz.answer == '2' ? 'selected': '' }>2</option>
			<option value="3" ${ quiz.answer == '3' ? 'selected': '' }>3</option>
			<option value="4" ${ quiz.answer == '4' ? 'selected': '' }>4</option>
		</select>
		<label>Marks:</label>
		<input type="number" name="quizMarks" value="${ quiz.quizMarks }" required>
		<input type="submit" class="btn bg-blue-500 hover:bg-blue-900" value="${ mode == 'UPDATE' ? mode : 'Create' }">
		<input type="button" class="btn bg-red-500 hover:bg-red-900" value="Cancel" onclick="document.getElementById('createQuiz').reset()">
		<a href="quiz?role=teacher" class="underline">Show All Quiz</a>
	</div>
</form>
<jsp:include page="footer.html"></jsp:include>