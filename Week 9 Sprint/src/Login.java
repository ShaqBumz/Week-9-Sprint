import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;

public class Login {

	private JFrame frame;
	private JPasswordField tfPIN;
	private JTextField tfUsername;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblLogo;
	private JLabel lblBackground;
	private JLabel lblError;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBackground(new Color(0, 0, 255));
		frame.getContentPane().setBackground(new Color(0, 0, 128));
		frame.setBounds(100, 100, 641, 451);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		tfPIN = new JPasswordField();
		tfPIN.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		tfPIN.setBounds(223, 234, 172, 20);
		frame.getContentPane().add(tfPIN);
		
		tfUsername = new JTextField();
		tfUsername.setBounds(223, 153, 172, 20);
		frame.getContentPane().add(tfUsername);
		tfUsername.setColumns(10);
		
		lblError = new JLabel("Incorrect username/password! Please retry.");
		lblError.setVisible(false);
		
		JButton btnLogin = new JButton("LOGIN");
		btnLogin.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		btnLogin.setBackground(Color.WHITE);
		Image photo = new ImageIcon(this.getClass().getResource("/login.png")).getImage();
		btnLogin.setIcon(new ImageIcon(photo));

		
		
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int Checker = 0;
				String search = tfUsername.getText();
				
				// Create a variable for the connection string.
				String connectionUrl = "jdbc:sqlserver://localhost:1433;"
						+ "databaseName= Week9Sprint;integratedSecurity=true;";

				// Declare the JDBC Objects.
				Connection con = null;
				
				try {
					// Establish the connection.
					Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
					con = DriverManager.getConnection(connectionUrl);

					// Create and execute an SQL statement that returns some data
					String SQLSearch = "SELECT * FROM [dbo].[Bank] WHERE Username = '" + search + "'";

					ResultSet rs = con.createStatement().executeQuery(SQLSearch);
					while (rs.next()) {

						
						Checker = 1;
						// insert Data into Text fields
						String PIN = rs.getString("PIN");
						String InputPass = tfPIN.getText();
						
						if (InputPass.equals(PIN)) {
							
							Bank Change = new Bank();
							Change.Banky();
							String Display = tfUsername.getText();
							
							frame.setVisible(false);
							
							System.out.println("Welcome " + Display + "!");
							
						}else {
							
							lblError.setVisible(true);
							
						}
						
						
						
						}//While End
					
					

				}//Try End

				catch (Exception e1) {

					lblError.setVisible(true);
				}
				
				if (Checker == 0) {
					
					lblError.setVisible(true);
					
				}
							
							
							
						
				
			}
		});
		btnLogin.setBounds(223, 274, 172, 69);
		frame.getContentPane().add(btnLogin);
		
		lblNewLabel = new JLabel("Username:");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		lblNewLabel.setBounds(223, 129, 116, 20);
		frame.getContentPane().add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("4-Digit PIN: ");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(222, 206, 144, 28);
		frame.getContentPane().add(lblNewLabel_1);
		
		lblLogo = new JLabel("");
		lblLogo.setBackground(new Color(0, 0, 255));
		lblLogo.setForeground(new Color(255, 255, 255));
		lblLogo.setBounds(540, 78, 64, 64);
		frame.getContentPane().add(lblLogo);
		ImageIcon iconBackground1 = new ImageIcon("Images/Logo.png");
		lblLogo.setIcon(iconBackground1);
		
		lblBackground = new JLabel("");
		lblBackground.setBackground(new Color(0, 0, 128));
		lblBackground.setBounds(0, 0, 626, 417);
		frame.getContentPane().add(lblBackground);
		ImageIcon iconBackground2 = new ImageIcon("Images/BankBackground2.jpg");
		lblBackground.setIcon(iconBackground2);
		
		
		lblError.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		lblError.setForeground(Color.RED);
		lblError.setBounds(140, 102, 329, 28);
		frame.getContentPane().add(lblError);
		
	}
}
