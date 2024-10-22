package models.xml;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public String marshal(LocalDate v) throws Exception {
        return v == null ? null : v.format(formatter);
    }

    @Override
    public LocalDate unmarshal(String v) throws Exception {
        return v == null ? null : LocalDate.parse(v, formatter);
    }
}
