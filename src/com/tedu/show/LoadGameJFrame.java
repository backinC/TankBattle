package com.tedu.show;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @˵�� ��Ϸ���ȼ��صĴ��壨ҳ�棩
 * 		1.����ͼƬ
 * 		2.������Ϸ��ť
 * 		3.�˳���ť
 * @author HP
 *
 */
public class LoadGameJFrame extends JFrame {
	
	//������GameJFrame�Ŀ���һ����������ļ���ȡ
	public static int LoadGameX = 807;//�����
	public static int LoadGameY = 630;//���߶�
	
	private JPanel jPanel = null; //������ʾ�����
	
	public LoadGameJFrame() {
		init();
	}

	/**
	 * ��ʼ������Ļ�������
	 */
	public void init() {
		this.setSize(LoadGameX, LoadGameY); // ���ô����С
		this.setTitle("̹�˴�ս"); // ���ô������
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // �����˳����ر�
		this.setLocationRelativeTo(null); // ��Ļ������ʾ		
		this.setResizable(false);
	}
	
	/**
	 * ��������
	 */
	public void start() {
		if(jPanel != null) {
			this.add(jPanel);
		}
		this.setVisible(true);//������ʾ
	}

	public void setjPanel(JPanel jPanel) {
		this.jPanel = jPanel;

	}	
	
}
