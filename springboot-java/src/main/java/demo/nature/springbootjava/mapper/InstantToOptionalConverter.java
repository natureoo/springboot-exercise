package demo.nature.springbootjava.mapper;

import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Optional;

/**
 * @author nature
 * @date 14/8/2020 5:31 下午
 * @email 924943578@qq.com
 */
public class InstantToOptionalConverter extends BidirectionalConverter<Optional, Instant> {
    @Override
    public Instant convertTo(Optional optional, Type<Instant> type, MappingContext mappingContext) {
        LocalDate localDate = (LocalDate) optional.orElse(LocalDate.now());
        return Instant.from(localDate);
    }

    @Override
    public Optional convertFrom(Instant instant, Type<Optional> type, MappingContext mappingContext) {
        LocalDate localDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();
        return Optional.of(localDate);
    }
}
