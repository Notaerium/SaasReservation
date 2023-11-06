<%@ include file="navbar.jsp" %>
<body>
	<p><h1 class="title text-center">Borrow Here !</h1></p>
	<c:if test="${empty employeeId}">
		<p><h2 class="title text-center">Borrowing employee: </h2></p>
		<form  method="get" action="borrow">
			<label for="departmentFocus">Department: </label>
			<select class="form-group" name="departmentFocus" id="departmentFocus">
				<option value="" selected="selected">-Every departments-</option>
				<c:forEach var="department" items="${departments }">
				  <option value="${department}">${department}</option>
			  	</c:forEach>
			</select>
			<label for="lastnameSearch">Search by lastname: </label>
			<input type="text" name="lastnameSearch" id="lastnameSearch"/>
			<input type="submit" value="Search" class="btn btn-primary m-5" />
		</form>
		<c:if test="${ !empty error }"><p style="color:red;"><c:out value="${ error }" /></p></c:if>
		<c:if test="${ !empty borrowData && empty error}">
			<p style="color:green;"><c:out value="${employeeName} put on a reservation for the ${item_name}. Details saved" /></p>
		</c:if>
		<div>
			<form  method="get" action="borrow">
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
					        <div id="item_table_row">
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
							              				<input type="radio" name="selectEmployee" value ="${employee.id}" class="custom-control-input" id="customCheck">
							              				<label class="custom-control-label" for="customCheck">${employee.lastName} </label>
							              			</div>
							            		</td>
							            		<td>${employee.firstName}</td>
							            		<td>${employee.department}</td>
							          		</tr>
								        </c:if>
					    		</c:forEach>
					        </div>				        	
				        </tbody>
				      </table>
				    </div>
				  </div>
				</div>
				<input type="submit" value="Select employee" class="btn btn-primary m-5" />
			</form>
		</div>
	</c:if>
	<c:if test="${!empty employeeId}">
		<p><h2 class="title text-center">Borrowing item: </h2></p>
		<c:forEach var="employee" items="${employees }">
			<c:if test="${employeeId eq employee.id }">
				<h3 style="text-decoration-line: underline;">For ${employee.firstName } ${employee.lastName }</h3>
				<c:set var="employee_fullName" value="${employee.firstName } ${employee.lastName }"/>
			</c:if>
		</c:forEach>
		<form  method="get" action="borrow">
			<input type="hidden" name="selectEmployee" value="${employeeId}">
			<input type="hidden" name="employee_fullName" value="${employee_fullName}">
			<label for="categoryFocus">Category : </label>
			<select class="form-group" name="categoryFocus" id="categoryFocus" onchange="VerifyDateInput()">
				<option value="" selected="selected">-Categories-</option>
				<c:forEach var="category" items="${categories }">
					<c:if test="${category eq focusOn}">
						<option value="${category}" selected="selected">${category}</option>
					</c:if>
					<c:if test="${category ne focusOn}">
						<option value="${category}">${category}</option>
					</c:if>				  
			  	</c:forEach>
			</select>
			<c:if test="${!empty startPeriod && !empty endPeriod}">
				<label for="start">Start date:</label>
				<input type="date" id="start" name="start" value="${startPeriod }" min="${now }" max="${maxDate }" onchange="VerifyDateInput()"/>
				<label for="end">Return date:</label>
				<input type="date" id="end" name="end" value="${endPeriod }" min="${now }" max="${maxDate }" onchange="VerifyDateInput()"/>
				<input type="submit" value="Search" class="btn btn-primary m-5" id="borrowInput" disabled/>
			</c:if>
			<c:if test="${empty startPeriod && empty endPeriod}">
				<label for="start">Start date:</label>
				<input type="date" id="start" name="start" value="${startPeriod }" min="${now }" max="${maxDate }" onchange="VerifyDateInput()"/>
				<label for="end">Return date:</label>
				<input type="date" id="end" name="end" value="${endPeriod }" min="${now }" max="${maxDate }" onchange="VerifyDateInput()"/>
				<input type="submit" value="Search" class="btn btn-primary m-5" id="borrowInput" disabled/>
			</c:if>		
		</form>
		<c:if test="${ !empty error }"><p style="color:red;"><c:out value="${ error }" /></p></c:if>
		<c:if test="${!empty startPeriod && !empty endPeriod }">
			<div>
			<form  method="post" action="borrow">
				<input type="hidden" name="selectEmployee" value="${employeeId}">
				<input type="hidden" name="employee_fullName" value="${employee_fullName}">
				<input type="hidden" name="start" value="${startPeriod}">
				<input type="hidden" name="end" value="${endPeriod}">
				<div class="container">
				  <div class="row">
				    <div class="col-12">
				      <table class="table table-bordered table-hover">
				        <thead>
				          <tr>
				          	<th scope="col">Item name</th>
				            <th scope="col">Item category</th>
				          </tr>
				        </thead>
				        <tbody>
				        	<c:forEach var="item" items="${ items }">
						        	<c:if test="${item.category eq focusOn or empty focusOn}">
						        		<c:set var="availabilty" value="true"/>	
						        		<c:forEach var="action" items="${borrowedItems }"> <!-- as the item have been borrowed and when-->
						        			<c:if test="${action.productID eq item.id && 
						        				((action.startDate ge startPeriod && action.startDate le endPeriod) ||
						        					(action.returnDate ge startPeriod && action.returnDate le endPeriod))}">
						        					<c:set var="availabilty" value="false"/>		        			
						        			</c:if>								        	
							        	</c:forEach>
							        	<c:if test="${availabilty eq true}">
							        		<div id="item_table_row">
									        		<tr>
									            		<td>
									              			<div class="custom-control custom-checkbox">						              												              				
								              					<input type="radio" name="selectItem" value ="${item.id}" class="custom-control-input" id="customCheck">
								              					<label class="custom-control-label" for="customCheck">${item.name}</label>
									              			</div>
									            		</td>
									            		<td>${item.category }</td>
									          		</tr>
								        		</div>		
							        	</c:if>								        										        
							        </c:if>
				    		</c:forEach>
				        </tbody>
				      </table>
				    </div>
				  </div>
				</div>
				<input type="submit" value="Submit" class="btn btn-primary m-5" />
			</form>
		</div>
		</c:if>
	</c:if>
</body>