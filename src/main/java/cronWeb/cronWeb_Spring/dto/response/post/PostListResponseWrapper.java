package cronWeb.cronWeb_Spring.dto.response.post;
import lombok.Data;
import java.util.List;

@Data
public class PostListResponseWrapper {
    private List<PostResponse> posts;
    public PostListResponseWrapper(List<PostResponse> posts) {
        this.posts = posts;
    }
}
