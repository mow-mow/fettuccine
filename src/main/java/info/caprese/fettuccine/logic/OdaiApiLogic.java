package info.caprese.fettuccine.logic;

import info.caprese.fettuccine.controller.OdaiRenponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class OdaiApiLogic {

    @Autowired
    RestTemplate restTemplate;

    @Value("${app.odai-api-url}")
    String url;

    public OdaiRenponse call() {
        log.info("お題情報取得【開始】" + url);
        OdaiRenponse result = restTemplate.getForObject(url, OdaiRenponse.class);
        log.info("お題取得情報" + result.toString());
        log.info("お題情報取得【終了】");
        return result;
    }
}
