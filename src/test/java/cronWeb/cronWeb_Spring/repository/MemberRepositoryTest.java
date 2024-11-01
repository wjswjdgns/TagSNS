package cronWeb.cronWeb_Spring.repository;

import cronWeb.cronWeb_Spring.domain.Follow;
import cronWeb.cronWeb_Spring.domain.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;



@ExtendWith(SpringExtension.class)
@SpringBootTest
class MemberRepositoryTest {

    @Autowired MemberRepository memberRepository;


    /* 정상 가입 & 멤버 찾기 */
    @Test
    @Transactional
    public void testMember() throws Exception{

        //given
        Member member = new Member();
        member.setName("memberA");
        member.setPassword("123");
        member.setPersonal("testA");

        //when
        Long saveId = memberRepository.save(member);
        Member findMember = memberRepository.find(saveId);

        //then
        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
        Assertions.assertThat(findMember.getName()).isEqualTo(member.getName());
    }







}