<%@ include file="navbar.jsp" %>
<body>
	<p><h1 class="title text-center">Delete items here!</h1></p>
	<form  method="get" action="delete">
	<label for="categoryFocus">Category : </label>
	<select class="form-group" name="categoryFocus" id="categoryFocus">
		<option value="" selected="selected">-Categories-</option>
		<c:forEach var="category" items="${categories }">
		  <option value="${category}">${category}</option>
	  	</c:forEach>
	</select>
	<input type="submit" value="Search" class="btn btn-primary m-5" />
	</form>
	<c:if test="${!empty values }">
		<p style="color:green;"><c:out value="${values} has/have been deleted" /></p>
	</c:if>
	<c:if test="${ !empty erreur }"><p style="color:red;"><c:out value="${ erreur }" /></p></c:if>
	<div>
		<form  method="post" action="delete">
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
							        <tr>
					            		<td>
					              			<div class="custom-control custom-checkbox">
					              				<input type="checkbox" name="checkItem" value ="${item.name}" class="custom-control-input" id="customCheck">
					              				<label class="custom-control-label" for="customCheck">${item.name}</label>
					              			</div>
					            		</td>
					            		<td>${item.category }</td>
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