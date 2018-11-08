package redis;

import com.jackman.commons.App;
import com.jackman.commons.redis.component.RedisComponent;
import com.jackman.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author shusheng
 * @Date 18/11/7 上午10:57
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class RedisTest {
    @Autowired
    private RedisComponent redisComponent;

    @Test
    public void testSetValue() {
        boolean lock = redisComponent.tryAcquire("123", 5 * 60 * 1000);
        try {
            if(!lock) {
                throw new Exception("获取锁失败!");
            }
            User user = new User();
            user.setAge((byte) 10);
            user.setIncoming(new BigDecimal(1000.00));
            user.setPassword("123123123131");
            user.setUserName("陆晓明");
            user.setWeight(100);
            redisComponent.set(user.getUserName(), user);

            User tempUser = redisComponent.get(user.getUserName(), User.class);
            System.out.println(tempUser.getUserName());

            Map<String, String> map = new HashMap<>();
            map.put("234", "1231");
            map.put("2345", "12315");
            redisComponent.setHash("mapKey", map);
            String temp = redisComponent.getHash("mapKey", "234", String.class);
            System.out.println(temp);

        }catch(Exception e) {

        }finally {
            if(lock) {
                redisComponent.releaseLock("123");
            }
        }
    }
}
