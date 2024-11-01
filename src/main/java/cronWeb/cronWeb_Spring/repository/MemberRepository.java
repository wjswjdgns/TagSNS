package cronWeb.cronWeb_Spring.repository;


import cronWeb.cronWeb_Spring.domain.Follow;
import cronWeb.cronWeb_Spring.domain.member.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

// 회원 관련 데이터 베이스
@Repository
public class MemberRepository {

    @PersistenceContext
    private EntityManager em;

    // 멤버 등록하기
    public Long save(Member member){
        em.persist(member);
        return member.getId();
    }

    // 멤버 1명 찾기
    public Member find(Long id){
        return em.find(Member.class, id);
    }



}
