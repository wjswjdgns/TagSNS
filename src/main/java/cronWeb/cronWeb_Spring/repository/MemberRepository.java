package cronWeb.cronWeb_Spring.repository;


import cronWeb.cronWeb_Spring.domain.Follow;
import cronWeb.cronWeb_Spring.domain.member.Member;
import cronWeb.cronWeb_Spring.domain.member.MemberInfo;
import cronWeb.cronWeb_Spring.dto.response.UserResponse;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

    // 멤버의 Info 등록하기
    public Long saveInfo(MemberInfo memberInfo){
        em.persist(memberInfo);
        return memberInfo.getId();
    }

    // 아이디 찾기
    @Transactional
    public Optional<Member> findByUserId(String name){
        String jpql = "SELECT m FROM Member m JOIN FETCH m.memberInfo WHERE m.name = :Username";
        List<Member> members = em.createQuery(jpql, Member.class)
                .setParameter("Username", name)
                .getResultList();
        return members.isEmpty() ? Optional.empty() : Optional.of(members.get(0));
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


    // 멤버 1명의 정보 찾기
    public Member findMemberInfo(String personal){
        String jpql = "SELECT m FROM Member m JOIN FETCH m.memberInfo WHERE m.personal = :personal";
        return em.createQuery(jpql, Member.class)
                .setParameter("personal", personal)
                .getSingleResult();
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
