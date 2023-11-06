<%@ include file="navbar.jsp" %>
<body>
	<p><h1 class="text-center">Reservation System</h1></p>
	<div class="container text-center">
		<div class="row row-cols-2">
			<div class="g-col-6">
		    	<div class="card h-200">
					<div class="card-body">
						<h3 class="card-title">Borrow Item</h3>
						<a href="/Reservation/borrow" class="btn btn-outline-primary">Continue</a>
					</div>
				</div>
		    </div>
		    <div class="g-col-6">
				<div class="card h-200">
					<div class="card-body">
						<h3 class="card-title">Return Item</h3>
						<a href="/Reservation/return" class="btn btn-outline-primary">Continue</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="container text-center mt-5">
		<div class="row row-cols-2">
		    <div class="g-col-6">
		    	<div class="card h-200">
					<div class="card-body">
						<h3 class="card-title">Add Item</h3>
						<a href="/Reservation/add" class="btn btn-outline-primary">Continue</a>
					</div>
				</div>
			</div>
		    <div class="g-col-6">
		    	<div class="card h-200">
					<div class="card-body">
						<h3 class="card-title">Delete Item</h3>
						<a href="/Reservation/delete" class="btn btn-outline-primary">Continue</a>
					</div>
				</div>	
			</div>
		 </div>
	</div>
	<div class="container text-center mt-5">
		<div class="row row-cols-2">
		    <div class="g-col-6">
		    	<div class="card h-200">
					<div class="card-body">
						<h3 class="card-title">Add Employee</h3>
						<a href="/Reservation/addEmployee" class="btn btn-outline-primary">Continue</a>
					</div>
				</div>
			</div>
		    <div class="g-col-6">
		    	<div class="card h-200">
					<div class="card-body">
						<h3 class="card-title">Remove Employee</h3>
						<a href="/Reservation/removeEmployee" class="btn btn-outline-primary">Continue</a>
					</div>
				</div>	
			</div>
		 </div>
	</div>
</body>