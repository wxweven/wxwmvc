package com.wxweven.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Font;
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
 * @Description: 生成Excel
 */
public class WxwevenExcelWriter {
	/**
	 * 根据表头，对应的键值，以及excel数据，生成excel输入流
	 *
	 * @param dataHead
	 *            表头
	 * @param key
	 *            键值
	 * @param dataList
	 *            excel数据list，内部是map
	 * @return
	 */
	public static InputStream getExcelStream(List<String> dataHead,
			List<String> key, List<Map<String, String>> dataList) {
		int dataHeadSize = dataHead.size();
		int keySize = key.size();

		// if(dataHeadSize != keySize) {
		// throw new Exception("表头dataHead 和 key 长度不一致，请检查");
		// }

		// 创建Excel的工作书册 Workbook,对应到一个excel文档
		Workbook wb = new XSSFWorkbook();
		Sheet sheet = wb.createSheet("sheet1");// 创建Excel的工作sheet,对应到一个excel文档的tab
		Row row = null;// excel中的行
		Cell cell = null;// excel行中的单元格

		Font font = null;// 字体样式
		font = wb.createFont();
		font.setFontName("微软雅黑");
		font.setBoldweight((short) 100);
		font.setFontHeight((short) 300);

		// 创建excel的表头
		row = sheet.createRow(0);// 创建表头行
		row.setHeight((short) 500);// 设定行的高度
		for (int cellIndex = 0; cellIndex < dataHeadSize; cellIndex++) {
			// 循环创建列
			cell = row.createCell(cellIndex);// 创建一个Excel的单元格
			cell.setCellValue(dataHead.get(cellIndex));
		}

		// 循环创建Excel的sheet的数据行
		for (int rowindex = 1; rowindex <= dataList.size(); rowindex++) {
			row = sheet.createRow(rowindex);// 创建行
			row.setHeight((short) 500);// 设定行的高度

			Map<String, String> rowData = dataList.get(rowindex - 1);// 拿到当前行对应的数据map
			for (int cellIndex = 0; cellIndex < keySize; cellIndex++) {
				// 循环创建列
				cell = row.createCell(cellIndex);// 创建一个Excel的单元格
				String cellKey = key.get(cellIndex);
				Object cellValue = rowData.get(cellKey);

				// if (cellKey.equals("fenChengStartTime")
				// || cellKey.equals("fenChengEndTime")
				// || cellKey.equals("contractSignTime")
				// || cellKey.equals("calcuTime")) {
				//
				// if(cellValue instanceof Timestamp) {
				// cellValue = DateUtil.getDateString((Timestamp)cellValue);
				// }
				//
				// System.out.println("time:" + cellValue);
				// }

				if (cellValue == null) {
					cell.setCellValue("");
				}
				cell.setCellValue(cellValue + "");

			}
		}

		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		try {
			wb.write(baos);
			wb.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return new ByteArrayInputStream(baos.toByteArray());
	}
}