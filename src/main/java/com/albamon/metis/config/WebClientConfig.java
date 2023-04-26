package com.albamon.metis.config;

import io.netty.channel.ChannelOption;
import io.netty.channel.epoll.EpollChannelOption;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;

@Configuration
public class WebClientConfig {

    /**
     * webClient 설정
     */
    @Bean
    public WebClient webClient() {
        HttpClient client = createHttpClient();
        ExchangeStrategies strategies = ExchangeStrategies.builder().codecs(
                configurer -> configurer.defaultCodecs().maxInMemorySize(16 * 1024 * 1024)).build();
        var connector = new ReactorClientHttpConnector(client);
        return WebClient.builder()
                .exchangeStrategies(strategies).clientConnector(connector).build();
    }

    public static HttpClient createHttpClient() {
        return HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .option(EpollChannelOption.TCP_KEEPIDLE, 300)
                .option(EpollChannelOption.TCP_KEEPINTVL, 60)
                .option(EpollChannelOption.TCP_KEEPCNT, 8)
                .responseTimeout(Duration.ofSeconds(10));
    }
}
