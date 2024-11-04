package cronWeb.cronWeb_Spring.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String Hello(Model model){
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
