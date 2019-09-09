package com.twins.lee.service;

import com.twins.lee.entity.Improv;

public interface IImprovService {
    int save(Improv improv);

    //资料是否完善进度
    Improv UserImproveResultById(Long usrId);

}
