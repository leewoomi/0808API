import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class WeatherMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Thread th = new Thread() {
			public void run() {

				try {
					URL url = new URL("http://www.kma.go.kr/weather/forecast/mid-term-xml.jsp?stnId=109");

					// URL 연결 객체 생성
					HttpURLConnection conn = (HttpURLConnection) url.openConnection();

					// 옵션 설정
					// 캐시 설정 여부 - 다운로드 받아놓고 다음에 다운로드 받은 데이터를 이용할 것인지 여부 설정
					conn.setUseCaches(false);
					// 얼마간 접속을 시도해 볼 것인지 설정
					conn.setConnectTimeout(30000);

					// 문자열을 읽기 위한 스트림 생성
					BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

					// 줄 단위로 데이터를 읽어서 sb에 추가
					StringBuilder sb = new StringBuilder();
					while (true) {
						String line = br.readLine();
						if (line == null) {
							break;
						}
						sb.append(line);
					}

					String xml = sb.toString();
					// System.out.println(xml);
					DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
					// System.out.println(factory);
					DocumentBuilder builder = factory.newDocumentBuilder();
					// System.out.println(builder);
					InputStream is = new ByteArrayInputStream(xml.getBytes());
					// System.out.println(is);
					Document doc = builder.parse(is);
					// System.out.println(doc);
					Element root = doc.getDocumentElement();
					// System.out.println(root);
					
					// tmx 태그 전부 찾기
					NodeList list = root.getElementsByTagName("tmx");
					System.out.println(list);
					
					//wf 태그 전부 찾기
					//NodeList list = root.getElementsByTagName("wf");
					//System.out.println(list);
					
					//title 태그 전부 찾기
					//NodeList list = root.getElementsByTagName("title");
					//System.out.println(list);
					int i = 0;
					while (i < list.getLength()) {
						
						Node item = list.item(i);
						//System.out.println(item);
						//첫번째 자식 찾기
						Node child = item.getFirstChild();
						//System.out.println(child);
						//데이터 찾기
						String tmx = child.getNodeValue();
						System.out.println(tmx);
						
						i += 1;
					}
				} catch (Exception e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
				}
			}
		};
		th.start();
	}

}
