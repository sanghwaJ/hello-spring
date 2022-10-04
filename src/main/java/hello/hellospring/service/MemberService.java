package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {
    private final MemberRepository memberRepository;

    // memberServiceTest에서 memberRepository를 new를 통한 인스턴스로 생성하는 것이 아니라 외부(MemberServiceTest)에서 생성(DI : Dependency Injection)
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 회원가입
    public long join(Member member) {
        // 중복 회원 방지
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    /* 중복 회원 방지 */
    private void validateDuplicateMember(Member member) {
        // findByName은 Optional 클래스이기 때문에 ifPresent()를 바로 사용할 수 있음
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원");
                });

    }

    /* 전체 회원 조회 */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /* 하나의 회원 조회 */
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
