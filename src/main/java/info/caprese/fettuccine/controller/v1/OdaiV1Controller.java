package info.caprese.fettuccine.controller.v1;

import info.caprese.fettuccine.controller.OdaiRenponse;
import info.caprese.fettuccine.controller.OdaiValidator;
import info.caprese.fettuccine.controller.Result;
import info.caprese.fettuccine.entity.Odai;
import info.caprese.fettuccine.service.OdaiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
public class OdaiV1Controller {
    @Autowired
    OdaiValidator validator;
    @Autowired
    OdaiService service;

    @GetMapping(value = "/v1/")
    ResponseEntity<OdaiRenponse> pastaGet() {
        LocalDateTime sysDate = LocalDateTime.now();
        String date = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(sysDate);


        List<Odai> odaiList = service.findOdai(date);

        return exchangeResponse(date, odaiList);
    }

    @GetMapping(value = "/v1/{date}")
    ResponseEntity<OdaiRenponse> pastaDateGet(@PathVariable("date") String date) {
        if (!validator.validateDate(date)) {
            log.info("入力チェック - [NG]");
            return new ResponseEntity<OdaiRenponse>(OdaiRenponse.builder().result(Result.NG)
                    .errorMsg("日付の指定が変だぞ:" + date).build(), HttpStatus.OK);
        }
        log.info("入力チェック - [OK]");

        List<Odai> odaiList = service.findOdai(date);

        return exchangeResponse(date, odaiList);
    }

    private ResponseEntity<OdaiRenponse> exchangeResponse(String date,List<Odai> odaiList) {
        List<String> themeNames = new ArrayList<>();
        odaiList.forEach(odai -> {
                             themeNames.add(odai.getOdaiName());
    });
        return new ResponseEntity<OdaiRenponse>(
                OdaiRenponse.builder()
                        .result(Result.OK)
                        .date(date)
                        .themeNames(themeNames)
                        .build(), HttpStatus.OK);
    }
}
