package cronWeb.cronWeb_Spring.domain.tag;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Tag {

    @Id @GeneratedValue
    @Column(name="TagId")
    private Long id;
    private String tagName;

    // mapping post id list, notice list,
}
