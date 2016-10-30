package org.dcsc.utilities.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Converter(autoApply = true)
public class ZonedDateTimeConverter implements AttributeConverter<ZonedDateTime, Timestamp> {
    
    @Override
    public Timestamp convertToDatabaseColumn(ZonedDateTime attribute) {
        return (attribute == null) ? null : Timestamp.from(attribute.toInstant());
    }

    @Override
    public ZonedDateTime convertToEntityAttribute(Timestamp dbData) {
        return (dbData == null) ? null : ZonedDateTime.ofInstant(dbData.toInstant(), ZoneId.of("UTC"));
    }
}
