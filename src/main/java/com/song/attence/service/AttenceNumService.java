package com.song.attence.service;

import com.song.attence.base.BusinessException;
import com.song.attence.base.Errors;
import com.song.attence.base.PageRes;
import com.song.attence.controller.req.AttenceNumAddReq;
import com.song.attence.controller.req.AttenceNumEditReq;
import com.song.attence.controller.req.AttenceNumPageReq;
import com.song.attence.controller.res.AttenceNumDetailRes;
import com.song.attence.controller.res.AttenceNumPageRes;
import com.song.attence.domain.AttenceNum;
import com.song.attence.repository.AttenceNumRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * @Author jiansong0720@gmail.com
 * @Describe 考勤
 * @Date 2018-09-01
 */
@Service
public class AttenceNumService {

    @Resource
    private AttenceNumRepository attenceNumRepository;

    public Long addAttenceNum(AttenceNumAddReq req) {
        AttenceNum attenceNum = new AttenceNum();
        BeanUtils.copyProperties(req, attenceNum);
        attenceNum = attenceNumRepository.save(attenceNum);
        return attenceNum.getId();
    }

    public void editAttenceNum(AttenceNumEditReq req) {
        AttenceNum attenceNum = getAndCheck(req.getId());
        BeanUtils.copyProperties(req, attenceNum);
        attenceNumRepository.save(attenceNum);
    }

    public void deleteAttenceNum(Long id) {
        getAndCheck(id);
        attenceNumRepository.deleteById(id);
    }

    public AttenceNumDetailRes detailAttenceNum(Long id) {
        AttenceNum attenceNum = getAndCheck(id);
        AttenceNumDetailRes attenceNumDetailRes = new AttenceNumDetailRes();
        BeanUtils.copyProperties(attenceNum, attenceNumDetailRes);
        return attenceNumDetailRes;
    }

    public PageRes<AttenceNumPageRes> pageAttenceNum(AttenceNumPageReq req) {
        PageRequest pageRequest = PageRequest.of(req.getPage(), req.getSize());
        Page<AttenceNum> page = attenceNumRepository.findAll(pageRequest);
        PageRes<AttenceNumPageRes> response = new PageRes(page);
        page.getContent().forEach(attenceNum -> {
            AttenceNumPageRes attenceNumPageRes = new AttenceNumPageRes();
            BeanUtils.copyProperties(attenceNum, attenceNumPageRes);
            response.getList().add(attenceNumPageRes);
        });
        return response;
    }

    public AttenceNum getAndCheck(Long id) {
        Optional<AttenceNum> attenceNum = attenceNumRepository.findById(id);
        if (!attenceNum.isPresent()) {
            throw new BusinessException(Errors.DATA_NOT_EXISTED);
        }
        return attenceNum.get();
    }

}