/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.User;
import util.Jpa;

/**
 *
 * @author ta2khu75
 */
@WebFilter(urlPatterns = {"/admin/*"})
public class Manager implements Filter {

    @Override
    public void doFilter(ServletRequest sr, ServletResponse sr1, FilterChain fc) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) sr;
        HttpServletResponse resp = (HttpServletResponse) sr1;
        String email=req.getSession().getAttribute("email").toString();
        if (email == null) {
            resp.sendRedirect("/assignment/login");
        } else {
        	User user=Jpa.find(new User(), email);
        	if(user.getAdmin()){
                fc.doFilter(sr, sr1);
            }else{
                resp.sendRedirect("/assignment/login");
            }
        }
    }

}
