package com.tedu.show;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.tedu.controller.GameListener;
import com.tedu.controller.GameThread;
import com.tedu.manager.GameLoad;

/**
 * @˵�� ��Ϸ���ȼ��ش�������
 * @author HP
 *
 */
public class LoadGameJPanel extends JPanel {
	
	private ImageIcon imageIcon = null;
	private JButton playBtn = null;
	private JFrame jFrame;

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(imageIcon.getImage(), 0, 0, null);
	}
	
	public LoadGameJPanel(JFrame jFrame) {
		this.jFrame = jFrame;
		init();
	}

	/**
	 * @˵�� ��ʼ����壬�������ĸ�������
	 */
	private void init() {
		//����ͼƬ
		GameLoad.loadGameImg();
		//��ȡ����ͼƬ
		this.imageIcon = GameLoad.loadGameImgMap.get("backgroundImg");
		this.setLayout(null);
		initPlayBtn();
		this.add(playBtn);
	}

	/**
	 * @˵�� ��ʼ�����������Ϸ��ť������
	 * @���� ����Ĭ��ͼƬ�����ͼƬ���ޱ߿��ڲ��޾۽�...
	 */
	private void initPlayBtn() {
		this.playBtn = new JButton();
		//���ð�ťĬ��ͼƬ
		this.playBtn.setIcon(GameLoad.loadGameImgMap.get("btnPlay"));
		//���ð�ť���ͼƬ
		this.playBtn.setPressedIcon(GameLoad.loadGameImgMap.get("btnPlayClick"));
		//����Ϊ����������
		this.playBtn.setContentAreaFilled(false);
		this.playBtn.setFocusPainted(false);
		this.playBtn.setBounds(350, 400, 97, 54);
		this.playBtn.setBorderPainted(false);
		this.playBtn.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				jFrame.setVisible(false);
				
				GameJFrame gj = new GameJFrame();
				/*ʵ������壬ע�뵽JFrame��*/
				GameMainJPanel jp = new GameMainJPanel();
				//ʵ��������
				GameListener listener = new GameListener();	
				//ʵ�������߳�
				GameThread th = new GameThread();
				//ע��
				gj.setjPanel(jp);//ע�����
				gj.setKeyListener(listener);//ע�����
				gj.setThread(th);//ע���߳�
				
				gj.start();	
//				jFrame.dispose();
			}
		});
		
	}

	public ImageIcon getImageIcon() {
		return imageIcon;
	}

	public JButton getPlayBtn() {
		return playBtn;
	}
	
	
	
}
