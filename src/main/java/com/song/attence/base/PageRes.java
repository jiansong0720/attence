package com.song.attence.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ApiModel
public class PageRes<T> {

    @ApiModelProperty("当前页码")
    private Integer page;

    @ApiModelProperty("每页多少条记录")
    private Integer size;

    @ApiModelProperty("总数据量")
    private Long total;

    @ApiModelProperty("总的页数")
    private Integer pages;

    @ApiModelProperty("对象集合")
    private List<T> list=new ArrayList<>();

    @ApiModelProperty("是否是第一页")
    private Boolean isFirstPage;

    @ApiModelProperty("是否是最后一页")
    private Boolean isLastPage;

    public PageRes() {
    }

    public PageRes(Long total) {
        super();
        this.total=total;
    }

    public PageRes(Page<?> page) {
        super();
        this.total = page.getTotalElements();
        this.pages = page.getTotalPages();
        this.isFirstPage = page.isFirst();
        this.isLastPage = page.isLast();
        this.size = page.getSize();
        this.page = page.getNumber();
    }

}
