package com.ktai.springlangchainforj.controller;

import com.ktai.springlangchainforj.dto.Person;
import com.ktai.springlangchainforj.dto.ProfileNameDto;
import com.ktai.springlangchainforj.service.HttpClientServiceImpl;
import com.ktai.springlangchainforj.service.LangChainService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gmogshd taichi-kaneko
 */
@RestController
public class ProfileController {

    private final HttpClientServiceImpl httpClientService;
    private final LangChainService langChainService;

    ProfileController(
            HttpClientServiceImpl httpClientService,
            LangChainService langChainService
    ) {
        this.httpClientService = httpClientService;
        this.langChainService = langChainService;
    }
    @PostMapping(value = "/send",produces= MediaType.APPLICATION_JSON_VALUE)
    public Person send(@RequestBody ProfileNameDto profileNameDto) {
        String wikiPage = httpClientService.getWikiPage(profileNameDto.getProfileName());
        Person person= langChainService.profileInvestigator(wikiPage);
        return person;
    }
}
