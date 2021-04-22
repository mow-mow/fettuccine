package info.caprese.fettuccine.controller;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Component
public class OdaiValidator {
    public boolean validateDate(String date) {
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            dtf.format(LocalDate.parse(date, dtf));
            return true;
        } catch (DateTimeParseException dtp) {
            return false;
        }
    }
}
