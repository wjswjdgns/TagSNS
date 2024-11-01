package cronWeb.cronWeb_Spring.domain.tag;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class TagInfo {

    @Id
    @GeneratedValue
    @Column(name="TagInfoId")
    private Long id;

    private Integer useTagCount;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="TagId")
    private Tag tag;
}
