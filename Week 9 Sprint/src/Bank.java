import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class Bank {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void Banky() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Bank window = new Bank();
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
	public Bank() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 639, 455);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblCurrent = new JLabel("Money");
		lblCurrent.setForeground(Color.WHITE);
		lblCurrent.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurrent.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		lblCurrent.setBounds(133, 218, 360, 29);
		frame.getContentPane().add(lblCurrent);
		
		JLabel lblTrans = new JLabel("Money");
		lblTrans.setForeground(Color.WHITE);
		lblTrans.setHorizontalAlignment(SwingConstants.CENTER);
		lblTrans.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		lblTrans.setBounds(133, 376, 360, 29);
		frame.getContentPane().add(lblTrans);

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		

		// Create a variable for the connection string.
		String connectionUrl = "jdbc:sqlserver://localhost:1433;"
				+ "databaseName= Week9Sprint;integratedSecurity=true;";

		// Declare the JDBC Objects.
		Connection con = null;

		try {
			String search = JOptionPane.showInputDialog("Please Confirm Username");
			// Establish the connection.
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(connectionUrl);

			// Create and execute an SQL statement that returns some data
			String SQLSearch = "SELECT * FROM [dbo].[Bank] WHERE Username = '" + search + "'";

			ResultSet rs = con.createStatement().executeQuery(SQLSearch);
			while (rs.next()) {

				// insert Data into Text fields
				String Balance = rs.getString("Balance");
				lblCurrent.setText("R " + Balance + ".00");
				lblTrans.setText("R " + "0.00");

			}

		}

		catch (Exception e1) {

			JOptionPane.showMessageDialog(null, "[Error-ID10T]: Username could not be confirmed");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
		
		JButton btnWithdraw = new JButton("");
		btnWithdraw.setIcon(new ImageIcon("C:\\Users\\shaqu\\eclipse-workspace\\Week 9 Sprint\\Images\\button_withdraw (1).png"));
		btnWithdraw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Create a variable for the connection string.
				String connectionUrl = "jdbc:sqlserver://localhost:1433;"
						+ "databaseName= Week9Sprint;integratedSecurity=true;";

				// Declare the JDBC Objects.
				Connection con = null;
				Statement stmt = null;

				try {
					String search = JOptionPane.showInputDialog("Please Confirm Username");
					// Establish the connection.
					Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
					con = DriverManager.getConnection(connectionUrl);

					// Create and execute an SQL statement that returns some data
					String SQLSearch = "SELECT * FROM [dbo].[Bank] WHERE Username = '" + search + "'";

					ResultSet rs = con.createStatement().executeQuery(SQLSearch);
					while (rs.next()) {

						// insert Data into Text fields
						String Balance = rs.getString("Balance");
						
						int withdraw = Integer.parseInt(JOptionPane.showInputDialog("How Much Would You Like To Withdraw?"));
						int Amount = Integer.parseInt(Balance) - withdraw;
						
						String SQLSet = "UPDATE [dbo].[Bank] SET [Balance] = '" + Integer.toString(Amount) + "' WHERE Username = '" + search + "' ";
						stmt = con.createStatement();
						stmt.execute(SQLSet);

						System.out.println("Your Info Has Successfully Been Added");
						
						lblTrans.setText("-R " + Integer.toString(withdraw) + ".00");
						lblCurrent.setText("R " + Integer.toString(Amount) + ".00");
						
						
						
					}

				}

				catch (Exception e1) {

					JOptionPane.showMessageDialog(null, "[Error-ID10T]: Username could not be confirmed");
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				}
				
			}
		});
		btnWithdraw.setForeground(Color.WHITE);
		btnWithdraw.setBackground(Color.WHITE);
		btnWithdraw.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		btnWithdraw.setBounds(102, 25, 138, 54);
		frame.getContentPane().add(btnWithdraw);
		btnWithdraw.setOpaque(false);
		btnWithdraw.setContentAreaFilled(false);
		btnWithdraw.setBorderPainted(false);
		
		
		JButton btnDeposit = new JButton("");
		btnDeposit.setIcon(new ImageIcon("C:\\Users\\shaqu\\eclipse-workspace\\Week 9 Sprint\\Images\\button_deposit (1).png"));
		btnDeposit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				

				// Create a variable for the connection string.
				String connectionUrl = "jdbc:sqlserver://localhost:1433;"
						+ "databaseName= Week9Sprint;integratedSecurity=true;";

				// Declare the JDBC Objects.
				Connection con = null;
				Statement stmt = null;

				try {
					String search = JOptionPane.showInputDialog("Please Confirm Username");
					// Establish the connection.
					Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
					con = DriverManager.getConnection(connectionUrl);

					// Create and execute an SQL statement that returns some data
					String SQLSearch = "SELECT * FROM [dbo].[Bank] WHERE Username = '" + search + "'";

					ResultSet rs = con.createStatement().executeQuery(SQLSearch);
					while (rs.next()) {

						// insert Data into Text fields
						String Balance = rs.getString("Balance");
						
						int deposit = Integer.parseInt(JOptionPane.showInputDialog("How Much Would You Like To Deposit?"));
						int Amount = Integer.parseInt(Balance) + deposit;
						
						String SQLSet = "UPDATE [dbo].[Bank] SET [Balance] = '" + Integer.toString(Amount) + "' WHERE Username = '" + search + "' ";
						stmt = con.createStatement();
						stmt.execute(SQLSet);

						System.out.println("Your Info Has Successfully Been Added");
						
						lblTrans.setText("+R " + Integer.toString(deposit) + ".00");
						lblCurrent.setText("R " + Integer.toString(Amount) + ".00");
						
						
						
					}

				}

				catch (Exception e1) {

					JOptionPane.showMessageDialog(null, "[Error-ID10T]: Username could not be confirmed");
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				}
				
			}
		});
		btnDeposit.setForeground(Color.WHITE);
		btnDeposit.setBackground(Color.WHITE);
		btnDeposit.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		btnDeposit.setBounds(386, 25, 138, 54);
		frame.getContentPane().add(btnDeposit);
		btnDeposit.setOpaque(false);
		btnDeposit.setContentAreaFilled(false);
		btnDeposit.setBorderPainted(false);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\shaqu\\eclipse-workspace\\Week 9 Sprint\\Images\\Logo.png"));
		lblNewLabel.setBackground(Color.YELLOW);
		lblNewLabel.setBounds(282, 11, 64, 67);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Latest Transaction:");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(133, 323, 360, 28);
		frame.getContentPane().add(lblNewLabel_1);
		
		
		
		JLabel lblNewLabel_3 = new JLabel("Current Balance:");
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(133, 182, 360, 22);
		frame.getContentPane().add(lblNewLabel_3);
		
		
		
		JLabel lblBackground = new JLabel("New label");
		lblBackground.setIcon(new ImageIcon("C:\\Users\\shaqu\\eclipse-workspace\\Week 9 Sprint\\Images\\BankBackground3.jpg"));
		lblBackground.setBounds(0, 0, 623, 416);
		frame.getContentPane().add(lblBackground);
		
		
		
	}
}
