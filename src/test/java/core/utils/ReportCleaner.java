package core.utils;

import java.io.File;

public class ReportCleaner {

    public static void cleanReports() {

        deleteFile("reports/BugReport.docx");
        deleteFile("reports/TestReport.html");

        deleteFolderFiles("reports/screenshots");
    }

    private static void deleteFile(String path) {

        File file = new File(path);

        if (file.exists()) {
            file.delete();
        }
    }

    private static void deleteFolderFiles(String folderPath) {

        File folder = new File(folderPath);

        if (folder.exists() && folder.isDirectory()) {

            File[] files = folder.listFiles();

            if (files != null) {

                for (File file : files) {
                    file.delete();
                }
            }
        }
    }
}