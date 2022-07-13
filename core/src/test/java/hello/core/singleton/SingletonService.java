package hello.core.singleton;

//웹 어플리케이션은 동시에 여러명이 요청을 한다. -> 그때마다 new를 통해 객체를 생성하면 메모리 낭비가 심각
// -> 객체를 1개만 만들어서 공유하는 방식 == Singleton
//무조건 공유를 할경우 공유데이터는 항상 최우선 고려사항이 되어야한다.

//자바 코드로 싱글톤 패턴 작성
public class SingletonService {

    //딱 1개만 존재하게 하는것
    private static final SingletonService instance = new SingletonService();

    //이 메서드를 통해서만 instance를 조회할수 있다.
    public static SingletonService getInstance(){
        return instance;
    }

    //private 생성자를 이용해서 다른 곳에서의 객체 생성을 막는다.
    private SingletonService(){

    }

    public void logic(){
        System.out.println("싱글톤 객체 로직 호출");
    }
}
