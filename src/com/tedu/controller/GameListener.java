package com.tedu.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.tedu.element.ElementObj;
import com.tedu.manager.ElementManager;
import com.tedu.manager.GameElement;

/**
 * @˵�� �����࣬���ڼ����û��Ĳ���
 * @author HP
 *
 */
public class GameListener implements KeyListener {
	
	private ElementManager em = ElementManager.getManager();

	/*�ܷ�ͨ��һ����������¼���а��µļ�������ظ���������ֱ�ӽ���
	 * ͬʱ����һ�ΰ��¾ͼ�¼�ڼ����У��ڶ����ж��������Ƿ��С�
	 * 	�ɿ���ֱ��ɾ�������еļ�¼
	 * set����
	 * 
	 */
	private Set<Integer> set = new HashSet<Integer>();
	
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	/**
	 * ���¼���: ��37  ��38  ��39  ��40 ��tab�޷�Ӧ
	 * ʵ�����ǵ��ƶ�
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		if(set.contains(e.getKeyCode())) { // ����������Ѵ��ڴ˶���ֱ�ӽ���
			return;
		}
		//��������в����ڣ��ͼ���
		set.add(e.getKeyCode());
		
		//�õ���Ҽ���
		List<ElementObj> play = em.getElementsByKey(GameElement.PLAY);
		for (ElementObj obj : play) {
			obj.keyClick(true, e.getKeyCode());
		}
	}
	
	/**
	 * �ɿ�����
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		if(!set.contains(e.getKeyCode())) { // ���������û�д˶���ֱ�ӷ���
			return;
		}
		//�Ƴ�����
		set.remove(e.getKeyCode());
		
		//�õ���Ҽ���
		List<ElementObj> play = em.getElementsByKey(GameElement.PLAY);
		for (ElementObj obj : play) {
			obj.keyClick(false, e.getKeyCode());
		}
	}

	
	
}
