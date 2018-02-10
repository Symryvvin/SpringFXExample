package org.name.app;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class Launcher extends Application {
    private static ClassPathXmlApplicationContext context;
    private Stage splashScreen;

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Контекст инициализируется не в UI потоке. Поэтому в методе init() UI поток вызывается через Platform.runLater()
     * @throws Exception
     */
    @Override
    public void init() throws Exception {
        Platform.runLater(this::showSplash);
        Thread.sleep(10000);
        context = new ClassPathXmlApplicationContext("application-context.xml");
        Platform.runLater(this::closeSplash);
    }

    @Override
    public void start(Stage stage) throws IOException {
        SpringStageLoader.loadMain().show();
    }

    /**
     * Освобождаем контекст
     */
    @Override
    public void stop() {
        context.close();
    }

    /**
     * Загружаем заставку обычным способом. Выставляем везде прозрачность
     */
    private void showSplash() {
        try {
            splashScreen = new Stage(StageStyle.TRANSPARENT);
            splashScreen.setTitle("Splash");
            Parent root = FXMLLoader.load(getClass().getResource("/view/fxml/splash.fxml"));
            Scene scene = new Scene(root, Color.TRANSPARENT);
            splashScreen.setScene(scene);
            splashScreen.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Закрывает сцену с заставкой
     */
    private void closeSplash() {
        splashScreen.close();
    }
}