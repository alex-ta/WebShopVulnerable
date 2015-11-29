<c:choose>
	<c:when test="${not empty contentName && contentName.containsValue(map.key)}">	
		<div class="row">
			<div>
				<div class="form-group">
     				<form:input  path ="${map.key.toLowerCase()}" name ="${map.key.toLowerCase()}" placeholder = "${contentName.get(map.key)}" class="rule-padding form-control"/>
  				</div>
  			</div>
		</div>
  	</c:when>    
    <c:otherwise>
		<div class="row">
			<div>
				<div class="form-group">    		
    				<form:input  path ="${map.key.toLowerCase()}" name ="${map.key.toLowerCase()}" placeholder = "${map.key}" class="rule-padding form-control"/>
    			</div>
   			</div>
		</div>
    </c:otherwise>
</c:choose>