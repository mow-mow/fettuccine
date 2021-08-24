package info.caprese.fettuccine.entity;

import info.caprese.fettuccine.service.OdaiPk;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "ODAI_STATE")
public class OdaiState {
    @Id
    private LocalDateTime odaiDate;
    private String targetDate;
    private String status;
    private String announcementStatus;
    private String startStatus;
    private String retweetStatus;
    private LocalDateTime updateDateTime;
    private LocalDateTime insertDateTime;
    @Version
    private int version;
}
