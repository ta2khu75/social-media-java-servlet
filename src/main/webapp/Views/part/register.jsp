<h3>Register</h3>
<h5><c:out value="${message}"/></h5>
 <form action="/assignment/register" method="post">
  <div class="mb-3 mt-3">
    <label for="email" class="form-label">Email:</label>
    <input type="email" class="form-control" id="email" placeholder="Enter email" name="email">
  </div>
  <div class="mb-3 mt-3">
    <label for="fullname" class="form-label">Fullname:</label>
    <input type="text" class="form-control" id="fullname" placeholder="Enter fullname" name="fullname">
  </div>
  <div class="mb-3">
    <label for="pwd" class="form-label">Password:</label>
    <input type="password" class="form-control" id="pwd" placeholder="Enter password" name="password">
  </div>
  
  <div class="mb-3">
    <label for="fir" class="form-label">Confirm:</label>
    <input type="password" class="form-control" id="fir" placeholder="Enter confirm password" name="confirm">
  </div>
	<div class="d-flex justify-content-between">
		<button type="submit" class="btn btn-primary">Submit</button>
		<a href="/assignment/login">Do you already have an account?</a>
	</div>
</form> 