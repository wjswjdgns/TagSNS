package cronWeb.cronWeb_Spring.repository;
import cronWeb.cronWeb_Spring.domain.post.Post;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class PostRepository {

    @PersistenceContext
    private EntityManager em;

    // 게시물 저장하기
    public Long posting(Post post){
        em.persist(post);
        return post.getId();
    }
    // 게시물 가져오기
    public Post findPost(Long postId){
        return em.find(Post.class, postId);
    }


    // 좋아요 남기기


    // 리트윗 하기


    // 전체 게시물 불러오기 --->  추후에 추천 게시물로 변경하기
    public List<Post> findByPosts(){
            String jpql = "SELECT p FROM Post p " +
                    "JOIN FETCH p.postInfo " +
                    "JOIN FETCH Member m " +
                    "JOIN FETCH m.memberInfo";

        return em.createQuery(jpql, Post.class).getResultList();
    }

    // 팔로우 게시물 불러오기

    // 태그 게시물 불러오기

    // 자신이 좋아요를 남긴 포스트 불러오기

    // 자신의 포스트 불러오기

    // 자신의 댓글 불러오기




}
