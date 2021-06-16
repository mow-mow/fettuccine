package info.caprese.fettuccine.service;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Embeddable
public class OdaiPk implements Serializable {
    public OdaiPk() {

    }
    public OdaiPk(LocalDateTime odaiDate, Integer odaiId) {
        this.odaiDate = odaiDate;
        this.odaiId = odaiId;
    }
    private LocalDateTime odaiDate;
    private Integer odaiId;
}
