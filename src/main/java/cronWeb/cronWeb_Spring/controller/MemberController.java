package cronWeb.cronWeb_Spring.controller;


import cronWeb.cronWeb_Spring.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor //lombok 기능
public class MemberController {
    private final MemberService memberService;

}
