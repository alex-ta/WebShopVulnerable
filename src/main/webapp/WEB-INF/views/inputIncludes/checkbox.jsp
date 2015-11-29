<c:choose>
	<c:when test="${not empty contentName && contentName.containsKey(map.key)}">
		<div class="row">
			<div>
				<div class="form-group">
     			<label for='${map.key.toLowerCase()}' style='font-weight: 200; float: left; margin-top: 4px; margin-left: 4px;' >${contentName.get(map.key)}</label><form:input type = "${contentType.get(map.key)}"  path ="${map.key.toLowerCase()}" name ="${map.key.toLowerCase()}" placeholder = "" class="rule-padding form-control"/>
  				</div>
  			</div>
		</div>
	</c:when>    
    <c:otherwise>
		<div class="row">
			<div>
				<div class="form-group">
    				<label for='${map.key.toLowerCase()}' style='font-weight: 200; float: left; margin-top: 4px; margin-left: 4px;' >${map.key}</label><form:checkbox  path ="${map.key.toLowerCase()}" name ="${map.key.toLowerCase()}" placeholder = "" class="rule-padding form-control"/>
    			</div>
    		</div>
		</div>
    </c:otherwise>
</c:choose>	