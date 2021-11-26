package by.xenon28082.shop.controller.validators;

import java.util.ArrayList;
import java.util.List;

public class ValidatorImpl implements Validator {

    private static final ValidatorImpl INSTANCE = new ValidatorImpl();

    private ValidatorImpl(){}

    public static ValidatorImpl getInstance(){
        return INSTANCE;
    }

    @Override
    public boolean validateIsEmpty(List<String> params) {
        for (String param : params) {
            if (param.isEmpty() || param == null) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean validateIsNotPositive(List<String> params) {
        for (String param : params) {
            try {
                if (Long.parseLong(param) < 1) {
                    return true;
                }
            }catch (Exception e){
                continue;
            }
        }
        return false;
    }

    @Override
    public boolean validateIsNull(Object obj) {
        if(obj == null){
            return true;
        }
        return false;
    }

    @Override
    public List<String> convertToStringList(List<Long> params) {
        List<String> strings = new ArrayList<>();
        for (Long param : params) {
            strings.add(String.valueOf(param));
        }
        return strings;
    }


}
