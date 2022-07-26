<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.Date" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<jsp:include page="/WEB-INF/include/head.jsp" />

<body>
	<jsp:include page="/WEB-INF/include/header.jsp" />
	
	<div id=pageHeader class="container-fluid">
		<h2>House Management</h2>
	</div>
	
	<div id=main class="card">
<%-- 
		<div id="recordHeader"> 
		<a href= "/house/${house.id}/editrecord"><button class="btn btn-primary">Edit</button></a>
		</div>
--%>
		<div id=about class="card">
			<form:form action='/house/edit' method='post' modelAttribute='house'>
				<!-- JRF note: above is clean, replaced by hidden input below.  
				and below is now embacing the modelAttribute/path approach.  -->
				<form:input type="hidden"  path="id" />
				
				<%-- <form:input type="hidden" value="${house.userMdl.id}" path="userMdl" /> --%>
				<%-- <form:hidden value="${house.userMdl.id}" path="userMdl" /> --%>  
				<!-- JRF: WHACK FOREVER MORE THIS SILLY HIDDEN INPUT FROM ALL EDIT FORMS; CREATEDBY IS BEING MAINTAINED BY LINE IN THE CONTROLLER;  -->
		
				<div class="form-group">
					<form:label path="houseName" for="houseName">houseName</form:label>
					<form:input type="text" class="form-control" path="houseName"/>
					<p class="errorText"><form:errors path="houseName" />
					</p>
				</div>
				
 	
				<%-- <div class="form-group">
					<p>
						<form:label path="dojoMdl" for="dojoMdl">Dojo:</form:label>
					</p>
					<form:select path="dojoMdl" name="dojoMdl">
	 					<c:forEach var="record" items="${dojoList}">
							<c:choose>
								<c:when test="${publication.dojoMdl.id == record.id}">
									<option value="${record.id}" selected>
										<c:out value="${record.dojoName}"/>
									</Option>
								</c:when>
								<c:otherwise>
									<option value="${record.id}">
										<c:out value="${record.dojoName}" />
									</Option>
								</c:otherwise>
							</c:choose>
						</c:forEach> 
					</form:select>
				</div> --%>
				
				<div>
					<button type="submit" class="btn btn-primary">Update</button>
				</div>
			</form:form>
	
			<a href="/house/${house.id}"><button class="btn btn-secondary">Cancel</button></a>
			
		</div>
		
<%-- 		<div id=list class="card">
			<h3>Twintwo List</h3>

			<table class="table table-striped table-dark">
				<thead>
					<tr>
						<th scope="col">id</th>
						<th scope="col">twintwoName</th>
						<th scope="col">actions</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="record" items="${assignedCategories}">
						<tr>
							<td>${record.id}</td>
							<td><a href="/twintwo/${record.id}">${record.twintwoName}</a></td>
 								
							<td>
								<form action="/removeHouseTwintwoJoin" method="post">
								    
								    <input type="hidden" name="_method" value="delete">
								    <input type="hidden" name="twintwoId" value="${record.id}"/>
								    <input type="hidden" name="houseId" value="${house.id}"/>
								    <!-- <input type="hidden" name="origin" value="1"/> -->
								    
								    <button class="btn btn-danger">Delete</button>
								</form>
							</td>

						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
 --%>

<%-- 
		<div id=form class="card">
			<h2>Add a Twintwo:</h2>
			<form action='/house/${house.id}/editTwintwoJoins' method='post' >
				<div class="form-group">
					<select name="twintwoId"> 
						<c:forEach var="record" items="${unassignedCategories}">
							<option value="${record.id}" >
								<c:out value="${record.twintwoName}" />
							</option>
						</c:forEach>
					</select>
				</div>
 				
 				<button type="submit" class="btn btn-primary">Add</button>
			</form>
		</div>
 --%>		

<%-- 		<div id=form class="card">
			<h2>Add a Onetwinchild:</h2>
			<form action='/house/${house.id}/new' method='post' >
				<div class="form-group">
					<select name="twintwoId"> 
						<c:forEach var="record" items="${unassignedCategories}">
							<option value="${record.id}" >
								<c:out value="${record.twintwoName}" />
							</option>
						</c:forEach>
					</select>
				</div>
 				
 				<button type="submit" class="btn btn-primary">Add</button>
			</form>
		</div>
 --%>
		
		
	</div>
 	
 	<jsp:include page="/WEB-INF/include/footerbuffer.jsp"/>
 	<jsp:include page="/WEB-INF/include/footer.jsp"/>
</body>
</html>