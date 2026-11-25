package system;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Leaderboards extends JPanel {

	private static final long serialVersionUID = 1L;
	private DefaultTableModel tableModel;
	private JTable table;
	private JTextField txtId;
	private JTextField txtName;
	private JTextField txtGrade;
	private ArrayList<GradeEntry> entries = new ArrayList<>();

	private static class GradeEntry {
		String id;
		String name;
		double grade;

		GradeEntry(String id, String name, double grade) {
			this.id = id;
			this.name = name;
			this.grade = grade;
		}
	}

	public Leaderboards() {
		setLayout(null);
		setBackground(Color.WHITE);

		JLabel lblHeader = new JLabel("Grades Leaderboard");
		lblHeader.setFont(new Font("SansSerif", Font.BOLD, 28));
		lblHeader.setForeground(new Color(0, 11, 26));
		lblHeader.setBounds(25, 15, 360, 40);
		add(lblHeader);

		JLabel lblId = new JLabel("ID:");
		lblId.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblId.setBounds(25, 65, 30, 28);
		add(lblId);

		txtId = new JTextField();
		txtId.setFont(new Font("SansSerif", Font.PLAIN, 14));
		txtId.setBounds(55, 65, 115, 28);
		add(txtId);

		JLabel lblName = new JLabel("Name:");
		lblName.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblName.setBounds(180, 65, 50, 28);
		add(lblName);

		txtName = new JTextField();
		txtName.setFont(new Font("SansSerif", Font.PLAIN, 14));
		txtName.setBounds(230, 65, 200, 28);
		add(txtName);

		JLabel lblGrade = new JLabel("Grade:");
		lblGrade.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblGrade.setBounds(440, 65, 55, 28);
		add(lblGrade);

		txtGrade = new JTextField();
		txtGrade.setFont(new Font("SansSerif", Font.PLAIN, 14));
		txtGrade.setBounds(495, 65, 95, 28);
		add(txtGrade);

		JButton btnAdd = new JButton("Add & Sort");
		btnAdd.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnAdd.setBackground(new Color(0, 11, 26));
		btnAdd.setForeground(Color.WHITE);
		btnAdd.setBounds(605, 65, 130, 28);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handleAddGrade();
			}
		});
		add(btnAdd);

		JButton btnSort = new JButton("Sort");
		btnSort.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnSort.setBackground(new Color(25, 38, 58));
		btnSort.setForeground(Color.WHITE);
		btnSort.setBounds(745, 65, 110, 28);
		btnSort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bubbleSortDescending();
				refreshLeaderboardTable();
			}
		});
		add(btnSort);

		String[] columns = { "Rank", "Student ID", "Name", "Grade" };
		tableModel = new DefaultTableModel(columns, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		table = new JTable(tableModel);
		table.setFont(new Font("SansSerif", Font.PLAIN, 15));
		table.setRowHeight(28);
		table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 15));

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(25, 108, 830, 547);
		add(scrollPane);

		// Sample initial grades
		addGradeRecord("2026-0002", "Maria Santos", 91.5);
		addGradeRecord("2026-0001", "Juan Dela Cruz", 88.0);
		bubbleSortDescending();
		refreshLeaderboardTable();
	}

	private void handleAddGrade() {
		String id = txtId.getText().trim();
		String name = txtName.getText().trim();
		String gradeText = txtGrade.getText().trim();

		if (id.isEmpty() || name.isEmpty() || gradeText.isEmpty()) {
			JOptionPane.showMessageDialog(
				this,
				"Please fill in ID, Name, and Grade.",
				"Missing Information",
				JOptionPane.WARNING_MESSAGE
			);
			return;
		}

		double grade;
		try {
			grade = Double.parseDouble(gradeText);
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(
				this,
				"Grade must be a valid number.",
				"Invalid Grade",
				JOptionPane.ERROR_MESSAGE
			);
			return;
		}

		if (grade < 0 || grade > 100) {
			JOptionPane.showMessageDialog(
				this,
				"Grade should be between 0 and 100.",
				"Out of Range",
				JOptionPane.WARNING_MESSAGE
			);
			return;
		}

		addGradeRecord(id, name, grade);
		bubbleSortDescending();
		refreshLeaderboardTable();

		txtId.setText("");
		txtName.setText("");
		txtGrade.setText("");
		txtId.requestFocusInWindow();
	}

	public void addGradeRecord(String id, String name, double grade) {
		entries.add(new GradeEntry(id, name, grade));
	}

	// Bubble Sort: highest grade first (descending)
	private void bubbleSortDescending() {
		int n = entries.size();
		for (int i = 0; i < n - 1; i++) {
			for (int j = 0; j < n - 1 - i; j++) {
				if (entries.get(j).grade < entries.get(j + 1).grade) {
					GradeEntry temp = entries.get(j);
					entries.set(j, entries.get(j + 1));
					entries.set(j + 1, temp);
				}
			}
		}
	}

	private void refreshLeaderboardTable() {
		tableModel.setRowCount(0);
		for (int i = 0; i < entries.size(); i++) {
			GradeEntry item = entries.get(i);
			String rank = "#" + (i + 1);
			tableModel.addRow(new Object[] {
				rank,
				item.id,
				item.name,
				String.format("%.2f", item.grade)
			});
		}
	}
}
