package com.tedu.element;

import java.awt.Graphics;

import javax.swing.ImageIcon;


/**
 * @˵�� ����ӵ��࣬�����ʵ�����������Ҷ�����úʹ���
 * @author hjc
 * @����Ŀ�������
 *   1.�̳���Ԫ�ػ���;��дshow����
 *   2.��������ѡ������д�����������磺move��
 *   3.˼���������������е�����
 */
public class PlayFile extends ElementObj{
	private int attack = 1;//������
	private int moveNum = 3;//�ƶ��ٶ�ֵ
	private int type = 1;//�ӵ��ȼ�
	private String fx;
	
//	ʣ�µĴ����չ; ������չ�������ӵ��� ���⣬�����ȵȡ�(��������Ҫ���ӵ�����)
	public PlayFile() {}//һ���յĹ��췽��
//	�Դ����������Ĺ��̽��з�װ�����ֻ��Ҫ�����Ҫ��Լ������������ֵ���Ƕ���ʵ��
	@Override   //{X:3,y:5,f:up}
	public  ElementObj createElement(String str) {//�����ַ����Ĺ���
		String[] split = str.split(",");
		for(String str1 : split) {//X:3
			String[] split2 = str1.split(":");// 0�±� �� x,y,f   1�±���ֵ
			switch(split2[0]) {
			case "x": this.setX(Integer.parseInt(split2[1]));break;
			case "y":this.setY(Integer.parseInt(split2[1]));break;
			case "f":this.fx=split2[1];break;
			case "t":this.setType(Integer.parseInt(split2[1]));break;
			}
		}
		if(type==2){
			this.setMoveNum(5);
		}
		else if(type==3){
			this.setAttack(2);
		}
		this.setW(10);
		this.setH(10);
		this.setIcon(new ImageIcon("image/file/file"+type+fx+".png"));
		return this;
	}
	@Override
	public void showElement(Graphics g) {	
		g.drawImage(this.getIcon().getImage(), 
				this.getX(), this.getY(), 
				this.getW(), this.getH(), null);
	}	
	@Override
	protected void move() {
		if(this.getX()<0 || this.getX() >900 || 
				this.getY() <0 || this.getY()>600) {
			this.setLive(false);
			return;
		}
		switch(this.fx) {
		case "up": this.setY(this.getY()-this.moveNum);break;
		case "left": this.setX(this.getX()-this.moveNum);break;
		case "right": this.setX(this.getX()+this.moveNum);break;
		case "down": this.setY(this.getY()+this.moveNum);break;
		}
		
	}
	/**
	 * �����ӵ���˵��1.���߽�  2.��ײ  3.��ҷű���
	 * ����ʽ���ǣ����ﵽ����������ʱ��ֻ���� �޸�����״̬�Ĳ�����
	 */
//	@Override
//	public void die() {
//		ElementManager em=ElementManager.getManager();
//		ImageIcon icon=new ImageIcon("image/tank/play2/player2_up.png");
//		ElementObj obj=new Play(this.getX(),this.getY(),50,50,icon);//ʵ��������
////		��������뵽 Ԫ�ع�������
////		em.getElementsByKey(GameElement.PLAY).add(obj);
//		em.addElement(obj,GameElement.DIE);//ֱ�����
//	}
	
//    /**�ӵ���װ*/
//	private long time=0;
//	protected void updateImage(long gameTime) {
//		if(gameTime-time>5) {
//			time=gameTime;//Ϊ�´α�װ��׼��
//			this.setW(this.getW()+2);
//			this.setH(this.getH()+2);
////			���ͼƬ��������
//		}
//	}
	
	public void setAttack(int attack) {
		this.attack = attack;
	}
	public void setType(int type) {
		this.type = type;
	}
	public void setMoveNum(int moveNum) {
		this.moveNum = moveNum;
	}
	
}





