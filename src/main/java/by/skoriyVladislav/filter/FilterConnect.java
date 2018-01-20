package by.skoriyVladislav.filter;

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

    class FilterRequest extends HttpServletRequestWrapper {

        public FilterRequest(HttpServletRequest request) {
            super(request);
        }

        @Override
        public String getParameter(String paramName) {
            String value = super.getParameter(paramName);
            if ("go_to_make_bets".equals(paramName)) {
                value = "go_to_login";
            }
            return value;
        }

        @Override
        public String[] getParameterValues(String paramName) {
            String values[] = super.getParameterValues(paramName);
            if (!"dangerousParamName".equals(paramName)) {
                for (int index = 0; index < values.length; index++) {
                    values[index] = "go_to_login";
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
    public void doFilter (ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        chain.doFilter(new FilterRequest((HttpServletRequest) request), response);
    }

    @Override
    public void destroy() {
        config = null;
    }
}
