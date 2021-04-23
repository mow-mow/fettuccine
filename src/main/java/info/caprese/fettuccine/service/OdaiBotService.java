package info.caprese.fettuccine.service;

import info.caprese.fettuccine.controller.OdaiRenponse;
import info.caprese.fettuccine.logic.OdaiApiLogic;
import info.caprese.fettuccine.logic.TweetLogic;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

@Service
@Slf4j
public class OdaiBotService {

    @Autowired
    OdaiApiLogic odaiApiLogic;
    @Autowired
    TweetLogic tweetLogic;

    public void announcement() {

        OdaiRenponse odai = odaiApiLogic.call();

        String text = generateTweetAnnouncement(odai);

        tweetLogic.tweet(text);

    }

    public void start() {

        OdaiRenponse odai = odaiApiLogic.call();

        String text = generateTweetStart(odai);

        tweetLogic.tweet(text);

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
