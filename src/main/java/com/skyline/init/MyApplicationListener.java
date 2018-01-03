package com.skyline.init;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class MyApplicationListener implements ApplicationListener<ContextRefreshedEvent> {
	public MyApplicationListener() {
	}
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if(event.getApplicationContext().getParent() == null) {
//			do something.
		}
	}
}
