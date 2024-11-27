package cronWeb.cronWeb_Spring.dto.response;
import cronWeb.cronWeb_Spring.domain.post.Post;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class PostResponse {

    private Long postId; // PostId
    private String nickname; // 작성자 이름
    private String personal; // 고유 ID
    private String profileImg; // 프로파일 이미지
    private String content; // 본문
    private String contentImg; // post 이미지
    private LocalDateTime UpdateAt; // 갱신일
    private List<PostResponse> comments; // 댓글

    public PostResponse(Post post, boolean includeComments) {
        postId = post.getId();
        nickname = post.getMember().getMemberInfo().getNickname();
        personal = post.getMember().getPersonal();
        profileImg = post.getMember().getMemberInfo().getProfileImg();
        content = post.getContent();
        contentImg = post.getContentImg();
        UpdateAt = post.getUpdateAt();

        if (includeComments){
            comments = post.getComments().stream()
                    .map(comment -> new PostResponse(comment, false)) // 댓글은 댓글까지만 변환
                    .collect(Collectors.toList());
        }
        else{
            comments = null;
        }
    }
}
