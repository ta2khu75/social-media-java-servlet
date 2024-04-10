package admin;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.util.UUID;
import models.Video;
import util.CodeExcel;
import util.Excel;
import util.Jpa;

/**
 * Servlet implementation class Video
 */
@MultipartConfig
@WebServlet(urlPatterns = {"/admin/video", "/admin/video/insert", "/admin/video/update", "/admin/video/delete", "/admin/video/find", "/admin/video/export", "/admin/video/import"})
public class AdminVideo extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminVideo() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 * response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("id") == null ? "0" : request.getParameter("id"));//.getParameter("id");
		Video video = new Video();
		String message = "";
		List<Video> videos = Jpa.selectAll(video);
		if (request.getRequestURI().contains("find")) {
			video = Jpa.find(new Video(), id);
		} else if (request.getRequestURI().contains("delete")) {
			message = Jpa.remove(new Video(), id) ? "Complete" : "Failed!";
			videos = Jpa.selectAll(video);
		} else if (request.getRequestURI().contains("export")) {
			try {
				String path = String.format("/home/ta2khu75/NetBeansProjects/assignment/excel/Video_%s.xlsx", UUID.randomUUID().toString());
				Excel.exportToExcel(videos, path);
				message = "export data successfully at" + path;
			} catch (Exception e) {
				message = "export data failded with error: " + e.getMessage();
			}
		}
		request.setAttribute("videos", videos);
		request.setAttribute("video", video);
		request.setAttribute("message", message);
		request.setAttribute("url", "video");
		if (request.getRequestURI().contains("import")) {
			request.setAttribute("url", "import");
		}
		request.getRequestDispatcher("/Views/admin.jsp").forward(request, response);
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 * response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		// TODO Auto-generated method stub
		if (request.getRequestURI().contains("import")) {
			File dir = new File(request.getServletContext().getRealPath("/files"));
			if (!dir.exists()) {
				dir.mkdirs();
			}
			Part picture = request.getPart("excel");
			File filePicture = new File(dir, picture.getSubmittedFileName());
			picture.write(filePicture.getAbsolutePath());
			CodeExcel.excelToDatabase(filePicture.getAbsolutePath());
			response.sendRedirect("/assignment/admin/video");
			return;
		}
		try {
			String message = "";
			Video video = getVideo(request, response);
			video.setDescription(video.getDescription());
			if (request.getRequestURI().contains("insert")) {
				System.out.println("Insert video");
				message = Jpa.presist(video) ? "Complete" : "Failed";
			} else {
				message = Jpa.merge(video) ? "Complete" : "Failed";
			}
			List<Video> videos = Jpa.selectAll(new Video());
			request.setAttribute("message", message);
			request.setAttribute("videos", videos);
			request.setAttribute("url", "video");
			request.getRequestDispatcher("/Views/admin.jsp").forward(request, response);
		} catch (IllegalAccessException | InvocationTargetException | ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Video getVideo(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException, IllegalAccessException, InvocationTargetException {
		Video video = new Video();
		BeanUtils.populate(video, request.getParameterMap());
		return video;
	}
}
