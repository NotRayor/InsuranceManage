package com.exbyte.insurance.admin.service;

import javax.inject.Inject;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.exbyte.insurance.admin.domain.AdminVO;
import com.exbyte.insurance.admin.domain.EmailAuthKey;
import com.exbyte.insurance.admin.persistence.AdminDAO;

@Service
public class AdminMailService {

	private Logger logger = LoggerFactory.getLogger(AdminMailService.class);
	
	@Autowired
	private JavaMailSender mailSender;
	
	static private AdminDAO adminDAO;
	final String TEST_VALID_EMAIL = "Y";
	
	
	private MimeMessage mail;
	EmailAuthKey emailAuthKey;
	String htmlStr;
	
	@Inject
	public AdminMailService(AdminDAO adminDAO) {
		this.adminDAO = adminDAO;
	}

	public AdminMailService(AdminDAO adminDAO, JavaMailSender mailSender) {
		this.adminDAO = adminDAO;
		this.mailSender = mailSender;
	}
	

	public String urlMaker(HttpServletRequest request) {
		String result = request.getScheme() + "://" + request.getServerName() +
				":" + request.getServerPort() + request.getContextPath();
		return result;
	}
	
	public String getAuthMessageHtml(AdminVO adminVO, String path) {
		htmlStr = "<h2> 안녕하세요 </>" + "<h4>" + adminVO.getAdminId() + "님</h4>"
				+ "<p> 인증하기 버튼으로 인증키 확인이 가능합니다."
				+ "<a href='" + path + "/admin/confirm?"
						+ "adminId="+adminVO.getAdminId() + "&authKey="+ adminVO.getAdminAuthKey() + "'>인증하기</a></p>";
		return htmlStr;
	}
	
	public String getFindMessageHtml(AdminVO adminVO, String path) {
		htmlStr = "<h1>계정찾기</h1>" + "<h2> 안녕하세요 </>" + "<h4>" + adminVO.getAdminName() + "님</h4>"
				+ "<p>아이디 : " + adminVO.getAdminId() + " </p>"
				+ "<p> 비밀번호 변경을 원하시면 아래 링크를 통해 변경하실 수 있습니다.</p>"
				+ "<a href='"  + path + "/admin/updatePw?"
						+ "adminId="+adminVO.getAdminId() + "&authKey="+ adminVO.getAdminAuthKey() + "'>인증하기</a></p>";
		return htmlStr;
	}
	
	public void mailSend(AdminVO adminVO, String contextPath) throws Exception {
		String htmlStr;
		MimeMessage mail = mailSender.createMimeMessage();
		htmlStr = getAuthMessageHtml(adminVO, contextPath);
		mail.setSubject("Com : " + adminVO.getAdminId() + "님의 인증메일입니다.", "utf-8");
		mail.setText(htmlStr, "utf-8", "html");
		mail.addRecipient(RecipientType.TO, new InternetAddress(adminVO.getAdminEmail())); // 수신자 설정
		mailSender.send(mail);
	}
	
	public void mailSendFindPw(AdminVO adminVO, String contextPath) throws Exception {
		String htmlStr;
		MimeMessage mail = mailSender.createMimeMessage();
		htmlStr = getFindMessageHtml(adminVO, contextPath);
		mail.setSubject("Com : " + adminVO.getAdminId() + "님의 아이디/비밀번호 찾기 메일입니다.", "utf-8");
		mail.setText(htmlStr, "utf-8", "html");
		mail.addRecipient(RecipientType.TO, new InternetAddress(adminVO.getAdminEmail())); // 수신자 설정
		mailSender.send(mail);
	}
}
