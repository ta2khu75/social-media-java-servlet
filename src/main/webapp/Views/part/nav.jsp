<fmt:setLocale value="${sessionScope.lang}" scope="request"/>
 <fmt:setBundle basename="message" scope="request" />
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
      <a class="navbar-brand" href="/assignment">YouTobeContinue</a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item">
            <a class="nav-link active" aria-current="page" href="/assignment/user/videos">Favorite</a>
          </li>
          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
              Account
            </a>
            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
              <li><a class="dropdown-item"  href="/assignment/logout"><fmt:message key="menu.home" />Logout</a></li>
              <li><a class="dropdown-item"  href="/assignment/login"><fmt:message key="menu.about" />Login</a></li>
              <li><a class="dropdown-item"  href="/assignment/register"><fmt:message key="menu.contact" />Register</a></li>
              <li><hr class="dropdown-divider"></li>
	     <li><a class="dropdown-item" href="/assignment/change">Change Password</a></li> 
              <li><a class="dropdown-item" href="#">Edit Profile</a></li>
            </ul>
          </li>
           <li class="nav-item">
            <a class="nav-link" href="/assignment?lang=vi">Tieng Viet</a>
          </li>
           <li class="nav-item">
            <a class="nav-link" href="/assignment?lang=en">English</a>
          </li>
        </ul>
        <form class="d-flex">
          <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
          <button class="btn btn-outline-success" type="submit">Search</button>
        </form>
      </div>
    </div>
  </nav>