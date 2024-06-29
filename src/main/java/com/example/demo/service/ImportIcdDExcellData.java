package com.example.demo.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

@Service
public class ImportIcdDExcellData {
    String excelFilePath = "/home/austine/Downloads/Kenya_Counties_Subcounties_Wards.xlsx";

	
    public void printValuesFromexcell() {
        try (FileInputStream fileInputStream = new FileInputStream(new File(excelFilePath));
             Workbook workbook = new XSSFWorkbook(fileInputStream)) {

            Sheet sheet = workbook.getSheetAt(0); // Get the first sheet

            // Iterate over rows, starting from the second row (index 1)
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row != null) {
                    getNonHeadingRows(row);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Could not extract the data from the Excel file.", e);
        }
    }
	
	private void getNonHeadingRows(Row row) {

		Cell firstCell = row.getCell(0, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);

		if (firstCell != null) {
			
 
			String firstColumn = "";
			String secondColumn = "";
			String thirdColumn = "";
			

			for (int i = 0; i <= 6; i++) {
				Cell cell = row.getCell(i, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
				if (cell != null) {
					switch (cell.getCellType()) {
					case STRING:

						// Assign values to variables based on column index
						switch (i) {

						case 1:
							secondColumn = cell.getStringCellValue();
							break;
						case 2:
							thirdColumn = cell.getStringCellValue();
							break;
						}
						break;
					case NUMERIC:
						
						if (i == 0) {
							firstColumn = String.valueOf(cell.getNumericCellValue());
						}
						
						break;
					default:
						break;
					}
				}
			}

			System.out.println("Short ID = " + firstColumn);
			System.out.println("Title = " + secondColumn);
			System.out.println("Summary = " + thirdColumn);
			System.out.println("\n================================================\n");
			

		}
		}
	

}
