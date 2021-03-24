package com.tedu.manager;

import com.tedu.element.Music;

/**
 * @˵�� ���ֹ�����
 * @author HP
 * @���� �ṩ���Ÿ������ַ�����������Ϸ��ʼ���֡��ӵ������Ч����ը��Ч 
 */
public class MusicManager {
	
//	private static MusicManager MM = null;
//	
//	public static synchronized MusicManager getManager() {
//		if (MM == null) {//��ֵ�ж�
//			MM = new MusicManager();
//		}		
//		return MM;
//	}
	
	/**
	 * @˵�� ��Ϸ��ʼ�ı�����
	 */
	public static void playGameStartMusic() {
		new Music("gameStartBGM").play();
	}
	
	/**
	 * @˵�� ����������е���/boss ����Ч
	 */
	public static void playFireMusic() {
		new Music("fireSound").play();
	}
	
	/**
	 * @˵�� ��ұ��򱬵���Ч
	 */
	public static void  playBoom() {
		new Music("playBoom").play();
	}
	
	/**
	 * @˵�� ��Ϸʧ�ܽ�������Ч
	 */
	public static void gameOver() {
		new Music("gameOver").play();
	}

}
