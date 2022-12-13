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

public class getData {
    private String time;
    private String value;

    public getData(){
        try{
            LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            this.time = now.format(formatter);
            this.time = Integer.toString(Integer.parseInt(time) - 1);

            StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1480523/MetalMeasuringResultService/MetalService"); /*URL*/
            urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=0y23S2rcH62CD0PUMxpta5XzAmgwFIWK7ZtGSHuXBYtyJXjt8DlQFy4ud1cZkM%2FzsTKGT8KhMPBdIem1UWu4iw%3D%3D"); /*Service Key*/
            urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
            urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*한 페이지 결과 수*/
            urlBuilder.append("&" + URLEncoder.encode("resultType","UTF-8") + "=" + URLEncoder.encode("JSON", "UTF-8")); /*결과형식(XML/JSON)*/
            urlBuilder.append("&" + URLEncoder.encode("date","UTF-8") + "=" + URLEncoder.encode(time, "UTF-8")); /*검색조건 날짜 (예시 : 20171208)*/
            urlBuilder.append("&" + URLEncoder.encode("stationcode","UTF-8") + "=" + URLEncoder.encode("7", "UTF-8")); /*검색조건 측정소코드*/
            urlBuilder.append("&" + URLEncoder.encode("itemcode","UTF-8") + "=" + URLEncoder.encode("90303", "UTF-8")); /*검색조건 항목코드*/
            urlBuilder.append("&" + URLEncoder.encode("timecode","UTF-8") + "=" + URLEncoder.encode("RH02", "UTF-8")); /*검색조건 시간구분*/

            
            URL url = new URL(urlBuilder.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/json");

            // System.out.println("Response code: " + conn.getResponseCode());
            BufferedReader rd;

            if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            }
            else {
                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }

            StringBuilder sb = new StringBuilder();
            String line;

            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }

            rd.close();
            conn.disconnect();

            JSONParser parser = new JSONParser();
            JSONObject object = (JSONObject)parser.parse(sb.toString());
            //System.out.println(object);
            JSONObject metalservice = (JSONObject)object.get("MetalService");
            //System.out.println(metalservice);
            JSONArray item = (JSONArray)metalservice.get("item");
            //System.out.println(item);
            for(int i=0; i< item.size(); i++){
                JSONObject value = (JSONObject)item.get(i);
                this.value = value.get("VALUE").toString();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public String getDust(){
        return this.value;
    }
    public String getTime(){
        return this.time;
    }
}