package com.aorun.attorney.dao;

import com.aorun.attorney.model.WorkerAdvisory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WorkerAdvisoryMapper {

    WorkerAdvisory selectByPrimaryKey(Long id);

    /**
     * @param attorneyId
     * @param start
     * @param limit
     * @return
     */
    List<WorkerAdvisory> getWorkerAdvisoryListByAttorneyId(@Param("attorneyId") Long attorneyId, @Param("start") Integer start, @Param("limit") Integer limit);

    /**
     * @param attorneyId
     * @return
     */
    List<WorkerAdvisory> getAllWorkerAdvisoryListByAttorneyId(@Param("attorneyId") Long attorneyId);


}