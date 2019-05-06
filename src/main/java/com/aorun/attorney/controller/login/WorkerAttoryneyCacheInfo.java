package com.aorun.attorney.controller.login;

import com.aorun.attorney.model.WorkerAttorney;
import com.aorun.attorney.util.cache.redis.CheckIsEmpty;
import com.aorun.attorney.util.cache.redis.RedisUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * 类描述:
 * Created by duxihu on 2019/4/29 0029.
 */
public class WorkerAttoryneyCacheInfo {

    //律师版本-获取用户信息
    public static WorkerAttorney get(String key) {
        if (CheckIsEmpty.isEmpty(key)) {
            return null;
        }
        WorkerAttorney workerAttorney = null;
        String string = null;
        try {
            string = new RedisUtil().get(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 解析data
        Gson gson = new Gson();
        workerAttorney = gson.fromJson(string, new TypeToken<WorkerAttorney>(){}.getType());
        return workerAttorney;
    }

}
