package org.name.app;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SpringStageLoader implements ApplicationContextAware {
    private static ApplicationContext staticContext;
    //инъекция заголовка главного окна
    @Value("${title}")
    private String appTitle;
    private static String staticTitle;

    private static final String FXML_DIR = "/view/fxml/";
    private static final String MAIN_STAGE = "main";

    /**
     * Загрузка корневого узла и его дочерних элементов из fxml шаблона
     * @param fxmlName наименование *.fxml файла в ресурсах
     * @return объект типа Parent
     * @throws IOException бросает исключение ввода-вывода
     */
    private static Parent load(String fxmlName) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        // setLocation необходим для корректной загрузки включенных шаблонов, таких как productTable.fxml,
        // без этого получим исключение javafx.fxml.LoadException: Base location is undefined.
        loader.setLocation(SpringStageLoader.class.getResource(FXML_DIR + fxmlName + ".fxml"));
        // setLocation необходим для корректной того чтобы loader видел наши кастомные котнролы
        loader.setClassLoader(SpringStageLoader.class.getClassLoader());
        loader.setControllerFactory(staticContext::getBean);
        return loader.load(SpringStageLoader.class.getResourceAsStream(FXML_DIR + fxmlName + ".fxml"));
    }

    /**
     * Реализуем загрузку главной сцены. На закрытие сцены стоит обработчик, которых выходит из приложения
     * @return главную сцену
     * @throws IOException бросает исключение ввода-вывода
     */
    public static Stage loadMain() throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(load(MAIN_STAGE)));
        stage.setOnHidden(event -> Platform.exit());
        stage.setTitle(staticTitle);
        return stage;
    }

    /**
     * Передаем данные в статические поля в реализации метода интерфейса ApplicationContextAware,
     т.к. методы их использующие тоже статические
     */
    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        SpringStageLoader.staticContext = context;
        SpringStageLoader.staticTitle = appTitle;
    }
}