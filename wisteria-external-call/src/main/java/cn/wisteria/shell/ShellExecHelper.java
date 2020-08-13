package cn.wisteria.shell;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * <p>
 * shell脚本的调用工具类
 * </p>
 *
 * @author: zhu.chen
 * @date: 2020/8/13
 * @version: v1.0.0
 */
public class ShellExecHelper<T> {

    private static final Logger logger = LoggerFactory.getLogger(ShellExecHelper.class);

    /**
     * <p>
     * shell脚本执行方法
     * </p>
     * 需要跟脚本返回值那边协商，统一返回Json的字符串来进行解析。
     * e.g：脚本echo为Json串 {"code"="0","data"="xxx","message"="xxx信息"}。注意脚本不能一个分支输出多个Json串
     *
     * @param commands 将脚本名和命令参数都作为commands
     * @return ShellResponse
     */
    public static <T> ShellResponse<T> exec(String... commands) {
        InputStream inputStream = null;
        BufferedReader bufferedReader = null;
        Process process = null;
        try {
            logger.info("Exec shell command {}.", commands);
            ProcessBuilder processBuilder = new ProcessBuilder(commands);
            processBuilder.redirectErrorStream(true);
            process = processBuilder.start();
            inputStream = process.getInputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
            ShellResponse<T> shellResponse = JsonHelper.deserialize(line, ShellResponse.class);
            return shellResponse;
        } catch (IOException e) {
            logger.info("Exec shell command error {}.", e);
        } finally {
            if (process != null && process.isAlive()) {
                process.destroy();
            }
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                logger.info("Close input stream error.", e);
            }
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e) {
                logger.info("Close buffered reader error.", e);
            }
        }
        return null;
    }

}
