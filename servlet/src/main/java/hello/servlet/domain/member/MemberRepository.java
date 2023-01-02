package hello.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 동시성 문제 고려X, 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
 */
public class MemberRepository {

    // static 사용시 new로 많아도 딱 하나만 사용 -> 싱글톤 사용시 static 생략 가능
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    // 싱글톤
    private static final MemberRepository instance = new MemberRepository();

    // 싱글톤 가져오기
    public static MemberRepository getInstance() {
        return instance;
    }

    // 객체 하나만 생성해서 공유해야 하기 떄문에, 생성자 private으로 막아둠
    private MemberRepository() {
    }

    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id) {
        return store.get(id);
    }

    public List<Member> findByAll() {
        return new ArrayList<>(store.values());
    }

    // 테스트용
    public void clearStore() {
        store.clear();
    }




}
