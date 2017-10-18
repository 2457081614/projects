package com.meession.market.common.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 * @author sam
 */
public class ExcelViewUtils {

	public class ExcelSheet {
		private List<ExcelRow> rowList;
		private List<CellRangeAddress> mergedRegions;

		private boolean displayGuts;
		private boolean fitToPage;
		private boolean autobreaks;

		public List<ExcelRow> getRowList() {
			return rowList;
		}

		public void setRowList(List<ExcelRow> rowList) {
			this.rowList = rowList;
		}

		public List<CellRangeAddress> getMergedRegions() {
			return mergedRegions;
		}

		public void setMergedRegions(List<CellRangeAddress> mergedRegions) {
			this.mergedRegions = mergedRegions;
		}

		public boolean getDisplayGuts() {
			return displayGuts;
		}

		public void setDisplayGuts(boolean displayGuts) {
			this.displayGuts = displayGuts;
		}

		public boolean getFitToPage() {
			return fitToPage;
		}

		public void setFitToPage(boolean fitToPage) {
			this.fitToPage = fitToPage;
		}

		public boolean getAutobreaks() {
			return autobreaks;
		}

		public void setAutobreaks(boolean autobreaks) {
			this.autobreaks = autobreaks;
		}

	}

	public class ExcelRow {
		private List<ExcelCell> cellList;
		private short height;

		public List<ExcelCell> getCellList() {
			return cellList;
		}

		public void setCellList(List<ExcelCell> cellList) {
			this.cellList = cellList;
		}

		public short getHeight() {
			return height;
		}

		public void setHeight(short height) {
			this.height = height;
		}

	}

	public class ExcelCell {
		private String cellValue;
		private int width;
		private CellStyle cellStyle;

		public String getCellValue() {
			return cellValue;
		}

		public void setCellValue(String cellValue) {
			this.cellValue = cellValue;
		}

		@Override
		public String toString() {
			return this.cellValue;
		}

		public int getWidth() {
			return width;
		}

		public void setWidth(int width) {
			this.width = width;
		}

		public CellStyle getCellStyle() {
			return cellStyle;
		}

		public void setCellStyle(CellStyle cellStyle) {
			this.cellStyle = cellStyle;
		}
	}

	public InputStream createExcel(Workbook wb, ExcelSheet excelSheet)
			throws Exception {
		Sheet sheet = wb.getSheetAt(0);
		List<ExcelRow> rowList = excelSheet.getRowList();
		int rowSize = rowList.size();
		for (int r = 0; r < rowSize; r++) {
			ExcelRow excelRow = rowList.get(r);
			Row row = sheet.createRow(r);
			row.setHeight(excelRow.getHeight());
			List<ExcelCell> cellList = excelRow.getCellList();
			int cellSize = cellList.size();
			for (int c = 0; c < cellSize; c++) {
				ExcelCell excelCell = cellList.get(c);
				if (r == 0) {
					sheet.setColumnWidth(c, excelCell.getWidth());
				}
				Cell cell = row.createCell(c);
				cell.setCellValue(excelCell.getCellValue());
				cell.setCellStyle(excelCell.getCellStyle());
			}
		}

		for (CellRangeAddress region : excelSheet.getMergedRegions()) {
			sheet.addMergedRegion(region);
		}
		wb.removeSheetAt(1);
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		wb.write(os);
		os.close();
		byte[] ba = os.toByteArray();

		return new ByteArrayInputStream(ba);
	}

	@SuppressWarnings("unchecked")
	public ExcelSheet generateExcelSheet(Sheet sheet,
			Map<String, Object> dataModel) throws Exception {
		ExcelSheet excelSheet = new ExcelSheet();

		boolean skipRow = false;
		boolean repeatRow = false;
		int repeatIndex = 0;
		int listIndex = 0;
		Map<String, Object> listDataModel = null;
		String listName = "";
		String itemName = "";
		List<Object> list = null;
		Object item = null;
		Map<Integer, List<CellRangeAddress>> mergedRegionMap = new HashMap<Integer, List<CellRangeAddress>>();

		int rowSize = sheet.getLastRowNum() + 1;
		int cellSize = 0;

		List<ExcelRow> rowList = new ArrayList<ExcelRow>();
		row: for (int r = 0; r < rowSize; r++) {
			Row row = sheet.getRow(r);
			ExcelRow excelRow = new ExcelRow();
			excelRow.setHeight(row.getHeight());

			cellSize = row.getLastCellNum();
			List<ExcelCell> cellList = new ArrayList<ExcelCell>();
			for (int c = 0; c < cellSize; c++) {
				Cell cell = row.getCell(c);
				ExcelCell excelCell = new ExcelCell();
				if (r == 0) {
					excelCell.setWidth(sheet.getColumnWidth(cell
							.getColumnIndex()));
				}

				CellStyle cellStyle = cell.getCellStyle();
				excelCell.setCellStyle(cellStyle);

				switch (cell.getCellType()) {
				case Cell.CELL_TYPE_STRING:
					String value = cell.getRichStringCellValue().getString();
					Pattern p = Pattern
							.compile("<#list (\\w+(\\.*\\w+)*) as (\\w+)>");
					Matcher m = p.matcher(value);
					if (m.matches()) {
						listName = m.group(1);
						itemName = m.group(3);
						list = (List<Object>) dataModel.get(listName);
						if (list != null && !list.isEmpty()) {
							repeatRow = true;
							item = list.get(listIndex);
							listDataModel = new HashMap<String, Object>();
							listDataModel.put(itemName, item);
							listDataModel.put(itemName + "_index", listIndex);
						} else {
							skipRow = true;
						}
						continue row;
					} else if ("</#list>".equals(value)) {
						skipRow = false;
						if (repeatRow) {
							listIndex++;
							if (listIndex < list.size()) {
								item = list.get(listIndex);
								listDataModel.put(itemName, item);
								listDataModel.put(itemName + "_index",
										listIndex);
								r = r - repeatIndex - 1;
								repeatIndex = 0;
							} else {
								repeatRow = false;
								repeatIndex = 0;
								listIndex = 0;
							}
						}
						continue row;
					} else {
						if (skipRow) {
							continue row;
						}
						String newText;
						if (repeatRow) {
							listDataModel.putAll(dataModel);
							newText = FreeMarkerHelper.process(value,
									listDataModel);
						} else {
							newText = FreeMarkerHelper
									.process(value, dataModel);
						}
						excelCell.setCellValue(newText);
						cellList.add(excelCell);
					}
					break;
				case Cell.CELL_TYPE_BLANK:
					excelCell.setCellValue(null);
					cellList.add(excelCell);
					break;
				default:
					break;
				}
			}
			if (repeatRow) {
				repeatIndex++;
			}
			excelRow.setCellList(cellList);

			List<CellRangeAddress> regionList = new ArrayList<CellRangeAddress>();
			mergedRegionMap.put(rowList.size(), regionList);
			for (int i = 0; i < sheet.getNumMergedRegions(); i++) {
				CellRangeAddress region = sheet.getMergedRegion(i);
				if (region.getFirstRow() == r && !regionList.contains(region)) {
					regionList.add(region);
				}
			}
			rowList.add(excelRow);
		}
		excelSheet.setRowList(rowList);

		List<CellRangeAddress> mergedRegions = new ArrayList<CellRangeAddress>();
		for (Integer newFirstRow : mergedRegionMap.keySet()) {
			List<CellRangeAddress> cellRangeAddressList = mergedRegionMap
					.get(newFirstRow);
			for (CellRangeAddress cellRangeAddress : cellRangeAddressList) {
				int newLastRow = cellRangeAddress.getLastRow() + newFirstRow
						- cellRangeAddress.getFirstRow();
				CellRangeAddress ncellRangeAddress = new CellRangeAddress(
						newFirstRow, newLastRow,
						cellRangeAddress.getFirstColumn(),
						cellRangeAddress.getLastColumn());
				mergedRegions.add(ncellRangeAddress);
			}
		}
		excelSheet.setMergedRegions(mergedRegions);
		return excelSheet;
	}

	public static InputStream convert(String templateFileName,
			Map<String, Object> dataModel) throws Exception {
		InputStream inp = new FileInputStream(templateFileName);
		Workbook wb = WorkbookFactory.create(inp);
		Sheet sheet = wb.getSheetAt(1);

		ExcelViewUtils excelViewUtils = new ExcelViewUtils();
		ExcelSheet excelSheet = excelViewUtils.generateExcelSheet(sheet,
				dataModel);

		return excelViewUtils.createExcel(wb, excelSheet);
	}

}
