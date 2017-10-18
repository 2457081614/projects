package com.meession.market.common.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

/**
 * @author sam
 */
public class FreeMarkerHelper {

	private Configuration templateCfg;

	public FreeMarkerHelper(String templatePath) throws Exception {
		super();
		getTemplateCfg().setDirectoryForTemplateLoading(new File(templatePath));
	}

	public FreeMarkerHelper(String templateName, String templateSource)
			throws Exception {
		super();
		StringTemplateLoader templateLoader = new StringTemplateLoader();
		templateLoader.putTemplate(templateName, templateSource);
		getTemplateCfg().setTemplateLoader(templateLoader);
	}

	public FreeMarkerHelper(Map<String, String> templateMap) throws Exception {
		super();
		StringTemplateLoader templateLoader = new StringTemplateLoader();
		for (Map.Entry<String, String> template : templateMap.entrySet()) {
			templateLoader.putTemplate(template.getKey(), template.getValue());
		}
		getTemplateCfg().setTemplateLoader(templateLoader);
	}

	private Configuration getTemplateCfg() throws Exception {
		if (templateCfg == null) {
			templateCfg = new Configuration();
			templateCfg.setObjectWrapper(new DefaultObjectWrapper());
		}
		return templateCfg;
	}

	private void checkPath(String targetFileName) {
		File targetFile = new File(targetFileName);
		String path = targetFile.getParent();
		File targetPath = new File(path);
		if (!targetPath.exists()) {
			targetPath.mkdirs();
		}
	}

	public void outputResult(String targetFileName, String templateFileName,
			Object dataModel) throws Exception {
		checkPath(targetFileName);
		Template template = getTemplateCfg().getTemplate(templateFileName);
		OutputStream out = new FileOutputStream(targetFileName);
		Writer writer = new OutputStreamWriter(out);
		try {
			template.process(dataModel, writer);
			writer.flush();
		} finally {
			writer.close();
		}
	}

	public String outputResultToString(String templateFileName, Object dataModel)
			throws Exception {
		Template template = getTemplateCfg().getTemplate(templateFileName);
		StringWriter result = new StringWriter();
		template.process(dataModel, result);
		return result.toString();
	}

	public static String process(String template, Object dataModel)
			throws Exception {
		FreeMarkerHelper freeMarkerHelper = new FreeMarkerHelper("template",
				template);
		return freeMarkerHelper.outputResultToString("template", dataModel);
	}

}