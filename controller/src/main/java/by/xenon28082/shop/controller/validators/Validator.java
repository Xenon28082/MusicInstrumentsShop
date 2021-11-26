package by.xenon28082.shop.controller.validators;

import java.util.List;

public interface Validator {

    boolean validateIsEmpty(List<String> params);
    boolean validateIsNotPositive(List<String> params);
    boolean validateIsNull(Object obj);
    List<String> convertToStringList(List<Long> params);

}
