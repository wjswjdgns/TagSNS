package cronWeb.cronWeb_Spring.repository;


import cronWeb.cronWeb_Spring.domain.Follow;
import cronWeb.cronWeb_Spring.domain.member.Member;
import cronWeb.cronWeb_Spring.domain.member.MemberInfo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// 회원 관련 데이터 베이스
@Repository
@Transactional
public class MemberRepository {

    @PersistenceContext
    private EntityManager em;

    // 멤버 등록하기
    public Long save(Member member){
        em.persist(member);
        return member.getId();
    }


    // 프로필 등록하기
    public Long saveProfile(Long memberId, MemberInfo memberInfo){
        em.persist(memberInfo); // memberInfo 등록
        Member member = em.find(Member.class, memberId);
        member.addMemberInfo(memberInfo); // member와 연결하기
        em.persist(member);
        return member.getId();
    }

    // 프로필 수정하기

    // 멤버 1명 찾기
    public Member find(Long id){
        return em.find(Member.class, id);
    }


    /**
     *  유효성 검사
     * **/

    // 고유 ID 검사
    public boolean existsByUniqueId(String uniqueId){
        String jpql = "SELECT COUNT(*) FROM Member m WHERE m.personal = :uniqueId";
        Long count = em.createQuery(jpql, Long.class)
                .setParameter("uniqueId", uniqueId)
                .getSingleResult();
        return count > 0;
    }

    // 고유 아이디 검사
    public boolean existsByName(String name){
        String jpql = "SELECT COUNT(*) FROM Member m WHERE m.name = :name";
        Long count = em.createQuery(jpql, Long.class)
                .setParameter("name", name)
                .getSingleResult();
        return count > 0;
    }

}
