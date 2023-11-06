<%@ include file="navbar.jsp" %>
<body>
	<p><h1 class="title text-center">Add a new item here!</h1></p>
	<div class="text-center">
		<form  method="post" action="add">
			<label for="categoriesSelect">Item Category: </label>
			<select class="form-group" name="categoriesSelect" id="categoriesSelect" onchange="ShowNewOption('categoriesSelect', 'newCategory')">
				<option value="" selected="selected">-Create a new category or select an existing one-</option>
				<c:forEach var="category" items="${categories }">
				  <option value="${category}">${category}</option>
			  	</c:forEach>
			</select>
		    <div id="newCategory" class="mt-3 form-group">
		    	<label for="newCategory">New Category : </label>
		    	<input type="text" name="newCategory" id="newCategory"/>
	    	</div>
	    	<div class="mt-3 form-group">
		    	<label for="item_name">Item name: </label>
		    	<input type="text" name="item_name" id="item_name" />
	    	</div>
	    	<input type="submit" value="Add" class="btn btn-primary m-5" />
    	</form>
	</div>
	
    <div class="text-center">
    	<c:if test="${ !empty erreur }"><p style="color:red;"><c:out value="${ erreur }" /></p></c:if>
    	<c:if test="${ !empty item && empty erreur && !empty item.category}">
		   <c:out value="${ item.category } named ${ item.name } has been added" />
		</c:if>
		<c:if test="${ !empty item && empty erreur && empty item.category}">
		   <c:out value="Uncategorized named ${ item.name } has been added" />
		</c:if>
    </div>

     
</body>