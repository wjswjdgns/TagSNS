package cronWeb.cronWeb_Spring.repository;

import cronWeb.cronWeb_Spring.domain.member.Member;
import cronWeb.cronWeb_Spring.domain.member.MemberInfo;
import cronWeb.cronWeb_Spring.domain.post.Post;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;


@ExtendWith(SpringExtension.class)
@SpringBootTest
class PostRepositoryTest {

    @Autowired PostRepository postRepository;
    @Autowired MemberRepository memberRepository;


    // 포스트 생성
    @Test
    @Transactional
    public void testSendPost() throws Exception{

        //given
        Member member = new Member("memberA", "123", "testA", LocalDateTime.now());
        memberRepository.save(member);

        // 포스트 등록
        Post post = new Post();
        post.setMember(member);
        post.setContent("테스트합니다");
        post.setPostLevel(1);
        post.setCreateAt(LocalDateTime.now());

        //when
        Long postId = postRepository.posting(post);
        Post sendPost = postRepository.findPost(postId);

        //then
        Assertions.assertThat(post.getId()).isEqualTo(sendPost.getId());
    }

    // 댓글 생성
    @Test
    @Transactional
    public void testSendComment() throws Exception{

        //given
        Member member = new Member("memberA", "123", "testA", LocalDateTime.now());
        Member member2 = new Member("memberB", "123", "testB", LocalDateTime.now());
        Member member3 = new Member("memberC", "123", "testC", LocalDateTime.now());
        memberRepository.save(member);
        memberRepository.save(member2);
        memberRepository.save(member3);

        // 포스트 등록
        Post post = new Post();
        post.setMember(member);
        post.setContent("테스트합니다");
        post.setPostLevel(1);
        post.setCreateAt(LocalDateTime.now());

        Long postId = postRepository.posting(post);


        Post comment = new Post();
        comment.setMember(member2);
        comment.setContent("댓글을 넣습니다.");
        comment.setPostLevel(2);
        comment.setCreateAt(LocalDateTime.now());
        postRepository.posting(comment);

        post.addComment(comment);


        Post comment2 = new Post();
        comment2.setMember(member3);
        comment2.setContent("두번째 댓글을 넣습니다.");
        comment2.setPostLevel(2);
        comment2.setCreateAt(LocalDateTime.now());
        postRepository.posting(comment2);

        post.addComment(comment2);

        //when
        Post sendPost = postRepository.findPost(postId);

        for (Post cm :sendPost.getComments()) {
            System.out.println(cm.getContent());
        }

        //then
        Assertions.assertThat(sendPost.getComments().size()).isEqualTo(2);
    }


    // 특정 포스트에서 사용자 불러오기
    @Test
    @Transactional
    public void testGetPostByUser() throws Exception {

        //given
        Member member = new Member("memberA", "123", "testA", LocalDateTime.now());
        Long saveId = memberRepository.save(member);

        MemberInfo memberInfo = new MemberInfo();
        memberInfo.setNickname("hello");
        memberInfo.setIntroduce("자기소개입니다");
        memberInfo.setUpdateAt(LocalDateTime.now());
        memberRepository.saveInfo(memberInfo);

        member.addMemberInfo(memberInfo);

        Post post = new Post();
        post.setMember(member);
        post.setContent("테스트합니다");
        post.setPostLevel(1);
        post.setCreateAt(LocalDateTime.now());

        Long postId = postRepository.posting(post);

        //when
        Member postByUser = postRepository.findPostByUser(postId);
        Member findUser = memberRepository.find(saveId);

        //then
        Assertions.assertThat(postByUser.getId()).isEqualTo(findUser.getId());
        Assertions.assertThat(postByUser.getMemberInfo().getNickname()).isEqualTo(findUser.getMemberInfo().getNickname());

    }

    // 하트 카운트하기

    // 리트윗 카운트하기

    // 댓글 카운트하기

}