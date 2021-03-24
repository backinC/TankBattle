package com.tedu.manager;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.swing.ImageIcon;

import com.tedu.element.ElementObj;

/**
 * @˵�� �����������ߣ����ڶ�ȡ�����ļ��Ĺ��ߣ������࣬����ṩ����static����
 * @author HP
 *
 */
public class GameLoad {
	
	//��ȡ��Դ������
	private static ElementManager em = ElementManager.getManager();

	//���ͼƬ����	ʹ��map�����д洢	ö����������ƶ�����չ��
	public static Map<String, ImageIcon> imgMap = new HashMap<>();
	//����ͼƬ����	ʹ��map�����д洢	ö����������ƶ�����չ��
	public static Map<String, ImageIcon> enemyImgMap;
	//GameLoad��gameLoad()��ʱ����ȡ�����ļ��е�����
	public static Map<String, String> EnemyMap = new HashMap<>();
	//��ʼ����ͼƬ���� ʹ��map�����д洢
	public static Map<String, ImageIcon> loadGameImgMap = new HashMap<>();
	//��Ϸ���ּ���
	public static Map<String, File> musicMap = new HashMap<>();
	//���ؼ���
	public static Map<String, ImageIcon> baseMap = new HashMap<>();
	
	
	//�û���ȡ�ļ�����
	private static Properties pro = new Properties();
	
	/**
	 * @˵�� �����ͼid�м��ط��������ļ������Զ����ɵ�ͼ�ļ����ƣ������ļ�
	 * @param mapId �ļ���� �ļ�Id
	 */
	public static void MapLoad(int mapId) {
		// ���ȼ���Obj
		loadObj();
		//�õ��ļ�·��
		String mapName = "com/tedu/text/"+mapId+".map";
		//ʹ��IO������ȡ�ļ�����      �õ��������		
		ClassLoader classLoader = GameLoad.class.getClassLoader();
		InputStream maps = classLoader.getResourceAsStream(mapName);
		if (maps == null) {
			System.out.println("�����ļ���ȡ�쳣��");
			return;
		}
		pro.clear();
		try {
			//�Ժ���xml��json
			pro.load(maps);
			//���Զ�̬��ȡ����key����key�Ϳ��Ի�ȡvalue
			Enumeration<?> names = pro.propertyNames();
			while(names.hasMoreElements()) {
				//��ȡkey
				String key = names.nextElement().toString();
				//����keyȡvalues
				//�ַ����ָ�������Ӧǽ������
				String[] arrs = pro.getProperty(key).split(";");
				for (int i = 0; i < arrs.length; i++) {
					ElementObj obj = getObj("map");
					ElementObj element = obj.createElement(key + "," + arrs[i]);
					em.addElement(element, GameElement.MAPS);
					em.addMapElement(element, key);	//����ͼ�������MapElement
				}				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @˵�� ����ͼƬ����
	 * ����ͼƬ  �����ͼƬ֮���һ��·��
	 */
	public static void loadImg() {//���Դ���������Ϊ��ͬ�ؿ�Ҳ������Ҫ��һ����ͼƬ��Դ
		String textUrl = "com/tedu/text/GameData.pro";//�ļ����������Ը����й���
		ClassLoader classLoader = GameLoad.class.getClassLoader();
		InputStream texts = classLoader.getResourceAsStream(textUrl);
//		imgMap���ڴ������
		pro.clear();
		try {
			pro.load(texts);
			Set<Object> set = pro.keySet();
			for (Object obj : set) {
				String url = pro.getProperty(obj.toString());
				imgMap.put(obj.toString(), new ImageIcon(url));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @˵�� ������Һ���
	 */
	public static void loadPlay() {
		// ���ȼ���Obj
		loadObj();
		String playStr = "425,525,up";//Ӧ�������ļ�
		ElementObj obj = getObj("play");		
		ElementObj play = obj.createElement(playStr);
//		 ������ʹ���ʹ���֮������
		em.addElement(play, GameElement.PLAY);
	}
	
	/**
	 * @˵�� ���ػ��غ���
	 */
	public static void loadBase() {
		//���ȼ���obj
		loadObj();
		String baseStr = "360,525,base";
		ElementObj obj = getObj("base");
		ElementObj base = obj.createElement(baseStr);
		em.addElement(base, GameElement.BASE);
	}
	
	/**
	 * @˵�� ����enemy����
	 * 
	 */
	public static void loadEnemyData(int gk) {	//�ɴ���������ͬ�ؿ�������Ҫ��ͬͼƬ
		String texturl = "com/tedu/text/EnemyData/EnemyData" + Integer.toString(gk) + ".pro";
		ClassLoader classLoader = GameLoad.class.getClassLoader();
		InputStream texts = classLoader.getResourceAsStream(texturl);
		pro.clear();
		try {
			pro.load(texts);
			Set<Object> set = pro.keySet();
			for(Object o : set) {
				String data = pro.getProperty(o.toString());
				EnemyMap.put(o.toString(), data);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void loadEnemy(long gameTime) {
		loadObj();
//		String enemyStr1 = "300,300,bot_up";//Ӧ�������ļ�	
		for(String key: GameLoad.EnemyMap.keySet()) {
			if(Long.parseLong(key) == gameTime) {
				String enemyStr= GameLoad.EnemyMap.get(key);
				ElementObj obj = getObj(enemyStr.split(",")[3]);
				ElementObj enemy = obj.createElement(enemyStr);
//				 ������ʹ���ʹ���֮������
				em.addElement(enemy, GameElement.ENEMY);
			}
		}
	}
	
	/**
	 * ����key����Ԫ��ʵ������
	 * @param str �����ļ��ж�Ӧ��key
	 * @return ����һ��Ԫ�ض���ʵ��
	 */
	public static ElementObj getObj(String str) {
//		loadObj();
		Class<?> class1 = objMap.get(str);		
		try {
			Object newInstance = class1.newInstance();
			if (newInstance instanceof ElementObj) {
				return (ElementObj)newInstance; //��������new Play()�ȼ�
			}
			
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//���ڴ��Ԫ��
	private static Map<String, Class<?>> objMap = new HashMap<>();
	
	/**
	 * ��չ��ʹ�������ļ�����ʵ��������  ͨ���̶���key���ַ�����ʵ������
	 */
	public static void loadObj() {
		String textUrl = "com/tedu/text/obj.pro";//�ļ����������Ը����й���
		ClassLoader classLoader = GameLoad.class.getClassLoader();
		InputStream texts = classLoader.getResourceAsStream(textUrl);
		pro.clear();
		try {
			pro.load(texts);
			Set<Object> set = pro.keySet();
			for (Object obj : set) {
				String classUrl = pro.getProperty(obj.toString());
//				ʹ�÷���ķ�ʽֱ�ӽ�����л�ȡ
				Class<?> forName = Class.forName(classUrl);
				objMap.put(obj.toString(), forName);
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @˵�� �˺������ڼ�����Ϸ��ʼҳ������Ҫ��ͼƬ
	 * 		���磺����ͼƬ�������ťͼƬ...
	 */
	public static void loadGameImg() {
		String loadGameProUrl = "com/tedu/text/loadGamePro.pro";//�ļ����������Ը����й���
		ClassLoader classLoader = GameLoad.class.getClassLoader();
		InputStream texts = classLoader.getResourceAsStream(loadGameProUrl);
		pro.clear();
		try {
			pro.load(texts);
			Set<Object> set = pro.keySet();
			for (Object obj : set) {
				String url = pro.getProperty(obj.toString());
				loadGameImgMap.put(obj.toString(), new ImageIcon(url));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @˵�� �˺������ڼ�����Ϸ����Ҫ������
	 */
	public static void loadMusic() {
		String loadMusicUrl = "com/tedu/text/music.pro";
		ClassLoader classLoader = GameLoad.class.getClassLoader();
		InputStream texts = classLoader.getResourceAsStream(loadMusicUrl);
		pro.clear();
		try {
			pro.load(texts);
			Set<Object> set = pro.keySet();
			for (Object obj : set) {
				String url = pro.getProperty(obj.toString());
				musicMap.put(obj.toString(), new File(url));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
