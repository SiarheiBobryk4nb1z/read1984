package com.jaemon.dingtalk.support;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * 异步处理条件类
 *
 * @author Jaemon@answer_ljm@163.com
 * @version 1.0
 */
public class AsyncCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        String enabled = context.getEnvironment().getProperty("spring.dingtalk.enabled");
//        String async = context.getEnvironment().getProperty("spring.dingtalk.async");
        return (enabled == null  || "true".equals(enabled))/*
                && "true".equals(async)*/;
    }
}