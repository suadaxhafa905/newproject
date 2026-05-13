package core.utils;

import core.config.ConfigReader;
import core.context.ExecutionInfo;
import core.driver.DriverFactory;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.*;
import org.openqa.selenium.HasCapabilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WordBugReportManager {

    private static final String REPORT_PATH =
            "reports/BugReport.docx";
/*
    private static final List<String[]> bugs =
            new ArrayList<>();

 */

    private static final List<String[]> bugs =
            Collections.synchronizedList(new ArrayList<>());

    public static void logBug(
            String testCase,
            String step,
            String expected,
            String actual,
            String severity,
            String screenshotPath
    ) {
        bugs.add(new String[]{
                testCase,
                step,
                expected,
                actual,
                severity,
                screenshotPath
        });
    }

    public static void generateReport() {

        try {
            File reportsDir = new File("reports");
            reportsDir.mkdirs();

            XWPFDocument document = new XWPFDocument();

            addTitle(document);

            addEnvironmentInfo(document);

            if (bugs.isEmpty()) {

                XWPFParagraph noBug = document.createParagraph();
                noBug.createRun().setText(
                        "Nuk u identifikuan bugs gjatë ekzekutimit."
                );

            } else {

                for (int i = 0; i < bugs.size(); i++) {

                    addBugSection(document, bugs.get(i), i + 1);
                }
            }

            FileOutputStream out =
                    new FileOutputStream(REPORT_PATH);

            document.write(out);

            out.close();
            document.close();

        } catch (Exception e) {

            throw new RuntimeException(
                    "Failed to create Word bug report",
                    e
            );
        }
    }

    private static void addTitle(XWPFDocument document) {

        XWPFParagraph title = document.createParagraph();
        title.setAlignment(ParagraphAlignment.CENTER);

        XWPFRun titleRun = title.createRun();
        titleRun.setBold(true);
        titleRun.setFontSize(18);
        titleRun.setText("Raport Automatik i Bugs");
    }

    private static void addEnvironmentInfo(XWPFDocument document) {

        XWPFParagraph infoTitle = document.createParagraph();

        XWPFRun infoRun = infoTitle.createRun();

        infoRun.setBold(true);

        infoRun.setFontSize(13);

        infoRun.setText("Informacion i Ekzekutimit");

        XWPFTable table = document.createTable(6, 2);

        table.getRow(0).getCell(0).setText("Data");
        table.getRow(0).getCell(1).setText(LocalDateTime.now().toString());

        table.getRow(1).getCell(0).setText("Base URL");
        table.getRow(1).getCell(1).setText(ConfigReader.get("baseUrl"));

        table.getRow(2).getCell(0).setText("Browser");
        table.getRow(2).getCell(1).setText(ConfigReader.get("browser"));

        table.getRow(3).getCell(0).setText("Headless");
        table.getRow(3).getCell(1).setText(ConfigReader.get("headless"));

        table.getRow(4).getCell(0).setText("Use Healenium");
        table.getRow(4).getCell(1).setText(ConfigReader.get("useHealenium"));

    //    table.getRow(5).getCell(0).setText("Browser Version");
        System.out.println("Browser version in Word: " + ExecutionInfo.getBrowserVersion());
        table.getRow(5).getCell(0).setText("Browser Version");
        table.getRow(5).getCell(1).setText(
                ExecutionInfo.getBrowserVersion()
        );
/*
        table.getRow(5).getCell(1).setText(
                ((HasCapabilities) DriverFactory.getDriver())
                        .getCapabilities()
                        .getBrowserVersion()
        );

 */
    }

    private static void addBugSection(
            XWPFDocument document,
            String[] bug,
            int bugNumber
    ) throws Exception {

        XWPFParagraph spacer = document.createParagraph();
        spacer.createRun().addBreak();

        XWPFParagraph bugTitle = document.createParagraph();
        XWPFRun bugRun = bugTitle.createRun();
        bugRun.setBold(true);
        bugRun.setFontSize(14);
        bugRun.setText("Bug #" + bugNumber);

        XWPFTable table = document.createTable(7, 2);

        table.getRow(0).getCell(0).setText("Test Case");
        table.getRow(0).getCell(1).setText(bug[0]);

        table.getRow(1).getCell(0).setText("Step");
        table.getRow(1).getCell(1).setText(bug[1]);

        table.getRow(2).getCell(0).setText("Expected Result");
        table.getRow(2).getCell(1).setText(bug[2]);

        table.getRow(3).getCell(0).setText("Actual Result");
        table.getRow(3).getCell(1).setText(bug[3]);

        table.getRow(4).getCell(0).setText("Severity");
        table.getRow(4).getCell(1).setText(bug[4]);

        table.getRow(5).getCell(0).setText("Screenshot Path");
        table.getRow(5).getCell(1).setText(bug[5]);

        table.getRow(6).getCell(0).setText("Status");
        table.getRow(6).getCell(1).setText("Open");

        addScreenshot(document, bug[5]);
    }

    private static void addScreenshot(
            XWPFDocument document,
            String screenshotPath
    ) {

        try {
            File screenshot = new File(screenshotPath);

            if (!screenshot.exists()) {

                XWPFParagraph missing = document.createParagraph();
                missing.createRun().setText(
                        "Screenshot nuk u gjet: " + screenshotPath
                );

                return;
            }

            XWPFParagraph screenshotTitle = document.createParagraph();
            XWPFRun titleRun = screenshotTitle.createRun();
            titleRun.setBold(true);
            titleRun.setText("Screenshot Evidence:");

            XWPFParagraph imageParagraph = document.createParagraph();
            XWPFRun imageRun = imageParagraph.createRun();

            FileInputStream imageStream =
                    new FileInputStream(screenshot);

            imageRun.addPicture(
                    imageStream,
                    XWPFDocument.PICTURE_TYPE_PNG,
                    screenshot.getName(),
                    Units.toEMU(500),
                    Units.toEMU(280)
            );

            imageStream.close();

        } catch (Exception e) {

            XWPFParagraph error = document.createParagraph();
            error.createRun().setText(
                    "Screenshot nuk mund të shtohej në raport: "
                            + screenshotPath
            );
        }
    }
}