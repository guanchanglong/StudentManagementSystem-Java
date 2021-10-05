package com.view;

import com.entity.Course;
import com.entity.Score;
import com.entity.Student;
import com.entity.Teacher;
import com.service.CourseService;
import com.service.ScoreService;
import com.service.SelectedCourseService;
import com.service.StudentService;
import com.service.impl.CourseServiceImpl;
import com.service.impl.ScoreServiceImpl;
import com.service.impl.SelectedCourseServiceImpl;
import com.service.impl.StudentServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.Vector;

public class ManageScoreFrm extends JInternalFrame {
	private final ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
	private final CourseService courseService = (CourseServiceImpl)context.getBean("CourseServiceImpl");
	private final ScoreService scoreService = (ScoreServiceImpl)context.getBean("ScoreServiceImpl");
	private final SelectedCourseService selectedCourseService = (SelectedCourseServiceImpl)context.getBean("SelectedCourseServiceImpl");
	private final StudentService studentService = (StudentServiceImpl)context.getBean("StudentServiceImpl");

	private JTable scoreListTable;
	private JTextField editScoreTextField;
	private List<Student>studentList;
	private JComboBox courseComboBox;
	private JComboBox studentComboBox;
	private List<Course>courseList;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageScoreFrm frame = new ManageScoreFrm();
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
	public ManageScoreFrm() {
		setClosable(true);
		setIconifiable(true);
		setTitle("\u6210\u7EE9\u7BA1\u7406\u754C\u9762");
		setBounds(100, 100, 791, 618);

		JLabel label = new JLabel("\u5B66\u751F\uFF1A");
		label.setIcon(new ImageIcon(ManageScoreFrm.class.getResource("/images/\u5B66\u751F\u7BA1\u7406.png")));
		label.setFont(new Font("微软雅黑", Font.PLAIN, 14));

		studentComboBox = new JComboBox();

		JLabel label_1 = new JLabel("\u8BFE\u7A0B");
		label_1.setIcon(new ImageIcon(ManageScoreFrm.class.getResource("/images/\u8BFE\u7A0B.png")));
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 14));

		courseComboBox = new JComboBox();
		courseComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent ie) {
				courseChangedAct(ie);
			}
		});

		JButton searchButton = new JButton("\u67E5\u8BE2");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				Student student = (Student)studentComboBox.getSelectedItem();
				Course course = (Course)courseComboBox.getSelectedItem();
				Score score = new Score();
				score.setStudent_id(student.getId());
				score.setCourse_id(course.getId());
				setTable(score);
			}
		});
		searchButton.setIcon(new ImageIcon(ManageScoreFrm.class.getResource("/images/\u641C\u7D22.png")));
		searchButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));

		JScrollPane scrollPane = new JScrollPane();

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "\u4FEE\u6539\u6210\u7EE9", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
												.addGap(79)
												.addComponent(label)
												.addGap(18)
												.addComponent(studentComboBox, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
												.addGap(31)
												.addComponent(label_1)
												.addGap(31)
												.addComponent(courseComboBox, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
												.addGap(68)
												.addComponent(searchButton))
										.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
												.addGap(58)
												.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
														.addComponent(panel, 0, 0, Short.MAX_VALUE)
														.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 659, Short.MAX_VALUE))))
								.addContainerGap(71, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
								.addGap(36)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(label)
										.addComponent(studentComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(label_1)
										.addComponent(courseComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(searchButton))
								.addGap(35)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 325, GroupLayout.PREFERRED_SIZE)
								.addGap(30)
								.addComponent(panel, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
								.addContainerGap(49, Short.MAX_VALUE))
		);

		JLabel label_2 = new JLabel("\u6210\u7EE9\uFF1A");
		label_2.setIcon(new ImageIcon(ManageScoreFrm.class.getResource("/images/\u6210\u7EE9.png")));
		label_2.setFont(new Font("微软雅黑", Font.PLAIN, 14));

		editScoreTextField = new JTextField();
		editScoreTextField.setColumns(10);

		JButton confirmEditButton = new JButton("\u786E\u8BA4\u4FEE\u6539");
		confirmEditButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				editSubmitAct(ae);
			}
		});
		confirmEditButton.setIcon(new ImageIcon(ManageScoreFrm.class.getResource("/images/\u786E\u8BA4.png")));
		confirmEditButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));

		JButton deleteButton = new JButton("\u5220\u9664\u6210\u7EE9");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				deleteAct(ae);
			}
		});
		deleteButton.setIcon(new ImageIcon(ManageScoreFrm.class.getResource("/images/\u5220\u9664.png")));
		deleteButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
								.addGap(32)
								.addComponent(label_2)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(editScoreTextField, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)
								.addGap(62)
								.addComponent(confirmEditButton)
								.addGap(62)
								.addComponent(deleteButton)
								.addContainerGap(78, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
								.addContainerGap(27, Short.MAX_VALUE)
								.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
										.addComponent(label_2)
										.addComponent(editScoreTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(confirmEditButton)
										.addComponent(deleteButton))
								.addGap(20))
		);
		panel.setLayout(gl_panel);

		scoreListTable = new JTable();
		scoreListTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent me) {
				tableItemClick(me);
			}
		});
		scoreListTable.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"\u6210\u7EE9ID", "\u5B66\u751F\u59D3\u540D", "\u8BFE\u7A0B\u540D\u79F0", "\u6210\u7EE9"
				}
		) {
			boolean[] columnEditables = new boolean[] {
					false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(scoreListTable);
		getContentPane().setLayout(groupLayout);
		setCourseCombox();
		setStudentCombox();
		initTable();
	}
	protected void deleteAct(ActionEvent ae) {
		int row = scoreListTable.getSelectedRow();
		if(row == -1){
			JOptionPane.showMessageDialog(this, "请先选择一行！");
			return;
		}
		if(JOptionPane.showConfirmDialog(this, "确定要删除么？") == JOptionPane.OK_OPTION){
			int scoreId = Integer.parseInt(scoreListTable.getValueAt(row, 0).toString());
			if(scoreService.delete(scoreId)){
				JOptionPane.showMessageDialog(this, "删除成功！");
				editScoreTextField.setText("");
				initTable();
			}else{
				JOptionPane.showMessageDialog(this, "删除失败！");
			}
		}
	}

	protected void editSubmitAct(ActionEvent ae) {
		int row = scoreListTable.getSelectedRow();
		if(row == -1){
			JOptionPane.showMessageDialog(this, "请先选择一行！");
			return;
		}
		int scoreId = Integer.parseInt(scoreListTable.getValueAt(row, 0).toString());
		int score = Integer.parseInt(editScoreTextField.getText().toString());
		if(scoreService.update(scoreId, score)){
			JOptionPane.showMessageDialog(this, "更新成功！");
			editScoreTextField.setText("");
			initTable();
		}else{
			JOptionPane.showMessageDialog(this, "更新失败！");
		}
	}

	protected void tableItemClick(MouseEvent me) {
		String score = (scoreListTable.getValueAt(scoreListTable.getSelectedRow(), 3).toString());
		editScoreTextField.setText(score);
	}

	protected void courseChangedAct(ItemEvent ie) {
		if(ie.getStateChange() == ItemEvent.SELECTED){
			setStudentCombox();
		}
	}

	private void setStudentCombox(){
		studentComboBox.removeAllItems();
		studentList = studentService.getStudentList(new Student());
		Course course = (Course)courseComboBox.getSelectedItem();
		List<Student> selectedCourseStudentList = getSelectedCourseStudentList(course);
		for (Student student : studentList) {
			for(Student student2 : selectedCourseStudentList){
				if(student.getId() == student2.getId())
					studentComboBox.addItem(student);
			}
		}

	}
	private void setCourseCombox(){
		courseList = courseService.getCourseList(new Course());
		for (Course course : courseList) {
			if("教师".equals(MainFrm.userType.getName())){
				Teacher teacher = (Teacher)MainFrm.userObject;
				if(course.getTeacher_id() == teacher.getId()){
					courseComboBox.addItem(course);
				}
				continue;
			}
			//执行到这里一定是超级管理员身份
			courseComboBox.addItem(course);
		}

	}
	private void initTable(){
		getScoreList(new Score());
	}
	private void setTable(Score score){
		getScoreList(score);
	}
	private void getScoreList(Score score){
		List<Score> scoreList = scoreService.getScoreList(score);
		DefaultTableModel dft = (DefaultTableModel) scoreListTable.getModel();
		dft.setRowCount(0);
		for (Score s : scoreList) {
			Vector v = new Vector();
			v.add(s.getId());
			v.add(getStudentNameById(s.getStudent_id()));
			v.add(getCourseById(s.getCourse_id()));
			v.add(s.getScore());
			dft.addRow(v);
		}
	}

	private Course getCourseById(int id){
		for (int i = 0; i < courseList.size(); i++) {
			if(id == courseList.get(i).getId())return courseList.get(i);
		}
		return null;
	}

	private List<Student> getSelectedCourseStudentList(Course course){
		List<Student> selectedCourseStudentList = selectedCourseService.getSelectedCourseStudentList(course);
		return selectedCourseStudentList;
	}

	private String getStudentNameById(int id){
		for(Student student :studentList){
			if(student.getId() == id)return student.getName();
		}
		return null;
	}
}
