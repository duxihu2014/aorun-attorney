package com.aorun.attorney.controller;


import com.aorun.attorney.controller.login.WorkerAttoryneyCacheInfo;
import com.aorun.attorney.dto.WorkerAdvisoryDto;
import com.aorun.attorney.model.WorkerAdvisory;
import com.aorun.attorney.model.WorkerAttorney;
import com.aorun.attorney.service.WorkerAdvisoryService;
import com.aorun.attorney.service.WorkerAttorneyService;
import com.aorun.attorney.util.DateFormat;
import com.aorun.attorney.util.PageConstant;
import com.aorun.attorney.util.biz.ImagePropertiesConfig;
import com.aorun.attorney.util.cache.redis.RedisCache;
import com.aorun.attorney.util.jsonp.Jsonp;
import com.aorun.attorney.util.jsonp.Jsonp_data;
import com.google.gson.Gson;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * 律师信息
 * Created by bysocket on 07/02/2017.
 */
@RequestMapping("/attorney")
@RestController
public class WorkerAttorneyRestController {

    @Autowired
    private WorkerAttorneyService workerAttorneyService;

    @Autowired
    private WorkerAdvisoryService workerAdvisoryService;

    /**
     * @param sid
     * @param pageIndex
     * @param pageSize
     * @return
     */
    //1.待律师处理咨询列表
    @RequestMapping(value = "/workerAdvisoryList", method = RequestMethod.GET)
    public Object workerAdvisoryList(
            @RequestParam(name = "sid", required = true, defaultValue = "") String sid,
            @RequestParam(name = "pageIndex", required = true, defaultValue = "1") Integer pageIndex,
            @RequestParam(name = "pageSize", required = false, defaultValue = PageConstant.APP_PAGE_SIZE + "") Integer pageSize
    ) {

        WorkerAttorney workerAttorney = WorkerAttoryneyCacheInfo.get(sid);
        Long workerAttorneyId = workerAttorney.getId();


        List<WorkerAdvisory> workerAdvisoryList = new ArrayList<WorkerAdvisory>();
        List<WorkerAdvisoryDto> workerAdvisoryDtoList = new ArrayList<WorkerAdvisoryDto>();
        workerAdvisoryList = workerAdvisoryService.getWorkerAdvisoryListByAttorneyId(workerAttorneyId, pageIndex, pageSize);
        for (WorkerAdvisory workerAdvisory : workerAdvisoryList) {
            WorkerAdvisoryDto workerAdvisoryDto = new WorkerAdvisoryDto();
            BeanUtils.copyProperties(workerAdvisory, workerAdvisoryDto);

            StringBuffer MaterialsUrls = new StringBuffer("");
            String materialsUrls = workerAdvisory.getMaterialsUrls();
            if (materialsUrls != null && !materialsUrls.equals("")) {
                String _MaterialsUrls[] = materialsUrls.split(",");
                for (String materialsUrl : _MaterialsUrls) {
                    MaterialsUrls.append(ImagePropertiesConfig.ADVISORY_SERVER_PATH + materialsUrl).append(",");
                }
            }
            workerAdvisoryDto.setMaterialsUrls(MaterialsUrls.toString());
            workerAdvisoryDto.setCreateTime(DateFormat.dateToString(workerAdvisory.getCreateTime()));
            workerAdvisoryDtoList.add(workerAdvisoryDto);
        }
        Map<String, Object> datamap = new HashMap<String, Object>();
        List<WorkerAdvisory> _workerAdvisoryList = workerAdvisoryService.getAllWorkerAdvisoryListByAttorneyId(workerAttorneyId);
        if (_workerAdvisoryList != null && _workerAdvisoryList.size() > 0) {
            Integer totalPage = _workerAdvisoryList.size();
            Integer pageTotal = totalPage / pageSize + 1;
            datamap.put("pageTotal", pageTotal);
        } else {
            datamap.put("pageTotal", 1);
        }

        datamap.put("workerAdvisoryDtoList", workerAdvisoryDtoList);

        return Jsonp_data.success(datamap);
    }


    //1.登陆接口
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public Object login(
            @RequestParam(value = "loginName", defaultValue = "") String loginName,
            @RequestParam(value = "password", defaultValue = "") String password
    ) {

        WorkerAttorney workerAttorney = workerAttorneyService.findWorkerAttorneyByLoginName(loginName);
        Map<String, Object> map = new HashMap<String, Object>();
        if (workerAttorney != null && workerAttorney.getPassword().equals(password)) {
            String sid = UUID.randomUUID().toString();
            RedisCache.put(sid, new Gson().toJson(workerAttorney));
            map.put("sid", sid);
            map.put("loginName", loginName);
        } else {
            return Jsonp.bussiness_tips_code("用户名或密码错误!");
        }
        return Jsonp_data.success(map);
    }


}
