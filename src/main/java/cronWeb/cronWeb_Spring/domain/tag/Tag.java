package cronWeb.cronWeb_Spring.domain.tag;

import cronWeb.cronWeb_Spring.domain.member.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Tag {

    @Id @GeneratedValue
    @Column(name="TagId")
    private Long id;
    private String tagName;

    @OneToOne(mappedBy = "tag",fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private TagInfo tagInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="UserId")
    private Member member;
}
