package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService {


    private final MemberRepository memberRepository;

    @Autowired
    //생성자를 이용해서 AppConfig를 이용해서 구현체를 정함 -->> 생성자 주입
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }



    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
       return memberRepository.findById(memberId);
    }


}
