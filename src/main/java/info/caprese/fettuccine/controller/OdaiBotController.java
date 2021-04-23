package info.caprese.fettuccine.controller;

import info.caprese.fettuccine.service.OdaiBotService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
class OdaiBotController {

    @Autowired
    OdaiBotService service;

    @Scheduled(cron = "${scheduler.cron}")
    public void tweetAnnouncement() {
        log.info("お題アナウンスつぶやき 【開始】");
        service.announcement();
        log.info("お題アナウンスつぶやき  [終了]");
    }
    @Scheduled(cron = "${scheduler.cron2}")
    public void tweetStart() {
        log.info("お題開始つぶやき 【開始】");
        service.start();
        log.info("お題開始つぶやき  [終了]");
    }
}
