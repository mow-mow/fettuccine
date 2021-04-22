package info.caprese.fettuccine.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "ODAI_M")
public class OdaiM {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer odaiId;
    private String odaiName;
    private LocalDateTime updateDateTime;
    private LocalDateTime insertDateTime;
}
