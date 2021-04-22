package info.caprese.fettuccine.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OdaiRenponse {
    private Result result;
    private String date;
    private List<String> themeNames;
    private String errorMsg;
}
