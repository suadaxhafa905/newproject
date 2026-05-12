package core.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class BugReportManager {

    private static final String BUG_REPORT_PATH =
            "reports/BugReport.csv";

    public static void logBug(
            String testCase,
            String step,
            String expected,
            String actual,
            String severity,
            String screenshotPath
    ) {

        try {

            FileWriter writer =
                    new FileWriter(BUG_REPORT_PATH, true);

            writer.append(LocalDateTime.now().toString()).append(",");
            writer.append(testCase).append(",");
            writer.append(step).append(",");
            writer.append(expected.replace(",", " ")).append(",");
            writer.append(actual.replace(",", " ")).append(",");
            writer.append(severity).append(",");
            writer.append(screenshotPath).append("\n");

            writer.close();

        } catch (IOException e) {

            throw new RuntimeException(
                    "Failed to write bug report",
                    e
            );
        }
    }
}