package org.youthnet.debug.util.exceptions;

/**
 * User: karl
 * Date: 21-Jun-2010
 */
public class ExceptionsUtil {

        public static final String NEW_LINE = System.getProperty("line.separator");

        public static String getStackTrace(Throwable e) {
            StringBuffer stackTrace = new StringBuffer();

            for (StackTraceElement element : e.getStackTrace() ) {
                stackTrace.append(element.toString());
                stackTrace.append(NEW_LINE);
            }

            return stackTrace.toString();
        }
    
}
