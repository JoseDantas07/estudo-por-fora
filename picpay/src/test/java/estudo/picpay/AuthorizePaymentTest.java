package estudo.picpay;

import estudo.picpay.Url.AuthorizePayment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class AuthorizePaymentTest {

    @Autowired
    AuthorizePayment authorizePayment;

    @Test
    public void test(){

        var result = authorizePayment.authorizeRequestDtos();

        assertEquals("success",result.status());
    }
}
