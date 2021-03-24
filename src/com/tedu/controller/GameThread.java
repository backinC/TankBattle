package com.tedu.controller;

import java.util.List;
import java.util.Map;


import com.tedu.element.ElementObj;
import com.tedu.element.Play;
import com.tedu.element.Tool;
import com.tedu.manager.ElementManager;
import com.tedu.manager.GameElement;
import com.tedu.manager.GameLoad;
import com.tedu.manager.MusicManager;

/**
 * @˵�� ��Ϸ�����̣߳����ڿ�����Ϸ���أ���Ϸ�ؿ�����Ϸ�����Զ���
 * 		��Ϸ�ж�����Ϸ��ͼ�л�  ��Դ�ͷź����¶�ȡ������
 * @author HP
 * @�̳� ʹ�ü̳еķ�ʽʵ�ֶ��̣߳�һ�㽨��ʹ�ýӿ�ʵ�֣�
 */
public class GameThread extends Thread {
	
	private ElementManager em;
	//������Ϸ�Ƿ����
	public static boolean isOver = false;
	//gameRun()��������
	public static boolean flag = true;
	//��������һ�ػ�����һ��
	public static int increment = 0;
	//��ǰ�ؿ�
	public int gk = 1;
	public GameThread() {
		em = ElementManager.getManager();
	}
	
	@Override
	public void run() { // ��Ϸ��run����  ���߳�
		while(!isOver) { // ��չ�����Խ�true��Ϊһ���������ƽ���	
		//��Ϸ��ʼǰ		����������������Ϸ��Դ��������Դ��
			gameLoad();
		//��Ϸ����ʱ		��Ϸ������
			gameRun();
		//��Ϸ��������	��Ϸ��Դ���գ�������Դ��
			gameOver();
			
			try {
				sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 *��Ϸ�ļ��� 
	 */
	private void gameLoad() {
		//���ص�ͼ
		GameLoad.MapLoad(gk);//����Ϊ������ÿһ�����¼���
		//���س�ʼ����
		GameLoad.loadImg();
		//��������
		GameLoad.loadPlay();//Ҳ���Դ���������������2��
		//���ص��ˡ�NPC�ȵ�
		GameLoad.loadEnemyData(gk);
		//���ػ���
		GameLoad.loadBase();
		//��������
		GameLoad.loadMusic();
		//ȫ��������ɣ���Ϸ����
		
	}
	
//	private long gameTime = 0/*System.currentTimeMillis()*/;

	/**
	 * @˵�� ��Ϸ����ʱ
	 * @����˵�� ��Ϸ��������Ҫ�������飺1.�Զ�����ҵ��ƶ�����ײ������
	 * 								   2.��Ԫ�ص����ӣ�NPC��������ֵ��ߣ�
	 * 								   3.��Ϸ��ͣ�ȵȡ�����
	 * ��ʵ�����ǵ��ƶ�
	 */
	private void gameRun() {
		MusicManager.playGameStartMusic();
		long gameTime = 0L;
		while(flag) { // Ԥ����չ��true���Ա�Ϊ���������ڿ��ƹ���ؿ�������
			GameLoad.loadEnemy(gameTime);
			Map<GameElement, List<ElementObj>> all = em.getGameElements();
			
			List<ElementObj> enemys = em.getElementsByKey(GameElement.ENEMY);
			List<ElementObj> files = em.getElementsByKey(GameElement.PLAYFILE);
			List<ElementObj> enfiles = em.getElementsByKey(GameElement.ENEMYFILE);
			List<ElementObj> base = em.getElementsByKey(GameElement.BASE);
			List<ElementObj> play = em.getElementsByKey(GameElement.PLAY);
			List<ElementObj> tool = em.getElementsByKey(GameElement.TOOL);
			
			auto(all, gameTime);//��ϷԪ���Զ�������
			//��ײ�ж�
			//�ӵ������
			elementPK(files, base);
			//���������
			elementPK(enemys, base);
			//�����ӵ������
			elementPK(play, enfiles);
			//����������ӵ�
			elementPK(base, enfiles);
			//����������ӵ�
			elementPK(files, enemys);
			//����ӵ�������ӵ�
			elementPK(files, enfiles);
			//�����������ײ(ʰȡ����)
			PlayPKTOOL(play,tool);
			
			//���ˡ�������ͼ  ��ײ
			ElePKMap(enemys);
			ElePKMap(play);
			
			//����ӵ��������ӵ����ͼ��ײ
			filePKMap(files);
			filePKMap(enfiles);
			
			gameTime++;//Ψһ��ʱ�����

			try {
				sleep(10);// Ĭ�����Ϊ1��ˢ��100��
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void PlayPKTOOL(List<ElementObj> listA, List<ElementObj> listB) {
		for (int i = 0; i < listA.size(); i++) {
			ElementObj play = listA.get(i);
			for (int j = 0; j < listB.size(); j++) {
				ElementObj tool = listB.get(j);
				if (play.pk(tool)) {
					// ��һ��buff��������ߵ������ж�����buff
					// ������ʧ
					tool.buff(play);// ����
					tool.setLive(false);
					break;
				}
			}
		}
	}
	
	//���˺����Ǻ͵�����ײ�ķ���������ͨ��GRASS���飬��ײ���ص�
		//listA���������ײ�Ķ���3��ѭ������1���Ƕ���������ǽ��(4��)������GRASS��continue
		//listA�ǵ�2��ѭ������ײ����listAǿ��ת��ΪEnemy����ûص�
	public void ElePKMap(List<ElementObj> listA) {	
		for(int i = 0; i < listA.size(); i++) {
			ElementObj enemy = listA.get(i);
			for(String key: em.getMapElement().keySet()) {
				if(key.equalsIgnoreCase("GRASS")) {continue;}
				List<ElementObj> listB = em.getMapElementByKey(key);	
				for(int j =0; j < listB.size(); j++) {
					ElementObj file = listB.get(j);
					if(enemy.pk(file)) {
						enemy.Rebound();
					}
				}
			}
		}	
	}
	
	//�ӵ��͵�ͼ��ײ���ӵ���һ��ѭ��
	public void filePKMap(List<ElementObj> listA) {
		// ʹ��ѭ������һ��һ�ж������Ϊ�棬������2�����������״̬
		for (int i = 0; i < listA.size(); i++) {
			ElementObj file = listA.get(i);
			for (String key : em.getMapElement().keySet()) {
				if (key.equalsIgnoreCase("RIVER")) {
					continue;
				}
				if (key.equalsIgnoreCase("GRASS")) {
					continue;
				}
				List<ElementObj> listB = em.getMapElementByKey(key);
				for (int j = 0; j < listB.size(); j++) {
					ElementObj map = listB.get(j);
					if (file.pk(map)) {
						// Ӧ�ý�setLive()��Ϊһ���ܹ��������������Դ��빥����
						// ���ܹ�������ִ��ʱ�����Ѫ����Ϊ0ʱ������������Ϊfalse
						file.setLive(false);
						map.setLive(false);
						break;
					}
				}
			}
		}
	}
	
	public void elementPK(List<ElementObj> listA, List<ElementObj> listB) {
		
//		ʹ��ѭ������һ��һ�ж������Ϊ�棬������2�����������״̬
		for (int i = 0; i < listA.size(); i++) {
			ElementObj enemy = listA.get(i);
			for (int j = 0; j < listB.size(); j++) {
				ElementObj file = listB.get(j);
				if (enemy.pk(file)) {
//					Ӧ�ý�setLive()��Ϊһ���ܹ��������������Դ��빥����
//					���ܹ�������ִ��ʱ�����Ѫ����Ϊ0ʱ������������Ϊfalse
					enemy.setLive(false);
					file.setLive(false);
					break;
				}
			}
		}
	}
	
	//��ϷԪ���Զ�������
	public void auto(Map<GameElement, List<ElementObj>> all, long gameTime) {
		for(String key:em.getMapElement().keySet()) {
			List<ElementObj> list = em.getMapElementByKey(key); // ������ö�ٵ�˳���ȡ��Ӧ����
			for(int i = list.size()-1; i >= 0; i--) {
				ElementObj obj =list.get(i); // ��ȡΪ����
			    if (!obj.isLive()) { //�������
//			     ����һ����������(�����п������ܶ����飬�磺������������װ��)
			    	obj.die();//��Ҫ�Լ�����
			    	list.remove(i);
			    	continue;
			    }
			}
		}
		
//		GameElement.values(); // ���ط��� ����ֵ��һ�����飬 �����˳����Ƕ���ö�ٵ�˳��
		for (GameElement ge : GameElement.values()) {
			List<ElementObj> list = all.get(ge); // ������ö�ٵ�˳���ȡ��Ӧ����
			//��дֱ�Ӳ����������ݵĴ��벻Ҫʹ�õ�����
			for(int i = list.size() - 1; i >= 0; i--) {
				ElementObj obj = list.get(i); // ��ȡΪ����
				if (!obj.isLive()) { //�������
//					����һ����������(�����п������ܶ����飬�磺������������װ��)
					obj.die();//��Ҫ�Լ�����
					list.remove(i);
					continue;
				}
				obj.model(gameTime);//����ģ�巽��
			}
		}
	}

	/**
	 * ��Ϸ�л�����
	 */
	private void gameOver() {
		//�����Ϸ������ָ���˻���Ӯ�ˣ�
		if (isOver) {
			//ִ�ж�Ӧ�Ĵ��루���򽻻���
			System.out.println("��Ϸ����");
			return;
		}
		//��ղ����³�ʼ��Ԫ�ع�����
		em.reInit();
		//�޸Ĺؿ�
		changeGk();	
		//���½��м���
		gameLoad();
		//gameRun()�����Ŀ���
		flag = true;
		gameRun();
		gameOver();
	}
	
	/**
	 * @˵�� �л��ؿ�������ͨ��������������һ�ػ�����һ��
	 */
	private void changeGk() {
		this.gk = this.gk + increment;
		if (this.gk <= 0) {
			this.gk = 1;
		} 
		if (this.gk > 10) {
			this.gk = 10;
		}
		increment = 0;
	}
	
}
