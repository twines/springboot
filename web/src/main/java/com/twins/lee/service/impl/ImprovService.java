package com.twins.lee.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.twins.lee.entity.Improv;
import com.twins.lee.mapper.ImprovMapper;
import com.twins.lee.service.IImprovService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ImprovService implements IImprovService {
    @Resource
    ImprovMapper improvMapper;

    @Override
    public int save(Improv improv) {
        if (improvMapper.selectById(improv.getId()) != null) {
            return improvMapper.updateById(improv);
        }
        int result = improvMapper.insert(improv);
        return result;
    }

    @Override
    public Improv UserImproveResultById(Long usrId) {

        Improv improv = improvMapper.selectById(usrId);
        return  improv;
    }


}
