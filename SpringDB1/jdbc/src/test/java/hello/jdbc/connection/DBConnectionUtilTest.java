package hello.jdbc.connection;

import hello.jdbc.repository.MemberRepositoryV0;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class DBConnectionUtilTest {

    MemberRepositoryV0 repository = new MemberRepositoryV0();

    @Test
    void connection(){
        //save
        Connection connection = DBConnectionUtil.getConnection();
        assertThat(connection).isNotNull();


    }

}