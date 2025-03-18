package com.cyl.config;

import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.client5.http.io.HttpClientConnectionManager;
import org.apache.hc.client5.http.socket.ConnectionSocketFactory;
import org.apache.hc.client5.http.socket.PlainConnectionSocketFactory;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactory;
import org.apache.hc.core5.http.config.Registry;
import org.apache.hc.core5.http.config.RegistryBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

@Configuration
public class RestTemplateConfig {
    //从连接池获取连接的超时时间，一般设置为较短；
    @Value("${http-pool.connection-request-timeout}")
    private int connectionRequestTimeout;

    //连接超时时间
    @Value("${http-pool.connection-timeout}")
    private int connectionTimeout;

    //完成连接后，socket通信超时时间
    @Value("${http-pool.socket-timeout}")
    private int socketTimeout;

    //单host（可以理解为单域名）最大并发数
    @Value("${http-pool.max-per-route}")
    private int maxPerRoute;

    //连接池最大连接数
    @Value("${http-pool.max-total}")
    private int maxTotal;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate(httpRequestFactory());
    }

    @Bean
    public ClientHttpRequestFactory httpRequestFactory() {
        return new HttpComponentsClientHttpRequestFactory(httpClient());
    }

    @Bean
    public HttpClient httpClient() {
        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.getSocketFactory())
                .register("https", SSLConnectionSocketFactory.getSocketFactory())
                .build();
        
        HttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(registry);
        ((PoolingHttpClientConnectionManager) connectionManager).setMaxTotal(maxTotal);
        ((PoolingHttpClientConnectionManager) connectionManager).setDefaultMaxPerRoute(maxPerRoute);

        RequestConfig requestConfig = RequestConfig.custom()
                .setResponseTimeout(socketTimeout, TimeUnit.MILLISECONDS)
                .setConnectTimeout(connectionTimeout, TimeUnit.MILLISECONDS)
                .setConnectionRequestTimeout(connectionRequestTimeout, TimeUnit.MILLISECONDS)
                .build();

        return HttpClientBuilder.create()
                .setDefaultRequestConfig(requestConfig)
                .setConnectionManager(connectionManager)
                .build();
    }
}
