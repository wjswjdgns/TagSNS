package cronWeb.cronWeb_Spring.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateMemberRequest {
    @NotBlank(message = "아이디를 입력해주세요")
    private String name;

    @NotBlank(message = "패스워드를 입력해주세요")
    @Size(min = 8, message = "비밀번호는 최소 8자리 이상이어야 합니다.")
    private String password;

    @NotBlank(message = "닉네임을 입력해주세요")
    private String nickname;

    @NotBlank(message = "고유 ID를 입력해주세요")
    private String uniqueId;

}
