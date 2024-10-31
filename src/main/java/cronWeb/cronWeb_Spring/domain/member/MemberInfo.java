package cronWeb.cronWeb_Spring.domain.member;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class MemberInfo {

    @Id
    @GeneratedValue
    @Column(name="ProfileId")
    private Long id;
    @OneToOne
    @JoinColumn(name="UserId")
    private Member member;
    private String nickname;
    private String introduce;
    private String profileImg;
    private String backgroundImg;
    private LocalDateTime updateAt;

}
