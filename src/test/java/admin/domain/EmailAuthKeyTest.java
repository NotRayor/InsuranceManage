package admin.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.exbyte.insurance.admin.domain.EmailAuthKey;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Slf4j
public class EmailAuthKeyTest {

	EmailAuthKey emailAuthKey;
	
	
	@Test
	public void generateEmailAuthKeyGetRandomStringKey(){
		emailAuthKey = EmailAuthKey.generateCode(30);
		
		log.info("AuthKey Value : " + emailAuthKey.getAuthKey());		
		assertNotNull(emailAuthKey.getAuthKey());
	}
	
	@Test
	public void generateEmailAuthKeyWithZeroSizeThrowException() {
		emailAuthKey = EmailAuthKey.generateCode(0);
		
		log.info("AuthKey Value : " + emailAuthKey.getAuthKey());
	}
	
	@Test
	public void confirmRequestWithValidAuthKeyChangeAuthKeyValueToY() {
		emailAuthKey = EmailAuthKey.generateCode(30);
		String validAuthKey = emailAuthKey.getAuthKey();
		
		Boolean result = emailAuthKey.confirmAuth(validAuthKey);
		
		assertTrue(result);
		assertEquals("Y", emailAuthKey.getAuthKey());
	}
	
	@Test
	public void confirmRequestWithInValidAuthKeyReturnFalse() {
		emailAuthKey = EmailAuthKey.generateCode(30);
		String invalidAuthKey = "invalidKey";
		
		Boolean result = emailAuthKey.confirmAuth(invalidAuthKey);
		
		assertFalse(result);
		assertNotEquals("Y", emailAuthKey.getAuthKey());
	}
	
	
}
