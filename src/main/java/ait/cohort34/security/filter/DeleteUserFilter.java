package ait.cohort34.security.filter;

import ait.cohort34.accounting.model.Role;
import ait.cohort34.security.model.User;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Order(40)
public class DeleteUserFilter implements Filter {


    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        if (checkEndPoint(request.getMethod(), request.getServletPath())) {
            User user = (User) request.getUserPrincipal();
            String[] arr = request.getServletPath().split("/");
            String owner = arr[arr.length - 1];
            if (!(user.getRoles().contains(Role.ADMINISTRATOR.name()) || user.getName().equalsIgnoreCase(owner))) {
                response.sendError(403, "Permission denied");
                return;
            }
        }
        chain.doFilter(request, response);
    }

    private boolean checkEndPoint(String method, String path) {
        // Проверяем, является ли метод запроса DELETE и конечная точка соответствует шаблону
        return HttpMethod.DELETE.matches(method) && path.matches("/account/user/\\w+");
    }
}

