package com.secoder.base;

/**
 * @file ThreadCooperationSingal
 * @author sf
 * @date 2020/8/27 1:54 上午
 * @description
 */

public class ThreadCooperationSignal {

public static void main(String[] args) {
	// main 方法
	TV tv = new TV();
	new Player(tv).start();
	new Watcher(tv).start();
}
}

/**
 * 生产者 -- 演员
 */
class Player extends Thread {
TV tv;

public Player(TV tv) {
	this.tv = tv;
}

@Override
public void run() {
	for (int i = 0; i < 20; i++) {
		if(i % 2 == 0) {
			this.tv.play("抖音");
		} else {
			this.tv.play("快手");
		}
	}
}
}


/**
 * 消费者 -- 观众
 */
class Watcher extends Thread {
TV tv;

public Watcher(TV tv) {
	this.tv = tv;
}

@Override
public void run() {
	for (int i = 0; i < 20; i++) {
		this.tv.watch();
	}
}
}


/**
 * 产品 -- 节目
 * 演员表演，观众观看 true
 * 观众观看，演员等待 false
 */
class TV {
// 表演的节目
String voice;
// 信号标志位
boolean flag = true;

/**
 * 表演
 *
 * @param voice
 */
public synchronized void play(String voice) {
	if(!flag) {
		try {
			this.wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	System.out.println("表演了 " + voice);
	
	// 通知观众观看
	this.notifyAll();
	this.voice = voice;
	this.flag = !this.flag;
}

/**
 * 观看
 */
public synchronized void watch() {
	if(flag) {
		try {
			// 观众等待
			this.wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	System.out.println("观看了 " + voice);
	// 通知表演
	this.notifyAll();
	this.flag = !this.flag;
}
}

