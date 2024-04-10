package assignment;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.Set;
import models.Favorite;
import models.Video;
import util.Jpa;

/**
 * Servlet implementation class VideoDetails
 */
@WebServlet(urlPatterns = {"/video/details", "/user/videos"})
public class VideoDetails extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public VideoDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request,
	 * HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email");
		if (request.getRequestURI().contains("user/videos")) {
			userVideos(request, email);
		} else {
			videoDetail(request, email, session);
		}
		request.getRequestDispatcher("/Views/home.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request,
	 * HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private void userVideos(HttpServletRequest request, String email) {
		List<Video> videos = Favorite.getFavoriteUserVideoAll(email);
		request.setAttribute("videos", videos);
		request.setAttribute("url", "home");
	}

	private void videoDetail(HttpServletRequest request, String email, HttpSession session) {
		Integer id = Integer.parseInt(request.getParameter("id"));
		Favorite favorite = null;
		if (email != null) {
			Set<Integer> videoIds = session.getAttribute(email) == null ? new HashSet<>() : (HashSet<Integer>) session.getAttribute(email);
			videoIds.add(id);
			session.setAttribute(email, videoIds);
			favorite = Favorite.getFavoriteUserVideo(id, email);
		} else {
			Set<Integer> videoIds = session.getAttribute("client") == null ? new HashSet<>() : (HashSet<Integer>) session.getAttribute("client");
			videoIds.add(id);
			session.setAttribute("client", videoIds);
		}
		List<Video> videos = Jpa.selectAll(new Video());
		Video video = Jpa.find(videos.get(0), id);
		video.setViews(video.getViews() + 1);
		Jpa.merge(video);
		request.setAttribute("videos", videos);
		request.setAttribute("video", video);
		request.setAttribute("favorite", favorite);
		request.setAttribute("url", "video_details");
	}
}
