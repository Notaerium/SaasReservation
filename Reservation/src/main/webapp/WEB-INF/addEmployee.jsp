<%@ include file="navbar.jsp" %>
<body>
	<p><h1 class="title text-center">Add a new employee here!</h1></p>
	<div class="text-center">
		<form  method="post" action="addEmployee">
	    	<div class="mt-3 form-group">
		    	<label for="emp_firstname">Employee's firstname : </label>
		    	<input type="text" name="emp_firstname" id="emp_firstname" />
	    	</div>
	    	<div class="mt-3 form-group">
		    	<label for="emp_lastname">Employee's lastname : </label>
		    	<input type="text" name="emp_lastname" id="emp_lastname" />
	    	</div>
	    	<label for="departmentsSelect">Employee's department : </label>
			<select class="form-group mt-3" name="departmentsSelect" id="departmentsSelect" onchange="ShowNewOption('departmentsSelect', 'newDepartment')">
				<option value="" selected="selected">-Create a new department or select an existing one-</option>
				<c:forEach var="department" items="${departments }">
				  <option value="${department}">${department}</option>
			  	</c:forEach>
			</select>
		    <div id="newDepartment" class="mt-3 form-group">
		    	<label for="newDepartment">New Department : </label>
		    	<input type="text" name="newDepartment" id="newDepartment"/>
	    	</div>
	    	<div class="custom-control custom-checkbox">
   				<input type="checkbox" name="checkAdmin" value=true class="custom-control-input" id="customCheck">
   				<label class="custom-control-label" for="customCheck">Check if administrator</label>
   			</div>
	    	<input type="submit" value="Add" class="btn btn-primary m-5" />
    	</form>
	</div>
	
	<div class="text-center">
    	<c:if test="${ !empty erreur }"><p style="color:red;"><c:out value="${ erreur }" /></p></c:if>
    	<p style="color:green;">
    	<c:if test="${ !empty employee && empty erreur && employee.admin eq true}">
    		 <c:out value="Administrator role :" />
    	</c:if>
    	<c:if test="${ !empty employee && empty erreur && !empty employee.department}">
		   <c:out value="${ employee.firstName } ${ employee.lastName } from ${employee.department } has been added" />
		</c:if>
		<c:if test="${ !empty employee && empty erreur && empty employee.department}">
		   <c:out value="${ employee.firstName } ${ employee.lastName } has been added" />
		</c:if>
    	</p>	
    </div>
</body>