package com.aorun.attorney.service.impl;

import com.aorun.attorney.dao.WorkerAttorneyMapper;
import com.aorun.attorney.model.WorkerAttorney;
import com.aorun.attorney.service.WorkerAttorneyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 城市业务逻辑实现类
 * <p>
 * Created by bysocket on 07/02/2017.
 */
@Service
public class WorkerAttorneyServiceImpl implements WorkerAttorneyService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WorkerAttorneyServiceImpl.class);

    @Autowired
    private WorkerAttorneyMapper workerAttorneyMapper;

    @Override
    public WorkerAttorney findWorkerAttorneyByLoginName(String loginName) {
        return workerAttorneyMapper.findWorkerAttorneyByLoginName(loginName);
    }


}
