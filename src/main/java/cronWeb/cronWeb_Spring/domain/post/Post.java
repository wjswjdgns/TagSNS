package cronWeb.cronWeb_Spring.domain.post;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

public class Post {

    @Id @GeneratedValue
    @Column(name= "PostId")
    private Long id;
    private Integer postLevel;
    private String content;
    private String contentImg;

    private LocalDateTime createAt;
    private LocalDateTime updateAt;


    // 좋아요, 리트윗, 댓글 수

}
