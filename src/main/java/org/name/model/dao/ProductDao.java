package org.name.model.dao;

import org.name.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

@Component
public class ProductDao {

    private JdbcTemplate template;

    /**
     * Инжектим dataSource и создаем объект JdbcTemplate
     */
    @Autowired
    public ProductDao(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    /**
     * Получаем весь список продуктов из таблицы. Т.к. класс Product построен на концепции JavaBean
     * мы можем воспользоваться классом BeanPropertyRowMapper.
     */
    public List<Product> getAllProducts(){
        String sql = "SELECT * FROM product";
        return template.query(sql, new BeanPropertyRowMapper<>(Product.class));
    }
}