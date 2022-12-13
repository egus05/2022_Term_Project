import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class TmpLogin extends JFrame{
	JPanel lgPanel = new JPanel(new GridLayout(3,2));
	JLabel idLabel = new JLabel("ID");
	JLabel pwLabel = new JLabel("PassWord");
	JTextField idText = new JTextField();
	JPasswordField pwText = new JPasswordField();
	JButton login = new JButton("login");
	
	public TmpLogin() {
		super("Login Page");
		this.setContentPane(lgPanel);
		lgPanel.add(idLabel);
		lgPanel.add(pwLabel);
		lgPanel.add(idText);
		lgPanel.add(pwText);
		lgPanel.add(login);
		
		//ID Password 가운데로 정렬
		idLabel.setHorizontalAlignment(NORMAL);
		pwLabel.setHorizontalAlignment(NORMAL);
		
		setSize(350,150);
		setVisible(true);
		//로그인 버튼을 누름
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//user가 보낸 id와 password 받아옴
				String id = idText.getText().trim();
				String pw = pwText.getText().trim();
				
				
				//아이디와 비밀번호가 맞는 경우 (예시로 name = Kia, pass = qw9984)
				if(LogId(id) && LogPw(pw)) {
					JOptionPane.showMessageDialog(null, "Welcome "+ id);
					UserManage user = new UserManage();
					//로그인창 끄기
					dispose();
					return;
				}
				//로그인 실패
				JOptionPane.showMessageDialog(null, "로그인 실패", "로그인 확인!", JOptionPane.DEFAULT_OPTION);
				
				
			}
		});
	}
	boolean LogId(String id) {
		boolean tmp = false;
		OpenTable ot = new OpenTable();
		ot.select("SELECT*FROM TMP2");
		
		try 
		{
			while(ot.rs.next())
			{
				System.out.println(ot.rs.getString("name"));
				if(id.equals(ot.rs.getString("name"))) {
					System.out.println("ID passed");
					return true;
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("errorID");
		}
		
		ot.close();
		return tmp;
	}
	
	boolean LogPw(String pw) {
		boolean tmp = false;
		OpenTable ot = new OpenTable();
		ot.select("SELECT*FROM TMP2");
		
		try 
		{
			while(ot.rs.next())
			{
				if(pw.equals(ot.rs.getString("password"))) {
					System.out.println("Pw passed");
					return true;
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("errorPW");
		}
		
		ot.close();
		return tmp;
	}
}

