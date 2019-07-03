package game;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;



import java.applet.AudioClip;

import java.io.*;

import java.applet.Applet;

import java.awt.Frame;

import java.net.MalformedURLException;

import java.net.URL;

/*
 * 用于播放BGM和其他音频，12.29初稿，事件发生（如僵尸被击中等）声音未完成，用todo标记
 * 
 */


public class SoundAndMusic {
	private String file_path;
	private int state;//1没开始 2正在播放 
	
	private String sound_path;

	Thread a;
	
	
	public SoundAndMusic(String file_path)
	{
		this.file_path=file_path;
		state=1;
		sound_path=file_path;

	}
	public void StartPlay_BGM() {
		if (state!=1) return;
		state=2;

		//音频API部分，下载自CSDN音乐播放教程,但是还有问题，只能播放一次！
		//----------------------播放音频API  以下-------------------------------------
		a=new Thread(new Runnable()
		{
			public void run()
			{
				
				while(state==2) {
				try
				{
					File file = new File(file_path);
					AudioInputStream stream = AudioSystem
							.getAudioInputStream(file);
					AudioFormat format = stream.getFormat();
					DataLine.Info info = new DataLine.Info(
							SourceDataLine.class, format);
					SourceDataLine line = (SourceDataLine) AudioSystem
							.getLine(info);
					byte[] buf = new byte[512 * 1024];
					line.open();
					line.start();
					int nbytes = 0;
					while (nbytes != -1)
					{
						if(  state==3  )   break;
						nbytes = stream.read(buf, 0, buf.length);
						if (nbytes >= 0)
							line.write(buf, 0, nbytes);
					}
					line.drain();
					line.close();
				}
				catch (UnsupportedAudioFileException e)
				{
					e.printStackTrace();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
				catch (LineUnavailableException e)
				{
					e.printStackTrace();
				}
			
				if (state==3)break;}}
		});
		a.start();
				

		//----------------------播放音频API  以上-------------------------------------
	
	}
	public void StopPlay_BGM() {
		state=3;
		a.stop();
	}
	
	public void playSound(String sound_path) {
		this.sound_path=sound_path;

		new Thread(new Runnable()
		{
			public void run()
			{
				try
				{
					File file = new File(sound_path);
					AudioInputStream stream = AudioSystem
							.getAudioInputStream(file);
					AudioFormat format = stream.getFormat();
					DataLine.Info info = new DataLine.Info(
							SourceDataLine.class, format);
					SourceDataLine line = (SourceDataLine) AudioSystem
							.getLine(info);
					byte[] buf = new byte[512 * 1024];
					line.open();
					line.start();
					int nbytes = 0;
					while (nbytes != -1)
					{
						if(  state==3  )   break;
						nbytes = stream.read(buf, 0, buf.length);
						if (nbytes >= 0)
							line.write(buf, 0, nbytes);
					}
					line.drain();
					line.close();
				}
				catch (UnsupportedAudioFileException e)
				{
					e.printStackTrace();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
				catch (LineUnavailableException e)
				{
					e.printStackTrace();
				}
			}
		}).start();



		    
         //事件发生播放音频,下载自CSDN,具体原理我也不知道 恰好能实现一次播放
	}
	

}
