package com.cloud.erp.utils;

import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class POIUtil {
	
	public static Workbook createWorkbook(){
		return new HSSFWorkbook();
	}
	
	public static Sheet createSheet(Workbook wb, String sheetname){
		return wb.createSheet(sheetname);
	}
	
	public static Row createRow(Sheet sheet, int rownum){
		return sheet.createRow(rownum);
	}

	public static Cell createCell(Workbook wb, Row row, int column, Object value) {
		DataFormat format = wb.createDataFormat();
		CellStyle cellStyle = wb.createCellStyle();
		Cell cell = row.createCell(column);
		if (value instanceof Date) {
			cellStyle.setDataFormat(format.getFormat("yyyy-MM-dd HH:mm:ss"));
			cell.setCellValue(value.toString());
			cell.setCellStyle(cellStyle);	
		} else if(value instanceof Number){
			cell.setCellType(Cell.CELL_TYPE_NUMERIC);
			cell.setCellValue(Double.parseDouble(value.toString()));
		} else if(value instanceof String){
			cell.setCellValue(value.toString());
		}
		return cell;
	}

}
