package com.aorun.attorney.service.impl;

import com.aorun.attorney.dao.WorkerAttorneyReplyAdvisoryMapper;
import com.aorun.attorney.model.WorkerAttorneyReplyAdvisory;
import com.aorun.attorney.service.WorkerAttorneyReplyAdvisoryService;
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
public class WorkerAttorneyReplyAdvisoryServiceImpl implements WorkerAttorneyReplyAdvisoryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WorkerAttorneyReplyAdvisoryServiceImpl.class);

    @Autowired
    private WorkerAttorneyReplyAdvisoryMapper workerAttorneyReplyAdvisoryMapper;

    @Override
    public int saveWorkerAttorneyReplyAdvisory(WorkerAttorneyReplyAdvisory workerAttorneyReplyAdvisory) {
        return workerAttorneyReplyAdvisoryMapper.insert(workerAttorneyReplyAdvisory);
    }

    @Override
    public List<WorkerAttorneyReplyAdvisory> getWorkerAttorneyReplyAdvisoryListByAttorneyId(Long attorneyId, Long advisoryId, String requestTimePoint, String isfirstPoint) {
        return workerAttorneyReplyAdvisoryMapper.getWorkerAttorneyReplyAdvisoryListByAttorneyId(attorneyId, advisoryId, requestTimePoint, isfirstPoint);
    }

    @Override
    public List<WorkerAttorneyReplyAdvisory> getAllWorkerAttorneyReplyAdvisoryListByAttorneyId(Long attorneyId, Long advisoryId) {
        return workerAttorneyReplyAdvisoryMapper.getAllWorkerAttorneyReplyAdvisoryListByAttorneyId(attorneyId, advisoryId);
    }

}
