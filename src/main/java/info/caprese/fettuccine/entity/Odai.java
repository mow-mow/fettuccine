package info.caprese.fettuccine.entity;

import info.caprese.fettuccine.service.OdaiPk;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "ODAI")
@IdClass(OdaiPk.class)
public class Odai {
    @Id
    private LocalDateTime odaiDate;
    private String targetDate;
    private Integer displayOrder;
    @Id
    private Integer odaiId;
    private String odaiName;
    private LocalDateTime updateDateTime;
    private LocalDateTime insertDateTime;
}
