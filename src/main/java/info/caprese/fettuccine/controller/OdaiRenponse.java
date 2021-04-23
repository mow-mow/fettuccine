package info.caprese.fettuccine.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

//@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OdaiRenponse implements Serializable {
    private Result result;
    private String date;
    @JsonProperty("theme_names")
    private List<String> themeNames;
    @JsonProperty("error_msg")
    private String errorMsg;
}
