package com.spartaMonster.train.batch.train.business.controller.admin;

import com.spartaMonster.train.batch.train.business.req.TrainStationQueryReq;
import com.spartaMonster.train.batch.train.business.req.TrainStationSaveReq;
import com.spartaMonster.train.batch.train.business.resp.TrainStationQueryResp;
import com.spartaMonster.train.batch.train.business.service.TrainStationService;
import com.spartaMonster.train.common.resp.CommonResp;
import com.spartaMonster.train.common.resp.PageResp;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/train-station")
public class TrainStationAdminController {

    @Resource
    private TrainStationService trainStationService;

    @PostMapping("/save")
    public CommonResp<Object> save(@Valid @RequestBody TrainStationSaveReq req) {
        trainStationService.save(req);
        return new CommonResp<>();
    }

    @GetMapping("/query-list")
    public CommonResp<PageResp<TrainStationQueryResp>> queryList(@Valid TrainStationQueryReq req) {
        PageResp<TrainStationQueryResp> list = trainStationService.queryList(req);
        return new CommonResp<>(list);
    }

    @DeleteMapping("/delete/{id}")
    public CommonResp<Object> delete(@PathVariable Long id) {
        trainStationService.delete(id);
        return new CommonResp<>();
    }

}
