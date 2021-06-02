package info.caprese.fettuccine.logic;

import info.caprese.fettuccine.entity.TweetJournal;
import info.caprese.fettuccine.model.TweetJournalRepository;
import info.caprese.fettuccine.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import twitter4j.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class TweetLogic {

    @Autowired
    private Twitter twitter;

    @Autowired
    TweetJournalRepository tweetJournalRepository;


    public List<Status> search(String queryStr) {
        log.info("ツイート検索処理 [開始]：" + queryStr);
        List<Status> statuses = new ArrayList<>();
        Query query = new Query(queryStr);
        try {
            QueryResult result = twitter.search(query);
            for (Status status : result.getTweets()) {
                log.info("検索結果 [" + status.getId() + "] @" + status.getUser().getScreenName() + ":" + status.getText());
                statuses.add(status);
            }
        } catch (TwitterException e) {
            log.error("検索時にエラーが発生しました", e);
        }
        log.info("ツイート検索処理 [終了]");
        return statuses;
    }

    public void retweet(List<Status> statuses) {
        log.info("リツイート処理 [開始]");
        for (Status status : statuses) {
            try {
                if (status.isRetweetedByMe()) {
                    log.info("既にリツイートされています:" + status.getId());
                    continue;
                }
                log.info("リツイート:" + status.getId());
                twitter.retweetStatus(status.getId());
            } catch (TwitterException e) {
                log.error("リツイート処理時にエラーが発生しました", e);
            }
        }
        log.info("リツイート処理 [終了]");
    }

    public boolean tweet(String message) {

        TweetJournal tweetJournal = generateTweetJournal(message);
        tweetJournalRepository.save(tweetJournal);

        Status status;
        try {
            status = twitter.updateStatus(message);
        } catch (TwitterException e) {
            log.error("ツイート - [NG]", e);
            return false;
        }

        updateTweetJournal(tweetJournal, status);
        tweetJournalRepository.save(tweetJournal);

        log.info("ツイート - [OK]");
        log.info("message : " + status.getText());
        return true;
    }

    public TweetJournal generateTweetJournal(String message) {
        LocalDateTime sysdate = LocalDateTime.now();
        TweetJournal tweetJournal = new TweetJournal();
        tweetJournal.setTargetDate(DateUtil.format(sysdate, "yyyyMM"));
        tweetJournal.setMessage(message);
        tweetJournal.setUpdateDate(sysdate);
        tweetJournal.setInsertDate(sysdate);
        tweetJournal.setDeleteFlag(false);
        return tweetJournal;
    }

    public void updateTweetJournal(TweetJournal tweetJournal, Status status) {
        LocalDateTime sysdate = LocalDateTime.now();
        tweetJournal.setStatus("OK");
        tweetJournal.setStatusId(status.getId());
        tweetJournal.setUpdateDate(sysdate);
    }


}





