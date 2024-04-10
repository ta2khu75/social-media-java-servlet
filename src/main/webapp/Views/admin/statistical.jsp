<ul class="nav nav-tabs" id="myTab" role="tablist">
  <li class="nav-item" role="presentation">
    <button class="nav-link <c:out value="${selected.equals('')?'active':''}"/>" id="home-tab" data-bs-toggle="tab" data-bs-target="#home" type="button" role="tab" aria-controls="home" aria-selected="true">Favorites</button>
  </li>
  <li class="nav-item" role="presentation">
    <button class="nav-link <c:out value="${selected.equals('user')?'active':''}"/>" id="profile-tab" data-bs-toggle="tab" data-bs-target="#profile" type="button" role="tab" aria-controls="profile" aria-selected="false">Favorite Users</button>
  </li>
  <li class="nav-item" role="presentation">
    <button class="nav-link <c:out value="${selected.equals('share')?'active':''}"/>" id="contact-tab" data-bs-toggle="tab" data-bs-target="#contact" type="button" role="tab" aria-controls="contact" aria-selected="false">Share Friends</button>
  </li>
</ul>
<div class="tab-content" id="myTabContent">
  <div class="tab-pane fade <c:out value="${selected.equals('')?'show active':''}"/>" id="home" role="tabpanel" aria-labelledby="home-tab">
  <table class="table table-hover">
      <thead><tr>
        <th>Video title</th>
        <th>Favorite count</th>
        <th>Latest Date</th>
        <th>Oldest Date</th>
      </tr></thead>
      <tbody>
        
        <c:forEach var="item" items="${favorite}">
          <tr>
            <td><c:out value="${item[0]}"/></td>
            <td><c:out value="${item[1]}"/></td>
            <td><c:out value="${item[2]}"/></td>
            <td><c:out value="${item[3]}"/></td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
  </div>
  <div class="tab-pane fade <c:out value="${selected.equals('user')?'show active':''}"/>" id="profile" role="tabpanel" aria-labelledby="profile-tab">
    <select class="form-select" id="user">
      <c:forEach var="video" items="${videos}">
	      <option <c:out value="${id==video.id?'selected':''}"/> value="/assignment/admin/statistical/user?id=<c:out value="${video.id}"/>"><c:out value="${video.title}"/></option>
      </c:forEach>
    </select>
    <table class="table table-hover">
      <thead><tr>
        <th>Email</th>
        <th>Full Name</th>
        <th>Favorite Date</th>
      </tr></thead>
      <tbody>
        <c:forEach var="item" items="${users}">
          <tr>
            <td><c:out value="${item.user.email}"/></td>
            <td><c:out value="${item.user.fullname}"/></td>
            <td><c:out value="${item.likeDate}"/></td>
          </tr>
        </c:forEach>
      </tbody>
    </table> 
  </div>
  <div class="tab-pane fade <c:out value="${selected.equals('share')?'show active':''}"/> " id="contact" role="tabpanel" aria-labelledby="contact-tab">
<select class="form-select" id="share">
      <c:forEach var="video" items="${videos}">
	      <option <c:out value="${id==video.id?'selected':''}"/> value="/assignment/admin/statistical/share?id=<c:out value="${video.id}"/>"><c:out value="${video.title}"/></option>
      </c:forEach>
    </select>
     <table class="table table-hover">
      <thead><tr>
        <th>Sender Name</th>
        <th>Sender Email</th>
        <th>Receiver Email</th>
        <th>Send Date</th>
      </tr></thead>
      <tbody>
        <c:forEach var="item" items="${shares}">
          <tr>
            <td><c:out value="${item.user.fullname}"/></td>
            <td><c:out value="${item.user.email}"/></td>
            <td><c:out value="${item.email}"/></td>
            <td><c:out value="${item.shareDate}"/></td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
  </div>
</div>
<script>
var selectElement = document.getElementById('user');
// Th�m s? ki?n 'change' cho select
selectElement.addEventListener('change', function() {
// L?y gi� tr? ???c ch?n
    var selectedOption = selectElement.options[selectElement.selectedIndex].value;
    // Ki?m tra xem gi� tr? c� t?n t?i kh�ng
    if (selectedOption) {
    // Chuy?n h??ng sang trang ?� ch?n
        window.location.href = selectedOption;
    }
});
var shareElement = document.getElementById('share');
// Th�m s? ki?n 'change' cho select
shareElement.addEventListener('change', function() {
// L?y gi� tr? ???c ch?n
    var selectedOption = shareElement.options[shareElement.selectedIndex].value;
    // Ki?m tra xem gi� tr? c� t?n t?i kh�ng
    if (selectedOption) {
    // Chuy?n h??ng sang trang ?� ch?n
        window.location.href = selectedOption;
    }
});
</script>

