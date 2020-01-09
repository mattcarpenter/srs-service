package net.mattcarpenter.benkyou.srsservice.controller;

import net.mattcarpenter.benkyou.srsservice.model.StatusResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatusController {

    @GetMapping("/status")
    public StatusResponse status() {
        return new StatusResponse("healthy");
    }
}
