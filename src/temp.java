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
        System.out.println(a.getDust()); //�̼����� �� ����:ng/m3
        System.out.println(a.getTime()); //���糯¥ -1
        System.out.println("\n");
        //�ʹ̼����� ��ޱ���: 0-15 ����/ 16-35 ���� /36-75 ���� /76�̻� �ſ쳪��

        getData2 b= new getData2();
        System.out.println(b.getTa()); //��ձ��
        System.out.println(b.getHumidity());  //��ս���
        System.out.println(b.getMinTa()); //�������
        System.out.println(b.getMaxTa());  //�ְ���
        System.out.println(b.getAvgWs()); //���ǳ��
        System.out.println(a.getTime()); //���糯¥ -1
        System.out.println("\n");
        
        
        add(new JLabel("�̼����� �� ����:ng/m3 "+ a.getDust()));
        add(new JLabel("���糯¥ -1 "+ a.getTime()));
        add(new JLabel("�ʹ̼����� ��ޱ���: 0-15 ����/ 16-35 ���� /36-75 ���� /76�̻� �ſ쳪��"));
        add(new JLabel("��ձ�� "+ b.getTa()));
        add(new JLabel("��ս��� "+ b.getHumidity()));
        add(new JLabel("������� "+ b.getMinTa()));
        add(new JLabel("�ְ��� "+ b.getMaxTa()));
        add(new JLabel("���ǳ�� "+ b.getAvgWs()));
        add(new JLabel("���糯¥ -1 "+ a.getTime()));
        
        setSize(300,700);
        setVisible(true);
    }
   
}
