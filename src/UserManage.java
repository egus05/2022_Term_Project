import java.awt.*;


import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class UserManage extends JFrame implements MouseListener{

    //임시 col
    String[] column = {"ID","Nickname","Name","e-mail","address","birthday","Friend"};
    String data[][];

    //임시 데이터
	/*String data[][] =  {{"1","Wo","Kim","est@naver.com","123-242-123","20000513","N"}
						,{"2","Wa","Kum","esta@naver.com","122-222-123","20100513","N"}
						, {"3","Wi","Mim","amt@naver.com","123-242-448","22000513","N"}
						};	*/

    JPopupMenu popupMenu = new JPopupMenu();
    //오른쪽 클릭 옵션
    JMenuItem seeInfo = new JMenuItem("상세 정보");
    JMenuItem addFriend = new JMenuItem("친구 추가");
    JMenuItem chat = new JMenuItem("채팅하기");
    JMenuItem file = new JMenuItem("파일 전송");





    //테이블을 유저가 수정 불가능하게 설정
    DefaultTableModel model = new DefaultTableModel(data, column) {
        @Override
        public boolean isCellEditable(int row, int column) {
            if (column >= 0) {
                return false;
            } else {
                return true;
            }
        }
    };

    JTable table = new JTable(model);
    JButton button = new JButton("변경");


    TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
    JTextField search = new JTextField();


    public UserManage() {
        super("UserManagement");
        setSize(600,800);
        table.setRowSorter(sorter);

        JPanel panel = new JPanel(new BorderLayout());

        //database에서 입력값 받아 jtable에 넣기
        OpenTable ot = new OpenTable();
        ot.select("SELECT*FROM TMP2");
        try
        {


            while(ot.rs.next())
            {
                String ID = ot.rs.getString("ID");
                String name = ot.rs.getString("name");
                String supname = ot.rs.getString("supname");
                String email = ot.rs.getString("email");
                String address = ot.rs.getString("address");
                String birthday = ot.rs.getString("birthday");
                String friend = ot.rs.getString("friend");

                model.addRow(new Object[] {ID,name,supname,email,address,birthday,friend});
                table.updateUI();
            }


        }
        catch(Exception e)
        {
            System.out.println("error1232312");
        }
        ot.close();
        panel.add(new JLabel("Enter for search ID,NickName etc..."),
                BorderLayout.WEST);
        panel.add(search, BorderLayout.CENTER);

        setLayout(new BorderLayout());
        add(button,BorderLayout.NORTH);
        add(panel, BorderLayout.SOUTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
        this.setContentPane(panel);

        popupMenu.add(seeInfo);
        popupMenu.add(addFriend);
        popupMenu.add(chat);
        popupMenu.add(file);

        table.setComponentPopupMenu(popupMenu);

        //일부 열들을 숨김
        //친구 여부는 임시로 보이게 표시함
        hideInfo(table,"ID");
        hideInfo(table,"Name");
        hideInfo(table,"e-mail");
        hideInfo(table,"address");
        hideInfo(table,"birthday");
        //hideInfo(table,"friend");


        //각 문자가 입력되거나 지워지거나 바뀌는 것을 인식
        //검색기능
        search.getDocument().addDocumentListener(new DocumentListener(){

            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = search.getText();

                if (text.trim().length() == 0) {
                    sorter.setRowFilter(null);
                    //필요에 따라 필터를 적용
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = search.getText();

                if (text.trim().length() == 0) {
                    sorter.setRowFilter(null);
                    //필요에 따라 필터를 적용
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            //문자가 바뀔경우지만 필요가 없음
            public void changedUpdate(DocumentEvent e) {

            }

        });



        setVisible(true);
        //상세정보 표시
        seeInfo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                TableModel data = table.getModel();

                String ID = (String)data.getValueAt(row, 0);
                String Nickname = (String)data.getValueAt(row, 1);
                String Name = (String)data.getValueAt(row, 2);
                String email = (String)data.getValueAt(row, 3);
                String address = (String)data.getValueAt(row, 4);
                String birthday = (String)data.getValueAt(row, 5);
                String friend = (String)data.getValueAt(row, 6);
                //새 창을 띄워서 보여줌
                ShowDetail show = new ShowDetail(ID,Nickname,Name,email,address,birthday,friend);

            }
        });

        //친구추가
        addFriend.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                OpenTable ot = new OpenTable();
                //바뀌는 행의 인덱스를 가져옴
                int row = table.getSelectedRow();
                TableModel data = table.getModel();

                String ID = (String)data.getValueAt(row, 0);
                //database 상에서 update
                String sql = "UPDATE TMP2 SET friend = \"Y\" " + " WHERE ID =" + ID;
                System.out.println(row );
                ot.update(sql);
                ot.close();
                //table update
                data.setValueAt("Y", row, 6);
            }
        });
    }
    private void setContentPane(JPanel panel) {
        // TODO Auto-generated method stub

    }

    public static void main(String[] args) {
        TmpLogin log = new TmpLogin();
        //UserManage um = new UserManage();
        OpenTable ot = new OpenTable();
        ot.select("SELECT*FROM TMP2");

        try
        {

            //database가 잘 작동하는지 테스트 차원에서 해본거 없어도 무관함
            while(ot.rs.next())
            {
                System.out.println(ot.rs.getString("ID")
                        + ot.rs.getString("name")
                        + ot.rs.getString("supname")
                        + ot.rs.getString("email")
                        + ot.rs.getString("address")
                        + ot.rs.getString("birthday")
                        + ot.rs.getString("friend")
                );

            }


        }
        catch(Exception e)
        {
            System.out.println("error123");
        }
        ot.close();

    }

    //table에 있는 특정 column을 안보이게
    void hideInfo(JTable table,String tmp){
        table.getColumn(tmp).setWidth(0);
        table.getColumn(tmp).setMinWidth(0);
        table.getColumn(tmp).setMaxWidth(0);
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub

    }
    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

    }
    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }
    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }
    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

}

