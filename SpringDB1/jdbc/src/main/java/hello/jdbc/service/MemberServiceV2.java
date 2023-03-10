package hello.jdbc.service;


import hello.jdbc.domain.Member;
import hello.jdbc.repository.MemberRepositoryV1;
import hello.jdbc.repository.MemberRepositoryV2;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.support.JdbcUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 트랜잭션 - 파라미터 연동, 풀을 고려한 종료
 */
@Slf4j
@RequiredArgsConstructor
public class MemberServiceV2 {

    private final DataSource dataSource;
    private final MemberRepositoryV2 memberRepository;

    public void accountTransfer(String fromId, String toId, int money) throws SQLException {

        Connection con = dataSource.getConnection();
        try{
            con.setAutoCommit(false);// 트랜잭션 시작
            //비즈니스 로직
            bizLogic(con, fromId, toId, money);
            //성공시 커밋
            con.commit();

        } catch (Exception e){
            //예외 -> 롤백을 해야함
            con.rollback();
            throw new IllegalStateException(e);
        } finally{
            release(con);
        }


    }

    private void bizLogic(Connection con,String fromId, String toId, int money) throws SQLException {
        Member fromMember = memberRepository.findById(con, fromId);
        Member toMember = memberRepository.findById(con, toId);

        memberRepository.update(con,fromId, fromMember.getMoney() - money);
        validation(toMember);
        memberRepository.update(con,toId, toMember.getMoney() + money);
    }

    private static void release(Connection con) {
        if (con != null) {
            try{
                con.setAutoCommit(true);//커넥션 풀을 고려해서 다시 설정함
                con.close(); // 커넥션을 커넥션 풀로 돌려줌
            }catch (Exception e){
                log.info("error", e);
            }
        }
    }

    private static void validation(Member toMember) {
        if (toMember.getMemberId().equals("ex")) {
            throw new IllegalStateException("이제중 예외 발생");
        }
    }
}
