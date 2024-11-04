package cronWeb.cronWeb_Spring.service;

import cronWeb.cronWeb_Spring.UserException.DuplicateName;
import cronWeb.cronWeb_Spring.UserException.DuplicateUniqueId;
import cronWeb.cronWeb_Spring.UserException.NotMatchPassword;
import cronWeb.cronWeb_Spring.domain.member.Member;
import cronWeb.cronWeb_Spring.domain.member.MemberInfo;
import cronWeb.cronWeb_Spring.dto.request.CreateMemberRequest;
import cronWeb.cronWeb_Spring.dto.response.CreateMemberResponse;
import cronWeb.cronWeb_Spring.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;


    // 회원 가입
    public Long saveMember(CreateMemberRequest RequestMember){

        /**
         * 유효성 검사
         * */
        ValidateDuplicateName(RequestMember.getName()); // 아이디 중복 검사
        ValidateDuplicateUniqueId(RequestMember.getUniqueId()); // 고유 아이디 중복 검사
        ValidatePassword(RequestMember.getPassword()); // 패스워드 유효성 검사

        try{
            /**
             * 레포지토리에 멤버 등록
             * */
            Member member = new Member(RequestMember.getName(), RequestMember.getPassword(), RequestMember.getUniqueId(), LocalDateTime.now());
            Long memberId = memberRepository.save(member);

            // 기본 속성 입력
            // 백그라운드와 프로필 사진은 추후 작업
            MemberInfo memberInfo = new MemberInfo();
            memberInfo.setNickname(RequestMember.getNickname());
            memberInfo.setUpdateAt(LocalDateTime.now());
            memberRepository.saveProfile(memberId,memberInfo);

            return memberId;
        }
        catch(DataAccessException e){
            e.printStackTrace();
            // 데이터베이스 오류 처리
            throw new RuntimeException("회원 가입 중 오류가 발생했습니다.");
        }
    }

    /***
     * 유효성 검사
     * */
    private void ValidateDuplicateName(String name){
        if (memberRepository.existsByName(name)){
            throw new DuplicateName("이미 존재하는 ID 입니다.");
        }
    }

    private void ValidateDuplicateUniqueId(String requestId){
        if (memberRepository.existsByUniqueId(requestId)){
            throw new DuplicateUniqueId("이미 존재하는 고유 ID 입니다.");
        }
    }

    // 비밀번호가 문자와 숫자가 동시에 사용되었는지
    private void ValidatePassword(String password){
        // 정규식으로 문자와 숫자가 모두 포함되어 있는지 검사
        if(!password.matches("(?=.*[a-zA-Z])(?=.*\\d).*")){
            throw new NotMatchPassword("비밀번호 형식이 틀렸습니다.");
        }
    }
}