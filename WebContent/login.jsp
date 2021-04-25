<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<jsp:include page="header.html"></jsp:include>
	<form id="login" action="account" method="post">
		
		<div class="grid grid-cols-2 w-1/2 h-1/2 bg-white space-y-6 p-5 mx-auto mt-20 items-center rounded-lg">
			<img src="${ initParam['logo'] }" class="col-span-2 logo pl-3">
			<input type="hidden" name="role" value="${ param.role }">
			<input type="hidden" name="mode" value="login">
			<label class="col-span-2 text-center text-2xl">Welcome ${ param.role }</label>
			<label>Name:</label>
			<input type="text" name="name" required="required">
			<label>Password:</label>
			<input type="password" name="password" required="required">
			<% if(request.getAttribute("message") != null) {
				out.print("<label class='text-red-600 col-span-2 text-center'>"+request.getAttribute("message")+"</label>");
			}
		%>
			<input type="submit" class="btn bg-blue-500 hover:bg-blue-600" value="Login">
			<input type="button" class="btn bg-red-500 hover:bg-red-600" value="Cancel" onclick="document.getElementById('login').reset()">
			<a href="signup.jsp?role=${ param.role }" class="underline">Create a new account</a>
			<a href="login.jsp?role=${ param.role == 'student' ? 'teacher' : 'student' }" class="underline">Log in as ${ param.role == 'student' ? 'teacher' : 'student' }</a>
		</div>
	</form>
<jsp:include page="footer.html"></jsp:include>