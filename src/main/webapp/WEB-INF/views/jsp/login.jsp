<%@include  file="../header/taglib.jsp" %>
<html>
<head>
<style>
.error {
	padding: 15px;
	margin-bottom: 20px;
	border: 1px solid transparent;
	border-radius: 4px;
	color: #a94442;
	background-color: #f2dede;
	border-color: #ebccd1;
}

.msg {
	padding: 15px;
	margin-bottom: 20px;
	border: 1px solid transparent;
	border-radius: 4px;
	color: #31708f;
	background-color: #d9edf7;
	border-color: #bce8f1;
}

</style>
	<%@include  file="../header/head.jsp" %>
</head>
<body onload='document.loginForm.username.focus();'>
	 <%@include  file="../header/topBar.jsp" %>
		<!--Login-->
		<div class="container">
	      	<form class="form-signin" name='loginForm'
		  action="<c:url value='/signin' />" method='POST'>
		<sec:csrfInput />	        
		<h2 class="form-signin-heading">Please sign in</h2>
	        <label for="inputEmail" class="sr-only">Email address</label>
	        <input id="inputEmail" class="form-control" placeholder="Email address" required="" autofocus="" type="text" name="username">
	        <label for="inputPassword" class="sr-only">Password</label>
	        <input id="inputPassword" class="form-control" placeholder="Password" required="" type="password" name="password">
	        <div class="checkbox">
	          <label>
	            <input value="remember-me" type="checkbox"> Remember me
	          </label>
	        </div>
	        <input class="btn btn-lg btn-primary btn-block" type="submit" value="Sign in"/>
		<c:if test="${not empty error}">
			<div class="error">${error}</div>
		</c:if>
		
		<c:if test="${not empty msg}">
			<div class="msg">${msg}</div>
		</c:if>
		
	    </div>

</body>