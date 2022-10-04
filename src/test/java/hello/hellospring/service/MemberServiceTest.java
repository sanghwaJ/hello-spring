package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    // 메모리 clear
    @BeforeEach
    public void beforeEach() {
        // memberRepository를 생성하고
        memberRepository = new MemoryMemberRepository();
        // 생성한 memberRepository를 memberService에 넣어줌으로써
        // MemberService와 MemberServiceTest가 같은 memberRepository를 사용하도록 함
        memberService = new MemberService(memberRepository);
    }

    @Test
    void 회원가입() {
        // given
        Member member = new Member();
        member.setName("hello");

        // when
        Long saveId = memberService.join(member);

        // then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복회원예외() {
        // given
        Member member1 = new Member();
        member1.setName("spring");
        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService.join(member1);

        /* assertThrows를 이용한 예외처리 방법 1 */
        assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        /* 방법 2
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원");
        */

        /* try-catch를 이용한 예외처리
        try {
            memberService.join(member2);
            fail();
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원");
        }
        */


        // then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}