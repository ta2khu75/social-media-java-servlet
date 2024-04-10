<%-- 
    Document   : managerUser
    Created on : Dec 1, 2023, 3:25:33 PM
    Author     : ta2khu75

--%>

	<h1>MANAGER USER</h1>
	<p style="color: red">${message}</p>
	<form>
		<div class="mb-3">
			<label for="email" class="form-label">Email</label> <input
				type="email" value="${user.email }" name="email" class="form-control" id="email"
				aria-describedby="emaillp">
		</div>
        <div class="mb-3">
			<label for="fullname" class="form-label">Fullname</label> <input
				type="fullname" value="${user.fullname}" name="fullname" class="form-control" id="fullname"
				aria-describedby="emaillp">
		</div>
		<div class="mb-3">
			<label for="password" class="form-label">Password</label> <input
				type="password" name="password" value="${user.password }" class="form-control" id="password">
		</div>
		<div class="mb-3" >
			<label for="confirm" class="form-label">Confirm Password</label> <input
				type="password" name="confirm" value="${user.password }" class="form-control" id="confirm">
		</div>
        <div class="d-flex">
            <div class="mb-3 form-check">
                <input type="radio" name="admin" <c:out value="${user.admin?'checked':''}"/> value="true" class="form-check-input" id="exampleCheck1">
                <label class="form-check-label" for="exampleCheck1">Admin</label>
              </div>
              <div class="mb-3 form-check">
                <input type="radio" name="admin" <c:out value="${user.admin?'':'checked'}"/> value="false" class="form-check-input" id="exampleCheck2">
                <label class="form-check-label" for="exampleCheck2">User</label>
              </div>
        </div>
        <div class="d-flex justify-content-between">
          <div>
            <button formaction="/assignment/admin/user/insert" formmethod="post" class="btn btn-primary">Insert</button>
		        <button formaction="/assignment/admin/user/update" formmethod="post" class="btn btn-success">Update</button>
            <a href="/assignment/admin/user">Reset</a>
          </div>
          <button class="btn btn-secondary" formaction="/assignment/admin/user/export" formmethod="get">Excel Export</button>
        </div>
		
	
	</form>
    <table class="table table-hover">
        <thead>
          <tr>
            <th scope="col">Email</th>
            <th scope="col">Fullname</th>
            <th scope="col">Admin</th>
            <th scope="col">Edit</th>
            <th scope="col">Delete</th>
          </tr>
        </thead>
        <tbody>
            <c:forEach var="item" items="${users}">
                <tr>
                    <th scope="row"><c:out value="${item.email}"/></th>
                    <td><c:out value="${item.fullname}"/></td>
                    <td><c:out value="${item.admin}"/></td>
                    <td><a href="/assignment/admin/user/find?id=<c:out value="${item.email}"/>">Edit</a></td>
                    <td><a href="/assignment/admin/user/delete?id=<c:out value="${item.email}"/>">Delete</a></td>
                  </tr>
            </c:forEach>
         
        </tbody>
      </table>

