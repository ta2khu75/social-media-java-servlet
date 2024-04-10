<div class="row">
    <div class="col-9">
        <iframe
            class="w-100"
            height="720px"
            src="<c:out value="${video.video}"/>"
        title="YouTube video player"
        frameborder="0"
        allow="accelerometer; autoplay ; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
        allowfullscreen
        ></iframe>
        <h5><c:out value="${video.title}"/></h5>
        <div class="d-flex justify-content-between">
            <h6><c:out value="${video.views}"/>Views</h6>
            <div>
                <a href="/assignment/<c:out value="${favorite==null?'like':'unlike'}"/>/video?id=<c:out value="${video.id}"/>" class="btn btn-primary"><h6><c:out value="${favorite==null?'Like':'Un Like'}"/></h6></a>
                <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#myModal">
                    Share
                </button>
                <!-- The Modal -->
                <div class="modal fade modal-lg" id="myModal">
                    <div class="modal-dialog">
                        <div class="modal-content">

                            <!-- Modal Header -->
                            <div class="modal-header">
                                <h4 class="modal-title">Share</h4>
                                <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                            </div>

                            <!-- Modal body -->
                            <div class="modal-body">
                                <form action="/assignment/share/video" method="post">
                                    <input type="text" class="form-control" value="<c:out value="${video.id}"/>" name="id" hidden>
                                <div class="mb-3 mt-3">
                                  <label for="email" class="form-label">Email:</label>
                                  <input type="email" class="form-control" id="email" placeholder="Enter email" name="email" required="">
                                </div>
                                <button type="submit" class="btn btn-primary">Submit</button>
                              </form> 
                            </div>

                            <!-- Modal footer -->
                            <div class="modal-footer">
                                <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Close</button>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </div>
        <p>
            <c:out value="${video.description}"/>
        </p>
    </div>
    <div class="col-3">
        <c:forEach var="item" items="${videos}">
            <a class="row" style="z-index: 1000;" href="/assignment/video/details?id=<c:out value="${item.id}"/>">
            <iframe
                class="w-100"
                src="<c:out value="${item.video}"/>"
            title="YouTube video player"
            frameborder="0"
            allow="accelerometer; autoplay ; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
            allowfullscreen
            ></iframe>
            <h6><c:out value="${item.title}"/></h6>
            <p><c:out value="${item.views}"/>Views</p>
            </a>
        </c:forEach>
    </div>
</div>