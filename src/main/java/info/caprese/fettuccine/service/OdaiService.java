package info.caprese.fettuccine.service;

import info.caprese.fettuccine.entity.Odai;
import info.caprese.fettuccine.entity.OdaiM;
import info.caprese.fettuccine.model.OdaiMRepository;
import info.caprese.fettuccine.model.OdaiRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class OdaiService {

    @Autowired
    private OdaiRepository odaiRepository;

    @Autowired
    private OdaiMRepository odaiMRepository;

    public List<Odai> findOdai(String date) {
        List<Odai> odaiList = odaiRepository.findByTargetDate(date);
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
        odaiMList.forEach(
                m -> {
                    Odai odai = new Odai();
                    odai.setTargetDate(date);
                    odai.setOdaiId(m.getOdaiId());
                    odai.setOdaiName(m.getOdaiName());
                    odai.setUpdateDateTime(LocalDateTime.now());
                    odai.setInsertDateTime(LocalDateTime.now());
                    odaiList.add(odai);
                }

        );

        log.info("お題保存開始");
        odaiRepository.saveAll(odaiList);
        log.info("お題保存終了");
        log.info("お題生成処理終了");
        return odaiList;
    }
}
