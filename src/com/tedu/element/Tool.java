package com.tedu.element;

import java.awt.Graphics;

import javax.swing.ImageIcon;
/**	
 * @˵��	������
 * ֻ����λ����һ���ԣ��Լ�����ǰ��Ĵ��״̬����ʰȡ����ʧ��
 * @author inback
 *
 */
public class Tool extends ElementObj{
	//���ߵ�type��  ����ʹ��ö��
	private String name;
	
	public String getName() {
		return name;
	}

	@Override
	public void showElement(Graphics g) {
		g.drawImage(this.getIcon().getImage(), this.getX(), this.getY(), this.getW(), this.getH(), null);
	}
	
	public Play buff(Play play) {
		if(this.getName()=="Armour_PiercingBullet") {
			//�ı��ӵ�Ϊ���׵���˳��һ�£������Ҳ���
			play.setTpye(2);
		}else if(this.getName()=="LaserBullet")	{
			//���ӵ�Ϊ���ⵯ�������ӵ�ͼƬ����image��
			//���׵����������˺��Ӹߣ����ⵯ���������ٶȱ��
			play.setTpye(3);
		}else if(this.getName()=="SpeedUp")	{
			play.setSpeed(2);
		}else if(this.getName()=="Star")	{
			play.setHP(4);
		}
		return play;
	}
	
	@Override // ������Դ���  ǿ���ͣ�x��y
	public ElementObj createElement(String str) {
		String[] arr = str.split(",");
		ImageIcon icon = null;
		switch (arr[0]) {
		//���׵����˺����
		case "Armour_PiercingBullet":
			icon = new ImageIcon("image/tool/01.png");
			name = "Armour_PiercingBullet";
			break;
		//���ⵯ���ӵ��ٶȱ��
		case "LaserBullet":
			icon = new ImageIcon("image/tool/02.png");
			name = "LaserBullet";
			break;
		//���٣�̹���ٶȱ��
		case "SpeedUp":
			icon = new ImageIcon("image/tool/03.png");
			name = "SpeedUp";
			break;
		//������ֵ
		case "Star":
			icon = new ImageIcon("image/tool/04.png");
			name = "Star";
			break;
		}		
		this.setX(Integer.parseInt(arr[1]));
		this.setY(Integer.parseInt(arr[2]));
		this.setW(icon.getIconWidth());
		this.setH(icon.getIconHeight());
		this.setIcon(icon);
		return this;
	}

}
