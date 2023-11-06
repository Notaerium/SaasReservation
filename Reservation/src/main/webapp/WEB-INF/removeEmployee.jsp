<%@ include file="navbar.jsp" %>
<body>
<p><h1 class="title text-center">Remove employees from the Database here!</h1></p>
	<form  method="get" action="removeEmployee">
		<label for="departmentFocus">Department: </label>
		<select class="form-group" name="departmentFocus" id="departmentFocus">
			<option value="" selected="selected">-Every departments-</option>
			<c:forEach var="department" items="${departments }">
			<c:if test="${department eq focusOn}">
				<option value="${department}" selected="selected">${department}</option>
			</c:if>
			<c:if test="${department ne focusOn}">
				<option value="${department}">${department}</option>
			</c:if>
		  	</c:forEach>
		</select>
		<label for="lastnameSearch">Search by lastname: </label>
		<c:if test="${!empty focusName }">
			<input type="text" name="lastnameSearch" id="lastnameSearch" value="${focusName }"/>
		</c:if>
		<c:if test="${empty focusName }">
			<input type="text" name="lastnameSearch" id="lastnameSearch""/>
		</c:if>
		<input type="submit" value="Search" class="btn btn-primary m-5" />
	</form>
	<c:if test="${!empty rmvedEmps }">
		<c:forEach var="rmvedEmp" items="${ rmvedEmps }">
			<p style="color:green;"><c:out value="${rmvedEmp.firstName} ${rmvedEmp.lastName}has been removed" /></p>
		</c:forEach>
	</c:if>
	<c:if test="${ !empty erreur }"><p style="color:red;"><c:out value="${ erreur }" /></p></c:if>
	<div>
		<form  method="post" action="removeEmployee">
			<div class="container">
			  <div class="row">
			    <div class="col-12">
			      <table class="table table-bordered table-hover">
			        <thead>
			          <tr>
			          	<th scope="col">Employee lastname</th>
			            <th scope="col">Employee firstname</th>
			            <th scope="col">Department attached</th>
			          </tr>
			        </thead>
			        <tbody>
			        	<c:forEach var="employee" items="${ employees }">
			        	<!-- if I focus nothing, show everything. If I look only for the department, show everyone in this dep.
			        	If I look only for a name, show everyone with this name. If a loot for a name and a department, show people in this dep with this name-->
					        	<c:if test="${(empty focusName and employee.department eq focusOn) or 
					        	(empty focusOn and employee.lastName eq focusName) or 
					        	(empty focusOn and empty focusName) or
					        	(!empty focusOn and employee.lastName eq focusName and !empty focusName and employee.department eq focusOn)}">
							        <tr>
					            		<td>
					              			<div class="custom-control custom-checkbox">
					              				<input type="checkbox" name="checkEmployee" value ="${employee.id}" class="custom-control-input" id="customCheck">
					              				<label class="custom-control-label" for="customCheck">${employee.lastName} </label>
					              			</div>
					            		</td>
					            		<td>${employee.firstName}</td>
					            		<td>${employee.department}</td>
					          		</tr>
						        </c:if>
			    		</c:forEach>
			        </tbody>
			      </table>
			    </div>
			  </div>
			</div>
			<input type="submit" value="Delete" class="btn btn-danger m-5" />
		</form>
	</div>
</body>