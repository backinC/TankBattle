package com.tedu.element;

import java.awt.Graphics;

import javax.swing.ImageIcon;

import com.tedu.controller.GameThread;
import com.tedu.manager.ElementManager;
import com.tedu.manager.GameElement;
import com.tedu.manager.GameLoad;
import com.tedu.manager.MusicManager;
import com.tedu.show.GameJFrame;

/**
 * @˵�� �����ֻ࣬��Ҫ������ʾ������������Ҫ�ƶ��Լ���������
 * @author HP
 *
 */
public class Base extends ElementObj {

	@Override
	public void showElement(Graphics g) {
		g.drawImage(this.getIcon().getImage(), this.getX(), this.getY(),
				this.getW(), this.getH(), null);
	}
	
	@Override
	public ElementObj createElement(String str) {
		String[] split = str.split(",");
		this.setX(Integer.parseInt(split[0]));
		this.setY(Integer.parseInt(split[1]));
		ImageIcon icon = GameLoad.imgMap.get(split[2]);
		this.setW(icon.getIconWidth());
		this.setH(icon.getIconHeight());
		this.setIcon(icon);
		return this;
	}
	
	@Override //���ر�ը��Ӧ��ֱ�ӽ�����Ϸ
	public void die() {
		this.setLive(false);
		GameLoad.loadObj();
		ElementObj element = GameLoad.getObj("base").createElement(this.baseToString()); 
		ElementManager.getManager().addElement(element, GameElement.DIE);
		MusicManager.gameOver();
		//���������Ի���
		GameJFrame.showDialog();
	}
	
	//��ȡ��ǰλ�á�Ϊ�������ر�ը��׼��
	private String baseToString() {
		return this.getX() + "," + this.getY() + "," + "break_base";  
	}

}
