package org.name.app.controller;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.name.app.SpringStageLoader;
import org.name.model.Product;

import java.io.IOException;

public class ProductDetailsModalStage extends Stage {
    private Label name;
    private Label guid;
    private Label quantity;
    private Label price;
    private Label costOfAll;
    private Label tax;

    public ProductDetailsModalStage() {
        this.initModality(Modality.WINDOW_MODAL);
        this.centerOnScreen();
        try {

            Scene scene = SpringStageLoader.loadScene("productDetails");
            this.setScene(scene);
            name = (Label) scene.lookup("#name");
            guid = (Label) scene.lookup("#guid");
            quantity = (Label) scene.lookup("#quantity");
            price = (Label) scene.lookup("#price");
            costOfAll = (Label) scene.lookup("#costOfAll");
            tax = (Label) scene.lookup("#tax");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showDetails(Product product) {
        name.setText(product.getName());
        guid.setText(product.getGuid());
        quantity.setText(String.valueOf(product.getQuantity()));
        price.setText(product.getPrice());
        costOfAll.setText("$" + getCostOfAll(product));
        tax.setText(String.valueOf(product.getTax()) + " %");
        setTitle("Детали продукта: " + product.getName());
        show();
    }

    private String getCostOfAll(Product product) {
        int quantity = product.getQuantity();
        double priceOfOne = Double.parseDouble(product
                .getPrice()
                .replace("$", ""));
        return String.valueOf(quantity * priceOfOne);
    }
}