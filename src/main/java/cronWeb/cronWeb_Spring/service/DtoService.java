package cronWeb.cronWeb_Spring.service;

import cronWeb.cronWeb_Spring.domain.member.Member;
import cronWeb.cronWeb_Spring.dto.response.UserResponse;
import cronWeb.cronWeb_Spring.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DtoService {
    /*필요한 정보만 받아와서 출력하는 서비스 영역*/

    private final MemberRepository memberRepository;


    // 프로필 : 닉네임, 고유아이디, 프로필 이미지
    public UserResponse getProfile(String personal){

        Member member = memberRepository.findMemberInfo(personal);
        return new UserResponse(member);

    }


}
