package ait.cohort34.security;


import ait.cohort34.accounting.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;

@Configuration
@RequiredArgsConstructor
public class AuthorizationConfiguration {
    final CustomWebSecurity webSecurity;

    @Bean
    SecurityFilterChain web(HttpSecurity http) throws Exception {
        // Конфигурация базовой HTTP аутентификации
        http.httpBasic(Customizer.withDefaults());
        // Отключение CSRF защиты
        http.csrf(csrf -> csrf.disable());
        //управление сессиями
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS));
        // Настройка прав доступа для различных URL-адресов и методов HTTP
        http.authorizeHttpRequests(authorize -> authorize
                // Открытый доступ
                .requestMatchers("/account/register", "/forum/posts/**").permitAll()
                // Только для администраторов
                .requestMatchers("/account/user/{login}/role/{role}").hasRole(Role.ADMINISTRATOR.name())
                // Проверка имени пользователя при обновлении данных
                .requestMatchers(HttpMethod.PUT, "/account/user/{login}").access(new WebExpressionAuthorizationManager("#login == authentication.name"))
                // Проверка имени пользователя или роли администратора при удалении данных
                .requestMatchers(HttpMethod.DELETE, "/account/user/{login}").access(new WebExpressionAuthorizationManager("#login == authentication or hasRole('ADMINISTRATOR')"))
                // Проверка авторства при создании поста
                .requestMatchers(HttpMethod.POST, "/forum/post/{author}").access(new WebExpressionAuthorizationManager("#author == authentication.name"))
                // Проверка авторства при обновлении комментария к посту
                .requestMatchers(HttpMethod.PUT, "/forum/post/{id}/comment/{author}").access(new WebExpressionAuthorizationManager("#author== authentication.name"))
                // Проверка авторства поста или модераторских прав
                .requestMatchers(HttpMethod.PUT, "/forum/post/{id}").access(((authentication, context) -> new AuthorizationDecision(webSecurity.checkPostAuthor(context.getVariables().get("id"), authentication.get().getName()))))
                // Проверка авторства или роли модератора при удалении поста
                .requestMatchers(HttpMethod.DELETE, "/forum/post/{id}").access((authentication, context) -> {
                    boolean checkAuthor = webSecurity.checkPostAuthor(context.getVariables().get("id"), authentication.get().getName());
                    boolean checkModerator = context.getRequest().isUserInRole(Role.MODERATOR.name());
                    return new AuthorizationDecision(checkAuthor || checkModerator);
                })
                .anyRequest().authenticated() // Все остальные запросы требуют аутентификации
        );
        return http.build(); // Возврат созданной конфигурации
    }
}