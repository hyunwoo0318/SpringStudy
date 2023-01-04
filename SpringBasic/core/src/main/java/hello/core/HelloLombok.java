package hello.core;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


//lombok이 알아서 만들어줌.
@Getter
@Setter
@NoArgsConstructor
public class HelloLombok {

    private String name;
    private int age;

    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok();
        helloLombok.setName("adsda");

    }
}
