package com.tedu.show;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.tedu.controller.GameListener;
import com.tedu.controller.GameThread;

/**
 * @˵�� ��Ϸ���� ��Ҫʵ�ֹ��ܣ��رա���ʾ����󻯡���С��
 * @author HP
 * @����˵������ҪǶ����壬�������̵߳ȵ�
 * @����˵����swing awt �����С����¼�û��ϴ�ʹ������Ĵ�����ʽ��
 * 
 * @���� 1.���󶨵�����
 * 		 2.������
 * 		 3.��Ϸ���߳�����
 * 		 4.��ʾ����
 */
public class GameJFrame extends JFrame {

	public static int GameX = 817;//GAMEX
	public static int GameY = 630;//GAMEY
	
	private JPanel jPanel = null; //������ʾ�����
	private KeyListener keyListener = null; //���̼���
	private MouseMotionListener mouseMotionListener = null; // ������
	private MouseListener mouseListener = null;
	private Thread thread = null; //��Ϸ���߳�
	Box b1;
	
	public GameJFrame() {
		init();
	}	
	
	public void init() {
		this.setSize(GameX, GameY); // ���ô����С
		this.setTitle("̹�˴�ս"); // ���ô������
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // �����˳����ر�
		this.setLocationRelativeTo(null); // ��Ļ������ʾ
		this.setResizable(false);
		this.setFocusable(true);
	}
	
	/*���岼�֣����Խ� �浵������...button�����������Ⲽ��*/
	public void addButton() {
//		this.setLayout(manager); // ���ָ�ʽ��������ӿؼ�
		JButton btn_pre = new JButton("��һ��");
		JButton btn_reStart = new JButton("���¿�ʼ");
		JButton btn_next = new JButton("��һ��");
		btn_pre.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				btn_pre.setFocusable(false);
				btn_reStart.setFocusable(false);
				btn_next.setFocusable(false);
				GameThread.flag = false;	
				GameThread.increment = -1;
			}
		});
		
		btn_next.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				btn_pre.setFocusable(false);
				btn_reStart.setFocusable(false);
				btn_next.setFocusable(false);
				GameThread.flag = false;	
				GameThread.increment = 1;
			}
		});
		
		btn_reStart.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				btn_pre.setFocusable(false);
				btn_reStart.setFocusable(false);
				btn_next.setFocusable(false);
				GameThread.flag = false;	
				GameThread.increment = 0;
			}
		});
		b1 = Box.createVerticalBox();
		Box b2 = Box.createHorizontalBox();
		b2.add(btn_pre);
		b2.add(btn_reStart);
		b2.add(btn_next);
		b1.add(b2);
		this.add(b1);
	}
	
	/**
	 * ��������
	 */
	public void start() {
		addButton();
		if(jPanel != null) {
			b1.add(jPanel);
		}
		if(keyListener != null) {
			this.addKeyListener(keyListener);
		}
		if(thread != null) {
			thread.start();//�����߳�
		}
		this.setVisible(true);//������ʾ
		//����ˢ��
		//���jPanel��Runnable������ʵ�����
		if(this.jPanel instanceof Runnable) {
			//�����ж���ǿ��ת���������
			new Thread((Runnable)this.jPanel).start();
		}
	}
	
	/*setע�룺��ѧϰssm  ͨ��set����ע�� �������ļ��ж�ȡ�����ݣ��������ļ�
	 * �е����ݸ�ֵΪ�������
	 * 
	 * ����ע�룺��Ҫ��Ϲ��췽��
	 * Spring ��ioc ���ж�����Զ����ɡ�����
	 * */
	
	public void setjPanel(JPanel jPanel) {
		this.jPanel = jPanel;
	}

	public void setKeyListener(KeyListener keyListener) {
		this.keyListener = keyListener;
	}

	public void setMouseMotionListener(MouseMotionListener mouseMotionListener) {
		this.mouseMotionListener = mouseMotionListener;
	}

	public void setMouseListener(MouseListener mouseListener) {
		this.mouseListener = mouseListener;
	}

	public void setThread(Thread thread) {
		this.thread = thread;
	}
	
	/**
	 * ���������Ի���
	 */
	public static void showDialog() {
		String options[]={"��һ��","���¿�ʼ","��һ��"};
		int value=JOptionPane.showOptionDialog(null, "ѡ��һ��������",
		"������Ϸ����", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null,
		options, "���¿�ʼ");
		if (value!=JOptionPane.CLOSED_OPTION) {
			switch (value) {
				case 0:
					GameThread.flag = false;	
					GameThread.increment = -1;
					break;
				case 1:
					GameThread.flag = false;	
					GameThread.increment = 0;
					break;
				case 2:
					GameThread.flag = false;	
					GameThread.increment = 1;
					break;
				default:
					break;
			}
		} else {
//			//�������  ��  ����Ϸֱ�ӽ�������Ҫ�������г����ˡ�����
//			GameThread.isOver = true;
//			GameThread.flag = false;
			showDialog();
		}
	}
	
}
