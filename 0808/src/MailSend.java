

import org.apache.commons.mail.SimpleEmail;

public class MailSend {

	public static void main(String[] args) {
		try {

			// 텍스트 메일을 보낼 수 있는 클래스의 객체 만들기
			SimpleEmail email = new SimpleEmail();

			// 서버 설정
			email.setAuthentication("dnal_zzz", "charm1458.0@@");
			email.setHostName("smtp.naver.com");
			email.setSmtpPort(587);

			// 메일 보안 설정 옵션 : 메일이 암호화되서 전송 됨.
			email.setTLS(true);
			email.setSSL(true);
			email.setFrom("dnal_zzz@naver.com", "관리자");
			email.setCharset("utf-8");
			
			int i=0;
			while(i<5) {
			//받는 설정
			email.addTo("dnal_zzz@naver.com");
			
			email.setSubject("메일 보내기 연습");
			email.setMsg("메일 보내기 연습");
			
			//보내기
			email.send();
			System.out.println("메일 보내기 성공");
			i+=1;
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}

}
