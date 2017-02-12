package com.wxweven.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author wxweven
 * @version 1.0
 * @date 2016年4月19日
 * @email wxweven@qq.com
 * @website http://wxweven.com/
 * @Copyright: Copyright (c) wxweven 2016
 * @Description: 读取Excel
 */
public class WxwevenExcelReader {

	private static Log logger = LogFactory.getLog(WxwevenExcelReader.class);

	/**
	 * 根据excel文件的路径，以及excel表头，得到excel内容的List
	 *
	 * @param filePath
	 *            excel文件的路径
	 * @param excelTitle
	 *            excel表头
	 * @return
	 */
	public static List<Map<String, String>> getExcelList(String filePath, List<String> excelTitle) {
		//默认第一个数据行为第二行
		return getExcelList(filePath, excelTitle, 2);
	}




	/**
	 * 根据excel文件的路径，已经excel表头，得到excel内容的List
	 *
	 * @param filePath
	 *            excel文件的路径
	 * @param excelTitle
	 *            excel表头
	 * @param firstDataRow
	 *            第一个数据行的行数
	 * @return
	 */
	public static List<Map<String, String>> getExcelList(String filePath, List<String> excelTitle,
			int firstDataRow) {

		List<Map<String, String>> resultList = new ArrayList<Map<String, String>>();// 最终返回的结果集

		FileInputStream fis = null;// 文件输入流
		Workbook workbook = null;// 03版工作薄
		String cellKey = null;// 单元格的键
		String cellValue = null;// 单元格的值

		DecimalFormat df = new DecimalFormat("#.########");//保留7位小数

		try {

			fis = new FileInputStream(filePath);// 文件输入流
			if(filePath.endsWith("xls")) {
				//03版excel
				workbook = new HSSFWorkbook(fis);// 创建工作簿
			} else {
				//07版excel
				workbook = new XSSFWorkbook(fis);// 创建工作簿
			}

			/** poi的excel操作，下标都是从0开始 */
			Sheet sheet = workbook.getSheetAt(0); // 工作表,0表示一个表
			Row row = null; // excel的行
			Cell cell = null; // excel的列
			int totalRow = sheet.getLastRowNum(); // excel最后一行的下标
			logger.debug("totalRow:" + totalRow);

			if(firstDataRow <= 0) {
				workbook.close();
				throw new IndexOutOfBoundsException("数据行的下标，必须大于0");
			}

			// 遍历excel的数据行，如果数据行从第二行开始，则下标是1
			for (int rowIndex = firstDataRow - 1; rowIndex <= totalRow; rowIndex++) {
				row = sheet.getRow(rowIndex); // 得到当前行
				Map<String, String> rowMap = new HashMap<String, String>();// 当前行的结果集Map

				// 遍历excel当前行的列
				for (int colIndex = 0; colIndex < excelTitle.size(); colIndex++) {
					cell = row.getCell(colIndex);
					cellKey = excelTitle.get(colIndex);

					if (cellKey.equals("date")) {
						// 日期格式单独处理
						cellValue = getDateCellValue(cell, df);
					} else {
						cellValue = getStringCellValue(cell, df);
					}

					rowMap.put(cellKey, cellValue);// 讲当前单元格的内容，存入rowMap
				}

				resultList.add(rowMap);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return resultList;
	}

	/**
	 * 获取单元格数据内容为字符串类型的数据
	 *
	 * @param cell
	 *            Excel单元格
	 * @return String 单元格数据内容
	 */
	private static String getStringCellValue(Cell cell, DecimalFormat df) {
		if (cell == null) {
			return "";
		}

		String strCell = "";
		switch (cell.getCellType()) {
		case HSSFCell.CELL_TYPE_STRING:
			strCell = cell.getStringCellValue();
			break;
		case HSSFCell.CELL_TYPE_NUMERIC:
			strCell = String.valueOf(df.format(cell.getNumericCellValue()));
			break;
		case HSSFCell.CELL_TYPE_BOOLEAN:
			strCell = String.valueOf(cell.getBooleanCellValue());
			break;
		case HSSFCell.CELL_TYPE_FORMULA:
			cell.setCellType(Cell.CELL_TYPE_NUMERIC); //设置其单元格类型为数字
			strCell = String.valueOf(df.format(cell.getNumericCellValue()));//获取数字值
			break;
		case HSSFCell.CELL_TYPE_BLANK:
			strCell = "";
			break;
		default:
			strCell = "";
			break;
		}

		if (strCell.equals("") || strCell == null) {
			return "";
		}

		return strCell;
	}

	/**
	 * 获取单元格数据内容为日期类型的数据
	 *
	 * @param cell
	 *            Excel单元格
	 * @return String 单元格数据内容
	 */
	private static String getDateCellValue(Cell cell, DecimalFormat df) {
		String result = "";
		try {
			int cellType = cell.getCellType();
			if (cellType == HSSFCell.CELL_TYPE_NUMERIC) {
				Date date = cell.getDateCellValue();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				result = sdf.format(date);

			} else if (cellType == HSSFCell.CELL_TYPE_STRING) {
				String date = getStringCellValue(cell, df);
				result = date.replaceAll("[年月]", "-").replace("日", "").trim();
			} else if (cellType == HSSFCell.CELL_TYPE_BLANK) {
				result = "";
			}
		} catch (Exception e) {
			logger.error("日期格式不正确!");
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 获取excelReslist中，key对应的那一列的值的集合set
	 */
	public static Set<String> getExcelColumntSet(List<Map<String, String>> excelReslist, String key) {

		if(excelReslist == null || excelReslist.size() == 0) {
			return Collections.emptySet();
		}

		Set<String> columnSet = new HashSet<>();
		for(Map<String, String> map : excelReslist) {
			String value = map.get(key);
			columnSet.add(value);
		}

		return columnSet;
	}
}
