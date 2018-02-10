package org.name.app.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.name.model.Product;

import java.util.List;

public class ProductTableController {

    @FXML private TableColumn<Integer, Product> id;
    @FXML private TableColumn<String, Product> name;
    @FXML private TableColumn<Integer, Product> quantity;
    @FXML private TableColumn<String, Product> price;
    @FXML private TableView<Product> productTable;

    /**
     * Устанавливаем value factory для полей таблицы
     */
    public void initialize() {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    /**
     * Заполняем таблицу данными из БД
     * @param products список продуктов
     */
    public void fillTable(List<Product> products) {
        productTable.setItems(FXCollections.observableArrayList(products));
    }
}