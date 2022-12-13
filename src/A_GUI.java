import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;

public class A_GUI extends JFrame{
    static JPanel mainPanel;
    static JTextArea chatArea;
    static JScrollPane scrollPane;
    static JTextField msgField;
    static JButton sendbtn;

    private A_ReceiveAndSend receiveAndSend;

    public A_GUI(A_ReceiveAndSend aras){
        this.receiveAndSend = aras;
        mainPanel = new JPanel();
        chatArea = new JTextArea(){
            @Override
            public Dimension getPreferredSize(){
                return new Dimension(new Dimension(500, 250));
            }
        };
        msgField = new JTextField();
        sendbtn = new JButton("send");

        mainPanel.setBackground(Color.white);
        mainPanel.setLayout(null);
        mainPanel.setBounds(0,0,500,300);

        chatArea.setEditable(false);
        chatArea.setBackground(Color.lightGray);
        chatArea.setBounds(8,8,470, 210);

        scrollPane = new JScrollPane(chatArea){
            @Override
            public Dimension getPreferredSize(){
                return new Dimension(new Dimension(470, 1000));
            }
        };
        scrollPane.setBounds(8,8,470, 210);
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        mainPanel.add(scrollPane);

        msgField.setBounds(8,218,484-104, 40);
        msgField.setColumns(4);
        msgField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(msgField.getText().length()==0) return;
                String write = msgField.getText();
                aras.sendMsg(write);
                appendMsg(write);
                msgField.setText("");
            }
        });
        mainPanel.add(msgField);

        sendbtn.setBounds(384,218,94,40);
        sendbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(msgField.getText().length()==0) return;
                String write = msgField.getText();
                aras.sendMsg(write);
                appendMsg(write);
                msgField.setText("");
            }
        });
        mainPanel.add(sendbtn);

        this.setContentPane(mainPanel);
        this.setTitle("Chat");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(500,300);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    public void appendMsg(String msg) {
        chatArea.append(msg + "\n");
    }

}