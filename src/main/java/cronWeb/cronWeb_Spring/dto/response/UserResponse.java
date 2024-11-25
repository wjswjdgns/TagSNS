package cronWeb.cronWeb_Spring.dto.response;

import cronWeb.cronWeb_Spring.domain.member.Member;
import lombok.Data;

@Data
public class UserResponse {
    private String personal;
    private String nickname;
    private String introduce;
    private String profileImg;
    private String backgroundImg;

    public UserResponse(Member member) {
        personal = member.getPersonal();
        nickname = member.getMemberInfo().getNickname();
        profileImg = member.getMemberInfo().getProfileImg();
    }

    public UserResponse(Member member, boolean check) {
        personal = member.getPersonal();
        nickname = member.getMemberInfo().getNickname();
        introduce = member.getMemberInfo().getIntroduce();
        profileImg = member.getMemberInfo().getProfileImg();
        backgroundImg = member.getMemberInfo().getBackgroundImg();
    }
}
