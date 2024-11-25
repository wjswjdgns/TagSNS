package cronWeb.cronWeb_Spring.controller;


import cronWeb.cronWeb_Spring.argumentresolver.Login;
import cronWeb.cronWeb_Spring.domain.member.Member;
import cronWeb.cronWeb_Spring.dto.response.UserResponse;
import cronWeb.cronWeb_Spring.service.DtoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HelloController {

    private final DtoService dtoService;

    @GetMapping("login")
    public String start(Model model){
        return "base/index";
    }

    @GetMapping("signup")
    public String signup(Model model){
        return "base/signup";
    }
    @GetMapping("main")
    public String main(@Login Member loginMember, Model model){

        UserResponse profile = new UserResponse(loginMember);
        model.addAttribute("userInfo", profile);

        return "base/main";
    }

    @GetMapping("mypage")
    public String mypage(Model model){
        return "base/mypage";
    }
}
