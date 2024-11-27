package cronWeb.cronWeb_Spring.service;
import cronWeb.cronWeb_Spring.domain.member.Member;
import cronWeb.cronWeb_Spring.domain.post.Post;
import cronWeb.cronWeb_Spring.dto.request.PostRequest;
import cronWeb.cronWeb_Spring.dto.response.PostResponse;
import cronWeb.cronWeb_Spring.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    // 포스트 작성하기
    public Long posting(PostRequest postRequest, Member loginMember){
        try {
            Post post = new Post();
            // 부모 글이 있을 경우 매핑해주기
            if(postRequest.getParentId() != null){
                Post ParentPost = postRepository.findPost(postRequest.getParentId());
                post.setParent(ParentPost);
                post.setPostLevel(ParentPost.getPostLevel() + 1);
            }
            else{
                post.setPostLevel(1);
            }

            post.setContent(postRequest.getContent());
            post.setContentImg(postRequest.getContentImg());
            post.setCreateAt(LocalDateTime.now());

            post.setMember(loginMember);

            return postRepository.posting(post);
        }
        catch(DataAccessException e){
            throw new RuntimeException("포스트 작성 중 실패했습니다.");
        }
    }

    // 모든 게시물 반환
    public List<PostResponse> getPosts(){
        return postRepository.findByPosts().stream()
                .map(o -> new PostResponse(o, true))
                .collect(Collectors.toList());
    }

}

