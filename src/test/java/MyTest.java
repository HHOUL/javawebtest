import com.abc.crm.settings.exception.loginException;
import com.abc.crm.utils.DateTimeUtil;
import com.abc.crm.utils.MD5Util;
import org.junit.Test;

public class MyTest {

    @Test
    public void test01() {

        String expireTime = "2021-06-23";
        int expireTimes = Integer.valueOf(expireTime.replace("-", ""));

        System.out.println(expireTimes);
        //获取当前时间
        int currentTime = Integer.valueOf(DateTimeUtil.getSysTime());

        System.out.println(currentTime);

        if (currentTime > expireTimes) {

            System.out.println("账号已失效！");
        } else {
            System.out.println("账号没失效！");
        }


    }
}
