<%@include  file="../header/taglib.jsp" %>
<html>
<head>
<title>Registration</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<%@include  file="../header/head.jsp" %>
</head>
<body>
	 <%@include  file="../header/topBar.jsp" %>
	<!--Sign In Form-->

<div class="container">
        <div class="row centered-form">
      		<div class="panel panel-primary">
        		<div class="panel-heading">
			    		<h3 class="panel-title">Please Sign Up </h3>
			 			</div>
			 			<div class="panel-body">
			    		<form:form role="form" action="${registerUrl}" method="post" modelAttribute="userForm">
			    			<div class="row">
			    				<div class="col-xs-6 col-sm-6 col-md-6">
			    					<div class="form-group" input-length="3">
			               				<form:input path= "firstname" class="form-control input-lg" input-length="3" placeholder="First Name" />
			    					</div>
			    				</div>
			    				<div class="col-xs-6 col-sm-6 col-md-6">
			    					<div class="form-group" input-length="3">
			    						<form:input path= "lastname" class="form-control input-lg" input-length="3" placeholder="Last Name"/>
			    					</div>
			    				</div>
			    			</div>

			    			<div class="form-group" input-length="3">
			    				<form:input type="email" path= "email" class="form-control input-lg" input-length="3" placeholder="Email Address"/>
			    			</div>

			    			<div class="row">
			    				<div class="col-xs-6 col-sm-6 col-md-6">
			    					<div class="form-group" input-length="3">
			    						<form:password path= "pwd" class="form-control input-lg" input-length="6" placeholder="Password"/>
			    					</div>
			    				</div>
			    				<div class="col-xs-6 col-sm-6 col-md-6">
			    					<div class="form-group" input-length="3">
			    						<form:password path= "pwd2" class="form-control input-lg" input-length="6" placeholder="Confirm Password" />
			    					</div> 
			    				</div>
			    			</div>
			    			
			    			<div class="row">
			    				<div class="col-xs-6 col-sm-6 col-md-6">
			    					<div class="form-group" input-length="3">
			    						<form:input path= "country" class="form-control input-lg" placeholder="Country"/>
			    					</div>
			    				</div>
			    				<div class="col-xs-6 col-sm-6 col-md-6">
			    					<div class="form-group" input-length="5">
			    						<form:input path= "zip" class="form-control input-lg" placeholder="ZIP"/>
			    					</div>
			    				</div>
			    			</div>
			    			
			    			<div class="row">
			    				<div class="col-xs-6 col-sm-6 col-md-6">
			    					<div class="form-group" input-length="1">
			    						<form:input path= "state" class="form-control input-lg" placeholder="State"/>
			    					</div>
			    				</div>
			    				<div class="col-xs-6 col-sm-6 col-md-6">
			    					<div class="form-group" input-length="1">
			    						<form:input path= "city" class="form-control input-lg" placeholder="City"/>
			    					</div>
			    				</div>
			    			</div>
			    			
			    			<div class="row">
			    				<div class="col-xs-6 col-sm-6 col-md-6">
			    					<div class="form-group" input-length="3">
			    						<form:input path= "street" class="form-control input-lg" placeholder="Street"/>
			    					</div>
			    				</div>
			    				<div class="col-xs-6 col-sm-6 col-md-6">
			    					<div class="form-group" input-length="1">
			    						<form:input path= "housenumber" class="form-control input-lg" placeholder="House Number"/>
			    					</div>
			    				</div>
			    			</div>
			    			
			    			<input type="submit" value="Register" class="btn btn-lg btn-primary btn-block"/>
			    		</form:form>
			    	</div>
	    		</div>
    		</div>
    	</div>
</body>


</html>