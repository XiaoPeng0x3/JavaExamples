import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public class Main {
    // LocalDate
    // LocalTime
    // LocalDateTime

    public static void main(String[] args) {

        // 定义好时间的格式
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // 应该使用新的API格式
        String now = dateTimeFormatter.format(LocalDateTime.now());
        System.out.println(now);

        // 日期加减
        LocalDateTime nowTime = LocalDateTime.now();
        // -10天
        System.out.println(dateTimeFormatter.format(nowTime.minusDays(10)));

        // +1小时
        System.out.println(dateTimeFormatter.format(nowTime.plusHours(1)));

        // 调整年、月、日、天、小时
        // 设置时为9时
        System.out.println(dateTimeFormatter.format(nowTime.withHour(9)));
    }
}
