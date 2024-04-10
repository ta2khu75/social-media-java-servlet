package assignment;

import dev.ta2khu75.util.SendMail;
import java.io.IOException;
import java.util.Date;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.Favorite;
import models.Share;
import models.User;
import models.Video;
import util.Jpa;

/**
 * Servlet implementation class LikeVideo
 */
@WebServlet(urlPatterns = {"/like/video", "/unlike/video", "/share/video"})
public class VideoService extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public VideoService() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        if (request.getRequestURI().contains("unlike")) {
            unlike(request, response);
        } else if (request.getRequestURI().contains("like")) {
            like(request, response);
        } else {

        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        share(request, response);
    }

    private void like(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        HttpSession session = request.getSession();
        Video video = Jpa.find(new Video(), id);
        User user = Jpa.find(new User(), session.getAttribute("email"));
        Favorite favorite = new Favorite();
        favorite.setVideo(video);
        favorite.setUser(user);
        favorite.setLikeDate(new Date());
        boolean result = Jpa.presist(favorite);
        System.out.println(result);
        response.sendRedirect("/assignment/video/details?id=" + id);
    }

    private void unlike(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        HttpSession session = request.getSession();
        Video video = Jpa.find(new Video(), id);
        User user = Jpa.find(new User(), session.getAttribute("email"));
        Favorite favorite = Favorite.getFavoriteUserVideo(video.getId(), user.getEmail());
        boolean result = Jpa.remove(favorite, favorite.getId());
        System.out.println(result);
        response.sendRedirect("/assignment/video/details?id=" + id);
    }

    private void share(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        String email = request.getParameter("email");
        HttpSession session = request.getSession();
        Video video = Jpa.find(new Video(), id);
        User user = Jpa.find(new User(), session.getAttribute("email"));
        Share share = new Share();
        share.setVideo(video);
        share.setUser(user);
        share.setEmail(email);
        share.setShareDate(new Date());
        SendMail.sendShare(email, "http://localhost:8080/assignment/video/details?id="+id);
        boolean result = Jpa.presist(share);
        System.out.println(result);
        response.sendRedirect("/assignment/video/details?id=" + id);
    }
}
