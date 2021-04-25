<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<jsp:include page="header.html"></jsp:include>
	<img src="${ initParam['logo'] }" class="mx-auto my-10">
	<h1 class="text-3xl text-white text-center">Welcome to Quiz</h1>
	<div class="role flex justify-around w-1/2">
		<input type="button" value="Teacher" class="btn w-40 bg-green-300 hover:bg-green-400" onclick="window.location.href='login.jsp?role=teacher'">
		<input type="button" value="Student" class="btn w-40 bg-green-300 hover:bg-green-400" onclick="window.location.href='login.jsp?role=student'">
	</div>
<jsp:include page="footer.html"></jsp:include>