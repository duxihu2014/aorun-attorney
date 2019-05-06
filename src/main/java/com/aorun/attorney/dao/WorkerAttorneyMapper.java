package com.aorun.attorney.dao;

import com.aorun.attorney.model.WorkerAttorney;

public interface WorkerAttorneyMapper {

    WorkerAttorney findWorkerAttorneyByLoginName(String loginName);


}