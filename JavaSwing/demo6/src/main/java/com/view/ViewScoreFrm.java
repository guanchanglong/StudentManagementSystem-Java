package com.view;

import com.entity.Course;
import com.entity.Score;
import com.entity.SelectedCourse;
import com.entity.Student;
import com.service.CourseService;
import com.service.ScoreService;
import com.service.SelectedCourseService;
import com.service.impl.CourseServiceImpl;
import com.service.impl.ScoreServiceImpl;
import com.service.impl.SelectedCourseServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class ViewScoreFrm extends JInternalFrame {
	private final ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
	private final CourseService courseService = (CourseServiceImpl)context.getBean("CourseServiceImpl");
	private final ScoreService scoreService = (ScoreServiceImpl)context.getBean("ScoreServiceImpl");
	private final SelectedCourseService selectedCourseService = (SelectedCourseServiceImpl)context.getBean("SelectedCourseServiceImpl");

	private JTable scoreListTable;
	private JLabel studentNameLabel;
	private JComboBox courseComboBox;
	private List<Course> courseList = new ArrayList<Course>();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewScoreFrm frame = new ViewScoreFrm();
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
	public ViewScoreFrm() {
		setClosable(true);
		setIconifiable(true);
		setTitle("\u5B66\u751F\u6210\u7EE9\u67E5\u770B");
		setBounds(100, 100, 698, 518);

		JLabel label = new JLabel("\u5B66\u751F\u59D3\u540D\uFF1A");
		label.setIcon(new ImageIcon(ViewScoreFrm.class.getResource("/images/\u5B66\u751F\u7BA1\u7406.png")));
		label.setFont(new Font("微软雅黑", Font.PLAIN, 14));

		studentNameLabel = new JLabel("");
		studentNameLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));

		JLabel label_1 = new JLabel("\u6240\u9009\u8BFE\u7A0B\uFF1A");
		label_1.setIcon(new ImageIcon(ViewScoreFrm.class.getResource("/images/\u8BFE\u7A0B.png")));
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 14));

		courseComboBox = new JComboBox();
		courseComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent ie) {
				courseChangedAct(ie);
			}
		});

		JScrollPane scrollPane = new JScrollPane();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
												.addGap(123)
												.addComponent(label)
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addComponent(studentNameLabel, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(label_1)
												.addGap(18)
												.addComponent(courseComboBox, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE))
										.addGroup(groupLayout.createSequentialGroup()
												.addGap(56)
												.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 574, GroupLayout.PREFERRED_SIZE)))
								.addContainerGap(52, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
								.addGap(56)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(label)
										.addComponent(studentNameLabel)
										.addComponent(label_1)
										.addComponent(courseComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGap(31)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 336, GroupLayout.PREFERRED_SIZE)
								.addContainerGap(44, Short.MAX_VALUE))
		);

		scoreListTable = new JTable();
		scoreListTable.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"\u6210\u7EE9ID", "\u5B66\u751F\u59D3\u540D", "\u8BFE\u7A0B\u540D\u79F0", "\u6240\u53D6\u6210\u7EE9"
				}
		) {
			boolean[] columnEditables = new boolean[] {
					true, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(scoreListTable);
		getContentPane().setLayout(groupLayout);
		setCourseCombox();
		initTable();
	}
	protected void courseChangedAct(ItemEvent ie) {
		// TODO Auto-generated method stub
		setTable();
	}

	private void setCourseCombox(){
		courseList = courseService.getCourseList(new Course());
		Student student = (Student)MainFrm.userObject;
		studentNameLabel.setText(student.getName());
		SelectedCourse sc = new SelectedCourse();
		sc.setStudent_id(student.getId());
		List<SelectedCourse> selectedCourseList = selectedCourseService.getSelectedCourseList(sc);
		for (SelectedCourse selectedCourse : selectedCourseList) {
			courseComboBox.addItem(getCourseById(selectedCourse.getCourse_id()));
		}
	}
	private Course getCourseById(int id){
		for (int i = 0; i < courseList.size(); i++) {
			if(id == courseList.get(i).getId())return courseList.get(i);
		}
		return null;
	}
	private void initTable(){
		Student student = (Student)MainFrm.userObject;
		Score score = new Score();
		score.setStudent_id(student.getId());
		getScoreList(score);
	}
	private void setTable(){
		Student student = (Student)MainFrm.userObject;
		Course course = (Course)courseComboBox.getSelectedItem();
		Score score = new Score();
		score.setStudent_id(student.getId());
		score.setCourse_id(course.getId());
		getScoreList(score);
	}
	private void getScoreList(Score score){
		Student student = (Student)MainFrm.userObject;
		List<Score> scoreList = scoreService.getScoreList(score);
		DefaultTableModel dft = (DefaultTableModel) scoreListTable.getModel();
		dft.setRowCount(0);
		for (Score s : scoreList) {
			Vector v = new Vector();
			v.add(s.getId());
			v.add(student.getName());
			v.add(getCourseById(s.getCourse_id()));
			v.add(s.getScore());
			dft.addRow(v);
		}
	}
}
