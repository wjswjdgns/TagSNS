package cronWeb.cronWeb_Spring.domain.member;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class MemberInfo {

    @Id
    @GeneratedValue
    @Column(name="MemberInfoId")
    private Long id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="UserId")
    private Member member;
    private String nickname;
    private String introduce;
    private String profileImg;
    private String backgroundImg;
    private LocalDateTime updateAt;

}
