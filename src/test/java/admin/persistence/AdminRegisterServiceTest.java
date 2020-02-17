package admin.persistence;

import static org.junit.Assert.assertNotNull;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.exbyte.insurance.admin.domain.AdminVO;
import com.exbyte.insurance.admin.dto.LoginDTO;
import com.exbyte.insurance.admin.dto.RegisterRequest;
import com.exbyte.insurance.admin.persistence.AdminDAO;
import com.exbyte.insurance.admin.service.AdminRegisterService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class AdminRegisterServiceTest {
	final String TEST_STRING = "registerCheck";
	final String TEST_UPDATE_STRING = "junitUpdateTest";
	int TEST_VALUE = 3;
	
	AdminVO admin;
	RegisterRequest registerRequest;
	LoginDTO login;
	
	@Inject
	AdminDAO adminDAO;
	
	AdminRegisterService adminRegisterService;
	
	@Before
	public void init() throws Exception {
		AdminVO existAdmin = adminDAO.read(TEST_STRING);
		adminRegisterService = new AdminRegisterService(adminDAO);

		login = LoginDTO.builder()
				.adminId(TEST_STRING)
				.adminPw(TEST_STRING)
				.adminPoint(1)
				.useCookie(false)
				.build();
		
		registerRequest = new RegisterRequest(TEST_STRING,TEST_STRING,TEST_STRING,TEST_STRING,
				TEST_STRING,TEST_STRING,TEST_STRING,TEST_VALUE);
		
		if(existAdmin != null){
			adminDAO.delete(existAdmin);
		}
	}
	
	
	@Test
	public void registerAdminWithValidDataCreateNewAdmin() throws Exception {
		adminRegisterService.registerAccount(registerRequest);
		
		AdminVO result = adminDAO.read(TEST_STRING);
		
		assertNotNull(result);
	}
	
}
