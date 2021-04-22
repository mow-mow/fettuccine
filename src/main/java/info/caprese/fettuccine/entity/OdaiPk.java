package info.caprese.fettuccine.service;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class OdaiPk implements Serializable {
    public OdaiPk() {

    }
    public OdaiPk(String targetDate, Integer odaiId) {
        this.targetDate = targetDate;
        this.odaiId = odaiId;
    }
    private String targetDate;
    private Integer odaiId;
}
