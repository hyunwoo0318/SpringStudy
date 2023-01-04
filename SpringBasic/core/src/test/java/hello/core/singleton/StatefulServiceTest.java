package hello.core.singleton;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {
    @Test
    void statefulServiceSingleton(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        //ThreadA : A 사용자 100000원 주문
        //지역변수, 파라미터 등을 이용해서 공유되지 않는 것들을 이용해서 공유필드를 조심해야함.
        int userAprice = statefulService1.order("userA", 100000);

        //ThreadB :B 사용자 200000원 주문
        statefulService2.order("userB", 200000);

        //ThreadA : 사용자A 주문 금액 조회
        System.out.println("price = " + userAprice);
    }

    static class TestConfig{

        @Bean
        public StatefulService statefulService(){
            return new StatefulService();
        }
    }

}