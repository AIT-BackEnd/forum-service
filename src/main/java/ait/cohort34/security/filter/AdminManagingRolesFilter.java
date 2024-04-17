package ait.cohort34.security.filter;

import ait.cohort34.accounting.dao.UserAccountRepository;
import ait.cohort34.accounting.model.Role;
import ait.cohort34.accounting.model.UserAccount;
import ait.cohort34.security.model.User;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.Principal;

@Component
@Order(20)
public class AdminManagingRolesFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        // Проверяем, является ли конечная точка запроса той, которая требует проверки ролей
        if (checkEndPoint(request.getMethod(), request.getServletPath())) {
            // Получаем текущего пользователя
            User principal = (User) request.getUserPrincipal();
            // Проверяем, имеет ли пользователь необходимую роль
            if (!principal.getRoles().contains(Role.ADMINISTRATOR.name())) {
                // Если нет, возвращаем ошибку доступа
                response.sendError(403, "You are not allowed to access this resource");
                return;
            }
        }
        // Пропускаем запрос дальше по цепочке фильтров
        chain.doFilter(request, response);
    }

    // Метод для проверки конечной точки запроса на соответствие шаблону
    private boolean checkEndPoint(String method, String path) {
        // Проверяем путь запроса с помощью регулярного выражения
        return (path.matches("/account/user/\\w+/roles/\\w+"));
    }
}
