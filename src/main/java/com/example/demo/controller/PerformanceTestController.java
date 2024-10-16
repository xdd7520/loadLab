package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.ArrayList;

@RestController
@RequestMapping("/performance")
public class PerformanceTestController {

    private static final Logger logger = LoggerFactory.getLogger(PerformanceTestController.class);

    private List<String> dataStore = new ArrayList<>();

    @GetMapping("/slow-query")
    public String slowQuery() {
        try {
            Thread.sleep(2000); // 模拟慢查询，睡眠2秒
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "慢查询完成";
    }

    @GetMapping("/query-then-insert")
    public String queryThenInsert() {
        // 先查询
        String result = dataStore.isEmpty() ? "无数据" : dataStore.get(dataStore.size() - 1);
        
        // 后插入
        String newData = "数据" + System.currentTimeMillis();
        dataStore.add(newData);
        
        return "查询结果: " + result + ", 新插入: " + newData;
    }

    @GetMapping("/query-with-logs")
    public String queryWithLogs() {
        logger.info("开始查询");
        logger.debug("调试信息1");
        logger.debug("调试信息2");
        logger.debug("调试信息3");
        logger.warn("警告信息");
        logger.error("错误信息");
        // 模拟查询操作
        String result = "查询结果";
        logger.info("查询完成");
        return result;
    }

    @GetMapping("/inefficient-query")
    public String inefficientQuery() {
        String result = "";
        for (int i = 0; i < 1000000; i++) {
            result += String.valueOf(i); // 非常低效的字符串拼接
        }
        return "低效查询完成，结果长度: " + result.length();
    }

    @GetMapping("/memory-leak")
    public String memoryLeak() {
        List<byte[]> leakyList = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            byte[] leakyArray = new byte[1024 * 1024]; // 1MB
            leakyList.add(leakyArray);
        }
        return "可能的内存泄漏操作完成";
    }

    @GetMapping("/delay")
    public String delay() {
        try {
            Thread.sleep(500); // 等待500ms
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "延迟500ms后返回";
    }
}
