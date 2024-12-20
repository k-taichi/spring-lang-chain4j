package com.ktai.springlangchainforj;

import dev.langchain4j.agent.tool.Tool;

import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * @author gmogshd taichi-kaneko
 */
public class Clock {
    @Tool
    public LocalDateTime getCurrentTime() {
        return LocalDateTime.now(ZoneId.of("Asia/Tokyo"));
    }
}
