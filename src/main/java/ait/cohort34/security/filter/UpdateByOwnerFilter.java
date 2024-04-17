package ait.cohort34.security.filter;

import ait.cohort34.security.model.User;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
//Эта аннотация говорит Spring'у, что этот класс является компонентом, который нужно управлять внутри контейнера приложения
@Order(30) // Устанавливает порядок выполнения фильтра в цепочке фильтров. В данном случае, он имеет приоритет 30
public class UpdateByOwnerFilter implements Filter {

    @Override
    //Это метод, который вызывается при каждом запросе. Он принимает запрос (ServletRequest),
    //ответ (ServletResponse) и цепочку фильтров (FilterChain), через которые должен пройти запрос.
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        //Здесь выполняется приведение типов, чтобы можно было использовать методы из HttpServletRequest и HttpServletResponse.
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        // Проверяет, соответствует ли текущий запрос определенному пути и методу.
        if (checkEndPoint(request.getMethod(), request.getServletPath())) {
            //Получает текущего пользователя из объекта запроса. Предполагается, что он был предварительно аутентифицирован.
            User principal = (User) request.getUserPrincipal();
            // Разделяет путь запроса по / и извлекает из него имя владельца, которое является последним элементом в пути.
            String[] parts = request.getServletPath().split("/");
            String owner = parts[parts.length - 1];
            // Проверяем, соответствует ли текущий пользователь владельцу ресурса
            if (!principal.getName().equalsIgnoreCase(owner)) {
                // Если нет, возвращаем ошибку доступа
                response.sendError(403, owner + " Not authorized");
                return;
            }
        }
        // Пропускаем запрос дальше по цепочке фильтров
        chain.doFilter(request, response);
    }

    // Метод для проверки конечной точки запроса на соответствие шаблону и методу PUT
    private boolean checkEndPoint(String method, String path) {
        // Проверяем метод запроса и путь с помощью регулярного выражения
        return (HttpMethod.PUT.matches(method) && path.matches("/account/user/\\w+"))
                || (HttpMethod.POST.matches(method) && path.matches("/forum/post/\\w+"))
                || (HttpMethod.PUT.matches(method) && path.matches("/forum/post/\\w+/comment/\\w+"));
    }
}
