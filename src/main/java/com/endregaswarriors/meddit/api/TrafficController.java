package com.endregaswarriors.meddit.api;

import com.endregaswarriors.meddit.models.api.LogTraffic;
import com.endregaswarriors.meddit.services.TraffickerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/meddit/traffic")
public class TrafficController {

    TraffickerService traffickerService;

    public TrafficController(TraffickerService traffickerService) {
        this.traffickerService = traffickerService;
    }

    @ApiOperation(value = "Save users traffic")
    @PostMapping("")
    public void createThread(@RequestBody LogTraffic traffic){
        traffickerService.logTraffic(traffic.getSubreddit_id(), traffic.getUser_id());
    }

}
