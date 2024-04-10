<h3>Change Password</h3>
<h5><c:out value="${message}"/></h5>
 <form action="/assignment/change" method="post">
  <div class="mb-3 mt-3">
<label for="oldPassword" class="form-label">Old Password:</label>
    <input type="password" class="form-control" id="oldPassword" placeholder="Enter old password" required name="oldPassword">
  </div>
  <div class="mb-3 mt-3">
    <label for="newPassword" class="form-label">New Password:</label>
    <input type="password" class="form-control" id="newPassword" placeholder="Enter new password" required name="newPassword">
  </div>
  <div class="mb-3">
    <label for="confirmPassword" class="form-label">Confirm Password:</label>
    <input type="password" class="form-control" id="confirmPassword" placeholder="Enter confirm password" required name="confirmPassword">
  </div>
  <button type="submit" class="btn btn-primary">Submit</button>
</form>