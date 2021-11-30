package by.xenon28082.shop.service.validators;

import java.util.List;

public interface Validator {

    boolean validateIsEmpty(List<String> params);
    boolean validateIsNotPositive(List<String> params);
    boolean validateIsNotPositive(long value);
    boolean validateIsNull(Object obj);
    List<String> convertToStringList(List<Long> params);

}
