package com.tedu.game;

import com.tedu.show.LoadGameJFrame;
import com.tedu.show.LoadGameJPanel;

public class GameStart {

	/**
	 * �����Ψһ���
	 */
	public static void main(String[] args) {
//		GameJFrame gj = new GameJFrame();
//		/*ʵ������壬ע�뵽JFrame��*/
//		GameMainJPanel jp = new GameMainJPanel();
//		//ʵ��������
//		GameListener listener = new GameListener();	
//		//ʵ�������߳�
//		GameThread th = new GameThread();
//		//ע��
//		gj.setjPanel(jp);//ע�����
//		gj.setKeyListener(listener);//ע�����
//		gj.setThread(th);//ע���߳�
//		
//		gj.start();		
		LoadGameJFrame lgj = new LoadGameJFrame();
		/*ʵ������壬ע�뵽JFrame��*/
		LoadGameJPanel lgjp = new LoadGameJPanel(lgj);
		lgj.setjPanel(lgjp);
		lgj.start();
	}
	
/**
 * 1.������Ϸ�������Ϸ�������ļ���ʽ���ļ���ȡ��ʽ��load��ʽ��
 * 2.�����Ϸ��ɫ��������Ϸ���󣨳�����ڻ���ļ̳У�
 * 3.����pojo�ࣨVo��...
 * 4.��Ҫ�ķ������ڸ�������д��������಻֧�֣����Բ����޸ĸ��ࣩ
 * 5.������ã���ɶ����load��add��Manager.
 * 6.��ײ�ȵ�ϸ�ڴ���
 */

}
