package com.ktai.springlangchainforj;

import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;

/**
 * @author gmogshd taichi-kaneko
 */
interface CountryAdvice {
/*
    @UserMessage("You are a good friend of mine. Answer using slang. {{it}}")
    String chat(String userMessage);
*/
    /*
    @UserMessage("You are a good friend of mine. Answer using slang. {{message}}")
    String chat(@V("message") String userMessage);
*/
    @UserMessage("{{country}}の{{something}} は何ですか?")
    String chat(@V("something") String something, @V("country") String country);
}

