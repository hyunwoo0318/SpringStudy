package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonTest {
    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer() {
        AppConfig appConfig = new AppConfig();

        //1. 조회 : 호출할때마다 객체를 생성하는지 확인
        MemberService memberService1 = appConfig.memberService();

        MemberService memberService2 = appConfig.memberService();

        //참조값이 다름을 확인 -> 객체가 각각 생성됨을 알수있음.
        System.out.println("memberService2 = " + memberService2);
        System.out.println("memberService1 = " + memberService1);

        assertThat(memberService1).isNotEqualTo(memberService2);
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTest() {
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        //참조값이 같음 -> 같은 객체를 이용 -->> 싱글톤 패턴
        System.out.println("singletonService2 = " + singletonService2);
        System.out.println("singletonService1 = " + singletonService1);

        assertThat(singletonService1).isSameAs(singletonService2);
        //same ==
        //equal -> java의 equals
    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer(){
    //    AppConfig appConfig = new AppConfig();

        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        //1. 조회 : 호출할때마다 객체를 생성하는지 확인
        MemberService memberService1 = ac.getBean("memberService", MemberService.class);
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        //참조값이 다름을 확인 -> 객체가 각각 생성됨을 알수있음.
        System.out.println("memberService2 = " + memberService2);
        System.out.println("memberService1 = " + memberService1);

        assertThat(memberService1).isEqualTo(memberService2);
    }
}
