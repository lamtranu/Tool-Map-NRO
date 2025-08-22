package tool.utils;
import java.io.PrintWriter;
import java.io.StringWriter;
public class Logger
{
public static final String RESET = "\033[0m";
public static final String RED = "\033[4;31m";
public static final String GREEN = "\033[0;32m";
public static final String PURPLE = "\033[0;35m";
public static final String BLUE = "\033[0;34m";
public static final String YELLOW = "\033[33m";
public static void log(String text) {
/* 26 */     System.out.print(text);
}
public static void log(String color, String text) {
/* 30 */     System.out.print(color + text + "\033[0m");
}
public static void success(String text) {
/* 37 */     System.out.print("\033[0;32m" + text + "\033[0m");
}
public static void warning(String text) {
/* 44 */     System.out.print("\033[0;34m" + text + "\033[0m");
}
public static void error(String text) {
/* 51 */     System.out.print("\033[4;31m" + text + "\033[0m");
}
public static void logException(Class clazz, Exception ex, String... log) {
try {
/* 56 */       if (log != null && log.length > 0) {
/* 57 */         log("\033[0;35m", log[0] + "\n");
}
/* 59 */       StackTraceElement[] stackTraceElements = (new Throwable()).getStackTrace();
/* 60 */       String nameMethod = stackTraceElements[1].getMethodName();
/* 61 */       StringWriter sw = new StringWriter();
/* 62 */       PrintWriter pw = new PrintWriter(sw);
/* 63 */       ex.printStackTrace(pw);
/* 64 */       String detail = sw.toString();
/* 65 */       String[] arr = detail.split("\n");
/* 66 */       warning("Có lỗi tại class: ");
/* 67 */       error(clazz.getName());
/* 68 */       warning(" - tại phương thức: ");
/* 69 */       error(nameMethod + "\n");
/* 70 */       warning("Chi tiết lỗi:\n");
/* 71 */       for (String str : arr) {
/* 72 */         error(str + "\n");
}
/* 74 */       log("--------------------------------------------------------\n");
/* 75 */     } catch (Exception exception) {}
}
}



