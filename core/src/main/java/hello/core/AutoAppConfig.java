package hello.core;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
       //시작 위치를 정해줌 -> 이 패키지를 포함해서 하위 패키지를 모두 탐색


        //앞에서 수동으로 맞춘 것들을 포함하지 않게 제외함
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {
}
