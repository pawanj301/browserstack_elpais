package utils;

public class TestContext {

	private static final ThreadLocal<String> device = new ThreadLocal<>();
	private static final ThreadLocal<String> browserStack = new ThreadLocal<>();

    public static void setDevice(String value) {
        device.set(value);
    }

    public static String getDevice() {
        return device.get();
    }
    
    public static void setBrowserStack(String value) {
        browserStack.set(value);
    }

    public static String getBrowserStack() {
        return browserStack.get();
    }

    public static void clear() {
        device.remove();
        browserStack.remove();
    }
}
