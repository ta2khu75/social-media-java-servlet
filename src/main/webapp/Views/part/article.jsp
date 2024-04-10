<div class="row">
  <div class="col-9">
    <div class="row">
      <c:forEach var="item" items="${videos}">
        <a class="card col-4" href="/assignment/video/details?id=<c:out value="${item.id}"/>">
          <div class="card-header">
            <iframe
            class="w-100"
            height="220px"
            src="<c:out value="${item.video}"/>"
            title="YouTube video player"
            frameborder="0"
            allow="accelerometer; autoplay ; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
            allowfullscreen
          ></iframe>
          </div>
          <div class="card-body">
            <h6><c:out value="${item.title}" /></h6>
          </div>
          <div class="card-footer"><c:out value="${item.views}"/> Views</div>
        </a>
      </c:forEach>
    </div>
    <div class="row">
      <nav class="d-flex justify-content-center" aria-label="Page navigation example">
        <ul class="pagination">
          <c:if test="${page>1}">
          <li class="page-item"><a class="page-link" href="/assignment?page=<c:out value="${page-1}" />">Previous</a></li>
          </c:if>
          <c:forEach var="num" items="${numPages}">
          <li class="page-item"><a class="page-link" href="/assignment?page=<c:out value="${num}" />"><c:out value="${num}"/></a></li>
          </c:forEach>        
          <c:if test="${page<maxPage}">
          <li class="page-item"><a class="page-link" href="/assignment?page=<c:out value="${page+1}" />">Next</a></li>
          </c:if>
        </ul>
      </nav>
    </div>
  </div>
    <div class="col-3">
      <h4>Video da xem</h4>
      <c:forEach var="item" items="${videos}">
          <c:if test="${videoIds.contains(item.id)}">
              <iframe
          class="w-100"
          height="220px"
          src="<c:out value="${item.video}"/>"
          title="YouTube video player"
          frameborder="0"
          allow="accelerometer; autoplay ; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
          allowfullscreen
        ></iframe>
          </c:if>
      </c:forEach>
    </div>
  </div>
 
