<%@include  file="../header/taglib.jsp" %>
<html>
<head>
	<%@include  file="../header/head.jsp" %>
	<c:choose>
		<c:when test="${not empty content}">
			<script type="text/javascript">
				var dataBase = ${content};
	 		 	$(document).ready(function(){
	  			new Table();
	  			C2CTable.addRows(dataBase);
	  			C2CTable.createData();
	  			});
			</script>
	 	</c:when>
	</c:choose>
</head>
<body>
	<%@include  file="../header/topBar.jsp" %>
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
		    			<div id="C2C">No Products availible</div>
			    	</div>
	    		</div>
    		</div>
    	</div>
</body>
</html>
