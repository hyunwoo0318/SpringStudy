package hello.servlet.domain.member;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Member {
    private Long id;
    private String username;
    private int age;

    public Member() {
    }

    //id는 repo에 저장될때 그때 id를 발급함.
    public Member(String username, int age) {
        this.username = username;
        this.age = age;
    }
}
