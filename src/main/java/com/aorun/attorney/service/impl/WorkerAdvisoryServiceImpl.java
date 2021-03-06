package com.aorun.attorney.service.impl;

import com.aorun.attorney.dao.WorkerAdvisoryMapper;
import com.aorun.attorney.model.WorkerAdvisory;
import com.aorun.attorney.service.WorkerAdvisoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 城市业务逻辑实现类
 * <p>
 * Created by bysocket on 07/02/2017.
 */
@Service
public class WorkerAdvisoryServiceImpl implements WorkerAdvisoryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WorkerAdvisoryServiceImpl.class);

    @Autowired
    private WorkerAdvisoryMapper workerAdvisoryMapper;

    @Override
    public WorkerAdvisory findWorkerAdvisoryById(Long id) {
        return workerAdvisoryMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<WorkerAdvisory> getWorkerAdvisoryListByAttorneyId(Long attorneyId, Integer pageIndex, Integer pageSize) {
        ///** 启始页-位置 */
        Integer start = (pageIndex - 1) * pageSize;
        /** 每页大小  */
        Integer limit = pageSize;
        return workerAdvisoryMapper.getWorkerAdvisoryListByAttorneyId(attorneyId, start, limit);
    }

    @Override
    public List<WorkerAdvisory> getAllWorkerAdvisoryListByAttorneyId(Long attorneyId) {
        return workerAdvisoryMapper.getAllWorkerAdvisoryListByAttorneyId(attorneyId);
    }


}
