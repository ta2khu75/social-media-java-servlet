/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import models.Favorite;
import models.Share;
import models.Video;
import util.Jpa;

/**
 *
 * @author ta2khu75
 */
@WebServlet(urlPatterns = {"/admin/statistical", "/admin/statistical/*"})
public class Statistical extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Object[]> countFavoriteVideo = Favorite.getCountFavoriteVideo();
		List<Video> videos = Jpa.selectAll(new Video());
		List<Favorite> users;// = User.getUserVideo(videos.get(0).getId());
		List<Share> shares;// = Share.getShareVideo(videos.get(0).getId());
		String selected = "";
		Integer id=0;
		if (req.getRequestURI().contains("user")) {
			id = Integer.valueOf(req.getParameter("id"));
			users = Favorite.getFavoriteVideo(id);
			shares = Share.getShareVideo(id);
			selected="user";
		} else if (req.getRequestURI().contains("share")) {
			id = Integer.valueOf(req.getParameter("id"));
			shares = Share.getShareVideo(id);
			users = Favorite.getFavoriteVideo(id);
			selected="share";
		} else {
			users = Favorite.getFavoriteVideo(videos.get(0).getId());
			shares = Share.getShareVideo(videos.get(0).getId());
		}
		req.setAttribute("id", id);
		req.setAttribute("selected", selected);
		req.setAttribute("favorite", countFavoriteVideo);
		req.setAttribute("videos", videos);
		req.setAttribute("users", users);
		req.setAttribute("shares", shares);
		req.setAttribute("url", "statistical");
		req.getRequestDispatcher("/Views/admin.jsp").forward(req, resp);
	}
}
