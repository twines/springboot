package com.twins.lee.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.twins.lee.entity.newEntity.DashboardIndex;

import java.util.List;


public interface DashboardIndexMapper extends BaseMapper<DashboardIndex> {

    public List<DashboardIndex> dashboardIndex(long userId);
}
