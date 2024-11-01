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

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class FollowRepositoryTest {

    @Autowired
    FollowRepository followRepository;
    @Autowired MemberRepository memberRepository;

    /* 팔로우 & 팔로잉  */
    @Test
    @Transactional
    public void testfollow() throws Exception{

        //given
        Member member = new Member();
        member.setName("memberA");
        member.setPassword("123");
        member.setPersonal("testA");

        Member member2 = new Member();
        member2.setName("memberB");
        member2.setPassword("123");
        member2.setPersonal("testB");

        Member member3 = new Member();
        member3.setName("memberC");
        member3.setPassword("123");
        member3.setPersonal("testC");

        Follow follow1 = new Follow();
        follow1.setRequestMember(member2); // 요청하는 사람
        follow1.setResponseMember(member); // 받는 사람

        Follow follow2 = new Follow();
        follow2.setRequestMember(member3); // 요청하는 사람
        follow2.setResponseMember(member); // 받는 사람

        Follow follow3 = new Follow();
        follow3.setRequestMember(member); // 요청하는 사람
        follow3.setResponseMember(member3); // 받는 사람

        //when
        Long memberId = memberRepository.save(member);
        Long memberId2 = memberRepository.save(member2);
        Long memberId3 = memberRepository.save(member3);

        // 저장
        followRepository.saveFollow(follow1);
        followRepository.saveFollow(follow2);
        followRepository.saveFollow(follow3);

        List<Follow> responseMembers = followRepository.getResponseMember(memberId); // 나를 등록한 사람
        List<Follow> requestMembers = followRepository.getRequestMember(memberId); // 내가 등록한 사람

        //then
        System.out.println(responseMembers.size());
        Assertions.assertThat(responseMembers.size()).isEqualTo(2);

        System.out.println(requestMembers.size());
        Assertions.assertThat(requestMembers.size()).isEqualTo(1);


        // memberA를 등록한 사람들 (memberB, memberC)
        System.out.println(responseMembers.get(0).getRequestMember().getName());
        System.out.println(responseMembers.get(1).getRequestMember().getName());

        Assertions.assertThat(responseMembers.get(0).getRequestMember()).isEqualTo(member2); // member를 등록한 사람은 member2이다
        Assertions.assertThat(responseMembers.get(1).getRequestMember()).isEqualTo(member3); // member를 등록한 사람은 member3이다.

        // memberA가 등록한 사람들 (memberC)
        System.out.println(requestMembers.get(0).getResponseMember().getName());
        System.out.println("====> 등록한 사람 " + requestMembers.get(0).getRequestMember().getName());

        Assertions.assertThat(requestMembers.get(0).getResponseMember()).isEqualTo(member3); // member가 등록한 사람은 member3이다
        Assertions.assertThat(requestMembers.get(0).getRequestMember()).isEqualTo(member);
    }

}