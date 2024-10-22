package models.xml;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeAdapter extends XmlAdapter<String, LocalDateTime> {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    @Override
    public String marshal(LocalDateTime v) throws Exception {
        return v == null ? null : v.format(formatter);
    }

    @Override
    public LocalDateTime unmarshal(String v) throws Exception {
        return v == null ? null : LocalDateTime.parse(v, formatter);
    }
}
