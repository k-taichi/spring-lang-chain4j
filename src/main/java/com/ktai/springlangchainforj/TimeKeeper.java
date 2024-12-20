package com.ktai.springlangchainforj;

import dev.langchain4j.service.SystemMessage;

/**
 * @author gmogshd taichi-kaneko
 */
public interface TimeKeeper {
    @SystemMessage("現在の時刻を何時何分形式で教えてください。")
    String ask(String question);

}
