package com.song.attence.controller;

import com.song.attence.base.ResBean;
import com.song.attence.base.PageRes;
import com.song.attence.domain.AttenceNum;
import com.song.attence.service.AttenceNumService;
import com.song.attence.controller.req.AttenceNumAddReq;
import com.song.attence.controller.req.AttenceNumEditReq;
import com.song.attence.controller.req.AttenceNumPageReq;
import com.song.attence.controller.res.AttenceNumPageRes;
import com.song.attence.controller.res.AttenceNumDetailRes;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.validation.Valid;

/** 
 * @Author jiansong0720@gmail.com
 * @Describe 考勤
 * @Date 2018-09-01
 */
@RestController
@RequestMapping("/attenceNum/")
@Api(tags = "考勤", produces = "application/json")
public class AttenceNumController {

    @Resource
    private AttenceNumService attenceNumService;

    @ApiOperation("考勤-新增")
    @PostMapping("addAttenceNum")
    public ResBean<Long> addAttenceNum(@Valid @RequestBody AttenceNumAddReq req) {
        return new ResBean(attenceNumService.addAttenceNum(req));
    }

    @ApiOperation("考勤-编辑")
    @PostMapping("editAttenceNum")
    public ResBean editAttenceNum(@Valid @RequestBody AttenceNumEditReq req) {
        attenceNumService.editAttenceNum(req);
        return new ResBean();
    }

    @ApiOperation("考勤-删除")
    @GetMapping("deleteAttenceNum/{id}")
    public ResBean deleteAttenceNum(@PathVariable("id") Long id) {
        attenceNumService.deleteAttenceNum(id);
        return new ResBean();
    }

    @ApiOperation("考勤-详情")
    @GetMapping("detailAttenceNum/{id}")
    public ResBean<AttenceNumDetailRes> detailAttenceNum(@PathVariable("id") Long id) {
        return new ResBean(attenceNumService.detailAttenceNum(id));
    }

    @ApiOperation("考勤-分页")
    @PostMapping("pageAttenceNum")
    public ResBean<PageRes<AttenceNumPageRes>> pageAttenceNum(@Valid @RequestBody AttenceNumPageReq req) {
        return new ResBean(attenceNumService.pageAttenceNum(req));
    }

}