package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository {

    // JPA는 EntityManager로 모든 동작이 진행됨
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        // EntityManager가 ID 셋팅과 함께 insert 쿼리를 작성하여 DB에 저장해줌
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        // PK를 통한 조회의 경우
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        // PK가 아닌 나머지 컬럼을 통한 조회의 경우 JPQL 사용
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        // PK가 아닌 나머지 컬럼을 통한 조회의 경우 JPQL 사용
        // 아래의 쿼리는 Table이 아닌 Entity(객체)를 대상으로 조회하는 것
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }
}
