package cronWeb.cronWeb_Spring.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PostRequest {
    private Long ParentId; // 부모 Id
    @NotBlank(message="글을 작성해주세요")
    private String content; // 본문
    private String contentImg; // 본문 이미지
    private String tag; // 태그
}
