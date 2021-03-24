package com.tedu.element;

import java.awt.Graphics;

import javax.swing.ImageIcon;

import com.tedu.manager.GameLoad;

public class Boom extends ElementObj{
	
	private int deadTime = 0;
	

	//�չ��캯��
	public Boom() {}
	
	@Override
	public void showElement(Graphics g) {
		g.drawImage(this.getIcon().getImage(), this.getX(), this.getY(), 
				this.getW(), this.getH(), null);
		
	}
	
	/**
	 * @˵�� ������ȡ���ַ������½�����
	 * ����X������Y��ͼƬ�������ļ�GameData��keyֵ��
	 */
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

	
	/**
	 * @˵�� move�������Ի�ȡʱ�䣬Ŀǰ1��20֡,1֡50ms
	 * 		��ը1s����ʧ
	 */
	@Override
	protected void move() {
		deadTime++;
		if(deadTime == 20) {
			this.setLive(false);
		}
	}
	
	

}
