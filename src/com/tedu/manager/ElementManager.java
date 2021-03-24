package com.tedu.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tedu.element.ElementObj;

/**
 * @˵�� ������Ԫ�ع�������ר�Ŵ洢���е�Ԫ�أ�ͬʱ���ṩ����
 * 		������ͼ�Ϳ��ƻ�ȡ����
 * @author HP
 * 
 * @����һ�� �洢����Ԫ�����ݣ���ô��ţ�list��map��set
 * @������� ����������ͼ�Ϳ���Ҫ���ʣ��������ͱ���ֻ��һ��������ģʽ
 */
public class ElementManager {

	/*
	 * String ��Ϊkeyƥ�����е�Ԫ�� play -> List<Object> listPlay
	 * 							  enemy -> List<Object> listEnemy
	 * ö������ ����map��key�������ֲ�һ������Դ�����ڻ�ȡ��Դ
	 * List��Ԫ�صķ��� Ӧ���� Ԫ�� ����
	 * ����Ԫ�ض����Դ�ŵ�map�����У���ʾģ��ֻ��Ҫ��ȡ�� ���map�Ϳ���
	 * ��ʾʱ�еĽ�����Ҫ��ʾ��Ԫ��(����Ԫ�ػ����showElement())
	 */
	private Map<GameElement, List<ElementObj>> gameElements;
	
	private Map<String, List<ElementObj>> MapElements;	//Mapר�Ŵ洢<ǽ�����ͣ�ǽ�����>
	
	//��ȡ��ͼ������
	public Map<String, List<ElementObj>> getMapElement() {
		return MapElements;
	}
	
	//����key��ȡ��ͼ
	public List<ElementObj> getMapElementByKey(String str) {
		return MapElements.get(str);
	}
	
	//���  ��ͼ  Ԫ�أ�����ɼ��������ã�
	public void addMapElement(ElementObj obj, String str) {
		MapElements.get(str).add(obj);//��Ӷ��󵽼����У�		
	}
	
	//������һ��������
	/**
	 * ��ȡ����Ԫ��
	 * @return Map
	 */
	public Map<GameElement, List<ElementObj>> getGameElements() {
		return gameElements;
	}
	
	//���Ԫ�أ�����ɼ��������ã�
	public void addElement(ElementObj obj, GameElement ge) {
		//��Ӷ��󵽼����У���keyֵ���д洢
		gameElements.get(ge).add(obj);
	}
	
	//����key����list���ϣ�ȡ��ĳһ��Ԫ��
	public List<ElementObj> getElementsByKey(GameElement ge) {
		return gameElements.get(ge);
	}
	
	/**
	 * ����ģʽ���ڴ�������ֻ��һ��ʵ��
	 * ����ģʽ���������Զ�����ʵ��
	 * ����ģʽ����Ҫʹ�õ�ʱ��ż���ʵ��
	 * 
	 * ��д��ʽ��
	 * 1.��Ҫһ����̬������ (����) ����������
	 * 2.�ṩһ����̬���������ش�ʵ����return ����������
	 * 3.һ��Ϊ��ֹ�������Լ�ʹ�ã����ǿ���ʵ������,���Ի�˽�л����췽��
	 * 		ElementManager em = new ElementManager();
	 */
	private static ElementManager EM = null; //����
	
	//synchronized �߳��� -> ��֤������ִ����ֻ��һ���߳�
	public static synchronized ElementManager getManager() {
		if (EM == null) {//��ֵ�ж�
			EM = new ElementManager();
		}		
		return EM;
	}
	
	private ElementManager(){//˽�л����췽��
		init(); //ʵ��������
	}
	
/*	static { // ����ʵ�������� //��̬���������౻���ص�ʱ��ֱ��ִ��
		EM = new ElementManager();//ֻ��ִ��һ��
	}*/
	
	/**
	 * ʵ��������
	 * ��������Ϊ�˽������ܳ��ֵĹ�����չ����д init ����׼����
	 */
	public void init() {
		// HashMap hashɢ��
		gameElements = new HashMap<GameElement, List<ElementObj>>();
		//��ÿ��Ԫ�ؼ��϶����뵽map��
		for (GameElement ge: GameElement.values()) { //ͨ��ѭ����ȡö�����͵ķ�ʽ��Ӽ���
			gameElements.put(ge, new ArrayList<ElementObj>());
		}
		//��ʼ��MapElement��ʹ��ӵ��4��key��ֵΪ��ArrayList<ElementObj>
		MapElements = new HashMap<String, List<ElementObj>>();
		for(int i = 0; i< 4; i++) {
			MapElements.put("BRICK", new ArrayList<ElementObj>());
			MapElements.put("GRASS", new ArrayList<ElementObj>());
			MapElements.put("RIVER", new ArrayList<ElementObj>());
			MapElements.put("IRON", new ArrayList<ElementObj>());
		}
		//�����ӵ��ߡ��ӵ�����ըЧ��������Ч������
	}
	
	/**
	 * @˵�� �÷����������Ԫ�ع����������е�Ԫ�أ������³�ʼ��
	 */
	public void reInit() {
		getGameElements().clear();
		init();
	}
	
}
