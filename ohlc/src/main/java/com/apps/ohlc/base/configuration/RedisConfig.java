package com.apps.ohlc.base.configuration;

import com.apps.ohlc.calculator.dto.OhlcDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        jedisConnectionFactory.setHostName("localhost"); // Set Redis host
        jedisConnectionFactory.setPort(6379); // Set Redis port
        return jedisConnectionFactory;
    }

    @Bean
    public RedisTemplate<String, OhlcDTO> redisTemplate() {
        RedisTemplate<String, OhlcDTO> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        template.setKeySerializer(new StringRedisSerializer());
        Jackson2JsonRedisSerializer<OhlcDTO> valueSerializer = new Jackson2JsonRedisSerializer<>(OhlcDTO.class);
        template.setValueSerializer(valueSerializer);
        return template;
    }
}