import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import javax.swing.JFrame;
import javax.swing.JLabel;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.awt.*;
public class temp extends JFrame{
    temp(){
    	super("info");
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	GridLayout grid = new GridLayout(10,1);
    	grid.setVgap(7);
    	setLayout(grid);
        getData a = new getData();
        System.out.println(a.getDust()); //미세먼지 농도 단위:ng/m3
        System.out.println(a.getTime()); //현재날짜 -1
        System.out.println("\n");
        //초미세먼지 등급기준: 0-15 좋음/ 16-35 보통 /36-75 나쁨 /76이상 매우나쁨

        getData2 b= new getData2();
        System.out.println(b.getTa()); //평균기온
        System.out.println(b.getHumidity());  //평균습도
        System.out.println(b.getMinTa()); //최저기온
        System.out.println(b.getMaxTa());  //최고기온
        System.out.println(b.getAvgWs()); //평균풍속
        System.out.println(a.getTime()); //현재날짜 -1
        System.out.println("\n");
        
        
        add(new JLabel("미세먼지 농도 단위:ng/m3 "+ a.getDust()));
        add(new JLabel("현재날짜 -1 "+ a.getTime()));
        add(new JLabel("초미세먼지 등급기준: 0-15 좋음/ 16-35 보통 /36-75 나쁨 /76이상 매우나쁨"));
        add(new JLabel("평균기온 "+ b.getTa()));
        add(new JLabel("평균습도 "+ b.getHumidity()));
        add(new JLabel("최저기온 "+ b.getMinTa()));
        add(new JLabel("최고기온 "+ b.getMaxTa()));
        add(new JLabel("평균풍속 "+ b.getAvgWs()));
        add(new JLabel("현재날짜 -1 "+ a.getTime()));
        
        setSize(300,700);
        setVisible(true);
    }
   
}
