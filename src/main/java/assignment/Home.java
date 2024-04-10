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
import models.Video;
import util.Jpa;

/**
 * Servlet implementation class Home
 */
@WebServlet(urlPatterns = {"/"})
public class Home extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Home() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request,
	 * HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		double numberVideos = Jpa.selectAll(new Video()).size();
		double sizePage=6.0;
		int maxPage = (int) ((double) Math.ceil(numberVideos /sizePage));
		int[] increasingArray = new int[maxPage];
		for (int i = 0; i < maxPage; i++) {
			increasingArray[i] = i + 1;
		}
		HttpSession session = request.getSession();
		int page = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));
		List<Video> videos = Video.getPageVideo(page, 6);
		String email = (String) session.getAttribute("email");
		Set<Integer> videoIds;
		if (email != null) {
			videoIds = session.getAttribute(email) == null ? new HashSet<>() : (HashSet<Integer>) session.getAttribute(email);
		} else {
			videoIds = session.getAttribute("client") == null ? new HashSet<>() : (HashSet<Integer>) session.getAttribute("client");
		}
		request.setAttribute("maxPage", maxPage);
		request.setAttribute("numPages",increasingArray );
		request.setAttribute("page", page);
		request.setAttribute("videoIds", videoIds);
		request.setAttribute("videos", videos);
		request.setAttribute("url", "home");
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

}
