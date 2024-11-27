package cronWeb.cronWeb_Spring.service;
import cronWeb.cronWeb_Spring.domain.member.Member;
import cronWeb.cronWeb_Spring.domain.post.Post;
import cronWeb.cronWeb_Spring.domain.post.PostInfo;
import cronWeb.cronWeb_Spring.dto.request.PostRequest;
import cronWeb.cronWeb_Spring.dto.response.PostResponse;
import cronWeb.cronWeb_Spring.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
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

            Long postId = postRepository.posting(post);

            // 기본 속성 등록하기
            PostInfo postInfo = new PostInfo();
            postInfo.setRetwitCount(0);
            postInfo.setCommentCount(0);
            postInfo.setLikeCount(0);
            postRepository.savePostInfo(postId, postInfo);

            return postId;
        }
        catch(DataAccessException e){
            throw new RuntimeException("포스트 작성 중 실패했습니다.");
        }
    }

    // 모든 게시물 반환
    public List<PostResponse> getPosts(){

        List<Post> byPosts = postRepository.findByPosts();

        log.info(String.valueOf(byPosts.size()));

        List<PostResponse> collect = byPosts.stream()
                .map(o -> new PostResponse(o, true))
                .collect(Collectors.toList());

        log.info("과연 어떻게 될지 모르겠네");
        log.info(String.valueOf(collect.size()));

        return collect;
    }

}

