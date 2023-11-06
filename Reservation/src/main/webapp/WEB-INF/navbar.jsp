<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Reservation System</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" />
	<script src="${pageContext.request.contextPath}/resources/js/bootstrap.bundle.min.js" defer></script>
	<script src="${pageContext.request.contextPath}/resources/js/script.js" defer></script>
</head>
<body>
	<nav class="navbar navbar-expand-lg bg-body-tertiary">
	  <div class="container-fluid">
	    <a class="navbar-brand" href="/Reservation/home">Home</a>
	    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
	      <span class="navbar-toggler-icon"></span>
	    </button>
	    <div class="collapse navbar-collapse" id="navbarSupportedContent">
	      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
	        <li class="nav-item">
	          <a class="nav-link active" aria-current="page" href="/Reservation/borrow">Borrow</a>
	        </li>
	         <li class="nav-item">
	          	<a class="nav-link active" aria-current="page" href="/Reservation/return">Return</a>
	        </li>
	         <li class="nav-item px-2">
		         <div class="btn-group">
					  <button type="button" class="btn btn-primary dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
					    Item management
					  </button>
					  <ul class="dropdown-menu">
					    <li><a class="dropdown-item" href="/Reservation/add">Add Item</a></li>
					    <li><a class="dropdown-item" href="/Reservation/delete">Delete Item</a></li>
					  </ul>
				</div>	          	
	        </li>
	        <li class="nav-item px-2">
		         <div class="btn-group">
					  <button type="button" class="btn btn-secondary dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
					    Employee management
					  </button>
					  <ul class="dropdown-menu">
					    <li><a class="dropdown-item" href="/Reservation/addEmployee">Add Employee</a></li>
					    <li><a class="dropdown-item" href="/Reservation/removeEmployee">Remove Employee</a></li>
					  </ul>
				</div>	          	
	        </li>     
	      </ul>
	    </div>
	  </div>
	</nav>
</body>
</html>