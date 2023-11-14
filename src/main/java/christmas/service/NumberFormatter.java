package christmas.service;

import java.text.DecimalFormat;

public class NumberFormatter {

    private final static String NUMBER_FORMAT = "#,###";

    public static String convertToOutputFormat(int money) {
        DecimalFormat formatter = new DecimalFormat(NUMBER_FORMAT);
        return formatter.format(money);
    }

}
