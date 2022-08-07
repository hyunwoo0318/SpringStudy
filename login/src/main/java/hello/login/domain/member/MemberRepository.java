package hello.login.domain.member;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.*;

@Slf4j
@Repository

public class MemberRepository {
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    public Member save(Member member) {
        member.setId(++sequence);
        log.info("save : member={}", member);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id) {
        return store.get(id);
    }

    public Optional<Member> findByLoginId(String loginId) {
        return findAll().stream() // 루프를 돔
                .filter(m -> m.getLoginId().equals(loginId)) // 람다함수에 해당하는 것들만 다음단계로 넘어감.
                .findFirst();
    }

    public void clearStore() {
        store.clear();
    }


    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

}
