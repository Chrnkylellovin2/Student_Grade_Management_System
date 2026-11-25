package system;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Student_Information extends JPanel {

	private static final long serialVersionUID = 1L;
	private DefaultTableModel tableModel;
	private JTable table;
	private JTextField txtId;
	private JTextField txtName;
	private JTextField txtCourseYear;
	private JComboBox<String> cmbStatus;

	public Student_Information() {
		setLayout(null);
		setBackground(Color.WHITE);

		JLabel lblHeader = new JLabel("Student List");
		lblHeader.setFont(new Font("SansSerif", Font.BOLD, 28));
		lblHeader.setForeground(new Color(0, 11, 26));
		lblHeader.setBounds(25, 15, 300, 40);
		add(lblHeader);

		JLabel lblId = new JLabel("ID:");
		lblId.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblId.setBounds(25, 65, 30, 25);
		add(lblId);

		txtId = new JTextField();
		txtId.setFont(new Font("SansSerif", Font.PLAIN, 14));
		txtId.setBounds(55, 65, 120, 28);
		add(txtId);
		txtId.setColumns(10);

		JLabel lblName = new JLabel("Name:");
		lblName.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblName.setBounds(185, 65, 50, 25);
		add(lblName);

		txtName = new JTextField();
		txtName.setFont(new Font("SansSerif", Font.PLAIN, 14));
		txtName.setBounds(235, 65, 190, 28);
		add(txtName);
		txtName.setColumns(10);

		JLabel lblCourse = new JLabel("Course/Yr:");
		lblCourse.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblCourse.setBounds(435, 65, 80, 25);
		add(lblCourse);

		txtCourseYear = new JLabel() != null ? new JTextField() : new JTextField();
		txtCourseYear.setFont(new Font("SansSerif", Font.PLAIN, 14));
		txtCourseYear.setBounds(515, 65, 140, 28);
		add(txtCourseYear);
		txtCourseYear.setColumns(10);

		cmbStatus = new JComboBox<String>();
		cmbStatus.addItem("Active");
		cmbStatus.addItem("Inactive");
		cmbStatus.setFont(new Font("SansSerif", Font.PLAIN, 14));
		cmbStatus.setBounds(665, 65, 95, 28);
		add(cmbStatus);

		JButton btnAdd = new JButton("Add");
		btnAdd.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnAdd.setBackground(new Color(0, 11, 26));
		btnAdd.setForeground(Color.WHITE);
		btnAdd.setBounds(770, 65, 85, 28);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handleAddStudent();
			}
		});
		add(btnAdd);

		tableModel = new DefaultTableModel(
			new Object[][] {},
			new String[] { "Student ID", "Name", "Course / Year", "Status" }
		);

		table = new JTable(tableModel);
		table.setFont(new Font("SansSerif", Font.PLAIN, 15));
		table.setRowHeight(28);
		table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 15));

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(25, 108, 830, 547);
		add(scrollPane);

		addStudent("2026-0001", "Juan Dela Cruz", "BSCS - 1st Year", "Active");
		addStudent("2026-0002", "Maria Santos", "BSCS - 1st Year", "Active");
	}

	private void handleAddStudent() {
		String id = txtId.getText().trim();
		String name = txtName.getText().trim();
		String courseYear = txtCourseYear.getText().trim();
		String status = (String) cmbStatus.getSelectedItem();

		if (id.isEmpty() || name.isEmpty() || courseYear.isEmpty()) {
			JOptionPane.showMessageDialog(
				this,
				"Please fill in Student ID, Name, and Course/Year.",
				"Missing Information",
				JOptionPane.WARNING_MESSAGE
			);
			return;
		}

		addStudent(id, name, courseYear, status);

		txtId.setText("");
		txtName.setText("");
		txtCourseYear.setText("");
		cmbStatus.setSelectedIndex(0);
		txtId.requestFocusInWindow();
	}

	public void addStudent(String id, String name, String courseYear, String status) {
		tableModel.addRow(new Object[] { id, name, courseYear, status });
	}

	public void clearStudents() {
		tableModel.setRowCount(0);
	}
}
