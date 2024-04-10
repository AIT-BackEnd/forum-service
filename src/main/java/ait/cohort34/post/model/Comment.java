package ait.cohort34.post.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@EqualsAndHashCode(of = {"user", "dateCreated"})
@NoArgsConstructor//дефолтный конструктор
public class Comment {
    @Setter
    private String user;
    @Setter
    private String message;
    private LocalDateTime dateCreated = LocalDateTime.now();
    private int likes;

    public Comment(String user, String message) {
        this.user = user;
        this.message = message;
    }

    public void addLike() {
        likes++;
    }
}
