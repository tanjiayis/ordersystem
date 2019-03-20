package com.example.demo.order.remark.dto;

import javax.validation.constraints.NotEmpty;

/**
 * Created by JIAYI_TAN on 2019/3/12.
 */
public class AddRemarkDto {
    @NotEmpty(message = "评论内容不能为空!")
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
