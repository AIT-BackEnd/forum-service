package ait.cohort34.security.filter;


import ait.cohort34.accounting.model.Role;
import ait.cohort34.post.dao.PostRepository;
import ait.cohort34.post.model.Post;
import ait.cohort34.security.model.User;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Order(60)
public class DeletePostFilter implements Filter {
    final PostRepository postRepository;

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        if (checkEndPoint(request.getMethod(), request.getServletPath())) {
            User user = (User) request.getUserPrincipal();
            String[] arr = request.getServletPath().split("/");
            String postId = arr[arr.length - 1]; // Идентификатор поста из пути запроса
            Post post = postRepository.findById(postId).orElse(null);
            if (post == null) {
                response.sendError(404, "Not found");
                return;
            }
            // Проверяем, является ли пользователь модератором или автором поста
            if (!(user.getRoles().contains(Role.MODERATOR.name()) || user.getName().equals(post.getAuthor()))) {
                response.sendError(403, "Not authorized to delete this post");
                return;
            }


        }
        chain.doFilter(request, response);
    }

    private boolean checkEndPoint(String method, String path) {
        return (HttpMethod.DELETE.matches(method) && path.matches("/forum/post/\\w+"));
    }
}
