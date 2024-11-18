package com.example.redisdemo1.config;

import com.example.redisdemo1.entity.Employee;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

@Configuration
@EnableRedisRepositories
public class RedisConfig {
    @Bean
    public JedisConnectionFactory connectionFactory() {
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
        config.setHostName("172.18.0.1"); // Set to the Docker container name for Redis
        config.setPort(6378);
        config.setDatabase(1);
        JedisClientConfiguration.JedisClientConfigurationBuilder builder = JedisClientConfiguration.builder();
        builder.connectTimeout(Duration.ofMinutes(1));
        return new JedisConnectionFactory(config);
    }


    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory());
        redisTemplate.setKeySerializer(new StringRedisSerializer());  // Serialize keys as Strings
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(Employee.class));  // Serialize Employee objects
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());  // Serialize hash keys as Strings
        redisTemplate.setHashValueSerializer(new Jackson2JsonRedisSerializer<>(Employee.class));  // Serialize hash values as Employee objects
        redisTemplate.afterPropertiesSet();  // Initialize the RedisTemplate
        return redisTemplate;
    }


    @Bean
    public JedisConnectionFactory jedisConnectionFactory1() {
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
        config.setHostName("172.18.0.1");
        config.setPort(6378);
        config.setDatabase(0);
        return new JedisConnectionFactory(config);
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate1() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory1());
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new StringRedisSerializer());
        return template;
    }




}
