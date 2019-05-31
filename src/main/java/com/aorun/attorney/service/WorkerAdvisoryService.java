package com.aorun.attorney.service;


import com.aorun.attorney.model.WorkerAdvisory;

import java.util.List;

/**
 * 法律咨询&我的留言-业务逻辑接口类
 * <p>
 * Created by bysocket on 07/02/2017.
 */
public interface WorkerAdvisoryService {
    /**
     * 根据 ID,查询信息
     *
     * @param id
     * @return
     */
    WorkerAdvisory findWorkerAdvisoryById(Long id);


    List<WorkerAdvisory> getWorkerAdvisoryListByAttorneyId(Long attorneyId, Integer pageIndex, Integer pageSize);

    List<WorkerAdvisory> getAllWorkerAdvisoryListByAttorneyId(Long attorneyId);


}
