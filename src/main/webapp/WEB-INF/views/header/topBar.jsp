<!-- Modal -->
  <div class="modal fade  text-center" id="myModal" role="dialog">
    <div class="modal-dialog modal-sm">
      <div class="modal-content">
        <div class="modal-header">
          <!-- disabled close
		<button type="button" class="close" data-dismiss="modal">&times;</button>
		-->          
		<h4 class="modal-title">Loading Content</h4>
        </div>
        <div class="modal-body"> 
         <!--Loadingbar-->
		<div class="progress progress-striped active">
		  <div class="progress-bar"  role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width: 100%">
		  </div>
		</div>
        </div>
      </div>
    </div>
  </div>
<!-- Nsvigation -->
<header>
     <nav class="navbar navbar-default">
        <div class="container">
          <div class="navbar-header">
            <a class="navbar-brand" href="${indexUrl}">Project name</a>
            <sec:authorize access="hasRole('ROLE_USER')"> 
            	<p> Hello <sec:authentication property="principal.realname" />  </p>
          	</sec:authorize>
          </div>
          <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
              <li class="active"><a href="${homeUrl}">Home</a></li>
              <li><a href="${aboutUrl}">About</a></li>
           	  <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>
                <ul class="dropdown-menu">
                  <li><a href="#">Action</a></li>
                  <li><a href="#">Another action</a></li>
                  <li><a href="#">Something else here</a></li>
                  <li role="separator" class="divider"></li>
                  <li class="dropdown-header">Nav header</li>
                  <li><a href="#">Separated link</a></li>
                  <li><a href="#">One more separated link</a></li>
                </ul>
              </li>
            </ul>
		
		<sec:authorize access="hasRole('ROLE_USER')"> 
			<div>
				<form:form action="${logoutUrl}" method="post" id="logoutForm">
				</form:form>
				<script>
					function formSubmit() {
						document.getElementById("logoutForm").submit();
					}
				</script>
				<ul class= "nav navbar-nav navbar-right">
					<li><a href="${dashboardUrl}">Dashboard</a></li>
					<li><a href="${settingsUrl}">Settings</a></li>
					<li><a href="${profilUrl}">Profil</a></li>
					<li><a href="${helpUrl}">Help</a></li>
					<li><a href="javascript:formSubmit()"> Logout</a></li>
				</ul>
			</div>
		</sec:authorize>	   
	 	  
	    	<form class="navbar-form navbar-right">
            	<input class="form-control" placeholder="Search..." type="text">
            </form>
	    
          </div><!--/.nav-collapse -->
        </div>
      </nav>
</header>