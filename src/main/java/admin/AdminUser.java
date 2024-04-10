package admin;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.UUID;
import models.User;
import util.Excel;
import util.Jpa;

/**
 * Servlet implementation class user
 */
@WebServlet(urlPatterns = { "/admin/user", "/admin/user/insert", "/admin/user/update", "/admin/user/delete",
		"/admin/user/find", "/admin/user/export" })
public class AdminUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminUser() {
        super();
        // TODO Auto-generated constructor stub
    }
protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email=request.getParameter("id");
		User user=new User();
		String message="";
		List<User> users=Jpa.selectAll(user);
		if(request.getRequestURI().contains("find")) {
			user=Jpa.find(new User(), email);
		}else if(request.getRequestURI().contains("delete")) {
			message=Jpa.remove(new User(), email)?"Complete":"Failed!";
		}else if(request.getRequestURI().contains("export")){
			try {
				String path=String.format("/home/ta2khu75/NetBeansProjects/assignment/excel/User_%s.xlsx", UUID.randomUUID().toString());
				Excel.exportToExcel(users, path);
				message="export data successfully"+path;
			} catch (Exception e) {
				message="export data failded with error: "+e.getMessage();
			}
			
		}
		request.setAttribute("users", users);
		request.setAttribute("user", user);
		request.setAttribute("message", message);
		request.setAttribute("url", "user");
		request.getRequestDispatcher("/Views/admin.jsp").forward(request, response);
		// TODO Auto-generated method stub
	}
 
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			String message = "";
			User user = getUser(request, response);
			if (request.getRequestURI().contains("insert")) {
				message = Jpa.presist(user) ? "Complete" : "Failed";
			} else {
				message = Jpa.merge(user) ? "Complete" : "Failed";
			}
			List<User> users = Jpa.selectAll(new User());
			request.setAttribute("message", message);
			request.setAttribute("users", users);
			request.setAttribute("url", "user");
			request.getRequestDispatcher("/Views/admin.jsp").forward(request, response);
		} catch (IllegalAccessException | InvocationTargetException | ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public User getUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, IllegalAccessException, InvocationTargetException {
		User user = new User();
		BeanUtils.populate(user, request.getParameterMap());
		return user;
	}
}
