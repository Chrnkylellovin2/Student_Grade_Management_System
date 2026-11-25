package system;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Main_Frame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main_Frame frame = new Main_Frame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main_Frame() {
		setTitle("Student Grade Management System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 750);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Sidebar
		JPanel sidebar = new JPanel();
		sidebar.setBackground(new Color(0, 11, 26));
		sidebar.setBounds(0, 0, 317, 711);
		sidebar.setLayout(null);
		contentPane.add(sidebar);

		JLabel Logo = new JLabel("");
		Logo.setForeground(new Color(255, 255, 255));
		Logo.setBounds(0, -43, 317, 375);
		Logo.setHorizontalAlignment(SwingConstants.CENTER);
		Logo.setVerticalAlignment(SwingConstants.CENTER);

		java.net.URL logoUrl = Main_Frame.class.getResource("/Images/System_Logo.png");
		if (logoUrl != null) {
			ImageIcon rawLogo = new ImageIcon(logoUrl);
			int origW = rawLogo.getIconWidth();
			int origH = rawLogo.getIconHeight();
			int boxW = Logo.getWidth();
			int boxH = Logo.getHeight();

			if (origW > 0 && origH > 0 && boxW > 0 && boxH > 0) {
				double scale = Math.min((double) boxW / origW, (double) boxH / origH);
				int targetW = Math.max(1, (int) Math.round(origW * scale));
				int targetH = Math.max(1, (int) Math.round(origH * scale));

				Image scaledLogo = rawLogo.getImage().getScaledInstance(targetW, targetH, Image.SCALE_SMOOTH);
				Logo.setIcon(new ImageIcon(scaledLogo));
			}
		}
		sidebar.add(Logo);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(192, 192, 192));
		panel.setBounds(10, 308, 297, 343);
		panel.setLayout(null);
		sidebar.add(panel);

		sidebar.setComponentZOrder(Logo, 0);
		sidebar.setComponentZOrder(panel, 1);

		// Right-side screen switcher (CardLayout)
		CardLayout cardLayout = new CardLayout();
		JPanel contentArea = new JPanel(cardLayout);
		contentArea.setBounds(317, 0, 883, 711);
		contentPane.add(contentArea);

		// 1. Home screen
		JPanel homePanel = new JPanel(null);
		homePanel.setBackground(Color.LIGHT_GRAY);

		ImageIcon original = new ImageIcon(
				getClass().getResource("/Images/System_Background.png"));
		Image img = original.getImage().getScaledInstance(883, 710, Image.SCALE_SMOOTH);
		JLabel background = new JLabel(new ImageIcon(img));
		background.setBounds(0, 0, 883, 710);
		homePanel.add(background);

		JLabel titleLabel = new JLabel("<html>Student Grade"
				+ "<br>Management System</html>");
		titleLabel.setBounds(22, 236, 446, 116);
		titleLabel.setFont(new Font("SansSerif", Font.BOLD, 45));
		titleLabel.setForeground(new Color(0, 11, 26));
		homePanel.add(titleLabel);

		JLabel titleBottom = new JLabel("<html>Manage Student records, track grades<br>"
				+ "and view performance with ease.</html>");
		titleBottom.setBounds(22, 351, 436, 61);
		titleBottom.setFont(new Font("SansSerif", Font.PLAIN, 20));
		titleBottom.setForeground(new Color(0, 11, 26));
		homePanel.add(titleBottom);

		homePanel.setComponentZOrder(titleLabel, 0);
		homePanel.setComponentZOrder(titleBottom, 1);
		homePanel.setComponentZOrder(background, homePanel.getComponentCount() - 1);

		// 2. Student Information screen
		Student_Information studentInfoPanel = new Student_Information();

		contentArea.add(homePanel, "HOME");
		contentArea.add(studentInfoPanel, "STUDENTS");
		cardLayout.show(contentArea, "HOME");
		
		// 3. Leaderboards screen
		Leaderboards leaderboardsPanel = new Leaderboards();

		contentArea.add(homePanel, "HOME");
		contentArea.add(studentInfoPanel, "STUDENTS");
		contentArea.add(leaderboardsPanel, "GRADES");
		cardLayout.show(contentArea, "HOME");

		// Sidebar buttons
		JButton home = new JButton("Home");
		home.setForeground(SystemColor.text);
		home.setFont(new Font("SansSerif", Font.PLAIN, 25));
		home.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(contentArea, "HOME");
			}
		});
		home.setBounds(10, 28, 277, 64);
		home.setBackground(new Color(0, 11, 26));
		panel.add(home);

		JButton student = new JButton("Students");
		student.setForeground(SystemColor.text);
		student.setFont(new Font("SansSerif", Font.PLAIN, 25));
		student.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(contentArea, "STUDENTS");
			}
		});
		student.setBounds(10, 103, 277, 64);
		student.setBackground(new Color(0, 11, 26));
		panel.add(student);

		JButton grades = new JButton("Grades");
		grades.setForeground(SystemColor.text);
		grades.setFont(new Font("SansSerif", Font.PLAIN, 25));
		grades.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(contentArea, "GRADES");
			}
		});
		grades.setBounds(10, 178, 277, 64);
		grades.setBackground(new Color(0, 11, 26));
		panel.add(grades);

		JButton exit = new JButton("Exit");
		exit.setForeground(SystemColor.text);
		exit.setFont(new Font("SansSerif", Font.PLAIN, 25));
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		exit.setBounds(10, 253, 277, 64);
		exit.setBackground(new Color(0, 11, 26));
		panel.add(exit);
		


	}
}
