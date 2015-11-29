<div class="row">
	<div>
		<div class="form-group">
        	<c:if test="${not empty contentObjectList && not empty contentObjectList.get(map.key) }">
        		<c:choose>
        			<c:when test="${not empty contentName && contentName.containsKey(map.key)}">
        				<label for='${map.key.toLowerCase()}' style='font-weight: 200; float: left; width:100%; text-align:center;' >${contentName.get(map.key)}
        					<form:select multiple="true" path ="${map.key.toLowerCase()}" name ="${map.key.toLowerCase()}" placeholder = "${contentName.get(map.key)}" class="rule-padding form-control">
	        					<c:forEach items ="${contentObjectList.get(map.key)}" var="obj">
	        						<form:option value="${obj.key}">${obj.value}</form:option>
	  							</c:forEach>
  							</form:select>
  						</label>
        			</c:when>
        			<c:otherwise>
        				<label for='${map.key.toLowerCase()}' style='font-weight: 200; float: left; width:100%; text-align:center;' >${map.key}
        					<form:select multiple="true" path ="${map.key.toLowerCase()}" name ="${map.key.toLowerCase()}" placeholder = "${contentName.get(map.key)}" class="rule-padding form-control">
	        					<c:forEach items ="${contentObjectList.get(map.key)}" var="obj">
	        						<form:option value="${obj.key}">${obj.value}</form:option>
	  							</c:forEach>
  							</form:select>
  						</label>
        			</c:otherwise>
        		</c:choose>
  			</c:if>
  		</div>
  	</div>
</div>