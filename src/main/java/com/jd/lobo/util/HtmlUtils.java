package com.jd.lobo.util;

import java.io.File;
import java.io.StringWriter;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.io.FileUtils;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.runtime.RuntimeConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jd.lobo.AppContext;

/**
 * html页面渲染工具.
 */

public final class HtmlUtils {

    private static String rootDir = null;

    private static ConcurrentHashMap<String, String> templates = new ConcurrentHashMap<>();

    private HtmlUtils() {
    }

    private static Logger LOGGER = LoggerFactory.getLogger(HtmlUtils.class); //日志系统

    static {
        try {
            // 获取web应用根目录的方法
            rootDir = AppContext.getInstance().getRootDir();

            Properties properties = new Properties();
            properties.setProperty(Velocity.INPUT_ENCODING, "UTF-8");
            properties.setProperty(Velocity.OUTPUT_ENCODING, "UTF-8");
            properties.setProperty(RuntimeConstants.RESOURCE_LOADER, "file");
            properties.setProperty("file.resource.loader.class",
                    "org.apache.velocity.runtime.resource.loader.FileResourceLoader");

            String templatrDir = rootDir + File.separator + "WEB-INF" + File.separator + "templates";
            properties.setProperty(RuntimeConstants.FILE_RESOURCE_LOADER_PATH, templatrDir);
            LOGGER.info("HtmlUtils初始化模板资源路径 {}", templatrDir);

            properties.setProperty(RuntimeConstants.EVENTHANDLER_INCLUDE,
                    "org.apache.velocity.app.event.implement.IncludeRelativePath");

            Velocity.init(properties);
        } catch (Exception e) {
            LOGGER.error("初始化Velocity异常，{}", e);
        }
    }

    public static String render(String templateBaseName,
                                Map<String, Object> variables, Locale locale) {
        String content = null;

        try {
            String templateContent = getTemplateContent(templateBaseName,
                    locale);

            VelocityContext context = new VelocityContext();
            if (variables != null) {
                for (Map.Entry<String, Object> entry : variables.entrySet()) {
                    context.put(entry.getKey(), entry.getValue());
                }
            }

            StringWriter writer = new StringWriter();
            Velocity.evaluate(context, writer, "VELOCITY", templateContent);
            content = writer.toString();
        } catch (Exception e) {
            LOGGER.error("渲染模板异常，{}", e);
        }

        return content;
    }

    private static String getTemplateContent(String templateBaseName,
                                             Locale locale) {
        String templateName = templateBaseName;

        boolean useCache = false;
        if (!useCache) {
            reloadTemplate(templateName);
        }

        if (locale != null) {
            String localizedTemplateName = String.format("%s_%s",
                    templateBaseName, locale.toString());
            if (templates.containsKey(localizedTemplateName)) {
                templateName = localizedTemplateName;
            }
        }

        return templates.get(templateName);
    }

    public static void reloadTemplate(String templateName) {
        if (rootDir == null) {
            LOGGER.error("根目录为空，无法获取模板");
            return;
        }

        String relativeDir = String.format("%sWEB-INF%stemplates%s", File.separator, File.separator, File.separator);
        String templateFileName = String.format("%s%s%s.html",
                rootDir, relativeDir, templateName);
        File file = new File(templateFileName);
        String name = file.getName();

        try {
            String templateContent = FileUtils.readFileToString(file, "utf-8");
            templates.put(templateName, templateContent);
            LOGGER.info("已加载模板'{}'", name);
        } catch (Exception e) {
            LOGGER.error("加载模板'{}'错误，异常信息{}", name, e);
        }
    }
}