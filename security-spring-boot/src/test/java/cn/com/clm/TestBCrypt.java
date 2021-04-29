package cn.com.clm;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class TestBCrypt {

    @Test
    public void test1() {
        String hashpw = BCrypt.hashpw("123", BCrypt.gensalt());
        System.out.println(hashpw);

        boolean checkpw = BCrypt.checkpw("123", "$2a$10$elc0WomTYbTSnInc8R32ouE8vIE3Hh9B.ql3CO1C/yYTTcklJLqLO");
        System.out.println(checkpw);
    }

}




