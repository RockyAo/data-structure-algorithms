package main.java.com.rocky.ao.common;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author yun.ao
 * @date 2022/11/7 15:30
 * @description use to print function excute durations
 */
public class Times {
    private static final SimpleDateFormat fmt = new SimpleDateFormat("HH:mm:ss.SSS");

    public interface Task {
        void execute();
    }

    public static void test(String title, Task task) {
        if (task == null) return;
        title = (title == null) ? "" : ("【" + title + "】");
        System.out.println(title);
        System.out.println("start：" + fmt.format(new Date()));
        long begin = System.currentTimeMillis();
        task.execute();
        long end = System.currentTimeMillis();
        System.out.println("end：" + fmt.format(new Date()));
        double delta = (end - begin) / 1000.0;
        System.out.println("durations：" + delta + "second(s)");
        System.out.println("-------------------------------------");
    }
}

