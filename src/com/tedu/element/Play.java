package com.tedu.element;

import java.awt.Graphics;
import javax.swing.ImageIcon;

import com.tedu.manager.ElementManager;
import com.tedu.manager.GameElement;
import com.tedu.manager.GameLoad;
import com.tedu.manager.MusicManager;
import com.tedu.show.GameJFrame;

public class Play extends ElementObj /* implements Comparable<Play> */ {

	/**
	 * �ƶ�����: 
	 * 	1.������ ��� ����ö������ʹ�ã�һ��ֻ���ƶ�һ������
	 *  2.˫���� ���� �� ���� ���booleanֵʹ��
	 * 		���磺true������falseΪ�� ��Ҫ��һ��������ȷ���Ƿ��·���� Լ��
	 * 		 0������ 1������ 2������ 
	 * 	3.������ �������Ҷ�����	boolean���ʹ�� true�����ƶ� false�����ƶ�
	 * 						 ͬʱ���Ϻ�����ô�죿�󰴵Ļ������Ȱ��� 
	 * 
	 * ˵��������3�з�ʽ �Ǵ����д���ж���ʽ ��һ�� 
	 * ˵������Ϸ�зǳ�����ж����������ʹ�����ж����ԣ��ܶ�״ֵ̬Ҳʹ���ж�����
	 * 	 ��״̬ ����ʹ��map<����, boolean>�� set<�ж�����> �ж���������ʱ��
	 * 
	 * @���� 1.ͼƬҪ��ȡ���ڴ��У�������    ��ʱ����ʽ���ֶ���д���洢���ڴ���
	 * 		 2.ʲôʱ������޸�ͼƬ����ΪͼƬ���ڸ����е����Դ洢
	 * 		 3.ͼƬӦ��ʹ��ʲô���Ͻ��д洢
	 */
	private boolean left = false; // ��
	private boolean up = false; // ��
	private boolean right = false; // ��
	private boolean down = false; // ��
	public int tpye = 1;//�ӵ�����
	
	public int getTpye() {
		return tpye;
	}

	public void setTpye(int tpye) {
		this.tpye = tpye;
	}
	
	//ר�ż�¼��ǰ��������ķ���Ĭ��Ϊ�� up
	private String fx = "up";
	//�Ƿ񹥻�״̬ ��true����  false ֹͣ
	private boolean pkType = false;
	//ÿ���ƶ����룬�൱���ٶ�
	private int speed = 1;
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	//Ѫ��
	private int HP = 2;
	
	public void setHP(int hP) {
		HP = hP;
	}
	
	public Play(){}
	
	public Play(int x, int y, int w, int h, ImageIcon icon) {
		super(x, y, w, h, icon);
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

	/**
	 * ��������һ��˼�룺�����Լ��������Լ���
	 */
	@Override
	public void showElement(Graphics g) {
		g.drawImage(this.getIcon().getImage(), this.getX(), this.getY(), this.getW(), this.getH(), null);
	}	

	/**
	 * @˵�� ��д���෽��
	 * @�ص� ������������Ҫ�ı�״ֵ̬
	 */
	@Override
	public void keyClick(boolean bl, int key) { // ֻ�а��»����ɿ��Ż����
//		System.out.println("���ԣ�" + key);
		if (bl) {// ����
			switch (key) {// ��� �Ż����Ӽ����������������û�з���ֻ����һ��
			case 65:
				this.up = false;
				this.down = false;
				this.right = false;
				this.left = true;
				this.fx = "left";
				break;
			case 87:				
				this.right = false;
				this.left = false;
				this.down = false;
				this.up = true;
				this.fx = "up";
				break;
			case 68:
				this.up = false;
				this.down = false;
				this.left = false;
				this.right = true;
				this.fx = "right";
				break;
			case 83:				
				this.right = false;
				this.left = false;
				this.up = false;
				this.down = true;
				this.fx = "down";
				break;
			case 32:
				this.pkType= true;//���¿ո�������״̬
				break;
			}
		} else {
			switch (key) {
			case 65:
				this.left = false;
				break;
			case 87:
				this.up = false;
				break;
			case 68:
				this.right = false;
				break;
			case 83:
				this.down = false;
				break;
			case 32:
				this.pkType= false;//�ɿ��ո�������״̬
				break;
			}
		}
	}

	/**
	 * 
	 */
	@Override
	protected void move() {
		if (this.left && this.getX() > 0) {
			this.setX(this.getX() - speed);
		}
		if (this.up && this.getY() > 0) {
			this.setY(this.getY() - speed);
		}
		if (this.right && this.getX() < 776 + speed/*900 - this.getW()*/) {
			this.setX(this.getX() + speed);
		}
		if (this.down && this.getY() < 543 + speed/*600 - this.getH()*/) {
			this.setY(this.getY() + speed);
		}
	}
	
	@Override
	protected void updateImage(long gameTime) {
		this.setIcon(GameLoad.imgMap.get(fx));
	}
	
	//��ӱ���ʱ��
	private long fileTime = 0;
//	fileTime �ʹ����ʱ�� gameTime ���бȽϣ���ֵ�Ȳ������㣬�����ӵ����
	
	/**
	 * @�������⣺1.������д�ķ����ķ������η��Ƿ�����޸ģ�
	 * 			  2.���������add�����Ƿ�����Զ��׳��쳣��
	 * @��д����1.��д �����ķ������ƺͷ���ֵ����͸����һ��
	 * 			  2.��д�����Ĵ�������������У�����͸����һ��
	 * 			  3.��д�ķ����������η�  ֻ��   �ȸ���ĸ��ӿ�
	 * 				�ȷ�˵������ķ������ܱ����ģ�����������Ҫ�ڷ������е���
	 * 						����ֱ������̳У���д��super.���෽����public����
	 * 			  4.��д�ķ����׳����쳣	�����Աȸ�����ӿ�
	 * �ӵ������  ��Ҫ���� �����������λ�ã������ߵķ���  �������Ա任�ӵ���˼������ô������
	 */
	@Override	//����ӵ�
	protected void add(long gameTime) {
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
		ElementManager.getManager().addElement(element, GameElement.PLAYFILE);
//		���Ҫ�����ӵ��ٶȵȵȡ���������Ҫ�����д
	}
	
	@Override
	public String toString() {
		int x = this.getX();
		int y = this.getY();
//		setTpye(2);
		int type = this.getTpye();	//�ӵ����ͣ�1Ϊ�����ӵ���2Ϊ���׵���3Ϊ���ⵯ
//		System.out.println(type);
		switch(this.fx) {
		case "up": x+=13;y-=5;break;
		case "down": y+=30;x+=13;break;
		case "left": y+=13;x-=5;break;
		case "right": y+=13;x+=30;break;
		}
		//X �� Y���Ϻ�̹�˵����λ��ƫ��
//		return "x:"+(this.getX()+20)+",y:"+(this.getY()+20)+",f:"+this.fx;
		return "x:"+x+",y:"+y+",f:"+this.fx+",t:"+type;
	}
	
	/**
	 *
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
			this.setX(this.getX() - this.speed);
		}
		if(this.down) {
			this.setY(this.getY() - this.speed);
		}
	}
	
	@Override
	public void setLive(boolean live) {
		this.HP--;
		if(this.HP > 0) {
			return;
		}
		super.setLive(live);
	}
	
	@Override
	public void die() {
		this.setLive(false);
		GameLoad.loadObj();
		ElementObj element = GameLoad.getObj("play").createElement(this.playToString()); 
		ElementManager.getManager().addElement(element, GameElement.DIE);
		MusicManager.gameOver();
		//���������Ի���
		GameJFrame.showDialog();
	}
	
	public String playToString() {
		return this.getX() + "," + this.getY() + "," + "boom";  
	}

}
