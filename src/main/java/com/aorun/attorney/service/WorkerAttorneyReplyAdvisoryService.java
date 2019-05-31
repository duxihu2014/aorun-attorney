package com.aorun.attorney.service;


import com.aorun.attorney.model.WorkerAttorneyReplyAdvisory;

import java.util.List;

/**
 * 援助-业务逻辑接口类
 * <p>
 * Created by bysocket on 07/02/2017.
 */
public interface WorkerAttorneyReplyAdvisoryService {
    /**
     * 新增信息
     *
     * @param workerAttorneyReplyAdvisory
     * @return
     */
    int saveWorkerAttorneyReplyAdvisory(WorkerAttorneyReplyAdvisory workerAttorneyReplyAdvisory);

    List<WorkerAttorneyReplyAdvisory> getWorkerAttorneyReplyAdvisoryListByAttorneyId(Long attorneyId, Long advisoryId, String requestTimePoint, String isfirstPoint);

    List<WorkerAttorneyReplyAdvisory> getAllWorkerAttorneyReplyAdvisoryListByAttorneyId(Long attorneyId, Long advisoryId);

}
