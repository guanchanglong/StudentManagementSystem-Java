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
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

public class AddScoreFrm extends JInternalFrame {
	private final ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
	private final CourseService courseService = (CourseServiceImpl)context.getBean("CourseServiceImpl");
	private final ScoreService scoreService = (ScoreServiceImpl)context.getBean("ScoreServiceImpl");
	private final SelectedCourseService selectedCourseService = (SelectedCourseServiceImpl)context.getBean("SelectedCourseServiceImpl");
	private final StudentService studentService = (StudentServiceImpl)context.getBean("StudentServiceImpl");

	private JTextField scoreTextField;
	private JComboBox studentComboBox;
	private JComboBox courseComboBox;
	private List<Course> courseList;
	private List<Student> studentList;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddScoreFrm frame = new AddScoreFrm();
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
	public AddScoreFrm() {
		setClosable(true);
		setIconifiable(true);
		setTitle("\u6210\u7EE9\u5F55\u5165\u754C\u9762");
		setBounds(100, 100, 641, 474);

		JLabel label = new JLabel("\u5B66\u751F\u59D3\u540D\uFF1A");
		label.setIcon(new ImageIcon(AddScoreFrm.class.getResource("/images/\u5B66\u751F\u7BA1\u7406.png")));
		label.setFont(new Font("微软雅黑", Font.PLAIN, 14));

		studentComboBox = new JComboBox();

		JLabel label_1 = new JLabel("\u8BFE\u7A0B\u4FE1\u606F\uFF1A");
		label_1.setIcon(new ImageIcon(AddScoreFrm.class.getResource("/images/\u8BFE\u7A0B.png")));
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 14));

		courseComboBox = new JComboBox();
		courseComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent ae) {
				courseChangeAct(ae);
			}
		});
		courseComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {

			}
		});

		JLabel label_2 = new JLabel("\u6240\u5F97\u6210\u7EE9\uFF1A");
		label_2.setIcon(new ImageIcon(AddScoreFrm.class.getResource("/images/\u6210\u7EE9.png")));
		label_2.setFont(new Font("微软雅黑", Font.PLAIN, 14));

		scoreTextField = new JTextField();
		scoreTextField.setColumns(10);

		JButton submitButton = new JButton("\u5F55\u5165\u6210\u7EE9");
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				submitAct(ae);
			}
		});
		submitButton.setIcon(new ImageIcon(AddScoreFrm.class.getResource("/images/\u786E\u8BA4.png")));
		submitButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
								.addGap(142)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
										.addGroup(groupLayout.createSequentialGroup()
												.addComponent(label_2)
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addComponent(scoreTextField))
										.addGroup(groupLayout.createSequentialGroup()
												.addComponent(label_1)
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addComponent(courseComboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										.addGroup(groupLayout.createSequentialGroup()
												.addComponent(label)
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addComponent(studentComboBox, GroupLayout.PREFERRED_SIZE, 214, GroupLayout.PREFERRED_SIZE)))
								.addContainerGap(213, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
								.addContainerGap(311, Short.MAX_VALUE)
								.addComponent(submitButton)
								.addGap(249))
		);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
								.addGap(63)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(label)
										.addComponent(studentComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGap(62)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(label_1)
										.addComponent(courseComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGap(66)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(label_2)
										.addComponent(scoreTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGap(74)
								.addComponent(submitButton)
								.addContainerGap(89, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);
		setCourseCombox();
		setStudentCombox();
	}
	protected void submitAct(ActionEvent ae) {
		// TODO Auto-generated method stub
		int score = 0;
		try {
			score = Integer.parseInt(scoreTextField.getText().toString());
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(this, "成绩必须输入大于0的整数！");
			return;
		}
		if(score <= 0){
			JOptionPane.showMessageDialog(this, "成绩必须输入大于0的整数！");
			return;
		}
		Student student = (Student) studentComboBox.getSelectedItem();
		Course course = (Course)courseComboBox.getSelectedItem();
		Score scoreObj = new Score();
		scoreObj.setStudent_id(student.getId());
		scoreObj.setCourse_id(course.getId());
		scoreObj.setScore(score);
		System.out.println("学生id："+student.getId());
		System.out.println("课程id："+course.getId());
		//如果存在，则已经录入过了
		if(scoreService.isAdd(scoreObj)){
			JOptionPane.showMessageDialog(this, "成绩已经录入，请勿重复录入！");
			return;
		}
		if(scoreService.addScore(scoreObj)){
			JOptionPane.showMessageDialog(this, "成绩录入成功！");
			scoreTextField.setText("");
		}else{
			JOptionPane.showMessageDialog(this, "成绩录入失败！");
		}
	}

	protected void courseChangeAct(ItemEvent ae) {
		if(ae.getStateChange() == ItemEvent.SELECTED){
			setStudentCombox();
		}
	}

	private void setCourseCombox(){
		courseList = courseService.getCourseList(new Course());
		for (Course course : courseList) {
			if("教师".equals(MainFrm.userType.getName())){
				Teacher teacher = (Teacher) MainFrm.userObject;
				if(course.getTeacher_id() == teacher.getId()){
					courseComboBox.addItem(course);
				}
				continue;
			}
			//执行到这里一定是超级管理员身份
			courseComboBox.addItem(course);
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
	private List<Student> getSelectedCourseStudentList(Course course){
		List<Student> selectedCourseStudentList = selectedCourseService.getSelectedCourseStudentList(course);
		return selectedCourseStudentList;
	}
}
