package ait.cohort34.post.controller;


import ait.cohort34.post.dto.DatePeriodDto;
import ait.cohort34.post.dto.NewCommentDto;
import ait.cohort34.post.dto.NewPostDto;
import ait.cohort34.post.dto.PostDto;
import ait.cohort34.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;


@RestController //в Spring Framework используется для обозначения класса,
// который обрабатывает HTTP-запросы и возвращает ответы. Как правило, этот класс служит контроллером в архитектуре MVC (Model-View-Controller)
// и используется для создания веб-сервисов RESTful.
@RequiredArgsConstructor //является частью проекта Lombok и используется для создания конструктора,
// принимающего все обязательные аргументы полей класса, помеченных аннотацией @NonNull или финальных полей.
// Она генерирует конструктор, принимающий только эти обязательные аргументы.
@RequestMapping("/forum")
public class PostController {

    final PostService postService;

    @PostMapping("/post/{author}")
    public PostDto addNewPost(@PathVariable String author, @RequestBody NewPostDto newPostDto) {
        return postService.addNewPost(author, newPostDto);
    }

    @GetMapping("/post/{id}")
    public PostDto findPostById(@PathVariable String id) {
        return postService.findPostById(id);
    }

    @DeleteMapping("/post/{id}")
    public PostDto removePost(@PathVariable String id) {
        return postService.removePost(id);
    }

    @PutMapping("/post/{id}")
    public PostDto updatePost(@PathVariable String id, @RequestBody NewPostDto newPostDto) {
        return postService.updatePost(id, newPostDto);
    }

    @PutMapping("/post/{id}/comment/{author}")
    public PostDto addComment(@PathVariable String id, @PathVariable String author, @RequestBody NewCommentDto newCommentDto) {
        return postService.addComment(id, author, newCommentDto);
    }

    @PutMapping("/post/{id}/like")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addLike(@PathVariable String id) {
        postService.addLike(id);
    }

    @GetMapping("/posts/author/{author}")
    public Iterable<PostDto> findPostsByAuthor(@PathVariable String author) {
        return postService.findPostsByAuthor(author);
    }

    @PostMapping("/posts/tags")
    public Iterable<PostDto> findPostsByTags(@RequestBody Set<String> tags) {
        return postService.findPostByTags(tags);
    }

    @PostMapping("/posts/period")
    public Iterable<PostDto> findPostsByPeriod(@RequestBody DatePeriodDto datePeriodDto) {
        return postService.findPostsByPeriod(datePeriodDto);
    }
}
