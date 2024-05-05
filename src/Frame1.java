import java.awt.EventQueue;

import javax.swing.JFrame;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Frame1 {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame1 window = new Frame1();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws ClassNotFoundException 
	 */
	public Frame1() throws ClassNotFoundException{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws ClassNotFoundException 
	 */
	private void initialize() throws ClassNotFoundException  {
		frame = new JFrame();
		frame.setBounds(100, 100, 869, 474);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(36, 31, 282, 393);
		frame.getContentPane().add(scrollPane);
		
		JTextPane textPane = new JTextPane();
		scrollPane.setViewportView(textPane);
		
		JLabel custLbl = new JLabel("");
		custLbl.setHorizontalAlignment(SwingConstants.CENTER);
		custLbl.setBounds(328, 247, 436, 55);
		frame.getContentPane().add(custLbl);
		
		
		
		JButton btnNewButton = new JButton("Get Customers (List of customer names + amount of customers)");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String url = "jdbc:sqlserver://KLAY5900X\\SQLEXPRESS:1433;databaseName=Northwind;integratedSecurity=true;encrypt=true;trustServerCertificate=true;";
				try {
				    Connection connection = DriverManager.getConnection(url);
				    
				} catch (SQLException f) {
				    
				    f.printStackTrace();
				}
				String sql = "SELECT * FROM dbo.Customers";

				try {
				    Connection connection = DriverManager.getConnection(url);
				    Statement statement = connection.createStatement();
				    ResultSet resultSet = statement.executeQuery(sql);
				    int amnt = 0;
				    String outputText = "";
				    while(resultSet.next()) {
				    	String name = resultSet.getString("ContactName");
				    	outputText += ( name + "\n");
				    	amnt++;
				    }
				    textPane.setText(outputText);
				    custLbl.setText("Total Customers: " + amnt);
				} catch (SQLException f) {
				    
				    f.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(328, 31, 436, 83);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Get Customers Last Names (Utilizing Database Object)");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String url = "jdbc:sqlserver://KLAY5900X\\SQLEXPRESS:1433;databaseName=Northwind;integratedSecurity=true;encrypt=true;trustServerCertificate=true;";
				try {
				    Connection connection = DriverManager.getConnection(url);
				    
				} catch (SQLException f) {
				    
				    f.printStackTrace();
				}
				String sql = "SELECT * FROM CustomerList";

				try {
				    Connection connection = DriverManager.getConnection(url);
				    Statement statement = connection.createStatement();
				    ResultSet resultSet = statement.executeQuery(sql);
				    while(resultSet.next()) {
				    	custLbl.setText("Total Customers: " + Integer.toString(resultSet.getInt("NumberOfCustomers")));
				    	textPane.setText(resultSet.getString("CustomerLastNames"));
				    }
	
				} catch (SQLException f) {
				    
				    f.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBounds(328, 153, 436, 83);
		frame.getContentPane().add(btnNewButton_1);
		

	
		
		
		
		
		
	}
}
