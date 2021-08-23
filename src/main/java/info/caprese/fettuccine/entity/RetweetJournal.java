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
@Table(name = "RETWEET_JOURNAL")
public class RetweetJournal implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer retweetJournalId;

	private String targetDate;

	private Long statusId;

	private Long userId;

	private String userName;

	private String userScreenName;

	private LocalDateTime insertDate;

	private LocalDateTime updateDate;

	@Version
	private int version;

	private boolean deleteFlag;
}