package assignment;

import dev.ta2khu75.util.SendMail;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.UUID;
import models.User;
import util.Jpa;

/**
 * Servlet implementation class UserService
 */
@WebServlet(urlPatterns = {"/login", "/register", "/logout", "/change", "/forget"})
public class UserService extends HttpServlet {

	private String code;
	private String email;
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserService() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request,
	 * HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		// TODO Auto-generated method stub
		// Map<String, String> user=new HashMap<>();
		boolean check = false;
		if (request.getRequestURI().contains("login")) {
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					if (cookie.getName().equals("email") || cookie.getName().equals("password")) {
						//user.put(cookie.getName(), cookie.getValue());
						request.setAttribute(cookie.getName(), cookie.getValue());
						cookie.setMaxAge(60 * 60 * 12);
						response.addCookie(cookie);
						HttpSession session = request.getSession();
						if (cookie.getName().equals("email")) {
							check = true;
							session.setAttribute(cookie.getName(), cookie.getValue());
						}
					}
				}
				if (check) {
					//User user=Jpa.find(User, this)
					response.sendRedirect("/assignment");
					return;
				}
			}

			request.setAttribute("url", "login");
		} else if (request.getRequestURI().contains("logout")) {
			Cookie[] cookies = request.getCookies();
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("email") || cookie.getName().equals("password")) {
					cookie.setMaxAge(0);
					response.addCookie(cookie);
				}
			}
			HttpSession httpSession = request.getSession();
			httpSession.removeAttribute("email");
			//response.sendRedirect("/assignment/login");
			//return;
			request.setAttribute("url", "login");
		} else if (request.getRequestURI().contains("forget")) {
			String emailUser = request.getParameter("email");
			{
				User user = Jpa.find(new User(), emailUser);
				if (user == null) {
					request.setAttribute("url", "login");
					request.setAttribute("message", "Email not existing");
				} else {
					email = emailUser;
					code = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
					SendMail.sendVerification(email, code);
					request.setAttribute("url", "forget");
				}
			}
		} else if (request.getRequestURI().contains("change")) {
			request.setAttribute("url", "change");
		} else {
			request.setAttribute("url", "register");
		}
		request.getRequestDispatcher("/Views/home.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request,
	 * HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			User user = getUser(request);
			if (request.getRequestURI().contains("login")) {
				boolean result = login(user, response, request);
				if (result) {
					return;
				}
			} else if (request.getRequestURI().contains("change")) {
				boolean result = change(request);
				if (!result) {
					response.sendRedirect("/assignment/login");
					return;
				}
				request.setAttribute("url", "change");
			} else if (request.getRequestURI().contains("forget")) {
				boolean result = forget(request);
				if (!result) {
					response.sendRedirect("/assignment/login");
					return;
				}
				request.setAttribute("url", "forget");
			} else {
				register(user, response, request);
			}
			request.getRequestDispatcher("/Views/home.jsp").forward(request, response);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private User getUser(HttpServletRequest request) throws IllegalAccessException, InvocationTargetException {
		User user = new User();
		BeanUtils.populate(user, request.getParameterMap());
		return user;
	}

	private boolean login(User user, HttpServletResponse resp, HttpServletRequest request)
		throws IOException, ServletException {
		// TODO Auto-generated method stub
		User existingUser = Jpa.find(user, user.getEmail());
		if (user != null && existingUser != null) {
			if (existingUser.getPassword().equals(user.getPassword())) {
				HttpSession session = request.getSession();
				session.setAttribute("email", user.getEmail());
				Cookie[] cookies = {new Cookie("email", user.getEmail()), new Cookie("password", user.getPassword())};
				for (Cookie cookie : cookies) {
					cookie.setPath("/");
					cookie.setMaxAge(60 * 60 * 12);
					if (request.getParameter("remember") == null) {
						cookie.setMaxAge(0);
					}
					resp.addCookie(cookie);
				}
				resp.sendRedirect("/assignment");
				return true;
			}
		}
		String message = "Email or password inCorrect";
		request.setAttribute("message", message);
		request.setAttribute("url", "login");
		return false;
	}

	private void register(User user, HttpServletResponse resp, HttpServletRequest request) throws ServletException, IOException {
		User existingUser = Jpa.find(user, user.getEmail());
		String message = "";
		if (existingUser == null) {
			if (user.getPassword().trim().equals(request.getParameter("confirm"))) {
				boolean result = Jpa.presist(user);
				if (result) {
					message = "Successfully";
				} else {
					message = "Failed";
				}
			} else {
				message = "Password not match";
			}
		} else {
			message = "Email already exist";
		}
		request.setAttribute("message", message);
		request.setAttribute("url", "register");
	}

	public boolean change(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String emaiString = session.getAttribute("email").toString();
		User user = Jpa.find(new User(), emaiString);
		if (user == null) {
			session.removeAttribute("email");
			return false;
		} else {
			String newPassword = request.getParameter("newPassword");
			String oldPassword = request.getParameter("oldPassword");
			String confirmPassword = request.getParameter("confirmPassword");
			if (user.getPassword().equals(oldPassword)) {
				if (newPassword.equals(confirmPassword)) {
					user.setPassword(newPassword);
					boolean result = Jpa.merge(user);
					if (result) {
						request.setAttribute("message", "Change successfully");
					} else {
						request.setAttribute("message", "Change password false");
					}
				} else {
					request.setAttribute("message", "New password and confirm password not math");
				}
			} else {
				request.setAttribute("message", "Old password incorrect");
			}
			return true;
		}

	}

	public boolean forget(HttpServletRequest request) {
		User user = Jpa.find(new User(), email);
		if (user == null) {
			email = null;
			return false;
		} else {
			String verification = request.getParameter("code");
			String newPassword = request.getParameter("newPassword");
			String confirmPassword = request.getParameter("confirmPassword");
			if (code.equals(verification)) {
				if (newPassword.equals(confirmPassword)) {
					user.setPassword(newPassword);
					boolean result = Jpa.merge(user);
					if (result) {
						request.setAttribute("message", "Change successfully");
					} else {
						request.setAttribute("message", "Change password false");
					}
				} else {
					request.setAttribute("message", "New password and confirm password not math");
				}
			} else {
				request.setAttribute("message", "Old password incorrect");
			}
			return true;
		}

	}

}
