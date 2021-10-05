package com.view;

import com.entity.Attendance;
import com.entity.Course;
import com.entity.SelectedCourse;
import com.entity.Student;
import com.service.AttendanceService;
import com.service.CourseService;
import com.service.SelectedCourseService;
import com.service.impl.AttendanceServiceImpl;
import com.service.impl.CourseServiceImpl;
import com.service.impl.SelectedCourseServiceImpl;
import com.utils.Chooser;
import com.utils.DateFormatUtil;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

public class AttendanceStudentFrm extends JInternalFrame {

	private final ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
	private final AttendanceService attendanceService = (AttendanceServiceImpl)context.getBean("AttendanceServiceImpl");
	private final CourseService courseService = (CourseServiceImpl)context.getBean("CourseServiceImpl");
	private final SelectedCourseService selectedCourseService = (SelectedCourseServiceImpl)context.getBean("SelectedCourseServiceImpl");
	private JTextField searchAttendanceDateTextField;
	private JTable attendancedListTable;
	private JComboBox addSelectedCourseComboBox;
	private JComboBox searchSelectedComboBox;
	private List<Course> courseList = new ArrayList<Course>();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AttendanceStudentFrm frame = new AttendanceStudentFrm();
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
	public AttendanceStudentFrm() {
		setClosable(true);
		setIconifiable(true);
		setTitle("\u5B66\u751F\u7B7E\u5230\u9762\u677F");
		setBounds(100, 100, 650, 515);

		JLabel label = new JLabel("\u8BFE\u7A0B\uFF1A");
		label.setIcon(new ImageIcon(AttendanceStudentFrm.class.getResource("/images/\u8BFE\u7A0B.png")));
		label.setFont(new Font("微软雅黑", Font.PLAIN, 14));

		addSelectedCourseComboBox = new JComboBox();

		JButton addAttendanceButton = new JButton("\u786E\u8BA4\u7B7E\u5230");
		addAttendanceButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				addAttendanceAct(ae);
			}
		});
		addAttendanceButton.setIcon(new ImageIcon(AttendanceStudentFrm.class.getResource("/images/\u786E\u8BA4.png")));
		addAttendanceButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "\u5DF2\u7B7E\u5230\u5217\u8868", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
								.addContainerGap(115, Short.MAX_VALUE)
								.addComponent(label)
								.addGap(35)
								.addComponent(addSelectedCourseComboBox, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
								.addGap(77)
								.addComponent(addAttendanceButton)
								.addGap(133))
						.addGroup(groupLayout.createSequentialGroup()
								.addGap(49)
								.addComponent(panel, GroupLayout.PREFERRED_SIZE, 538, GroupLayout.PREFERRED_SIZE)
								.addContainerGap(47, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
								.addGap(23)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(label)
										.addComponent(addSelectedCourseComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(addAttendanceButton))
								.addGap(28)
								.addComponent(panel, GroupLayout.PREFERRED_SIZE, 370, GroupLayout.PREFERRED_SIZE)
								.addContainerGap(37, Short.MAX_VALUE))
		);

		JLabel label_1 = new JLabel("\u8BFE\u7A0B\uFF1A");
		label_1.setIcon(new ImageIcon(AttendanceStudentFrm.class.getResource("/images/\u8BFE\u7A0B.png")));
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 14));

		searchSelectedComboBox = new JComboBox();

		JLabel label_2 = new JLabel("\u65E5\u671F\uFF1A");
		label_2.setIcon(new ImageIcon(AttendanceStudentFrm.class.getResource("/images/\u65E5\u671F.png")));
		label_2.setFont(new Font("微软雅黑", Font.PLAIN, 14));

		searchAttendanceDateTextField = new JTextField();
		searchAttendanceDateTextField.setColumns(10);

		JButton searchAttendanceButton = new JButton("\u67E5\u8BE2");
		searchAttendanceButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				searchAct(ae);
			}
		});
		searchAttendanceButton.setIcon(new ImageIcon(AttendanceStudentFrm.class.getResource("/images/\u641C\u7D22.png")));
		searchAttendanceButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));

		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
								.addContainerGap()
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 506, Short.MAX_VALUE)
										.addGroup(gl_panel.createSequentialGroup()
												.addComponent(label_1)
												.addGap(18)
												.addComponent(searchSelectedComboBox, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
												.addGap(18)
												.addComponent(label_2)
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addComponent(searchAttendanceDateTextField, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
												.addGap(18)
												.addComponent(searchAttendanceButton)))
								.addContainerGap())
		);
		gl_panel.setVerticalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
								.addGap(20)
								.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
										.addComponent(label_1)
										.addComponent(searchSelectedComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(label_2)
										.addComponent(searchAttendanceDateTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(searchAttendanceButton))
								.addGap(28)
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
								.addContainerGap())
		);

		attendancedListTable = new JTable();
		attendancedListTable.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"\u7B7E\u5230ID", "\u5B66\u751F\u59D3\u540D", "\u8BFE\u7A0B\u540D\u79F0", "\u7B7E\u5230\u65E5\u671F"
				}
		) {
			boolean[] columnEditables = new boolean[] {
					false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(attendancedListTable);
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);
		setCourseCombox();
		initTable();
		Chooser.getInstance().register(searchAttendanceDateTextField);
	}
	protected void searchAct(ActionEvent ae) {
		Student student = (Student) MainFrm.userObject;
		Course course = (Course)searchSelectedComboBox.getSelectedItem();
		String dateString = searchAttendanceDateTextField.getText().toString();
		Attendance attendance = new Attendance();
		attendance.setAttendance_date(dateString);
		attendance.setStudent_id(student.getId());
		attendance.setCourse_id(course.getId());
		getAttendancedList(attendance);
	}

	protected void addAttendanceAct(ActionEvent ae) {
		Student student = (Student) MainFrm.userObject;
		Course course = (Course)addSelectedCourseComboBox.getSelectedItem();
		String dateString = DateFormatUtil.getDateString(new Date(System.currentTimeMillis()), "yyyy-MM-dd");
		Attendance attendance = new Attendance();
		attendance.setAttendance_date(dateString);
		attendance.setStudent_id(student.getId());
		attendance.setCourse_id(course.getId());
		if(attendanceService.isAttendanced(attendance)){
			JOptionPane.showMessageDialog(this, "已经签到，请勿重复签到！");
			return;
		}
		if(attendanceService.addAttendance(attendance)){
			JOptionPane.showMessageDialog(this, "签到成功！");
		}else{
			JOptionPane.showMessageDialog(this, "签到失败！");
		}
		initTable();
	}

	private void setCourseCombox(){
		courseList = courseService.getCourseList(new Course());
		Student student = (Student) MainFrm.userObject;
		SelectedCourse sc = new SelectedCourse();
		sc.setStudent_id(student.getId());
		List<SelectedCourse> selectedCourseList = selectedCourseService.getSelectedCourseList(sc);
		for (SelectedCourse selectedCourse : selectedCourseList) {
			addSelectedCourseComboBox.addItem(getCourseById(selectedCourse.getCourse_id()));
			searchSelectedComboBox.addItem(getCourseById(selectedCourse.getCourse_id()));
		}
	}
	private Course getCourseById(int id){
		for (int i = 0; i < courseList.size(); i++) {
			if(id == courseList.get(i).getId())return courseList.get(i);
		}
		return null;
	}
	private void initTable(){
		Student student = (Student) MainFrm.userObject;
		Attendance attendance = new Attendance();
		attendance.setStudent_id(student.getId());
		getAttendancedList(attendance);
	}
	private void getAttendancedList(Attendance attendance){
		Student student = (Student) MainFrm.userObject;
		List<Attendance> attendancedList = attendanceService.getAttendancedList(attendance);
		DefaultTableModel dft = (DefaultTableModel) attendancedListTable.getModel();
		dft.setRowCount(0);
		for (Attendance a : attendancedList) {
			Vector v = new Vector();
			v.add(a.getId());
			v.add(student.getName());
			v.add(getCourseById(a.getCourse_id()));
			v.add(a.getAttendance_date());
			dft.addRow(v);
		}
	}

}
