<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<jsp:include page="header.html"></jsp:include>
	<img src="${ initParam['logo'] }" class="logo mx-auto">
	<h1 class="text-center text-3xl mb-10">Congratulations!</h1>
	<p class="text-center text-xl">Your marks is ${ marks } out of ${ total }</p>
<jsp:include page="footer.html"></jsp:include>