import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HTMLParsing {

	public static void main(String[] args) {
		// 스레드 만들기

		Thread th = new Thread() {
			public void run() {

				try {
					// 다운로드 받을 주소 만들기
					URL url = new URL("https://tv.naver.com/");
					// 다운로드 받을 주소와 연결하는 객체 만들기
					HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					// 옵션 설정
					conn.setUseCaches(false);
					conn.setConnectTimeout(30000);

					// 문자열을 읽을 수 있는 스트림 생성
					BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

					// 다운로드 받는 문자열을 중간 저장할 인스턴스를 생성
					// String에 바로 추가하면 메모리 낭비가 발생합니다.
					// String은 자기 자신에게 추가할 수 없기 때문에
					// 기존 내용을 복사해서 추가하기 때문에

					StringBuilder sb = new StringBuilder();
					while (true) {
						// 한줄을 읽기
						String line = br.readLine();
						// 읽은 게 없으면 종료
						if (line == null)
							break;
						sb.append(line + "\n");

					}
					// 다 읽은 데이터는 String으로 변환
					String html = sb.toString();
					// sb 메모리 정리
					sb = null;
					// 연결 객체 정리
					br.close();
					conn.disconnect();
				 System.out.println(html);

					// html 루트를 찾아줍니다.

					Document doc = Jsoup.parse(html);
					
					// //h2 태그 전체 가져오기
					 Elements elements = doc.getElementsByTag("tootip");

					//Elements elements = doc.getElementsByClass("screen_out");
					 int i=0;
					 while(i < elements.size()) {
					 //데이터 1개 가져오기
					 Element element = elements.get(i);
					 //태그 안의 내용 출력
					 System.out.println(element.text());
					 i+=1;
					 }

				
					//Element element = doc.getElementById("mainServiceTitle");
				
					//System.out.println(element.text());
				} catch (Exception e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
				}

			}
		};
		th.start();
	}

}
