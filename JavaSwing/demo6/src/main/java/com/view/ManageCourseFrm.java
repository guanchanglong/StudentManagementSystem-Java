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
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class ManageCourseFrm extends JInternalFrame {
	private final ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
	private final CourseService courseService = (CourseServiceImpl)context.getBean("CourseServiceImpl");
	private final TeacherService teacherService = (TeacherServiceImpl)context.getBean("TeacherServiceImpl");

	private JTextField searchCourseNameTextField;
	private JTable courseListTable;
	private JTextField editCourseTextField;
	private JTextField editCourseStudentNumTextField;
	private JComboBox editCourseTeachComboBox;
	private JTextArea editCourseInfoTextArea;
	private List<Teacher> teacherList = new ArrayList<Teacher>();
	private JComboBox searchTeacherComboBox;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageCourseFrm frame = new ManageCourseFrm();
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
	public ManageCourseFrm() {
		setTitle("\u8BFE\u7A0B\u4FE1\u606F\u7BA1\u7406");
		setBounds(100, 100, 819, 704);
		setClosable(true);
		setIconifiable(true);
		JLabel label = new JLabel("\u8BFE\u7A0B\u540D\u79F0\uFF1A");
		label.setIcon(new ImageIcon(ManageCourseFrm.class.getResource("/images/\u8BFE\u7A0B.png")));
		label.setFont(new Font("微软雅黑", Font.PLAIN, 14));

		searchCourseNameTextField = new JTextField();
		searchCourseNameTextField.setColumns(10);

		JLabel label_1 = new JLabel("\u6388\u8BFE\u8001\u5E08\uFF1A");
		label_1.setIcon(new ImageIcon(ManageCourseFrm.class.getResource("/images/\u8001\u5E08.png")));
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 14));

		searchTeacherComboBox = new JComboBox();

		JButton searchButton = new JButton("\u67E5\u8BE2");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				searchCourse(ae);
			}
		});
		searchButton.setIcon(new ImageIcon(ManageCourseFrm.class.getResource("/images/\u641C\u7D22.png")));
		searchButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));

		JScrollPane scrollPane = new JScrollPane();

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "\u7F16\u8F91\u8BFE\u7A0B\u4FE1\u606F", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
								.addGap(81)
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(panel, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
										.addGroup(Alignment.LEADING, groupLayout.createParallelGroup(Alignment.TRAILING, false)
												.addComponent(scrollPane, Alignment.LEADING)
												.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
														.addComponent(label)
														.addGap(18)
														.addComponent(searchCourseNameTextField, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
														.addGap(32)
														.addComponent(label_1)
														.addPreferredGap(ComponentPlacement.UNRELATED)
														.addComponent(searchTeacherComboBox, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)
														.addGap(50)
														.addComponent(searchButton))))
								.addContainerGap(116, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
								.addGap(38)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(label)
										.addComponent(searchCourseNameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(label_1)
										.addComponent(searchTeacherComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(searchButton))
								.addGap(41)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 266, GroupLayout.PREFERRED_SIZE)
								.addGap(35)
								.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addContainerGap(31, Short.MAX_VALUE))
		);

		JLabel label_2 = new JLabel("\u8BFE\u7A0B\u540D\u79F0\uFF1A");
		label_2.setIcon(new ImageIcon(ManageCourseFrm.class.getResource("/images/\u8BFE\u7A0B.png")));
		label_2.setFont(new Font("微软雅黑", Font.PLAIN, 14));

		editCourseTextField = new JTextField();
		editCourseTextField.setColumns(10);

		JLabel label_3 = new JLabel("\u6388\u8BFE\u8001\u5E08\uFF1A");
		label_3.setIcon(new ImageIcon(ManageCourseFrm.class.getResource("/images/\u8001\u5E08.png")));
		label_3.setFont(new Font("微软雅黑", Font.PLAIN, 14));

		editCourseTeachComboBox = new JComboBox();

		JLabel label_4 = new JLabel("\u5B66\u751F\u4EBA\u6570\uFF1A");
		label_4.setIcon(new ImageIcon(ManageCourseFrm.class.getResource("/images/\u4EBA\u6570.png")));
		label_4.setFont(new Font("微软雅黑", Font.PLAIN, 14));

		editCourseStudentNumTextField = new JTextField();
		editCourseStudentNumTextField.setColumns(10);

		JLabel label_5 = new JLabel("\u8BFE\u7A0B\u4ECB\u7ECD\uFF1A");
		label_5.setIcon(new ImageIcon(ManageCourseFrm.class.getResource("/images/\u4ECB\u7ECD.png")));
		label_5.setFont(new Font("微软雅黑", Font.PLAIN, 14));

		editCourseInfoTextArea = new JTextArea();

		JButton submitEditButton = new JButton("\u786E\u8BA4\u4FEE\u6539");
		submitEditButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				editCourseSubmit(ae);
			}
		});
		submitEditButton.setIcon(new ImageIcon(ManageCourseFrm.class.getResource("/images/\u786E\u8BA4.png")));
		submitEditButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));

		JButton deleteCourseButton = new JButton("\u5220\u9664\u8BFE\u7A0B");
		deleteCourseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				deleteCourse(ae);
			}
		});
		deleteCourseButton.setIcon(new ImageIcon(ManageCourseFrm.class.getResource("/images/\u5220\u9664.png")));
		deleteCourseButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
								.addGap(37)
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
										.addGroup(gl_panel.createSequentialGroup()
												.addComponent(label_4)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(editCourseStudentNumTextField))
										.addGroup(gl_panel.createSequentialGroup()
												.addComponent(label_2)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(editCourseTextField, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)))
								.addGap(18)
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel.createSequentialGroup()
												.addComponent(label_3)
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addComponent(editCourseTeachComboBox, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_panel.createSequentialGroup()
												.addComponent(label_5)
												.addGap(18)
												.addComponent(editCourseInfoTextArea)))
								.addContainerGap(118, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
								.addGap(121)
								.addComponent(submitEditButton)
								.addGap(113)
								.addComponent(deleteCourseButton)
								.addContainerGap(160, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
								.addGap(27)
								.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
										.addComponent(label_2)
										.addComponent(editCourseTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(label_3)
										.addComponent(editCourseTeachComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGap(43)
								.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
										.addComponent(label_4)
										.addComponent(editCourseStudentNumTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(label_5)
										.addComponent(editCourseInfoTextArea, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE))
								.addGap(18)
								.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
										.addComponent(submitEditButton)
										.addComponent(deleteCourseButton))
								.addContainerGap(15, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);

		courseListTable = new JTable();
		courseListTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent me) {
				selectedCourse(me);
			}
		});
		courseListTable.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"\u8BFE\u7A0B\u7F16\u53F7", "\u8BFE\u7A0B\u540D\u79F0", "\u6388\u8BFE\u8001\u5E08", "\u8BFE\u7A0B\u6700\u5927\u4EBA\u6570", "\u5DF2\u9009\u4EBA\u6570", "\u8BFE\u7A0B\u4ECB\u7ECD"
				}
		) {
			boolean[] columnEditables = new boolean[] {
					false, true, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		courseListTable.getColumnModel().getColumn(3).setPreferredWidth(90);
		courseListTable.getColumnModel().getColumn(5).setPreferredWidth(225);
		scrollPane.setViewportView(courseListTable);
		getContentPane().setLayout(groupLayout);
		setTeacherCombox();
		setCourseListTable(new Course());
	}
	protected void editCourseSubmit(ActionEvent ae) {
		int row = courseListTable.getSelectedRow();
		if(row == -1){
			JOptionPane.showMessageDialog(this, "请选中要修改的数据！");
			return;
		}
		int course_id = Integer.parseInt(courseListTable.getValueAt(row, 0).toString());
		Teacher teacher = (Teacher) editCourseTeachComboBox.getSelectedItem();
		String courseName = editCourseTextField.getText().toString();
		if(StringUtil.isEmpty(courseName)){
			JOptionPane.showMessageDialog(this, "课程名称不能为空！");
			return;
		}
		int max_student_num = 0;
		try {
			max_student_num = Integer.parseInt(editCourseStudentNumTextField.getText().toString());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "学生人数请输入大于0的整数！");
			return;
		}
		if(max_student_num <= 0){
			JOptionPane.showMessageDialog(this, "学生人数请输入大于0的整数！");
			return;
		}
		String courseInfo = editCourseInfoTextArea.getText().toString();
		Course course = new Course();
		course.setId(course_id);
		course.setName(courseName);
		course.setTeacher_id(teacher.getId());
		course.setMax_student_num(max_student_num);
		course.setInfo(courseInfo);
		if(courseService.update(course)){
			JOptionPane.showMessageDialog(this, "修改成功！");
		}else{
			JOptionPane.showMessageDialog(this, "修改失败！");
		}
		setCourseListTable(new Course());

	}

	protected void selectedCourse(MouseEvent me) {
		int row = courseListTable.getSelectedRow();
		String couseName = courseListTable.getValueAt(row, 1).toString();
		int teacher_id = getTeacherIdByName(courseListTable.getValueAt(row, 2).toString());
		int max_student_num = Integer.parseInt(courseListTable.getValueAt(row, 3).toString());
		String couseInfo = courseListTable.getValueAt(row, 5).toString();
		editCourseTextField.setText(couseName);
		editCourseStudentNumTextField.setText(max_student_num+"");
		editCourseInfoTextArea.setText(couseInfo);
		for(int i=0;i<editCourseTeachComboBox.getItemCount();i++){
			Teacher t = (Teacher) editCourseTeachComboBox.getItemAt(i);
			if(t.getId() == teacher_id){
				editCourseTeachComboBox.setSelectedIndex(i);
				break;
			}
		}
	}

	protected void searchCourse(ActionEvent ae) {
		String searchCourseName = searchCourseNameTextField.getText().toString();
		Teacher teacher = (Teacher) searchTeacherComboBox.getSelectedItem();
		Course course = new Course();
		course.setName(searchCourseName);
		course.setTeacher_id(teacher.getId());
		setCourseListTable(course);
	}

	protected void deleteCourse(ActionEvent ae) {
		int row = courseListTable.getSelectedRow();
		if(row == -1){
			JOptionPane.showMessageDialog(this, "请选中要删除的数据！");
			return;
		}
		int course_id = Integer.parseInt(courseListTable.getValueAt(row, 0).toString());
		if(courseService.delete(course_id)){
			JOptionPane.showMessageDialog(this, "删除成功！");
		}else{
			JOptionPane.showMessageDialog(this, "删除失败！");
		}
		setCourseListTable(new Course());
	}

	private void setCourseListTable(Course course){
		List<Course> courseList = courseService.getCourseList(course);
		DefaultTableModel dft = (DefaultTableModel) courseListTable.getModel();
		dft.setRowCount(0);
		for (Course c : courseList) {
			Vector v = new Vector();
			v.add(c.getId());
			v.add(c.getName());
			v.add(getTeacherNameById(c.getTeacher_id()));
			v.add(c.getMax_student_num());
			v.add(c.getSelected_num());
			v.add(c.getInfo());
			dft.addRow(v);
		}
	}

	private void setTeacherCombox(){
		teacherList = teacherService.getTeacherList(new Teacher());
		for (Teacher teacher : teacherList) {
			editCourseTeachComboBox.addItem(teacher);
			searchTeacherComboBox.addItem(teacher);
		}
	}

	private String getTeacherNameById(int teacher_id){
		String retString = "";
		for (Teacher teacher : teacherList) {
			if(teacher.getId() == teacher_id){
				retString = teacher.getName();
				break;
			}
		}
		return retString;
	}

	private int getTeacherIdByName(String teacher_name){
		int retId = -1;
		for (Teacher teacher : teacherList) {
			if(teacher_name.equals(teacher.getName())){
				retId = teacher.getId();
				break;
			}
		}
		return retId;
	}
}
