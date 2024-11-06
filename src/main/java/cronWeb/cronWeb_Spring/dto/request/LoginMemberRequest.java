package cronWeb.cronWeb_Spring.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginMemberRequest {
    @NotBlank(message = "아이디를 입력해주세요")
    private String name; // 아이디
    @NotBlank(message = "패스워드를 입력해주세요")
    private String password; // 비밀번호

}
