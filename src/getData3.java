 import java.io.InputStreamReader;
 import java.net.HttpURLConnection;
 import java.net.URL;
 import java.net.URLEncoder;
 import java.io.BufferedReader;
 import java.io.IOException;

 public class getData3 {
     public static void main(String[] args) throws IOException {
         StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1352000/ODMS_COVID_02/callCovid02Api"); /*URL*/
         urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=http://apis.data.go.kr/1352000/ODMS_COVID_02"); /*Service Key*/
         urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
         urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("500", "UTF-8")); /*한 페이지 결과 수*/
         urlBuilder.append("&" + URLEncoder.encode("apiType","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*결과형식(xml/json)*/
         urlBuilder.append("&" + URLEncoder.encode("status_dt","UTF-8") + "=" + URLEncoder.encode("20200425", "UTF-8")); /*기준일자*/
         URL url = new URL(urlBuilder.toString());
         HttpURLConnection conn = (HttpURLConnection) url.openConnection();
         conn.setRequestMethod("GET");
         conn.setRequestProperty("Content-type", "application/json");
         System.out.println("Response code: " + conn.getResponseCode());
                  BufferedReader rd;
         if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
             rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
         } else {
             rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
         }
         StringBuilder sb = new StringBuilder();
         String line;
         while ((line = rd.readLine()) != null) {
             sb.append(line);
         }
         rd.close();
         conn.disconnect();
         System.out.println(sb.toString());
     }
 }