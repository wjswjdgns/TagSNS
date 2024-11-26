package cronWeb.cronWeb_Spring.domain.post;

import cronWeb.cronWeb_Spring.domain.Like;
import cronWeb.cronWeb_Spring.domain.Retwit;
import cronWeb.cronWeb_Spring.domain.member.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Entity
@Getter
@Setter
public class Post {

    @Id @GeneratedValue
    @Column(name= "PostId")
    private Long id;
    private Integer postLevel;
    private String content;
    private String contentImg;

    private LocalDateTime createAt;
    private LocalDateTime updateAt;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="UserId")
    private Member member;

    @OneToOne(mappedBy = "post", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private PostInfo postInfo;

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    private Set<Like> likes = new HashSet<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    private Set<Retwit> retwits = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Post parent; // 부모 게시물 (댓글일 경우 해당)

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<Post> comments = new ArrayList<>(); // 자식 댓글 리스트


    // 댓글 추가하기 메서드
    public void addComment(Post comment) {
        comments.add(comment);
        comment.setParent(this);
    }

}
