package org.dcsc.utilities.converter;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import javax.persistence.AttributeConverter;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@RunWith(MockitoJUnitRunner.class)
public class LocalDateTimeConverterTest {
    private static final AttributeConverter<LocalDateTime, Timestamp> converter = new LocalDateTimeConverter();

    @Test
    public void convertToDatabaseColumnWithNullLocalDateTime() {
        Assert.assertNull(converter.convertToDatabaseColumn(null));
    }

    @Test
    public void convertToDatabaseColumn() {
        LocalDateTime localDateTime = LocalDateTime.now();

        Assert.assertEquals(Timestamp.valueOf(localDateTime), converter.convertToDatabaseColumn(localDateTime));
    }

    @Test
    public void convertToEntityAttributeWithNullTimestamp() {
        Assert.assertNull(converter.convertToEntityAttribute(null));
    }

    @Test
    public void convertToEntityAttribute() {
        LocalDateTime localDateTime = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(localDateTime);

        Assert.assertEquals(localDateTime, converter.convertToEntityAttribute(timestamp));
    }
}