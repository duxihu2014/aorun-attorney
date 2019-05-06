package com.aorun.attorney.dao;

import com.aorun.attorney.model.WorkerAttorneyReplyAdvisory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WorkerAttorneyReplyAdvisoryMapper {

    int insert(WorkerAttorneyReplyAdvisory record);


    List<WorkerAttorneyReplyAdvisory> getWorkerAttorneyReplyAdvisoryListByAttorneyId(@Param("attorneyId") Long attorneyId, @Param("advisoryId") Long advisoryId, @Param("requestTimePoint") String requestTimePoint, @Param("isfirstPoint") String isfirstPoint);

    List<WorkerAttorneyReplyAdvisory> getAllWorkerAttorneyReplyAdvisoryListByAttorneyId(@Param("attorneyId") Long attorneyId,@Param("advisoryId") Long advisoryId);


}