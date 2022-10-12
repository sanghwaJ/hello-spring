package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
// test 코드에 @Transactional Annotation을 넣으면 DB에 수행했던 테스트 데이터들을 모두 RollBack 해줌
@Transactional
class MemberServiceIntegrationTest {

    // @Autowired => 테스트 코드를 작성할 땐 가장 편한 방법을 쓰자
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    void 회원가입() {
        // given (상황이 주어졌을 때)
        Member member = new Member();
        member.setName("hello");

        // when (검증 테스트)
        Long saveId = memberService.join(member);

        // then (이러한 결과가 나와야 함)
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
}