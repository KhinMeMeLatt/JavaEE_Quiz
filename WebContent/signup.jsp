<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<jsp:include page="header.html"></jsp:include>
	<form id="signUp" action="account" method="post">
		<input type="hidden" name="role" value="${ param.role }">
		<input type="hidden" name="mode" value="signup">
		<div class="grid grid-cols-2 w-1/2 h-1/2 bg-white space-y-6 p-5 mx-auto mt-24 items-center rounded-lg">
			<img src="${ initParam['logo'] }" class="col-span-2 logo pl-3">
			<label>Name:</label>
			<input type="text" name="name" required="required">
			<label>Email:</label>
			<input type="email" name="email" required="required">
			<label>Password:</label>
			<input type="password" name="password" required="required">
			<input type="submit" class="btn bg-blue-500" value="Sign Up">
			<input type="button" class="btn bg-red-500" value="Cancel" onclick="document.getElementById('signUp').reset()">
			<a href="login.jsp?role=${ param.role }" class="underline">Log in an existing account</a>
			<a href="signup.jsp?role=${ param.role == 'student' ? 'teacher' : 'student' }" class="underline">Sign up as ${ param.role == 'student' ? 'teacher' : 'student' }</a>
		</div>
	</form>
<jsp:include page="footer.html"></jsp:include>