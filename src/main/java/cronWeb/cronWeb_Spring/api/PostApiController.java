package cronWeb.cronWeb_Spring.api;
import cronWeb.cronWeb_Spring.dto.response.PostResponse;
import cronWeb.cronWeb_Spring.service.MemberService;
import cronWeb.cronWeb_Spring.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostApiController {

    private final PostService postService;


    // 글쓰기
//    @PostMapping
//    public ResponseEntity<PostResponse> write(){}

    // 포스트 받아오기 --> 조건을 주면 그에 맞게 필터되도록
    @PostMapping
    public ResponseEntity<PostResponse> getPost(){
        List<PostResponse> posts = postService.getPosts();
        return ResponseEntity.status(HttpStatus.CREATED).body((PostResponse) posts);
    }


}
