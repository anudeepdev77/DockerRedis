package com.example.redisdemo1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;



@Data
@AllArgsConstructor
@NoArgsConstructor
//@RedisHash("Employee")
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private String name;
}
