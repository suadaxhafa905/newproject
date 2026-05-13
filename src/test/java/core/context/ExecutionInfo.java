package core.context;

public class ExecutionInfo {

    private static volatile String browserVersion = "N/A";

    public static String getBrowserVersion() {
        return browserVersion;
    }

    public static void setBrowserVersion(String version) {
        if (version != null && !version.isEmpty()) {
            browserVersion = version;
        }
    }
}