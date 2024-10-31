package cronWeb.cronWeb_Spring.domain.member;

import cronWeb.cronWeb_Spring.domain.Follow;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id
    @GeneratedValue
    @Column(name="UserId")
    private Long id;
    private String name; // 회원 아이디 값을 나타냅니다.
    private String password; // 회원의 패스워드 값을 나타냅니다.
    private String personal; // 회원의 고유 ID
    private LocalDateTime createAt; // 생성일

    @OneToOne(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private MemberInfo memberInfo;
    // 팔로우
    @OneToMany(mappedBy = "requestMember", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Follow> requestMembers = new ArrayList<>(); // 직접 신청을 받은 사람

    @OneToMany(mappedBy = "responseMember", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Follow> responseMembers = new ArrayList<>();; // 직접 신청한 사람


    // Member를 기준으로 profile(프로필),follow(팔로우 목록),post(사용자 작성 포스트),action (각 포스트에 대한 반응),userTag (사용자가 만든 태그), notice (알림)

}
