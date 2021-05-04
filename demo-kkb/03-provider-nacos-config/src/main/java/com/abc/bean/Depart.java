package com.abc.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "fieldHandler"})//作用是json序列化时将java bean中的一些属性忽略掉，序列化和反序列化都受影响。
public class Depart {
    private Integer id;
    private String name;
}


