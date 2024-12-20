package com.ktai.springlangchainforj.service;

import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author gmogshd taichi-kaneko
 */
@Service
public class HttpClientServiceImpl implements HttpClientService {

    public String getWikiPage(String name) {
        String encodedName = null;
        try {
            encodedName = URLEncoder.encode(name, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .build();

        // ②HttpRequestを生成
        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create("https://ja.wikipedia.org/w/index.php?title="+encodedName))
                .build();
        AtomicReference<String> body = new AtomicReference<>("");
        client.sendAsync(req, HttpResponse.BodyHandlers.ofString())
                .thenAccept(res -> {
                    body.set(res.body());
                });
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return body.get();
    }
}
