package com.view;

import com.entity.UserType;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;

public class MainFrm extends JFrame {

	private JPanel contentPane;
	private JDesktopPane desktopPane;
	public static UserType userType;
	public static Object userObject;
	private JMenuItem addStudentMenuItem ;
	private JMenu manageClassMenu ;
	private JMenu manageTeacherMenu;
	private JMenuItem addTeacherMenuItem;
	private JMenu courseMenu;
	private JMenuItem studentAttdentanceMenuItem;
	private JMenuItem manageAttendanceMenuItem;
	private JMenuItem statsAttendanceMenuItem;
	private JMenuItem addScoreMenuItem;
	private JMenuItem viewScoreMenuItem;
	private JMenuItem manageScoreMenuItem;
	private JMenuItem scoreStatsMenuItem;

	/**
	 * Create the frame.
	 */
	public MainFrm(UserType userType,Object userObject) {
		this.userType = userType;
		this.userObject = userObject;
		//学生信息系统主界面
		setTitle("学生管理系统主界面");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 936, 774);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		//系统设置
		JMenu menu = new JMenu("系统设置");
		//系统设置
		menu.setIcon(new ImageIcon(MainFrm.class.getResource("/images/系统设置.png")));
		menuBar.add(menu);

		//修改密码
		JMenuItem menuItem = new JMenuItem("修改密码");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				editPassword(ae);
			}
		});
		//修改密码
		menuItem.setIcon(new ImageIcon(MainFrm.class.getResource("/images/修改密码.png")));
		menu.add(menuItem);

		//退出系统
		JMenuItem menuItem_1 = new JMenuItem("退出系统");
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(MainFrm.this, "确定退出么？") == JOptionPane.OK_OPTION){
					System.exit(0);
				}
			}
		});
		//退出
		menuItem_1.setIcon(new ImageIcon(MainFrm.class.getResource("/images/退出.png")));
		menu.add(menuItem_1);

		//学生管理
		JMenu menu_1 = new JMenu("学生管理");
		//学生管理
		menu_1.setIcon(new ImageIcon(MainFrm.class.getResource("/images/学生管理.png")));
		menuBar.add(menu_1);

		addStudentMenuItem = new JMenuItem("学生添加");
		addStudentMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddStudentFrm addStudentFrm = new AddStudentFrm();
				addStudentFrm.setVisible(true);
				desktopPane.add(addStudentFrm);
			}
		});
		addStudentMenuItem.setIcon(new ImageIcon(MainFrm.class.getResource("/images/添加.png")));
		menu_1.add(addStudentMenuItem);

		JMenuItem menuItem_3 = new JMenuItem("学生列表");
		menuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManageStudentFrm studentManageFrm = new ManageStudentFrm();
				studentManageFrm.setVisible(true);
				desktopPane.add(studentManageFrm);
			}
		});


		menuItem_3.setIcon(new ImageIcon(MainFrm.class.getResource("/images/用户列表.png")));
		menu_1.add(menuItem_3);

		manageClassMenu = new JMenu("班级管理");
		manageClassMenu.setIcon(new ImageIcon(MainFrm.class.getResource("/images/班级管理.png")));
		menuBar.add(manageClassMenu);
		JMenuItem menuItem_4 = new JMenuItem("班级添加");
		menuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				addStudentClass(ae);
			}
		});
		menuItem_4.setIcon(new ImageIcon(MainFrm.class.getResource("/images/添加.png")));
		manageClassMenu.add(menuItem_4);

		JMenuItem menuItem_5 = new JMenuItem("班级管理");
		menuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ManageClassFrm classManageFrm = new ManageClassFrm();
				classManageFrm.setVisible(true);
				desktopPane.add(classManageFrm);
			}
		});
		menuItem_5.setIcon(new ImageIcon(MainFrm.class.getResource("/images/班级列表.png")));
		manageClassMenu.add(menuItem_5);

		manageTeacherMenu = new JMenu("教师管理");
		manageTeacherMenu.setIcon(new ImageIcon(MainFrm.class.getResource("/images/老师.png")));
		menuBar.add(manageTeacherMenu);
		addTeacherMenuItem = new JMenuItem("添加教师");
		addTeacherMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddTeacherFrm addTeacherFrm = new AddTeacherFrm();
				addTeacherFrm.setVisible(true);
				desktopPane.add(addTeacherFrm);
			}
		});
		addTeacherMenuItem.setIcon(new ImageIcon(MainFrm.class.getResource("/images/添加.png")));
		manageTeacherMenu.add(addTeacherMenuItem);

		JMenuItem menuItem_8 = new JMenuItem("教师列表");
		menuItem_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManageTeacherFrm manageTeacherFrm = new ManageTeacherFrm();
				manageTeacherFrm.setVisible(true);
				desktopPane.add(manageTeacherFrm);
			}
		});
		menuItem_8.setIcon(new ImageIcon(MainFrm.class.getResource("/images/用户列表.png")));
		manageTeacherMenu.add(menuItem_8);

		courseMenu = new JMenu("课程管理");
		courseMenu.setIcon(new ImageIcon(MainFrm.class.getResource("/images/课程.png")));
		menuBar.add(courseMenu);
		JMenuItem addCourseMenuItem = new JMenuItem("添加课程");
		addCourseMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddCourseFrm addCourseFrm = new AddCourseFrm();
				addCourseFrm.setVisible(true);
				desktopPane.add(addCourseFrm);
			}
		});
		addCourseMenuItem.setIcon(new ImageIcon(MainFrm.class.getResource("/images/添加.png")));
		courseMenu.add(addCourseMenuItem);

		JMenuItem courseListMenuItem = new JMenuItem("课程列表");
		courseListMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ManageCourseFrm manageCourseFrm = new ManageCourseFrm();
				manageCourseFrm.setVisible(true);
				desktopPane.add(manageCourseFrm);
			}
		});
		courseListMenuItem.setIcon(new ImageIcon(MainFrm.class.getResource("/images/课程列表.png")));
		courseMenu.add(courseListMenuItem);

		JMenu menu_4 = new JMenu("选课管理");
		menu_4.setIcon(new ImageIcon(MainFrm.class.getResource("/images/选择.png")));
		menuBar.add(menu_4);

		JMenuItem menuItem_2 = new JMenuItem("选课管理");
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ManageSelectedCourseFrm manageSelectedCourseFrm = new ManageSelectedCourseFrm();
				manageSelectedCourseFrm.setVisible(true);
				desktopPane.add(manageSelectedCourseFrm);
			}
		});
		menuItem_2.setIcon(new ImageIcon(MainFrm.class.getResource("/images/选择.png")));
		menu_4.add(menuItem_2);

		JMenu menu_2 = new JMenu("签到考勤");
		menu_2.setIcon(new ImageIcon(MainFrm.class.getResource("/images/签到.png")));
		menuBar.add(menu_2);

		studentAttdentanceMenuItem = new JMenuItem("学生签到");
		studentAttdentanceMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AttendanceStudentFrm attendanceStudentFrm = new AttendanceStudentFrm();
				attendanceStudentFrm.setVisible(true);
				desktopPane.add(attendanceStudentFrm);
			}
		});
		studentAttdentanceMenuItem.setIcon(new ImageIcon(MainFrm.class.getResource("/images/学生管理.png")));
		studentAttdentanceMenuItem.setEnabled(false);
		menu_2.add(studentAttdentanceMenuItem);
		manageAttendanceMenuItem = new JMenuItem("签到管理");
		manageAttendanceMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ManageAttendanceFrm manageAttendanceFrm = new ManageAttendanceFrm();
				manageAttendanceFrm.setVisible(true);
				desktopPane.add(manageAttendanceFrm);
			}
		});
		manageAttendanceMenuItem.setIcon(new ImageIcon(MainFrm.class.getResource("/images/签到列表.png")));
		menu_2.add(manageAttendanceMenuItem);

		statsAttendanceMenuItem = new JMenuItem("签到统计");
		statsAttendanceMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StatsAttendanceFrm statsAttendanceFrm = new StatsAttendanceFrm();
				statsAttendanceFrm.setVisible(true);
				desktopPane.add(statsAttendanceFrm);
			}
		});
		statsAttendanceMenuItem.setIcon(new ImageIcon(MainFrm.class.getResource("/images/统计.png")));
		menu_2.add(statsAttendanceMenuItem);

		JMenu menu_5 = new JMenu("成绩管理");
		menu_5.setIcon(new ImageIcon(MainFrm.class.getResource("/images/成绩.png")));
		menuBar.add(menu_5);

		addScoreMenuItem = new JMenuItem("录入成绩");
		addScoreMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddScoreFrm addScoreFrm = new AddScoreFrm();
				addScoreFrm.setVisible(true);
				desktopPane.add(addScoreFrm);
			}
		});
		addScoreMenuItem.setIcon(new ImageIcon(MainFrm.class.getResource("/images/添加.png")));
		menu_5.add(addScoreMenuItem);

		viewScoreMenuItem = new JMenuItem("成绩查看");
		viewScoreMenuItem.setEnabled(false);
		viewScoreMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ViewScoreFrm viewScoreFrm = new ViewScoreFrm();
				viewScoreFrm.setVisible(true);
				desktopPane.add(viewScoreFrm);
			}
		});
		viewScoreMenuItem.setIcon(new ImageIcon(MainFrm.class.getResource("/images/查看.png")));
		menu_5.add(viewScoreMenuItem);

		manageScoreMenuItem = new JMenuItem("成绩管理");
		manageScoreMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ManageScoreFrm manageScoreFrm = new ManageScoreFrm();
				manageScoreFrm.setVisible(true);
				desktopPane.add(manageScoreFrm);
			}
		});
		manageScoreMenuItem.setIcon(new ImageIcon(MainFrm.class.getResource("/images/成绩.png")));
		menu_5.add(manageScoreMenuItem);

		scoreStatsMenuItem = new JMenuItem("成绩统计");
		scoreStatsMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				StatsScoreFrm statsScoreFrm = new StatsScoreFrm();
				statsScoreFrm.setVisible(true);
				desktopPane.add(statsScoreFrm);
			}
		});
		scoreStatsMenuItem.setIcon(new ImageIcon(MainFrm.class.getResource("/images/统计.png")));
		menu_5.add(scoreStatsMenuItem);



		JMenu menu_3 = new JMenu("帮助");
		menu_3.setIcon(new ImageIcon(MainFrm.class.getResource("/images/帮助.png")));
		menuBar.add(menu_3);

		JMenuItem menuItem_6 = new JMenuItem("关于我们");
		menuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				aboutUs(ae);
			}
		});
		menuItem_6.setIcon(new ImageIcon(MainFrm.class.getResource("/images/关于我们.png")));
		menu_3.add(menuItem_6);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);




		JMenu menu_7 = new JMenu("网页版");
		menu_7.setIcon(new ImageIcon(MainFrm.class.getResource("/images/网页.png")));
		menuBar.add(menu_7);

		JMenuItem menuItem_7 = new JMenuItem("网页版");
		menuItem_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				webApplication();
			}
		});
		menuItem_7.setIcon(new ImageIcon(MainFrm.class.getResource("/images/网页.png")));
		menu_7.add(menuItem_7);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);




		desktopPane = new JDesktopPane();
		desktopPane.setBackground(new Color(0, 128, 128));
		contentPane.add(desktopPane, BorderLayout.CENTER);
		setLocationRelativeTo(null);
		setAuthority();
	}

	protected void addStudentClass(ActionEvent ae) {
		AddStudentClassFrm sca = new AddStudentClassFrm();
		sca.setVisible(true);
		desktopPane.add(sca);
	}

	protected void editPassword(ActionEvent ae) {
		EditPasswordFrm editPasswordFrm = new EditPasswordFrm();
		editPasswordFrm.setVisible(true);
		desktopPane.add(editPasswordFrm);
	}

	protected void aboutUs(ActionEvent ae) {
		// TODO Auto-generated method stub
		String info = "软件1901第三组出品\n";
		info+="组员:关昌隆、王永鑫、李静宜、王梦华\n";
		String[] buttons = {"点赞!","还是点赞!"};
		int ret = JOptionPane.showOptionDialog(this, info, "关于我们", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.DEFAULT_OPTION, new ImageIcon(LoginFrm.class.getResource("/images/logo.png")), buttons, null);
		if (ret==0 || ret==1){
			JOptionPane.showMessageDialog(this, "感谢您的支持！");
		}else{
			JOptionPane.showMessageDialog(this, "不点赞就走，你真是狠心，坏人！");
		}
	}

	private void setAuthority(){
		if("学生".equals(userType.getName())){
			addStudentMenuItem.setEnabled(false);
			manageClassMenu.setEnabled(false);
			manageTeacherMenu.setEnabled(false);
			courseMenu.setEnabled(false);
			studentAttdentanceMenuItem.setEnabled(true);
			manageAttendanceMenuItem.setEnabled(false);
			statsAttendanceMenuItem.setEnabled(false);
			addScoreMenuItem.setEnabled(false);
			viewScoreMenuItem.setEnabled(true);
			manageScoreMenuItem.setEnabled(false);
			scoreStatsMenuItem.setEnabled(false);
		}
		if("教师".equals(userType.getName())){
			addTeacherMenuItem.setEnabled(false);
		}
	}

	protected void webApplication(){
		try {
			URI uri = new URI("http://localhost:8080/");
			Desktop.getDesktop().browse(uri);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
