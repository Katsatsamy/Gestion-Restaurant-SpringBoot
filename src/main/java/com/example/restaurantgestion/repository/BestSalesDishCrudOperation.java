package com.example.restaurantgestion.repository;

import com.example.restaurantgestion.rest.BestSalesDish;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BestSalesDishCrudOperation implements CrudOperations<BestSalesDish> {
    private DataSource dataSource = new DataSource();


    @Override
    public List<BestSalesDish> getAll(int page, int size) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<BestSalesDish> getAllByDate(LocalDateTime startDate, LocalDateTime endDate, Double X) {
        if(startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("startDate must be before endDate");
        }
        if(X <= 0) {
            throw new IllegalArgumentException("X must be positive");
        }

        List<BestSalesDish> bestSalesDishes = new ArrayList<>();
        String sql = "SELECT \n" +
                "    d.name AS dish_name,\n" +
                "    SUM(dorder.quantity) AS total_quantity_sold,\n" +
                "    SUM(dorder.quantity * d.unit_price) AS total_revenue\n" +  // Utilise le prix du plat
                "FROM dish_order dorder\n" +
                "JOIN orders o ON dorder.id_order = o.id\n" +
                "JOIN dish d ON dorder.id_dish = d.id\n" +
                "WHERE o.status = 'TERMINE'\n" +
                "  AND o.date BETWEEN ? AND ?\n" +
                "GROUP BY d.id, d.name\n" +  // Ajout de d.id pour certaines versions de PostgreSQL
                "ORDER BY total_quantity_sold DESC\n" +
                "LIMIT ?;\n";

        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setTimestamp(1, Timestamp.valueOf(startDate));
            statement.setTimestamp(2, Timestamp.valueOf(endDate));
            statement.setInt(3, X.intValue());  // Conversion de Double à int

            try(ResultSet resultSet = statement.executeQuery()){
                while(resultSet.next()) {
                    BestSalesDish bestSalesDish = new BestSalesDish(
                            resultSet.getString("dish_name"),
                            resultSet.getDouble("total_quantity_sold"),
                            resultSet.getDouble("total_revenue")
                    );
                    bestSalesDishes.add(bestSalesDish);
                }
            }
            return bestSalesDishes;
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching best selling dishes", e);
        }
    }

    @Override
    public BestSalesDish findById(String id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<BestSalesDish> findByName(String name) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<BestSalesDish> saveAll(List<BestSalesDish> list) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
