<%@include  file="../header/taglib.jsp" %>
<html>
<head>
	<%@include  file="../header/head.jsp" %>
</head>
<body>
	<%@include  file="../header/topBar.jsp" %>
	<c:if test="${not empty contentMap && not empty targetURL}">
	<div class="container">
        <div class="row centered-form">
      		<div class="panel panel-primary col-xs-13 col-sm-13 col-md-13">
        		<div class="panel-heading">
        			<c:choose>
	        			<c:when test="${not empty contentHeader}">
	        				${contentHeader }
	        			</c:when>
	        			<c:otherwise>
	        				Edit your data
	        			</c:otherwise>
	       			</c:choose>
        			</div>
			 		<div class="panel-body">
			    		<form:form role="form" action="${pageContext.request.contextPath}${targetURL}" method="post" modelAttribute="form">
			    			<c:forEach items="${contentMap}" var="map">
								<spring:bind path="${map.key}">
					    			<div class="row">
				    	   				<div class="rule-padding big">
					    					<div class="form-group">	
							    				<c:choose>
												    <c:when test="${not empty contentName && contentName.containsKey(map.key)}">
												        ${contentName.get(map.key)}
												    </c:when>    
												    <c:otherwise>
												    	${map.key}
												    </c:otherwise>
												</c:choose>
							    			</div>
							    		</div>
							    		<div class="rule-padding big">
					    					<div class="form-group">	
							    				<c:choose>
												    <c:when test="${not empty contentType && contentType.containsKey(map.key)}">
												      <form:input type = "${contentType.get(map.key)}"  path ="${map.key.toLowerCase()}" name ="${map.key.toLowerCase()}" placeholder = "${map.value}" class="form-control rule-padding"/>	
												    </c:when>    
												    <c:otherwise>
												      <form:input path ="${map.key.toLowerCase()}" name ="${map.key.toLowerCase()}" placeholder = "${map.value}" class="form-control rule-padding"/>
												    </c:otherwise>
												</c:choose>
											</div>
										</div>
									</div>	
					    		</spring:bind>
							</c:forEach>
							<br />
							<c:choose>
								<c:when test="${ not empty contentSubmit }">
								<input type="submit" value="${contentSubmit}" class="btn btn-lg btn-primary btn-block"/>
								</c:when>
								<c:otherwise>
								<input type="submit" value="Edit" class="btn btn-lg btn-primary btn-block"/>
								</c:otherwise>
							</c:choose>
			    		</form:form>
			    	</div>
	    		</div>
    		</div>
    	</div>
	</c:if>
</body>
</html>
