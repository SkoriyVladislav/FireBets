package by.skoriyVladislav.servlet.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;

@WebFilter(
        filterName = "SecurityFilter",
        servletNames = {"MainController"}
)
public class FilterConnect implements Filter {
    private FilterConfig config = null;
    private boolean active = true;

    @Override
    public void doFilter (ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (((HttpServletRequest) request).getSession().getAttribute("user") == null) {
            chain.doFilter(new FilterRequest((HttpServletRequest) request), response);
        } else {
            chain.doFilter( request, response);
        }
    }
    class FilterRequest extends HttpServletRequestWrapper {

        public FilterRequest(HttpServletRequest request) {
            super(request);
        }

        @Override
        public String getParameter(String paramName) {
            String value = super.getParameter(paramName);
            try {
                BadCommands.valueOf(value.toUpperCase());
                return "go_to_login";
            } catch (IllegalArgumentException ex) {
                return value;
            }
        }

        @Override
        public String[] getParameterValues(String paramName) {
            String values[] = super.getParameterValues(paramName);

            for (int index = 0; index < values.length; index++) {
                try {
                    BadCommands.valueOf(values[index].toUpperCase());
                    values[index] = "go_to_login";
                } catch (IllegalArgumentException ex) {

                }
            }

            return values;
        }
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
        this.config = config;
        String act = config.getInitParameter("active");
        if (act != null) {
            active = (act.toUpperCase().equals("TRUE"));
        }
    }


    @Override
    public void destroy() {
        config = null;
    }
}
