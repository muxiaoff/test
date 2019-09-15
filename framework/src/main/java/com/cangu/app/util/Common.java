package com.cangu.app.util;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

/**
 * 公用类
 * @author zff
 * @Date 2019-08-19
 */
public class Common {

    public static void responseMessage(String message, HttpServletResponse response) throws IOException {
        OutputStream out = null;
        try {
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            out = response.getOutputStream();
            // 创建缓冲区
            byte buffer[] = message.getBytes(Charset.forName("UTF-8"));
            out.write(buffer, 0, buffer.length);
        } finally {
            // 关闭输出流
            if (out != null) {
                out.close();
            }
        }
    }

    /**
     * Description:日期格式化
     *
     * @return yyyy-MM-dd格式<br>
     * @author byw<br>
     * @taskId <br>
     */
    public static String getSysTimes() {
        LocalDate date = LocalDate.now();
        return date.format(DateTimeFormatter.ISO_DATE);
    }

    /**
     * Description:判断系统是linux
     *
     * @return yyyy-MM-dd格式<br>
     * @author byw<br>
     * @taskId <br>
     */
    public static boolean isOSLinux() {
        Properties prop = System.getProperties();
        String os = prop.getProperty("os.name");
        if (os != null && os.toLowerCase().indexOf("linux") > -1) {
            return true;
        } else {
            return false;
        }
    }
}
