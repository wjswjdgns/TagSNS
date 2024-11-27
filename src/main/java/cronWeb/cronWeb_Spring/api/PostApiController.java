package cronWeb.cronWeb_Spring.api;
import cronWeb.cronWeb_Spring.SessionConst;
import cronWeb.cronWeb_Spring.argumentresolver.Login;
import cronWeb.cronWeb_Spring.domain.member.Member;
import cronWeb.cronWeb_Spring.dto.request.PostRequest;
import cronWeb.cronWeb_Spring.dto.response.PostResponse;
import cronWeb.cronWeb_Spring.dto.response.ServerResponse;
import cronWeb.cronWeb_Spring.service.MemberService;
import cronWeb.cronWeb_Spring.service.PostService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostApiController {

    private final PostService postService;

    // 포스트 작성하기
    @PostMapping("new/post")
    public ResponseEntity<ServerResponse> sentPost(@Login Member loginMember, @RequestBody @Validated PostRequest postRequest){
        postService.posting(postRequest, loginMember);
        ServerResponse response = new ServerResponse(HttpStatus.CREATED.value(), "포스팅 성공", null);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // 포스트 받아오기 --> 조건을 주면 그에 맞게 필터되도록
    @PostMapping
    public ResponseEntity<PostResponse> getPost(){
        List<PostResponse> posts = postService.getPosts();
        return ResponseEntity.status(HttpStatus.CREATED).body((PostResponse) posts);
    }


}
