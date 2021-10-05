package com.view;

import com.entity.Course;
import com.entity.Teacher;
import com.service.CourseService;
import com.service.TeacherService;
import com.service.impl.CourseServiceImpl;
import com.service.impl.TeacherServiceImpl;
import com.utils.StringUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AddCourseFrm extends JInternalFrame {
	private final ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
	private final CourseService courseService = (CourseServiceImpl)context.getBean("CourseServiceImpl");
	private final TeacherService teacherService = (TeacherServiceImpl)context.getBean("TeacherServiceImpl");

	private JTextField courseNameTextField;
	private JTextField studentNumTextField;
	private JComboBox teacherListComboBox;
	private JTextArea courseInfoTextArea;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddCourseFrm frame = new AddCourseFrm();
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
	public AddCourseFrm() {
		setClosable(true);
		setIconifiable(true);
		setTitle("\u6DFB\u52A0\u8BFE\u7A0B");
		setBounds(100, 100, 453, 471);

		JLabel label = new JLabel("\u8BFE\u7A0B\u540D\u79F0\uFF1A");
		label.setIcon(new ImageIcon(AddCourseFrm.class.getResource("/images/\u8BFE\u7A0B.png")));
		label.setFont(new Font("微软雅黑", Font.PLAIN, 14));

		courseNameTextField = new JTextField();
		courseNameTextField.setColumns(10);

		JLabel label_1 = new JLabel("\u6388\u8BFE\u8001\u5E08\uFF1A");
		label_1.setIcon(new ImageIcon(AddCourseFrm.class.getResource("/images/\u8001\u5E08.png")));
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 14));

		teacherListComboBox = new JComboBox();

		JLabel label_2 = new JLabel("\u5B66\u751F\u4EBA\u6570\uFF1A");
		label_2.setIcon(new ImageIcon(AddCourseFrm.class.getResource("/images/\u4EBA\u6570.png")));
		label_2.setFont(new Font("微软雅黑", Font.PLAIN, 14));

		studentNumTextField = new JTextField();
		studentNumTextField.setColumns(10);

		JLabel label_3 = new JLabel("\u8BFE\u7A0B\u4ECB\u7ECD\uFF1A");
		label_3.setIcon(new ImageIcon(AddCourseFrm.class.getResource("/images/\u4ECB\u7ECD.png")));
		label_3.setFont(new Font("微软雅黑", Font.PLAIN, 14));

		courseInfoTextArea = new JTextArea();

		JButton addCourseButton = new JButton("\u786E\u8BA4\u6DFB\u52A0");
		addCourseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				addCourseAct(ae);
			}
		});
		addCourseButton.setIcon(new ImageIcon(AddCourseFrm.class.getResource("/images/\u786E\u8BA4.png")));
		addCourseButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));

		JButton resetButton = new JButton("\u91CD\u7F6E\u4FE1\u606F");
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				resetValue(ae);
			}
		});
		resetButton.setIcon(new ImageIcon(AddCourseFrm.class.getResource("/images/\u91CD\u7F6E.png")));
		resetButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
												.addGap(88)
												.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
														.addGroup(groupLayout.createSequentialGroup()
																.addComponent(label_2)
																.addPreferredGap(ComponentPlacement.UNRELATED)
																.addComponent(studentNumTextField, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE))
														.addGroup(groupLayout.createSequentialGroup()
																.addComponent(label_1)
																.addPreferredGap(ComponentPlacement.UNRELATED)
																.addComponent(teacherListComboBox, 0, 149, Short.MAX_VALUE))
														.addGroup(groupLayout.createSequentialGroup()
																.addComponent(label)
																.addPreferredGap(ComponentPlacement.UNRELATED)
																.addComponent(courseNameTextField, GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE))
														.addGroup(groupLayout.createSequentialGroup()
																.addComponent(label_3)
																.addPreferredGap(ComponentPlacement.UNRELATED)
																.addComponent(courseInfoTextArea, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE))))
										.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
												.addGap(103)
												.addComponent(addCourseButton)
												.addGap(18)
												.addComponent(resetButton)))
								.addGap(117))
		);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
								.addGap(19)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(label)
										.addComponent(courseNameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGap(35)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(label_1)
										.addComponent(teacherListComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGap(37)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(label_2)
										.addComponent(studentNumTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGap(38)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(label_3)
										.addComponent(courseInfoTextArea, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(addCourseButton)
										.addComponent(resetButton))
								.addGap(57))
		);
		getContentPane().setLayout(groupLayout);
		setTeacherCombox();
	}

	protected void resetValue(ActionEvent ae) {
		// TODO Auto-generated method stub
		courseNameTextField.setText("");
		courseInfoTextArea.setText("");
		studentNumTextField.setText("");
		teacherListComboBox.setSelectedIndex(0);
	}

	protected void addCourseAct(ActionEvent ae) {
		// TODO Auto-generated method stub
		String couserName = courseNameTextField.getText().toString();
		String courseInfo = courseInfoTextArea.getText().toString();
		Teacher selectedTeacher = (Teacher)teacherListComboBox.getSelectedItem();
		int studentMaxNum = 0;
		try {
			studentMaxNum = Integer.parseInt(studentNumTextField.getText());
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(this, "学生人数只能输入数字!");
			return;
		}
		if(StringUtil.isEmpty(couserName)){
			JOptionPane.showMessageDialog(this, "请输入课程名称!");
			return;
		}
		if(studentMaxNum <= 0){
			JOptionPane.showMessageDialog(this, "学生人数只能输入大于0的数字!");
			return;
		}
		Course course = new Course();
		course.setName(couserName);
		course.setMax_student_num(studentMaxNum);
		course.setInfo(courseInfo);
		course.setTeacher_id(selectedTeacher.getId());
		if(courseService.addCourse(course)){
			JOptionPane.showMessageDialog(this, "添加成功!");
		}else{
			JOptionPane.showMessageDialog(this, "添加失败!");
		}
		resetValue(ae);
	}
	private void setTeacherCombox(){
		List<Teacher> teacherList = teacherService.getTeacherList(new Teacher());
		for (Teacher teacher : teacherList) {
			teacherListComboBox.addItem(teacher);
		}
	}
}
