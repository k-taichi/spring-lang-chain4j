package com.ktai.springlangchainforj.service;

import com.ktai.springlangchainforj.dto.Person;
import com.ktai.springlangchainforj.dto.ProfileNameDto;
import org.springframework.stereotype.Service;

/**
 * @author gmogshd taichi-kaneko
 */

public interface LangChainService {
    Person profileInvestigator(String body);
}
