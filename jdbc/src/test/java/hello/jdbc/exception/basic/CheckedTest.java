package hello.jdbc.exception.basic;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class CheckedTest {

    /***
     * Exception을 상속받은 예외는 체크 예외가 된다.
     */
    static class MyCheckedException extends Exception{
        public MyCheckedException(String message) {
            super(message);
        }
    }



    /***
     * Checked예외는 잡아서 처리하거나 던져야한다.
     *
     */
    static class Service{
        Repository repository = new Repository();

        /***
         * 예외를 잡아서 처리하는 코드         *
         */

        public void callCatch(){
            try {
                repository.call();
            } catch (MyCheckedException e) {
                //예외 처리 로직
                log.info("예외처리, message={}", e.getMessage(), e);
            }
        }

        /**
         * 예외를 던지는 경우
         * @throws MyCheckedException
         */
        public void throwException() throws MyCheckedException {
            repository.call();
        }
    }

    static class Repository{
        public void call() throws MyCheckedException{
            throw new MyCheckedException("ex");
        }
    }
}
