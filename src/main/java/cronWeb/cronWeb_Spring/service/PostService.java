package cronWeb.cronWeb_Spring.service;
import cronWeb.cronWeb_Spring.dto.response.PostResponse;
import cronWeb.cronWeb_Spring.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    // 모든 게시물 반환
    public List<PostResponse> getPosts(){
        return postRepository.findByPosts().stream()
                .map(o -> new PostResponse(o, true))
                .collect(Collectors.toList());
    }

}

