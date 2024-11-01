package cronWeb.cronWeb_Spring.domain;


import cronWeb.cronWeb_Spring.domain.member.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Notice {

    @Id
    @GeneratedValue
    @Column(name="NoticeId")
    private Long id;

    private Integer noticeStatus;
    private LocalDateTime createAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "requestMemberNotice_id")
    private Member requestMember;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "responseMemberNotice_id")
    private Member responseMember;
}
