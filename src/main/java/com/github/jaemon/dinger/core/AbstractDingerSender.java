/*
 * Copyright ©2015-2022 Jaemon. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.jaemon.dinger.core;

import com.github.jaemon.dinger.DingerSender;
import com.github.jaemon.dinger.core.entity.DingerProperties;
import com.github.jaemon.dinger.core.entity.enums.MessageSubType;
import com.github.jaemon.dinger.exception.DingerException;
import com.github.jaemon.dinger.core.entity.DingerCallback;
import com.github.jaemon.dinger.support.CustomMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * AbstractDingTalkSender
 *
 * @author Jaemon
 * @since 1.0
 */
public abstract class AbstractDingerSender
        extends DingerHelper
        implements DingerSender {
    protected static final Logger log = LoggerFactory.getLogger(AbstractDingerSender.class);

    protected DingerProperties dingerProperties;
    protected DingerManagerBuilder dingTalkManagerBuilder;

    public AbstractDingerSender(DingerProperties dingerProperties, DingerManagerBuilder dingTalkManagerBuilder) {
        this.dingerProperties = dingerProperties;
        this.dingTalkManagerBuilder = dingTalkManagerBuilder;
    }

    /**
     * 消息类型校验
     *
     * @param messageSubType
     *              消息类型
     * @return
     *              消息生成器
     */
    protected CustomMessage customMessage(MessageSubType messageSubType) {
        return messageSubType == MessageSubType.TEXT ? dingTalkManagerBuilder.textMessage : dingTalkManagerBuilder.markDownMessage;
    }

    /**
     * 异常回调
     *
     * @param dingerId
     *          dingerId
     * @param message
     *          message
     * @param ex
     *          ex
     * @param <T>
     *          T
     */
    protected <T> void exceptionCallback(String dingerId, T message, DingerException ex) {
        DingerCallback dkExCallable = new DingerCallback(dingerId, message, ex);
        dingTalkManagerBuilder.dingerExceptionCallback.execute(dkExCallable);
    }
}