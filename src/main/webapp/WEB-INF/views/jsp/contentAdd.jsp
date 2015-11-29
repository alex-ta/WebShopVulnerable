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
	        				Add your data
	        			</c:otherwise>
	       			</c:choose>
	       			</div>
			 		<div class="panel-body">
			    		<form:form role="form" action="${pageContext.request.contextPath}${targetURL}" method="post" modelAttribute="form">
			    			<c:forEach items="${contentMap}" var="map">
								<spring:bind path="${map.key}">
				    					<form:errors path ="${map.key.toLowerCase()}" />
					    				<c:choose>
									    	<c:when test="${not empty contentType && contentType.containsKey(map.key)}">
								      			<c:choose >
											    	<c:when  test="${contentType.get(map.key) == 'textarea'}">
											       		<%@include  file="../inputIncludes/textarea.jsp" %>										  		
											  		</c:when>
											  		<c:when  test="${contentType.get(map.key) == 'object'}">	      	
														<%@include  file="../inputIncludes/object.jsp" %>							    			
											  		</c:when>
											  		<c:when  test="${contentType.get(map.key) == 'objects'}">	      	
														<%@include  file="../inputIncludes/objects.jsp" %>							    			
											  		</c:when>
											  		<c:when  test="${contentType.get(map.key) == 'checkbox'}">
											        	<%@include  file="../inputIncludes/checkbox.jsp" %>	
											  		</c:when>
											  		<c:when  test="${contentType.get(map.key) == 'address'}">
									       				<%@include  file="../inputIncludes/address.jsp" %>
									       			</c:when>       
											    	<c:otherwise>
														<%@include  file="../inputIncludes/defaultType.jsp" %>
											    	</c:otherwise>
						    					</c:choose>
										    </c:when>    
										    <c:otherwise>
										      	<%@include  file="../inputIncludes/text.jsp" %>
						    				</c:otherwise>
										</c:choose>
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
							</c:choose></form:form>
			    	</div>
	    		</div>
    		</div>
    	</div>
	</c:if>
</body>
</html>
