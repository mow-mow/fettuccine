package info.caprese.fettuccine.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TWEET_JOURNAL")
public class TweetJournal implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer tweetJournalId;

	private String targetDate;

	private String message;

	private String status;

	private Long statusId;

	private LocalDateTime insertDate;

	private LocalDateTime updateDate;

	@Version
	private int version;

	private boolean deleteFlag;
}