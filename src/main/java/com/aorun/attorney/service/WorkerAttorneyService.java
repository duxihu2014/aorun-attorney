package com.aorun.attorney.service;


import com.aorun.attorney.model.WorkerAttorney;

/**
 * 律师-业务逻辑接口类
 *
 * Created by bysocket on 07/02/2017.
 */
public interface WorkerAttorneyService {


    WorkerAttorney findWorkerAttorneyByLoginName(String loginName);

}
