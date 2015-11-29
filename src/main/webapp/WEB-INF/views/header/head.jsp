<spring:url value="/resources/dist/css/bootstrap.min.css" var="bootstrapCss" />
<spring:url value="/resources/dist/css/bootstrap-theme.min.css" var="bootstrapThemeCss" />
<spring:url value="/resources/dist/js/bootstrap.min.js" var="bootstrapJs" />
<!-- 
 
//<spring:url value="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" var="jQuery" />

-->

<spring:url value="/resources/libs/jQuery.js" var="jQuery"/>

<spring:url value="/logout" var="logoutUrl" />
<spring:url value="/login" var="loginUrl" />
<spring:url value="/register" var="registerUrl" />
<spring:url value="/help" var="helpUrl" />
<spring:url value="/home" var="homeUrl" />
<spring:url value="/" var="indexUrl" />
<spring:url value="/dashboard" var="dashboardUrl" />
<spring:url value="/settings" var="settingsUrl" />
<spring:url value="/profil" var="profilUrl" />
<spring:url value="/" var="baseUrl" />

<script type="text/javascript" src="${jQuery}"></script>
<script type="text/javascript" src="${bootstrapJs}"></script>
<c:choose>
	<c:when test="${not empty ajaxUrl}">
	  <meta name="token" content="${_csrf.token}"/>
	  <meta name="tokenheader" content="${_csrf.headerName}"/>
	  <script language="javascript" type="text/javascript">
	    hostUrl = window.location.host;
	    baseType = '${baseType}';
	    baseUrl = '${baseUrl}';
	    typeUrl = '${typeUrl}';
		ajaxUrl = '${ajaxUrl}';
		idName = '${ajaxId}';
	  </script>
	</c:when>
</c:choose>
<link href="${bootstrapCss}" rel="stylesheet" />
<link href="${bootstrapThemeCss}" rel="stylesheet" />