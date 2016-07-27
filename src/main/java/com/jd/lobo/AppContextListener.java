package com.jd.lobo;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AppContextListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent e) {
		AppContext.getInstance().init(e);
	}

	@Override
	public void contextDestroyed(ServletContextEvent e) {
		AppContext.getInstance().destroy(e);
	}
}
