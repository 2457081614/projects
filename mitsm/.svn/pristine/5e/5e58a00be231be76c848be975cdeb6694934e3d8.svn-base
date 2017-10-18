package com.meession.market.common.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.xhtmlrenderer.pdf.ITextRenderer;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;

/**
 * @author sam
 */
public abstract class PDFUtils {

	/**
	 * Generate PDF from HTML
	 * 
	 * @throws IOException
	 * @throws DocumentException
	 */
	public static void generatePDF(String htmlFile, String outputFile)
			throws IOException, DocumentException {
		FileOutputStream os = new FileOutputStream(outputFile);
		ITextRenderer renderer = new ITextRenderer();
		setFont(renderer);
		renderer.setDocument(new File(htmlFile));
		renderer.layout();
		renderer.createPDF(os);
		os.close();
	}

	public static InputStream generatePDF(String htmlContent)
			throws DocumentException, IOException {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		ITextRenderer renderer = new ITextRenderer();
		setFont(renderer);
		renderer.setDocumentFromString(htmlContent);
		renderer.layout();
		renderer.createPDF(os);
		os.close();

		return new ByteArrayInputStream(os.toByteArray());
	}

	public static InputStream generateMultiplePagesPDF(String... htmlContents)
			throws DocumentException, IOException {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		ITextRenderer renderer = new ITextRenderer();
		setFont(renderer);
		renderer.setDocumentFromString(htmlContents[0]);
		renderer.layout();
		renderer.createPDF(os, false);
		for (int i = 1; i < htmlContents.length; i++) {
			renderer.setDocumentFromString(htmlContents[i]);
			renderer.layout();
			renderer.writeNextDocument();
		}
		renderer.finishPDF();
		os.close();

		return new ByteArrayInputStream(os.toByteArray());
	}

	private static void setFont(ITextRenderer renderer)
			throws DocumentException, IOException {
		renderer.getFontResolver().addFont(
				PDFUtils.class.getResource("/../../fonts/ARIALUNI.TTF")
						.toString(), BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
		renderer.getFontResolver().addFont(
				PDFUtils.class.getResource("/../../fonts/icomoon.ttf")
						.toString(), BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
	}

}
