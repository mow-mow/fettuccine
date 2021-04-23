package info.caprese.fettuccine.logic;

import info.caprese.fettuccine.entity.TweetJournal;
import info.caprese.fettuccine.model.TweetJournalRepository;
import info.caprese.fettuccine.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

import java.time.LocalDateTime;

@Slf4j
@Service
public class TweetLogic {

    @Autowired
    private Twitter twitter;

    @Autowired
    TweetJournalRepository tweetJournalRepository;

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





