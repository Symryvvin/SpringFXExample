package org.name.app;

import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class Launcher extends Application {
    private static ClassPathXmlApplicationContext context;

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Инициализируем контекст
     */
    @Override
    public void init() {
        context = new ClassPathXmlApplicationContext("application-context.xml");
    }

    @Override
    public void start(Stage stage) throws IOException {
        SpringStageLoader.loadMain().show();
    }

    /**
     * Освобождаем контекст
     */
    @Override
    public void stop() throws IOException {
        context.close();
    }
}