package cronWeb.cronWeb_Spring.domain.member;

import com.fasterxml.jackson.annotation.JsonIgnore;
import cronWeb.cronWeb_Spring.domain.Follow;
import cronWeb.cronWeb_Spring.domain.Like;
import cronWeb.cronWeb_Spring.domain.Notice;
import cronWeb.cronWeb_Spring.domain.Retwit;
import cronWeb.cronWeb_Spring.domain.post.Post;
import cronWeb.cronWeb_Spring.domain.tag.Tag;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
public class Member {

    @Id
    @GeneratedValue
    @Column(name="UserId")
    private Long id;
    private String name; // 회원 아이디 값을 나타냅니다.
    private String password; // 회원의 패스워드 값을 나타냅니다.
    private String personal; // 회원의 고유 ID
    private LocalDateTime createAt; // 생성일

    // 기본 생성자 추가
    protected Member(){

    }

    public Member(String name, String password, String personal, LocalDateTime createAt) {
        this.name = name;
        this.password = password;
        this.personal = personal;
        this.createAt = createAt;
    }

    @OneToOne(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JsonIgnore
    private MemberInfo memberInfo;

    public void addMemberInfo(MemberInfo memberInfo){
        this.memberInfo = memberInfo;
        memberInfo.setMember(this);
    }

    // 팔로우
    @OneToMany(mappedBy = "requestMember", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Follow> requestMembers = new ArrayList<>(); // 직접 신청을 받은 사람

    @OneToMany(mappedBy = "responseMember", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Follow> responseMembers = new ArrayList<>();; // 직접 신청한 사람

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<Post> postList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private Set<Like> likeList = new HashSet<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private Set<Retwit> retwitList = new HashSet<>();

    // 알림
    @OneToMany(mappedBy = "requestMember", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Notice> requestMemberNotice = new ArrayList<>(); // 직접 신청을 받은 사람

    @OneToMany(mappedBy = "responseMember", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Notice> responseMemberNotice = new ArrayList<>(); // 직접 신청한 사람

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Tag> tagList = new ArrayList<>(); // 직접 신청한 사람

}
