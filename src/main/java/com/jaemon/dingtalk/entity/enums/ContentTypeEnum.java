/*
 * Copyright(c) 2015-2020, AnswerAIL
 * ShenZhen Answer.AI.L Technology Co., Ltd.
 * All rights reserved.
 *
 * <a>https://github.com/AnswerAIL/</a>
 *
 */
package com.jaemon.dingtalk.entity.enums;

import okhttp3.MediaType;

/**
 * ContentType
 *
 * @author Jaemon@answer_ljm@163.com
 * @version 1.0
 */
public enum ContentTypeEnum {
    JSON(MediaType.parse("application/json; charset=utf-8")),
    XML(MediaType.parse("application/xml; charset=utf-8"));

    private MediaType mediaType;

    ContentTypeEnum(MediaType mediaType) {
        this.mediaType = mediaType;
    }

    public MediaType mediaType() {
        return mediaType;
    }
}