package christmas.service;

import java.util.regex.Pattern;

public class InputValidator {

    private static final String NUMBER = "^[0-9]+$";

    public void validateInputDataIsNumber(String inputData) {
        if (!Pattern.matches(NUMBER, inputData)) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }

    public void validateInputDataInDateRange(int date) {
        if (date > 31 || date < 1) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요");
        }
    }
}
