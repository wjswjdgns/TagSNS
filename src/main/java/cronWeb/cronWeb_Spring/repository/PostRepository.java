package cronWeb.cronWeb_Spring.repository;
import cronWeb.cronWeb_Spring.domain.member.Member;
import cronWeb.cronWeb_Spring.domain.post.Post;
import cronWeb.cronWeb_Spring.domain.post.PostInfo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Slf4j
@Transactional
public class PostRepository {

    @PersistenceContext
    private EntityManager em;

    // 게시물 저장하기
    public Long posting(Post post){
        em.persist(post);
        return post.getId();
    }

    // 게시물 Info 저장하기 --->  영속성 컨텍스트에 대해서 구체적으로 정리할 필요가 있다.
    public Long savePostInfo(Long postId, PostInfo postInfo){
        em.persist(postInfo);
        Post post = em.find(Post.class, postId);
        post.addPostInfo(postInfo);
        em.persist(post);
        return postInfo.getId();
    }

    // 게시물 가져오기
    public Post findPost(Long postId){
        return em.find(Post.class, postId);
    }

    // 포스트의 작성자 보내기
    public Member findPostByUser(Long postId) {

        String jpql = "SELECT p.member FROM Post p " +
                "WHERE p.id = :postId ";

        return em.createQuery(jpql, Member.class)
                .setParameter("postId", postId)
                .getSingleResult();
    }

    // 좋아요 남기기

    // 리트윗 하기

    // 전체 게시물 불러오기 --->  추후에 추천 게시물로 변경하기
    public List<Post> findByPosts(){
            String jpql = "SELECT DISTINCT p FROM Post p " +
                    "JOIN FETCH p.postInfo pi " +
                    "JOIN FETCH p.member m " +
                    "JOIN FETCH m.memberInfo mi";
        return em.createQuery(jpql, Post.class).getResultList();
    }

    // 팔로우 게시물 불러오기

    // 태그 게시물 불러오기

    // 자신이 좋아요를 남긴 포스트 불러오기

    // 자신의 포스트 불러오기

    // 자신의 댓글 불러오기




}
