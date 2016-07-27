package com.jd.lobo;

import java.util.ResourceBundle;

import javax.servlet.ServletContextEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppContext {

	private static final Logger LOGGER = LoggerFactory.getLogger(AppContext.class);
	
	private static final AppContext singleton = new AppContext();
	
	private AppContext() { }
	
	public static AppContext getInstance() {
		return singleton;
	}
	
	private ResourceBundle appConfig;
	
	private String rootDir;
	
	public void init(ServletContextEvent e) {
		
		if (e != null) {
			rootDir = e.getServletContext().getRealPath("/");
			LOGGER.info("根目录为: {}", rootDir);
		}
		
		// 读取app.properties配置
		reloadAppConf();
	}
	
	public void destroy(ServletContextEvent e) {	
		LOGGER.info("site-web 已关闭");
	}
	
	public synchronized void reloadAppConf() {
		if (appConfig != null) {
			ResourceBundle.clearCache();
		}
		
		appConfig = ResourceBundle.getBundle("app");
		
		LOGGER.info("已重新加载应用配置 {}", appConfig);
	}
	
	public String getRootDir() {
		return rootDir;
	}
	
	public String getSetting(String key) {
		
		if ("site.static.root".equals(key)) {
			return appConfig.getString("site.static.root");
		}
		
		if ("site".equals(key)) {
			return appConfig.getString("site");
		}
		
		return null;
	}
}
