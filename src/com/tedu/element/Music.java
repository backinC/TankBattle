package com.tedu.element;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import com.tedu.manager.GameLoad;

/**
 * @˵�� �����࣬����ʱ���봫���Ӧ��������
 * @author HP
 * @���� �ṩ���������ֲ��š�ѭ�����š�ֹͣ���ŷ���
 *
 */
public class Music {

	private String name;
	private AudioClip aau;
	
	public Music(String name){
		this.name = name;
		init();
	}	

	private void init() {
		File file = GameLoad.musicMap.get(name);
		URI uri = file.toURI();
		try {
			URL url = uri.toURL();
			aau = Applet.newAudioClip(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @˵�� ���ֿ�ʼ����
	 */
	public void play() {
		this.aau.play();
	}
	
	/**
	 * @˵�� ���ֿ�ʼѭ������
	 */
	public void loop() {
		this.aau.loop();
	}
	
	/**
	 * @˵�� ����ֹͣ����
	 */
	public void stop() {
		this.aau.stop();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public AudioClip getAau() {
		return aau;
	}		
}
