package org.name.app.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.name.model.Product;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductTableController extends Controller{

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
        productTable.setRowFactory(rf -> {
            TableRow<Product> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    ProductDetailsModalStage stage = new ProductDetailsModalStage();
                    stage.showDetails(row.getItem());
                }
            });
            return row;
        });
    }

    /**
     * Заполняем таблицу данными из БД
     * @param products список продуктов
     */
    public void fillTable(List<Product> products) {
        productTable.setItems(FXCollections.observableArrayList(products));
    }
}