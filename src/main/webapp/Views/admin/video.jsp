<%-- 
    Document   : managerUser
    Created on : Dec 1, 2023, 3:25:33 PM
    Author     : ta2khu75
--%>

	<h1>MANAGER VIDEO</h1>
	<p style="color: red">${message}</p>
	<form>
    <input type="number" name="id" value="<c:out value="${video.id}"/>" hidden>
		<div class="mb-3">
			<label for="title" class="form-label">Title</label> <input
				type="text" value="${video.title }" name="title" class="form-control" id="title"
				aria-describedby="emailHelp">
		</div>
		<div class="mb-3">
			<label for="video" class="form-label">Video</label> <input
				type="text" name="video" value="${video.video }" class="form-control" id="video">
		</div>
		<div class="mb-3" hidden>
			<label for="view" class="form-label">View</label> <input
				type="number" name="views" value="${video.views }" class="form-control" id="view">
		</div>
        <div class="mb-3">
			<label for="description" class="form-label">Description</label>
            <textarea class="w-100" name="description" id="description" rows="10">${video.description }</textarea>
		</div>
    <div class="d-flex justify-content-between">
      <div>
        <button formaction="/assignment/admin/video/insert" formmethod="post" class="btn btn-primary">Insert</button>
		    <button formaction="/assignment/admin/video/update" formmethod="post" class="btn btn-success">Update</button>
        <a href="/assignment/admin/video">Reset</a>
      </div>
      <button class="btn btn-secondary" formaction="/assignment/admin/video/import" formmethod="get">Excel Import</button>
      <button class="btn btn-secondary" formaction="/assignment/admin/video/export" formmethod="get">Excel Export</button>
    </div>
		
	</form>
    <table class="table table-hover">
        <thead>
          <tr>
            <th scope="col">Id</th>
            <th scope="col">Title</th>
            <th scope="col">Video</th>
            <th scope="col">Views</th>
            <th scope="col">Edit</th>
            <th scope="col">Delete</th>
          </tr>
        </thead>
        <tbody>
            <c:forEach var="item" items="${videos}">
                <tr>
                    <th scope="row"><c:out value="${item.id}"/></th>
                    <td><c:out value="${item.title}"/></td>
                    <td><c:out value="${item.video}"/></td>
                    <td><c:out value="${item.views}"/></td>
                    <td><a href="/assignment/admin/video/find?id=<c:out value="${item.id}"/>">Edit</a></td>
                    <td><a href="/assignment/admin/video/delete?id=<c:out value="${item.id}"/>">Delete</a></td>
                  </tr>
            </c:forEach>
         
        </tbody>
      </table>
