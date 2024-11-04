package cronWeb.cronWeb_Spring.api;

import cronWeb.cronWeb_Spring.UserException.DuplicateName;
import cronWeb.cronWeb_Spring.dto.request.CreateMemberRequest;
import cronWeb.cronWeb_Spring.service.MemberService;
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
    public ResponseEntity<String> saveMember(@RequestBody @Validated CreateMemberRequest member){
        try {
            memberService.saveMember(member);
            return ResponseEntity.status(HttpStatus.CREATED).body("회원가입 성공");
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("회원가입 실패");
        }
    }

}
