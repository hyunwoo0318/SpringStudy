package hello.jdbc.repository;


import hello.jdbc.connection.DBConnectionUtil;
import hello.jdbc.domain.Member;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.NoSuchElementException;


/**
 * JDBC - DriverManager 사용
 */
@Slf4j
public class MemberRepositoryV0 {

    public Member save(Member member) throws SQLException {
        String sql = "insert into member(member_id, money) values(?,?)";

        Connection con = null;
        PreparedStatement pstmt = null;
        try{
            con = getConnection();
            pstmt= con.prepareStatement(sql);
            pstmt.setString(1, member.getMemberId());
            pstmt.setInt(2,member.getMoney());
            pstmt.executeUpdate();
            return member;
        } catch(SQLException e){
            log.info("db error", e);
            throw e;
        }finally {
            close(con, pstmt, null);
        }
    }

    public Member findById(String memberId) throws SQLException {
        String sql  = "select * from member where member_id = ?";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            con = getConnection();
           pstmt = con.prepareStatement(sql);
            pstmt.setString(1, memberId);

            rs = pstmt.executeQuery();
            if (rs.next()) {
                Member member = new Member();
                member.setMemberId(rs.getString("member_id"));
                member.setMoney(rs.getInt("money"));
                return member;
            } else {
                throw new NoSuchElementException("member not found memberID =" + memberId);
            }


        } catch(SQLException e){
            log.error("db error", e);
            throw e;
        }finally {
            close(con, pstmt, rs);
        }

    }

    public void update(String memberId, int money) throws SQLException {
        String sql = "update member set money = ? where member_id = ?";

        Connection con = null;
        PreparedStatement pstmt = null;
        try{
            con = getConnection();
            pstmt= con.prepareStatement(sql);
            pstmt.setInt(1, money);
            pstmt.setString(2,memberId);
            int resultSize = pstmt.executeUpdate(); // 쿼리를 실행하고 영향받은 row의 수를 반환함.
            log.info("resultSize = {}", resultSize);
        } catch(SQLException e){
            log.info("db error", e);
            throw e;
        }finally {
            close(con, pstmt, null);
        }
    }

    public void delete(String memberId) throws SQLException {
        String sql = "delete from member where member_id = ?";

        Connection con = null;
        PreparedStatement pstmt = null;
        try{
            con = getConnection();
            pstmt= con.prepareStatement(sql);
            pstmt.setString(1,memberId);
            int resultSize = pstmt.executeUpdate(); // 쿼리를 실행하고 영향받은 row의 수를 반환함.
            log.info("resultSize = {}", resultSize);
        } catch(SQLException e){
            log.info("db error", e);
            throw e;
        }finally {
            close(con, pstmt, null);
        }
    }


    private void close(Connection con, Statement stmt, ResultSet rs) {
        //3개를 모두 닫아야하는데 다음 단게에 영향을 주면 안되서 하나하나 try - catch로 묶어서함.
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                log.info("error", e);
            }
        }

        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                log.info("error", e);
            }
        }
        if(con != null){
            try {
                con.close();
            } catch (SQLException e) {
                log.info("error", e);
            }
        }

    }

    private static Connection getConnection() {
        return DBConnectionUtil.getConnection();
    }
}
