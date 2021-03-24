package com.tedu.element;

import java.awt.Graphics;
import java.util.Random;

import javax.swing.ImageIcon;

import com.tedu.manager.ElementManager;
import com.tedu.manager.GameElement;
import com.tedu.manager.GameLoad;

/**	
 * @˵��	boss��
 * �ƶ�����������ֵ�����ܹ���
 * @author inback
 *
 */
public class Boss extends Enemy{
	
	private int HP = 5;	//����ֵ
	
	private int speed = 2;	//�ٶ�
	
	//�չ��캯��
	public Boss() {}
	
	//����ж�
	@Override
	public boolean isLive() {
		return super.isLive();
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
	
	//��װ���������������Զ�ע�롱�ɷ���������泯ͼƬ��
	@Override
	protected void updateImage(long gameTime) {
//		if(!this.isLive()) {return;}	//��������������Զ�ע�롱�ɷ���������泯ͼƬ��
		this.setIcon(GameLoad.imgMap.get("boss_"+fx));
	}
	
	//��ͼ����
	@Override
	public void showElement(Graphics g) {
		g.drawImage(this.getIcon().getImage(), this.getX(), this.getY(), 
				this.getW(), this.getH(), null);
	}
	
	@Override
	public void setLive(boolean live) {
		this.HP--;
		if(this.HP > 0) {
			return;
		}
		super.setLive(live);
	}
	
	/**
	 * @˵�� ������������ͼƬ�л�Ϊ��ը
	 */
	@Override
	public void die() {
		this.setLive(false);
//		this.setIcon(GameLoad.imgMap.get("boom"));
//		ElementManager.getManager().addElement(this, GameElement.DIE);
		GameLoad.loadObj();
		ElementObj element = GameLoad.getObj("boom").createElement(this.BoomString()); 
		ElementManager.getManager().addElement(element, GameElement.DIE);
	}
	
	@Override
	protected String BoomString() {
		return super.BoomString();
	}
	
	/**
	 * @˵��	����ƶ�����
	 * ����ÿ0.5s~2s�л�����Ŀǰ50msˢ��һ�Σ�ˢ����Ϊ20֡
	 * ��ÿ10~40�κ������øı�һ�η���
	 */
	@Override
	protected void move() {
//		if(!this.isLive()) {return;}	//����������������ƶ���ֱ��return
		if(this.DirFlagNum == this.DirFlag) {
			RandomFx();
		}
		this.DirFlagNum++;
		if (this.left && this.getX() > 0) {
			this.setX(this.getX() - speed);
		}
		if (this.up && this.getY() > 0) {
			this.setY(this.getY() - this.speed);
		}
		if (this.right && this.getX() < 776 + this.speed/*900 - this.getW()*/) {
			this.setX(this.getX() + this.speed);
		}
		if (this.down && this.getY() < 543 + this.speed/*600 - this.getH()*/) {
			this.setY(this.getY() + this.speed);
		}
	}
	
	@Override
	protected void RandomFx() {
		super.RandomFx();
	}
	
	/**
	 * @˵�� 	��ײ�ص�����������Ŀǰ�ķ��򣬷�����ص�һ���ٶȾ���
	 */
	@Override
	public void Rebound() {
		if(this.left) {
			this.setX(this.getX() + this.speed);
		}
		if(this.up) {
			this.setY(this.getY() + this.speed);
		}
		if(this.right) {
			this.setX(this.getX() - speed);
		}
		if(this.down) {
			this.setY(this.getY() - this.speed);
		}
	}
	
	@Override
	public void hurt() {
		super.hurt();
	}
	
	@Override
	protected void add(long gameTime) {
		super.add(gameTime);
	}
	
	@Override
	protected void randomShoot(int x1, int x2) {
		super.randomShoot(50, 20);
	}
	
	@Override
	public String toString() {
		int x = this.getX();
		int y = this.getY();
		int l = 3;
		switch(this.fx) {
		case "up": x+=13;y-=5;break;
		case "down": y+=30;x+=13;break;
		case "left": y+=13;x-=5;break;
		case "right": y+=13;x+=30;break;
		}
		//X �� Y���Ϻ�̹�˵����λ��ƫ��
//		return "x:"+(this.getX()+20)+",y:"+(this.getY()+20)+",f:"+this.fx;
		return "x:"+x+",y:"+y+",f:"+this.fx+",l"+l;
	}

}
