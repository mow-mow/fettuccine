package info.caprese.fettuccine.service;

import info.caprese.fettuccine.controller.OdaiRenponse;
import info.caprese.fettuccine.entity.OdaiState;
import info.caprese.fettuccine.logic.OdaiApiLogic;
import info.caprese.fettuccine.logic.TweetLogic;
import info.caprese.fettuccine.model.OdaiStateRepository;
import info.caprese.fettuccine.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import twitter4j.Status;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class OdaiBotService {

    @Autowired
    OdaiApiLogic odaiApiLogic;
    @Autowired
    TweetLogic tweetLogic;
    @Autowired
    private OdaiStateRepository odaiStateRepository;

    public void announcement() {

        OdaiRenponse odai = odaiApiLogic.call();

        String text = generateTweetAnnouncement(odai);


        tweetLogic.tweet(text);
        Optional<OdaiState> odaiState = odaiStateRepository.findById(DateUtil.toLocalDateTime(odai.getDate(), "yyyy-MM-dd"));
        odaiState.ifPresent(s -> {
            s.setStatus("1");
            s.setAnnouncementStatus("0");
            s.setUpdateDateTime(LocalDateTime.now());
            odaiStateRepository.save(s);
        });
    }

    public void start() {

        OdaiRenponse odai = odaiApiLogic.call();

        String text = generateTweetStart(odai);

        tweetLogic.tweet(text);
        Optional<OdaiState> odaiState = odaiStateRepository.findById(DateUtil.toLocalDateTime(odai.getDate(), "yyyy-MM-dd"));
        odaiState.ifPresent(s -> {
            s.setStartStatus("0");
            s.setUpdateDateTime(LocalDateTime.now());
            odaiStateRepository.save(s);
        });
    }

    public void retweetPostedWorks() {
        String targetOdaiHashTag = createTargetOdaiHashTag();

        List<Status> result = tweetLogic.search(targetOdaiHashTag + " filter:media -filter:retweets");

        tweetLogic.retweet(result);
        Optional<OdaiState> odaiState = odaiStateRepository.findById(LocalDateTime.now().minusDays(1L).with(LocalTime.of(0, 0));
        odaiState.ifPresent(s -> {
            s.setRetweetStatus("0");
            s.setStatus("0");
            s.setUpdateDateTime(LocalDateTime.now());
            odaiStateRepository.save(s);
        });
    }

    private String createTargetOdaiHashTag() {
        return "#mow版深夜のお絵描き60分一本勝負_" + DateUtil.format(LocalDateTime.now().minusDays(1L), "yyyyMMdd");
    }


    private String generateTweetAnnouncement(OdaiRenponse odai) {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setTemplateMode(TemplateMode.TEXT);
        templateResolver.setCharacterEncoding("UTF-8");

        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        // ThymeleafのContextインスタンスを作成する
        Context context = new Context();
        // テンプレート内の変数に値を設定する
        context.setVariable("date", odai.getDate().replaceAll("-",""));
        context.setVariable("themeNames", odai.getThemeNames());
        // テンプレートを処理する
        return templateEngine.process("tweet_announcement.txt", context);
    }


    private String generateTweetStart(OdaiRenponse odai) {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setTemplateMode(TemplateMode.TEXT);
        templateResolver.setCharacterEncoding("UTF-8");

        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        // ThymeleafのContextインスタンスを作成する
        Context context = new Context();
        // テンプレート内の変数に値を設定する
        context.setVariable("date", odai.getDate().replaceAll("-",""));
        context.setVariable("themeNames", odai.getThemeNames());
        // テンプレートを処理する
        return templateEngine.process("tweet_start.txt", context);
    }
}
