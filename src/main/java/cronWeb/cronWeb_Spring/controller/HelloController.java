package cronWeb.cronWeb_Spring.controller;


import cronWeb.cronWeb_Spring.dto.request.CreateMemberRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("login")
    public String start(Model model){
        return "base/index";
    }

    @GetMapping("signup")
    public String signup(Model model){
        return "base/signup";
    }

    @GetMapping("main")
    public String main(Model model){
        return "base/main";
    }
    @GetMapping("mypage")
    public String mypage(Model model){
        return "base/mypage";
    }
}
