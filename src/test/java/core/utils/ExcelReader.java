package core.utils;

import org.apache.poi.ss.usermodel.*;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

public class ExcelReader {

    public static Map<String, String> getTestData(
            String filePath,
            String sheetName,
            String testCase
    ) {
        try {
            FileInputStream fis = new FileInputStream(filePath);
            Workbook workbook = WorkbookFactory.create(fis);
            Sheet sheet = workbook.getSheet(sheetName);

            Row headerRow = sheet.getRow(0);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row currentRow = sheet.getRow(i);

                if (currentRow.getCell(0).getStringCellValue()
                        .equalsIgnoreCase(testCase)) {

                    Map<String, String> data = new HashMap<>();

                    for (int j = 0; j < headerRow.getLastCellNum(); j++) {
                        data.put(
                                headerRow.getCell(j).getStringCellValue(),
                                currentRow.getCell(j).toString()
                        );
                    }

                    workbook.close();
                    fis.close();

                    return data;
                }
            }

            workbook.close();
            fis.close();

            throw new RuntimeException(
                    "Test case not found in Excel: " + testCase
            );

        } catch (Exception e) {
            throw new RuntimeException("Failed to read Excel test data", e);
        }
    }
}