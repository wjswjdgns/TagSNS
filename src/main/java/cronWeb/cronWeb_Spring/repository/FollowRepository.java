package cronWeb.cronWeb_Spring.repository;


import cronWeb.cronWeb_Spring.domain.Follow;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FollowRepository {
    @PersistenceContext
    private EntityManager em;

    // 팔로우 등록하기
    public Long saveFollow(Follow followMember){
        em.persist(followMember);
        return followMember.getId();
    }

    // 특정 회원의 팔로워 (follower) 목록 가져오기
    public List<Follow> getResponseMember(Long memberId){
        String jpql = "SELECT f FROM Follow f WHERE f.responseMember.id = :memberId";
        return em.createQuery(jpql, Follow.class)
                .setParameter("memberId", memberId)
                .getResultList();
    }

    //특정 회원의 팔로잉(following)한 목록을 가져오기
    public List<Follow> getRequestMember(Long memberId) {
        String jpql = "SELECT f FROM Follow f WHERE f.requestMember.id = :memberId";
        return em.createQuery(jpql, Follow.class)
                .setParameter("memberId", memberId)
                .getResultList();
    }
}
