package com.tedu.show;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import com.tedu.element.ElementObj;
import com.tedu.manager.ElementManager;
import com.tedu.manager.GameElement;

/**
 * @˵�� ��Ϸ����Ҫ���
 * @author HP
 * @����˵�� ��Ҫ����Ԫ�ص���ʾ��ͬʱ���н����ˢ�£����̣߳�
 * 
 * @���⻰ java����ʵ��˼����Ӧ���ǣ����̳л����ǽӿ�ʵ��
 * @���߳�ˢ�� 1.����ʵ��Runnable�ӿ�
 * 			   2.�����ж���һ���ڲ���ʵ��
 */
public class GameMainJPanel extends JPanel implements Runnable {

	//����������
	private ElementManager em;
	
	public GameMainJPanel() {
		init();
	}
	
	public void init() {
		em = ElementManager.getManager(); // �õ�Ԫ�ع���������
	}
	
	/**
	 * paint�����ǽ��л滭Ԫ�ء�
	 * �滭ʱ���й̶���˳�� �Ȼ滭��ͼƬ���ڵײ㣬��滭�ĻḲ���Ȼ滭��
	 * Լ����������ִֻ��һ�Σ���ʵʱˢ����Ҫʹ�ö��߳�
	 */
	@Override  //���ڻ滭�� Graphic ����ר�����ڻ滭��
	public void paint(Graphics g) {
		super.paint(g);
		//map key-value key�����򲻿��ظ���
		//set ��map��keyһ�������򲻿��ظ���
		Map<GameElement, List<ElementObj>> all = em.getGameElements();
//		GameElement.values(); // ���ط��� ����ֵ��һ�����飬 �����˳����Ƕ���ö�ٵ�˳��
		for (GameElement ge : GameElement.values()) {
			List<ElementObj> list = all.get(ge); // ������ö�ٵ�˳���ȡ��Ӧ����
			for(int i = 0; i < list.size(); i++) {
				ElementObj obj = list.get(i); // ��ȡΪ����
				obj.showElement(g); //����ÿ�����Լ���showElement����
			}
		}
		//������ȡGRASS��ͼ�飬������
		List<ElementObj> GRASSList = em.getMapElementByKey("GRASS");
		for(int i = 0;i < GRASSList.size(); i++) {
			ElementObj obj = GRASSList.get(i);
			obj.showElement(g);
		}
	}

	@Override
	public void run() { //�ӿ�ʵ��
		while(true) {
			//���»滭
			this.repaint();
			//һ������¶��̶߳���ʹ��һ�����ߣ������ٶ�
			try {
				Thread.sleep(30); // ����50���� 1��ˢ��20��
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
