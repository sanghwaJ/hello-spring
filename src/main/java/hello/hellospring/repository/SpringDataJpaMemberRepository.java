package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// interface가 implements를 받을 땐 extends를 사용해야함
// SpringDataJpa는 Data Repository를 가지고 있으면 자동으로 구현체를 만들어줌
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    // 아래의 코드를 JPQL로 표현하면, select m from Member m where m.name = ?
    // findByNameAndId와 같이 인터페이스 이름만으로도 쿼리 작성이 끝남
    @Override
    Optional<Member> findByName(String name);

}