package hello.jdbc.exception.basic;

import lombok.extern.slf4j.Slf4j;

/**
 * RunTimeException을 상속받은 예외는 언체크 예외가 된다.
 */
@Slf4j
public class UnCheckedTest {
    static class MyUncheckedException extends RuntimeException {
        public MyUncheckedException(String message) {
            super(message);
        }
    }

    static class Repository{
        public void call(){
            throw new MyUncheckedException("ex");
        }
    }

    /**
     * Unchecked 예외는 예외를 잡거나 던지지 않아도됨.
     * 예외를 잡지 않는경우 자동으로 던져줌
     */
    static class Service{
        Repository repository = new Repository();

        public void callCatch(){
            try{
                repository.call();
            }
            catch (RuntimeException e){
                log.info("log error class={}", e.getMessage(), e);
            }
        }

        /**
         * 체크 예외와 다르게 던지는 선언을 하지 않아도 자동으로 던져줌.
         */
        public void callThrow(){
            repository.call();
        }
    }

}
