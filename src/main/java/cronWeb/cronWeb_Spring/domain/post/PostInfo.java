package cronWeb.cronWeb_Spring.domain.post;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class PostInfo {

    @Id
    @GeneratedValue
    @Column(name= "PostInfoId")
    private Long id;
    private Integer likeCount; // 좋아요 갯수
    private Integer retwitCount; // 리트윗 갯수
    private Integer commentCount; // 댓글 갯수

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="PostId")
    private Post post;
}
