<h3>Forget Password</h3>
<h5><c:out value="${message}"/></h5>
 <form action="/assignment/forget" method="post">
  <div class="mb-3 mt-3">
<label for="code" class="form-label">Verification Code</label>
    <input type="password" class="form-control" id="code" placeholder="Enter Verification Code" required name="code">
  </div>
  <div class="mb-3 mt-3">
    <label for="newPassword" class="form-label">New Password:</label>
    <input type="password" class="form-control" id="newPassword" placeholder="Enter new password" required name="newPassword">
  </div>
  <div class="mb-3">
    <label for="confirmPassword" class="form-label">Confirm Password:</label>
    <input type="password" class="form-control" id="confirmPassword" placeholder="Enter confirm password" required name="confirmPassword">
  </div>
  <button type="submit" class="btn btn-primary">Change Password</button>
</form>