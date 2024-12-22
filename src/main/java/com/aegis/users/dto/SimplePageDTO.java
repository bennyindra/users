package com.aegis.users.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
public class SimplePageDTO <T> implements Serializable {

    private int page;
    private int totalPage;
    private T content;
    public SimplePageDTO(int page, int totalPage, T content){
        this.setPage(page);
        this.setTotalPage(totalPage);
        this.setContent(content);
    }
}
