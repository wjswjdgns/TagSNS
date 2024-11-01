package cronWeb.cronWeb_Spring.domain.post;

import cronWeb.cronWeb_Spring.domain.Like;
import cronWeb.cronWeb_Spring.domain.Retwit;
import cronWeb.cronWeb_Spring.domain.member.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
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

}
