package com.tedu.element;

import java.awt.Graphics;
import java.util.Random;

import javax.swing.ImageIcon;

import com.tedu.manager.ElementManager;
import com.tedu.manager.GameElement;
import com.tedu.manager.GameLoad;

/**	
 * @˵��	������
 * �ƶ�����������ֵ�����ܹ���
 * @author inback
 *
 */
public class Enemy extends ElementObj{

	protected boolean left = false; // ��
	protected boolean up = false; // ��
	protected boolean right = false; // ��
	protected boolean down = false; // ��
	
	protected int HP = 1;	//����ֵ
	
	protected int speed = 1;	//�ٶ�
	
	/**
	 * @fx:ר�ż�¼��ǰ��������ķ���Ĭ��Ϊ�� up
	 * @DirFlag	�����ȡӦ���л������ʱ�̵���ֵ��
	 * @DirFlagNum	�溯�����ô��������ֶΣ�����ȷ���Ѿ���ȥ��ʱ��
	 */
	protected String fx = "up";
	protected int DirFlag = 0;
	protected int DirFlagNum = 0;
	
	//�Ƿ񹥻�״̬ ��true����  false ֹͣ	
	protected boolean pkType = false;
	
	protected int pkRestNum = 0;
	protected int pkRest = new Random().nextInt(31)+10;
	
	//�չ��캯��
	public Enemy() {}
	
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
		this.setIcon(GameLoad.imgMap.get("bot_"+fx));
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
	
	protected String BoomString() {
		return this.getX() + "," + this.getY() + "," + "boom";  
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
	
	protected void RandomFx(){
		int x = 0;
		Random r = new Random();
		x = r.nextInt(4);
		switch(x) {
		case 0:
			this.up = false;
			this.down = false;
			this.right = false;
			this.left = true;
			this.fx = "left";
			break;
		case 1:
			this.right = false;
			this.left = false;
			this.down = false;
			this.up = true;
			this.fx = "up";
			break;
		case 2:
			this.up = false;
			this.down = false;
			this.left = false;
			this.right = true;
			this.fx = "right";
			break;
		case 3:
			this.right = false;
			this.left = false;
			this.up = false;
			this.down = true;
			this.fx = "down";
			break;
		}
		this.DirFlagNum = 0;
		this.DirFlag = new Random().nextInt(31)+10;
	}
	
	/**
	 * @˵�� 	��ײ�ص�����������Ŀǰ�ķ��򣬷�����ص�һ���ٶȾ���
	 */
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
	
	public void hurt() {
		this.HP--;
		if(this.HP == 0) {
			this.die();
		}
	}
	
	@Override
	protected void add(long gameTime) {
		randomShoot(100,20);
		//		�����ǰ���ǹ���״̬����ֱ�ӷ���
		if (!this.pkType) {
			return;
		}
		this.pkType = false;
//		new PlayFile(); //����һ����  ��Ҫ���Ĺ����Ƚ϶�   ����ѡ��һ�ַ�ʽ��ʹ��С����
//		���������Ķ��������з�װ��Ϊһ������������ֱֵ�����������
//		��������ض����ʵ�壬����ʼ������
//		����һ���̶���ʽ {x:3,y:5,f:up} json ��ʽ
		ElementObj element = GameLoad.getObj("playfile").createElement(this.toString()); //�Ժ�Ŀ��ѧϰ�л�����
//		װ�뵽������
		ElementManager.getManager().addElement(element, GameElement.ENEMYFILE);
	}
	
	/**
	 * @˵�� ������亯��
	 * 
	 */
	protected void randomShoot(int x1, int x2) {
		if(this.pkRestNum == this.pkRest) {
			this.pkType = true;
			this.pkRestNum = 0;
			this.pkRest = new Random().nextInt(x1+1)+x2;//����������ǰ����������䣬����ÿ�η�����뾭���ļ��
		}
		this.pkRestNum++;
	}
	
	@Override
	public String toString() {
		int x = this.getX();
		int y = this.getY();
		int l = 1;
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
