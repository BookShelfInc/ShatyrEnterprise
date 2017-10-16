import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//@WebFilter(urlPatterns = {"/*"})
public class SecurityFilter implements Filter {

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {


        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)resp;

        HttpSession userSession = request.getSession();

        if (userSession == null || userSession.getAttribute("AUTHENTICATED") == null) {
//            response.sendRedirect("login.jsp");
        	request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        } else {
            chain.doFilter(req, resp);
        }

    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {

    }

}
