package demo.nature.springbootweb.service;

import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author nature
 * @date 14/6/2020 12:01 下午
 * @email 924943578@qq.com
 */
@Service
public class UUIDService {

    /**
     * 返回随机的字符串
     *
     * @param index
     * @return
     */
    public String randUUID(int index) {
        try {
            Thread.sleep(1000);
            System.out.println("in randUUID before process!");
            return UUID.randomUUID() + "|" + index;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("in randUUID finally!");
        }
        return "";
    }
}
