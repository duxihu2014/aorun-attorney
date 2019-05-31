package com.aorun.attorney.controller;


import com.aorun.attorney.controller.login.WorkerAttoryneyCacheInfo;
import com.aorun.attorney.model.WorkerAdvisory;
import com.aorun.attorney.model.WorkerAttorney;
import com.aorun.attorney.model.WorkerAttorneyReplyAdvisory;
import com.aorun.attorney.service.WorkerAdvisoryService;
import com.aorun.attorney.service.WorkerAttorneyReplyAdvisoryService;
import com.aorun.attorney.util.DateFormat;
import com.aorun.attorney.util.jsonp.Jsonp;
import com.aorun.attorney.util.jsonp.Jsonp_data;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 律师_回复咨询记录
 * Created by bysocket on 07/02/2017.
 */
@RequestMapping("/attorney")
@RestController
public class WorkerAttorneyReplyAdvisoryRestController {

    private static final Log LOGGER = LogFactory.getLog(WorkerAttorneyReplyAdvisoryRestController.class);
    @Autowired
    private WorkerAttorneyReplyAdvisoryService workerAttorneyReplyAdvisoryService;

    @Autowired
    private WorkerAdvisoryService workerAdvisoryService;


    /**
     * @param sid
     * @param advisoryId       咨询ID
     * @param requestTimePoint 当前请求时间点
     * @param isfirstPoint     第一次请求为y,第二次之后请求为n,
     *                         * @return
     */
    //1.维权&留言---咨询中接口
    @RequestMapping(value = "/workerAttorneyReplyAdvisoryList", method = RequestMethod.GET)
    public Object workerAttorneyReplyAdvisoryList(
            @RequestParam(name = "sid", required = true, defaultValue = "") String sid,
            @RequestParam(value = "advisoryId") Long advisoryId,
            @RequestParam(value = "requestTimePoint", required = true, defaultValue = "") String requestTimePoint,
            @RequestParam(value = "isfirstPoint", required = true, defaultValue = "") String isfirstPoint
    ) {

        WorkerAttorney workerAttorney = WorkerAttoryneyCacheInfo.get(sid);
        Long attorneyId = workerAttorney.getId();


        HashMap<String, Object> dataMap = new HashMap<String, Object>();
        List<WorkerAttorneyReplyAdvisory> workerAttorneyReplyAdvisoryList = workerAttorneyReplyAdvisoryService.getWorkerAttorneyReplyAdvisoryListByAttorneyId(attorneyId, advisoryId, requestTimePoint, isfirstPoint);

        if (workerAttorneyReplyAdvisoryList != null && workerAttorneyReplyAdvisoryList.size() > 0) {
            requestTimePoint = DateFormat.dateToString(workerAttorneyReplyAdvisoryList.get(workerAttorneyReplyAdvisoryList.size() - 1).getReplyTime());
        }

        List<HashMap<String, Object>> dataMapList = new ArrayList<>();
        for (WorkerAttorneyReplyAdvisory workerAttorneyReplyAdvisory : workerAttorneyReplyAdvisoryList) {
            HashMap<String, Object> myDataMap = new HashMap<String, Object>();
            myDataMap.put("replyType", workerAttorneyReplyAdvisory.getReplyType());
            myDataMap.put("replyContent", workerAttorneyReplyAdvisory.getReplyContent());
//                    Long attorneyId = workerAttorneyReplyAdvisory.getAttorneyId();//律师ID
//                    Long myworkerId = workerAttorneyReplyAdvisory.getWorkerId();// 工会用户ID
//                    WorkerMember myworkerMember = workerMemberService.findWorkerMemberById(myworkerId);
//                    myDataMap.put("workerMemberImgPath", ImagePropertiesConfig.WORKERMEMBER_SERVER_PATH +myworkerMember.getImgPath()); //用户头像
//                    WorkerAttorney workerAttorney = workerAttorneyService.findWorkerAttorneyById(attorneyId);
//                    myDataMap.put("attorneyImgPath",ImagePropertiesConfig.WORKERATTORNEY_SERVER_PATH +workerAttorney.getImgPath()); //律师头像，
            //myDataMap.put("nickName",workerAttorney.getNickName());// 律师昵称
            //myDataMap.put("replyTime", DateFriendlyShow.showTimeText(workerAttorneyReplyAdvisory.getReplyTime()));
            myDataMap.put("replyTime", DateFormat.dateToString(workerAttorneyReplyAdvisory.getReplyTime()));
            dataMapList.add(myDataMap);

        }
        dataMap.put("requestTimePoint", requestTimePoint);
        dataMap.put("workerAttorneyReplyAdvisoryList", dataMapList);


        return Jsonp_data.success(dataMap);
    }

    /**
     * @param sid
     * @param advisoryId 咨询ID
     */
    //2.维权&留言---聊天详情接口
    @RequestMapping(value = "/allWorkerAttorneyReplyAdvisoryList", method = RequestMethod.GET)
    public Object allWorkerAttorneyReplyAdvisoryList(
            @RequestParam(name = "sid", required = true, defaultValue = "") String sid,
            @RequestParam(value = "advisoryId") Long advisoryId
    ) {

        WorkerAttorney workerAttorney = WorkerAttoryneyCacheInfo.get(sid);
        Long attorneyId = workerAttorney.getId();

        HashMap<String, Object> dataMap = new HashMap<String, Object>();
        List<WorkerAttorneyReplyAdvisory> workerAttorneyReplyAdvisoryList = workerAttorneyReplyAdvisoryService.getAllWorkerAttorneyReplyAdvisoryListByAttorneyId(attorneyId, advisoryId);

        List<HashMap<String, Object>> dataMapList = new ArrayList<>();
        for (WorkerAttorneyReplyAdvisory workerAttorneyReplyAdvisory : workerAttorneyReplyAdvisoryList) {
            HashMap<String, Object> myDataMap = new HashMap<String, Object>();
            myDataMap.put("replyType", workerAttorneyReplyAdvisory.getReplyType());//1-律师回复，2-咨询人回复
            myDataMap.put("replyContent", workerAttorneyReplyAdvisory.getReplyContent());
            myDataMap.put("replyTime", DateFormat.dateToString(workerAttorneyReplyAdvisory.getReplyTime()));
            dataMapList.add(myDataMap);
        }
        dataMap.put("workerAttorneyReplyAdvisoryList", dataMapList);
        return Jsonp_data.success(dataMap);
    }


    //维权&留言---回复咨询接口
    @RequestMapping(value = "/workerAttorneyReplyAdvisory", method = RequestMethod.POST)
    public Object createWorkerAttorneyReplyAdvisory(@RequestParam(name = "sid", required = true, defaultValue = "") String sid,
                                                    @RequestParam(value = "advisoryId", required = true, defaultValue = "") Long advisoryId,
                                                    @RequestParam(value = "replyContent", required = true, defaultValue = "") String replyContent
    ) {
        WorkerAdvisory workerAdvisory = workerAdvisoryService.findWorkerAdvisoryById(advisoryId);
        if (workerAdvisory != null) {
            WorkerAttorneyReplyAdvisory workerAttorneyReplyAdvisory = new WorkerAttorneyReplyAdvisory();
            workerAttorneyReplyAdvisory.setWorkerId(workerAdvisory.getWorkerId());
            workerAttorneyReplyAdvisory.setAdvisoryId(Long.valueOf(advisoryId));
            workerAttorneyReplyAdvisory.setAttorneyId(workerAdvisory.getAttorneyId());
            workerAttorneyReplyAdvisory.setReplyContent(replyContent);
            workerAttorneyReplyAdvisory.setReplyType(1);//1-律师回复，2-咨询人回复
            workerAttorneyReplyAdvisoryService.saveWorkerAttorneyReplyAdvisory(workerAttorneyReplyAdvisory);
        } else {
            return Jsonp.bussiness_tips_code("暂未找到对应数据!");
        }
        return Jsonp.success();
    }

}
