<h3>Login</h3>
 <h5><c:out value="${message}"/></h5>
 <form action="/assignment/login" method="post">
  <div class="mb-3 mt-3">
    <label for="email" class="form-label">Email:</label>
    <input type="email" class="form-control" id="email" value="${email}" placeholder="Enter email" name="email">
  </div>
  <div>
    <label for="pwd" class="form-label">Password:</label>
    <input type="password" class="form-control" id="pwd" placeholder="Enter password" name="password">
  </div>
   <div class="form-check mb-3">
    <label class="form-check-label">
      <input class="form-check-input" type="checkbox" name="remember"> Remember me
    </label>
  </div> 
  <div class="mb-4">
	<a  href="/assignment/register">Do not have an account?</a>
  </div>	
  <div class="d-flex justify-content-between">
	<button type="submit" class="btn btn-primary">Submit</button>
	<button formaction="/assignment/forget" class="btn btn-success" formmethod="get">Forget Password</button>
  </div>
</form> 