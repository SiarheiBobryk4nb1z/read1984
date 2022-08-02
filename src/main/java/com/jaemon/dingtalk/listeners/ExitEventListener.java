/*
 * Copyright(c) 2015-2020, AnswerAIL
 * ShenZhen Answer.AI.L Technology Co., Ltd.
 * All rights reserved.
 *
 * <a>https://github.com/AnswerAIL/</a>
 *
 */
package com.jaemon.dingtalk.listeners;

import com.jaemon.dingtalk.DingTalkRobot;
import com.jaemon.dingtalk.entity.DingTalkProperties;
import com.jaemon.dingtalk.entity.message.MsgType;
import com.jaemon.dingtalk.support.Notification;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;

import static com.jaemon.dingtalk.constant.DkConstant.DK_PREFIX;
import static com.jaemon.dingtalk.constant.DkConstant.EXIT_KEYWORD;

/**
 * Exit Listener
 *
 * @author Jaemon@answer_ljm@163.com
 * @version 1.0
 */
public class ExitEventListener implements ApplicationListener<ContextClosedEvent> {

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        ApplicationContext applicationContext = event.getApplicationContext();
        DingTalkProperties properties = applicationContext.getBean(DingTalkProperties.class);

        if (properties.isEnabled()
                && properties.getMonitor().isExit()
                // exclude start-up failed
                && ApplicationEventTimeTable.successTime() > 0) {
            DingTalkRobot dingTalkRobot = applicationContext.getBean(DingTalkRobot.class);
            Notification notification = applicationContext.getBean(Notification.class);
            String projectId = properties.getProjectId();
            projectId = projectId == null ? DK_PREFIX : projectId;

            ApplicationEventTimeTable.exitTime = event.getTimestamp();

            MsgType message = notification.exit(event, projectId);
            dingTalkRobot.send(projectId + EXIT_KEYWORD, message);
        }
    }
}