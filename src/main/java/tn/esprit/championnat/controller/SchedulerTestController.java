package tn.esprit.championnat.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.championnat.services.SchedulerService;

@RestController
@AllArgsConstructor
@RequestMapping("/scheduler")
public class SchedulerTestController {

    private final SchedulerService schedulerService;

    @GetMapping("/test51")
    public String test51() {
        schedulerService.testerScheduler51();
        return "Scheduler 5.1 exécuté";
    }

    @GetMapping("/test52")
    public String test52() {
        schedulerService.testerScheduler52();
        return "Scheduler 5.2 exécuté";
    }

    @GetMapping("/test53")
    public String test53() {
        schedulerService.testerScheduler53();
        return "Scheduler 5.3 exécuté";
    }
}