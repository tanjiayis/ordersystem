package com.example.demo.data;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by JIAYI_TAN on 2019/3/10.
 */
public class BaseDto {
    public Map<String, Object> wrapAsMap() {
        Map<String, Object> result = new HashMap<>();
        try {
            Class<?> parameterType = getClass();
            for (; parameterType != BaseDto.class; parameterType = parameterType.getSuperclass()) {
                Field[] fields = parameterType.getDeclaredFields();
                if (fields != null) {
                    for (Field field : fields) {
                        try {
                            boolean accessible = field.isAccessible();
                            field.setAccessible(true);
                            String name = field.getName();
                            Object value = field.get(this);
                            field.setAccessible(accessible);
                            result.put(name, value);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
}
