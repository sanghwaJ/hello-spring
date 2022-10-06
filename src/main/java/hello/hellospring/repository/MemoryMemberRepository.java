package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

// @Repository
public class MemoryMemberRepository implements MemberRepository {
    // 메모리에 저장할 Map
    private static Map<Long, Member> store = new HashMap<>();
    // index
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        // 증감 연산자를 사용할 때 전위나 후위 모두 사용해도 될 경우, 전위 증감 연산자를 사용하면 성능상 이득을 볼 수 있음
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        // null일 가능성이 있는 경우
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                // 파라미터 값과 getName이 같은지 필터링
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        // list로 반환
        return new ArrayList<>(store.values());
    }
    public void clearStore() {
        store.clear();
    }
}
