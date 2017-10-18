package com.meession.market.common.util;

import java.io.File;
import java.io.InputStream;
import java.text.DecimalFormat;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * @author sam
 */
public class ExcelReader {

	private Workbook workbook;

	public ExcelReader(File file) throws Exception {
		super();
		this.workbook = WorkbookFactory.create(file);
	}

	public ExcelReader(InputStream inputStream) throws Exception {
		super();
		this.workbook = WorkbookFactory.create(inputStream);
	}

	
	public String[][] readExcel(int sheetIndex, int offsetX, int offsetY)
			throws Exception {
		String[][] result = null;

		Sheet sheet = workbook.getSheetAt(sheetIndex);

		int countX = sheet.getRow(0).getLastCellNum() - offsetX;
		int countY = sheet.getLastRowNum() + 1 - offsetY;
		result = new String[countY][countX];
		for (int y = 0; y < countY; y++) {
			Row row = sheet.getRow(y + offsetY);
			for (int x = 0; x < countX; x++) {
				String content = null;
				Cell cell = row.getCell(x + offsetX, Row.CREATE_NULL_AS_BLANK);
				switch (cell.getCellType()) {
				case Cell.CELL_TYPE_STRING:
					content = cell.getRichStringCellValue().getString();
					break;
				case Cell.CELL_TYPE_NUMERIC:
					DecimalFormat df = new DecimalFormat("#0.##");
					content = df.format(cell.getNumericCellValue());
					break;
				default:
					break;
				}
				result[y][x] = content;
			}
		}
		return result;
	}

}
