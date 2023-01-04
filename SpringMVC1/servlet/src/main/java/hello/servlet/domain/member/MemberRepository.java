package hello.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberRepository {

    private Map<Long, Member> store = new HashMap<>();
    //sequence번호는 하나만 발급해야하기때문에 static으로 선언
    private static Long sequence = 0L;

    //singleton 보장 -> 처음에 하나 만들고 더이상의 생성을 금지시킴
    private static final MemberRepository instance = new MemberRepository();

    public static MemberRepository getInstance(){
        return instance;
    }

    //private생성자로 더이상의 생성을 금지시킴
    private MemberRepository(){

    }

    public Member save(Member member){
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id){
        return store.get(id);
    }
    public List<Member> findAll(){
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }


}
