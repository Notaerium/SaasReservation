<%@ include file="navbar.jsp" %>
<body>
<p><h1 class="title text-center">Return items here!</h1></p>
	<form  method="get" action="return">
		<label for="categoryFocus">Category : </label>
		<select class="form-group" name="categoryFocus" id="categoryFocus">
			<option value="" selected="selected">-All categories-</option>
			<c:forEach var="category" items="${categories }">
			  <c:if test="${category eq categoryFocus}">
				<option value="${category}" selected="selected">${category}</option>
				</c:if>
				<c:if test="${category ne categoryFocus}">
					<option value="${category}">${category}</option>
				</c:if>
		  	</c:forEach>
		  	
		</select>
		<input type="submit" value="Search" class="btn btn-primary m-5" />
	</form>
	<c:if test="${!empty rtrnItems }">
		<c:forEach var="rtrnItem" items="${ rtrnItems }">
			<c:forEach var="item" items="${items }">
				<c:if test="${rtrnItem.productID eq item.id &&  fn:contains(formerActionList, rtrnItem)}">
					<p style="color:green;"><c:out value="${item.name} has been returned" /></p>
				</c:if>
			</c:forEach>
		</c:forEach>
	</c:if>
	<c:if test="${ !empty erreur }"><p style="color:red;"><c:out value="${ erreur }" /></p></c:if>
	<div>
		<form  class="mt-5" method="post" action="return">
			<div class="container">
			  <div class="row">
			    <div class="col-12">
			      <table class="table table-bordered table-hover">
			        <thead>
			          <tr>
			          	<th scope="col">Item name</th>
			            <th scope="col">Item category</th>
			            <th scope="col">Employee name</th>
			            <th scope="col">Employee Department</th>
						<th scope="col">Borrowed date</th>
			            <th scope="col">Return Limit date</th>
			          </tr>
			        </thead>
			        <tbody>
			        	<c:forEach var="activity" items="${ actions }">
			        		<c:if test="${now ge activity.returnDate}">
			        			<c:set var="linecolor" value="#FF6262"/>
			        		</c:if>
			        		<c:if test="${!(now ge activity.returnDate) }">
			        			<c:set var="linecolor" value="#FFFFFF"/>
			        		</c:if>
			        		<c:forEach var="item" items="${items}">
			        		<c:if test="${item.id eq activity.productID}">
			        			<tr class="tableRow">			        			
				        			
					            		<td style="background-color:${linecolor};">
					              			<div class="custom-control custom-checkbox">
					              				<input type="checkbox" name="checkAction" value ="${activity.actionID}" class="custom-control-input" id="customCheck">
					              				<label class="custom-control-label" for="customCheck">${item.name }</label>
					              			</div>
					            		</td>
					            		<td class="itemCategory" style="background-color:${linecolor};">${item.category }</td>
				            				            		
			            		<c:forEach var="employee" items="${employees }">
			            			<c:if test="${employee.id eq activity.employeeID }">
					            		<td style="background-color:${linecolor};">${employee.lastName } ${employee.firstName}</td>
					            		<td style="background-color:${linecolor};">${employee.department }</td>
					            	</c:if>
				            	</c:forEach>
			            		<td style="background-color:${linecolor};">${activity.startDate }</td>
			            		<td style="background-color:${linecolor};">${activity.returnDate }</td>
			            		
			          		</tr>
			          		</c:if>	
			          		</c:forEach>
			    		</c:forEach>
			        </tbody>
			      </table>
			    </div>
			  </div>
			</div>
			<input type="submit" value="Return" class="btn btn-success m-5" />
		</form>
	</div>
</body>