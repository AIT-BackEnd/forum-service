package ait.cohort34.post.dto;

import lombok.*;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder // для создания обьектов в Set и List
public class PostDto {
    private String id;
    private String title;
    private String content;
    private String author;
    private LocalDateTime dateCreated;
    @Singular //создание обьекта по одному
    private Set<String> tags;
    private Integer likes;
    @Singular
    private List<CommentDto> comments;
}
