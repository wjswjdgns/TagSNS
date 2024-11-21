package cronWeb.cronWeb_Spring.api;

import cronWeb.cronWeb_Spring.SessionConst;
import cronWeb.cronWeb_Spring.domain.member.Member;
import cronWeb.cronWeb_Spring.dto.request.CreateMemberRequest;
import cronWeb.cronWeb_Spring.dto.request.LoginMemberRequest;
import cronWeb.cronWeb_Spring.dto.response.ServerResponse;
import cronWeb.cronWeb_Spring.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberApiController {
    private final MemberService memberService;

    @PostMapping("new/member")
    public ResponseEntity<ServerResponse> saveMember(@RequestBody @Validated CreateMemberRequest member){
        memberService.saveMember(member);
        ServerResponse response = new ServerResponse(HttpStatus.CREATED.value(), "회원가입 성공", null);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<ServerResponse> login(@RequestBody @Validated LoginMemberRequest member, HttpServletRequest request){

        Long loginId = memberService.login(member);
        Member loginMember = memberService.getMember(loginId);

        ServerResponse response = new ServerResponse(HttpStatus.CREATED.value(), "로그인 성공", null);
        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<ServerResponse> logout(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session != null){
            session.invalidate();
        }

        ServerResponse response = new ServerResponse(HttpStatus.CREATED.value(), "로그아웃 성공", null);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
