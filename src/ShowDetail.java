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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class ShowDetail extends JFrame{
	String ID;
	String NickName;
	String Name;
	String email;
	String adderss;
	String birhtday;
	String friend;
	String[] column = {"ID","Nickname","Name","e-mail","address","birthday","Friend"};
	ShowDetail(String ID,String NickName,String Name,String email,String adderss,String birhtday,String friend){
		super(ID);
		setSize(600,100);
		this.ID = ID;
		this.NickName = NickName;
		this.Name = Name;
		this.email = email;
		this.adderss = adderss;
		this.birhtday = birhtday;
		this.friend = friend;
		
		
		String data[][] = {{ID,NickName,Name,email,adderss,birhtday,friend}};
		DefaultTableModel model = new DefaultTableModel(data,column);
		JTable table = new JTable(model);
		
		JPanel panel = new JPanel();
		this.setContentPane(panel);
		panel.add(table);
		setVisible(true);
		
		System.out.println(friend);
	}
}
