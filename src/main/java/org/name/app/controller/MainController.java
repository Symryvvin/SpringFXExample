package org.name.app.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainController {
    @FXML private Button load;

    /**
     * Обработка нажатия кнопки загрузки товаров
     */
    @FXML
    public void onClickLoad() {
        System.out.println("Загружаем...");
        // TODO: Реализовать получение данный из БД с помощью DAO класса
        // TODO: и передать полученный данные в таблицу для отображения
    }
}