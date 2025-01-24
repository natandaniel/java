package jdbc;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

@ReadingConverter
class StringToCoffeeMakerTypeConverter implements Converter<String, CoffeeMakerType> {

  @Override
  public CoffeeMakerType convert(String source) {
    return CoffeeMakerType.AEROPRESS;
  }
}