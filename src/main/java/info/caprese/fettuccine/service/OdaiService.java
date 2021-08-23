package info.caprese.fettuccine.service;

import info.caprese.fettuccine.entity.Odai;
import info.caprese.fettuccine.entity.OdaiM;
import info.caprese.fettuccine.entity.OdaiState;
import info.caprese.fettuccine.model.OdaiMRepository;
import info.caprese.fettuccine.model.OdaiRepository;
import info.caprese.fettuccine.model.OdaiStateRepository;
import info.caprese.fettuccine.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@Slf4j
public class OdaiService {

    @Autowired
    private OdaiRepository odaiRepository;

    @Autowired
    private OdaiStateRepository odaiStateRepository;

    @Autowired
    private OdaiMRepository odaiMRepository;

    public List<Odai> findOdai(String date) {
        List<Odai> odaiList = odaiRepository.findByOdaiDateOrderByDisplayOrder(DateUtil.toLocalDateTime(date, "yyyy-MM-dd"));
        if (odaiList.size() != 0) {
            log.info("お題あり");
            return odaiList;
        }
        log.info("お題なし");
        return generatePasta(date);

    }

    private List<Odai> generatePasta(String date) {
        log.info("お題生成処理 開始");


        List<Odai> odaiList = new ArrayList<>();
        List<OdaiM> odaiMList = odaiMRepository.findOdaiMRandom();
        AtomicInteger i = new AtomicInteger();
        odaiMList.forEach(
                m -> {
                    Odai odai = new Odai();
                    odai.setOdaiDate(DateUtil.toLocalDateTime(date, "yyyy-MM-dd"));
                    odai.setTargetDate(StringUtils.left(date, 7));
                    odai.setOdaiId(m.getOdaiId());
                    odai.setDisplayOrder(i.incrementAndGet());
                    odai.setOdaiName(m.getOdaiName());
                    odai.setUpdateDateTime(LocalDateTime.now());
                    odai.setInsertDateTime(LocalDateTime.now());
                    odaiList.add(odai);

                }

        );

        OdaiState odaiState = new OdaiState();
        odaiState.setTargetDate(StringUtils.left(date, 7));
        odaiState.setOdaiDate(DateUtil.toLocalDateTime(date, "yyyy-MM-dd"));
        odaiState.setStatus("n");
        odaiState.setAnnouncementStatus("n");
        odaiState.setStartStatus("n");
        odaiState.setRetweetStatus("n");
        odaiState.setUpdateDateTime(LocalDateTime.now());
        odaiState.setInsertDateTime(LocalDateTime.now());
        odaiStateRepository.save(odaiState);

        log.info("お題保存開始");
        odaiRepository.saveAll(odaiList);
        log.info("お題保存終了");
        log.info("お題生成処理終了");
        return odaiList;
    }
}
