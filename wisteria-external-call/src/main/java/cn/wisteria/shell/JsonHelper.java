package cn.wisteria.shell;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * <p>
 * Json和JavaBean之间的相互转化
 * </p>
 *
 * @author: zhu.chen
 * @date: 2020/8/13
 * @version: v1.0.0
 */
public class JsonHelper<T> {

    private static final Logger logger = LoggerFactory.getLogger(JsonHelper.class);

    /**
     * <p>
     * javabean ==> json
     * </p>
     *
     * @param t
     * @param <T>
     * @return String
     */
    public static <T> String serialize(T t) {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonStr = null;
        try {
            jsonStr = objectMapper.writeValueAsString(t);
        } catch (JsonProcessingException e) {
            logger.info("JsonUtils#serialize has a error.", e);
        }
        return jsonStr;
    }

    /**
     * <p>
     * json ==> javabean
     * </p>
     *
     * @param json
     * @param clazz
     * @param <T>
     * @return T
     */
    public static <T> T deserialize(String json, Class<T> clazz) {
        ObjectMapper objectMapper = new ObjectMapper();
        T t = null;
        try {
            t = objectMapper.readValue(json, clazz);
        } catch (IOException e) {
            logger.info("JsonUtils#deserialize has a error.", e);
        }
        return t;
    }

}
