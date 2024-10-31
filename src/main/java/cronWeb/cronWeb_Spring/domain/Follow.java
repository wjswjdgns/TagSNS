package cronWeb.cronWeb_Spring.domain;

import cronWeb.cronWeb_Spring.domain.member.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Follow {

    @Id
    @GeneratedValue
    @Column(name="FollowId")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "requestMember_id")
    private Member requestMember; // 팔로워

    @ManyToOne
    @JoinColumn(name = "responseMember_id")
    private Member responseMember; // 팔로잉

}
