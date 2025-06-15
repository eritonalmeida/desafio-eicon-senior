package br.com.eicon.desafio.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import static org.springframework.core.Ordered.HIGHEST_PRECEDENCE;
import java.io.IOException;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(HIGHEST_PRECEDENCE)
@Component
public class CorsFilter implements Filter {

    @Override
    public void doFilter(final ServletRequest req, final ServletResponse resp, final FilterChain chain) throws IOException, ServletException {

        HttpServletResponse response = (HttpServletResponse) resp;

        HttpServletRequest request = (HttpServletRequest) req;

        response.setHeader("Access-Control-Allow-Origin", "*");

        response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, OPTIONS");

        response.setHeader("Access-Control-Max-Age", "3600");

        response.setHeader("Access-Control-Allow-Headers", "X-Requested-With, Authorization");

        if ("OPTIONS".equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            return;
        }

        chain.doFilter(req, resp);
    }
}
