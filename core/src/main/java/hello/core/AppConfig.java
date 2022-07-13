package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Bean memberService -> new MemoryMemberRepository()
//@Bean orderService -> new MemoryMemberRepository()

//앱 전체의 구성을 손쉽게 판단할수 있음.

//ApplicationContext는 스프링 컨테이너임
//ApplicationContext는 BeanFactory와 여러가지 기능들을 모두 상속받아 사용 -> 내가 직접 beanFactory를 이용 x

//스프링이 CGLIB라는 바이트코드 조작 라이브러리를 사용해서 AppConfig를 상속받은 임의의 다른 클래스를 만들고, 그 클래스를 스프링빈으로 등록.
//-->> 위 클래스가 싱글톤을 보장해준다.
@Configuration
public class AppConfig {

    //메서드 이름을 key로 return값을 value로 스프링 컨테이너에 저장함. -> 스프링 빈
    //스프링 빈의 이름은 항상 다른 이름을 부여해야함 -> 중복될 경우 오류발생할수있다.
    //역할과 구현을 정확하게 구별할수 있음.
    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }

    @Bean
    public DiscountPolicy discountPolicy(){
        return new RateDiscountPolicy();
    }

    //생성자 주입
    @Bean
    public MemberService memberService(){
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public OrderService orderService(){
        return new OrderServiceImpl(memberRepository(),discountPolicy());
    }
}
