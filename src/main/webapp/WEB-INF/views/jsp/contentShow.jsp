<%@include  file="../header/taglib.jsp" %>
<html>
<head>
	<%@include  file="../header/head.jsp" %>
</head>
<body>
	<%@include  file="../header/topBar.jsp" %>
	<c:if test="${not empty content}">
	<div class="container">
        <div class="row centered-form">
      		<div class="panel panel-primary">
        		<div class="panel-heading">
        			<c:choose>
        			<c:when test="${not empty contentHeader}">
        				${contentHeader }
        			</c:when>
        			<c:otherwise>
        				
        			</c:otherwise>
        			</c:choose>
			    </div>
			 		<!-- check js for autocolumn -->
			 		<div class="panel-body">
		    			${content}
			    	</div>
	    		</div>
    		</div>
    	</div>
	</c:if>
</body>
</html>
