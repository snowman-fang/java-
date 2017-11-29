package filter;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Administrator on 2017/9/18.
 */
@WebFilter(urlPatterns = "/*", filterName = "LoginFilter")
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        HttpServletResponse servletResponse = (HttpServletResponse) response;
        HttpSession session = servletRequest.getSession();
        String path = servletRequest.getRequestURI();
        String account = (String) session.getAttribute("account");
        if(account==null&&(path.contains("user/index")||path.contains("forum/add"))){
            servletResponse.sendRedirect("/user/login");
        }else{
            chain.doFilter(request, response);
        }
    }
    @Override
    public void destroy() {

    }
}
