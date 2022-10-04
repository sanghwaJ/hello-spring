package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    // Optional : Java8부터 사용, NullPointException을 방지하기 위해 null 체크를 직접하지 않아도 됨
    // 즉, Optional은 존재할 수도 있지만, 안 할 수도 있는 객체를 감싸고 있는 wrapper 클래스
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
