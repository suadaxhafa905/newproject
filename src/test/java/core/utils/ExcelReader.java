package core.utils;

import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

public class ExcelReader {

    private static final Logger logger =
            LoggerHelper.getLogger(ExcelReader.class);

    public static Map<String, String> getTestData(
            String filePath,
            String sheetName,
            String testCase
    ) {

        logger.info(
                "Reading test data from Excel. File: {}, Sheet: {}, TestCase: {}",
                filePath,
                sheetName,
                testCase
        );

        try (
                FileInputStream fis =
                        new FileInputStream(filePath);

                Workbook workbook =
                        WorkbookFactory.create(fis)
        ) {

            Sheet sheet =
                    workbook.getSheet(sheetName);

            if (sheet == null) {
                throw new RuntimeException(
                        "Sheet not found in Excel: " + sheetName
                );
            }

            Row headerRow =
                    sheet.getRow(0);

            if (headerRow == null) {
                throw new RuntimeException(
                        "Header row is missing in sheet: " + sheetName
                );
            }

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {

                Row currentRow =
                        sheet.getRow(i);

                if (currentRow == null ||
                        currentRow.getCell(0) == null) {
                    continue;
                }

                String currentTestCase =
                        getCellValue(currentRow.getCell(0));

                if (currentTestCase.equalsIgnoreCase(testCase)) {

                    Map<String, String> data =
                            new HashMap<>();

                    for (int j = 0; j < headerRow.getLastCellNum(); j++) {

                        Cell headerCell =
                                headerRow.getCell(j);

                        if (headerCell == null) {
                            continue;
                        }

                        String key =
                                getCellValue(headerCell).trim();

                        String value =
                                "";

                        if (currentRow.getCell(j) != null) {
                            value =
                                    getCellValue(
                                            currentRow.getCell(j)
                                    ).trim();
                        }

                        data.put(key, value);
                    }

                    logger.info(
                            "Test data loaded successfully for: {}",
                            testCase
                    );

                    return data;
                }
            }

            throw new RuntimeException(
                    "Test case not found in Excel: " + testCase
            );

        } catch (Exception e) {

            logger.error(
                    "Failed to read Excel test data for testCase: {}",
                    testCase,
                    e
            );

            throw new RuntimeException(
                    "Failed to read Excel test data",
                    e
            );
        }
    }

    private static String getCellValue(Cell cell) {

        DataFormatter formatter =
                new DataFormatter();

        return formatter.formatCellValue(cell);
    }
}