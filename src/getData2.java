import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.IOException;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class getData2 {
    
    static String avgTa;
    static String avgHumidity;
    static String minTa;
    static String maxTa;
    static String avgWs;
    private String time;
    public getData2() {
        try {
            LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            this.time = now.format(formatter);
            this.time = Integer.toString(Integer.parseInt(time) - 1);

            StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1360000/AsosDalyInfoService/getWthrDataList"); /*URL*/
            urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=0y23S2rcH62CD0PUMxpta5XzAmgwFIWK7ZtGSHuXBYtyJXjt8DlQFy4ud1cZkM%2FzsTKGT8KhMPBdIem1UWu4iw%3D%3D"); /*Service Key*/
            urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호 Default : 1*/
            urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*한 페이지 결과 수 Default : 10*/
            urlBuilder.append("&" + URLEncoder.encode("dataType","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*요청자료형식(XML/JSON) Default : XML*/
            urlBuilder.append("&" + URLEncoder.encode("dataCd","UTF-8") + "=" + URLEncoder.encode("ASOS", "UTF-8")); /*자료 분류 코드(ASOS)*/
            urlBuilder.append("&" + URLEncoder.encode("dateCd","UTF-8") + "=" + URLEncoder.encode("DAY", "UTF-8")); /*날짜 분류 코드(DAY)*/
            urlBuilder.append("&" + URLEncoder.encode("startDt","UTF-8") + "=" + URLEncoder.encode(time, "UTF-8")); /*조회 기간 시작일(YYYYMMDD)*/
            urlBuilder.append("&" + URLEncoder.encode("endDt","UTF-8") + "=" + URLEncoder.encode(time, "UTF-8")); /*조회 기간 종료일(YYYYMMDD) (전일(D-1)까지 제공)*/
            urlBuilder.append("&" + URLEncoder.encode("stnIds","UTF-8") + "=" + URLEncoder.encode("108", "UTF-8")); /*종관기상관측 지점 번호 (활용가이드 하단 첨부 참조)*/
            URL url = new URL(urlBuilder.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/json");
            //System.out.println("Response code: " + conn.getResponseCode());
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
            //System.out.println(sb.toString());
    
            try{
                JSONParser parser = new JSONParser();
                JSONObject object = (JSONObject)parser.parse(sb.toString());
                //System.out.println(object);
                JSONObject response = (JSONObject)object.get("response");
                // System.out.println(response);
                JSONObject body = (JSONObject)response.get("body");
                // System.out.println(body);
                JSONObject items = (JSONObject)body.get("items");
                // System.out.println(items);
    
                JSONArray item = (JSONArray)items.get("item");
                // System.out.println(item);
                for(int i=0; i< item.size(); i++){
                    JSONObject value = (JSONObject)item.get(i);
                    this.avgTa = value.get("avgTa").toString();   
                    this.avgHumidity = value.get("avgRhm").toString();      
                    
                    this.minTa = value.get("minTa").toString();  
                    this.maxTa = value.get("maxTa").toString();  
                    this.avgWs = value.get("avgWs").toString();  
                }
            
 
            }catch(Exception e){
                e.printStackTrace();
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String getTa(){
        return this.avgTa;
    }
    public String getHumidity(){
        return this.avgHumidity;
    }
    public String getMinTa(){
        return this.minTa;
    }
    public String getMaxTa(){
        return this.maxTa;
    }
    public String getAvgWs(){
        return this.avgWs;
    }
    public String getTime(){
        return this.time;
    }

}
