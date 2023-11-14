package christmas;

import christmas.controller.SystemController;
import christmas.view.OutputView;

public class Application {
    public static void main(String[] args) {

        SystemController systemController = new SystemController(new OutputView());
        systemController.run();
    }
}
