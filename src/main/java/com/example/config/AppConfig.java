package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import com.example.model.Employee;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import com.example.repository.EmployeeRepository;
import com.example.service.dto.EmployeeDTO;

@Configuration
public class AppConfig {

   //Creating Connection with Redis
   @Bean
   public RedisConnectionFactory redisConnectionFactory() {
       return new LettuceConnectionFactory();
   }

   //Creating RedisTemplate for Entity 'Employee'
   @Bean
   public RedisTemplate<Integer, EmployeeDTO> redisTemplate(){
      RedisTemplate<Integer, EmployeeDTO> empTemplate = new RedisTemplate<>();
      empTemplate.setConnectionFactory(redisConnectionFactory());
      empTemplate.setKeySerializer(new StringRedisSerializer());
        empTemplate.setValueSerializer(new StringRedisSerializer());  
      return empTemplate;
   }
}