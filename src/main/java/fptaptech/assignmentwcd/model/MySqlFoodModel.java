package fptaptech.assignmentwcd.model;

import fptaptech.assignmentwcd.constant.SqlConstant;
import fptaptech.assignmentwcd.entity.Food;
import fptaptech.assignmentwcd.entity.entityEnum.FoodStatus;
import fptaptech.assignmentwcd.model.interfaceModel.FoodModel;
import fptaptech.assignmentwcd.util.ConnectionHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MySqlFoodModel implements FoodModel {
    @Override
    public boolean create(Food food) {
        try{
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SqlConstant.FOOD_INSERT);
            preparedStatement.setString(1, food.getName());
            preparedStatement.setString(2, food.getCode());
            preparedStatement.setDouble(3, food.getPrice());
            preparedStatement.setString(4, food.getThumbnail());
            preparedStatement.setString(5, food.getDescription());
            preparedStatement.setInt(6, food.getCategoryId());
            preparedStatement.setString(7, food.getCreatedAt().toString());
            preparedStatement.setString(8, food.getUpdatedAt().toString());
            preparedStatement.setInt(9, food.getCreatedBy());
            preparedStatement.setInt(10, food.getUpdatedBy());
            preparedStatement.setInt(11, food.getStatus().getValue());
            return preparedStatement.executeUpdate() > 0;
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(int id, Food food) {
        try{
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SqlConstant.FOOD_UPDATE);
            preparedStatement.setString(1, food.getName());
            preparedStatement.setString(2, food.getCode());
            preparedStatement.setDouble(3, food.getPrice());
            preparedStatement.setString(4, food.getThumbnail());
            preparedStatement.setString(5, food.getDescription());
            preparedStatement.setInt(6, food.getCategoryId());
            preparedStatement.setString(7, food.getCreatedAt().toString());
            preparedStatement.setString(8, food.getUpdatedAt().toString());
            if(food.getDeletedAt() != null) {
                preparedStatement.setString(9, food.getDeletedAt().toString());
            }else {
                preparedStatement.setString(9, null);
            }
            preparedStatement.setInt(10, food.getCreatedBy());
            preparedStatement.setInt(11, food.getUpdatedBy());
            preparedStatement.setInt(12, food.getDeletedBy());
            preparedStatement.setInt(13, food.getStatus().getValue());
            preparedStatement.setInt(14, id);
            return preparedStatement.executeUpdate() > 0;
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        try{
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SqlConstant.FOOD_DELETE);
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() > 0;
        }catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    @Override
    public Food findById(int id) {
        try{
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SqlConstant.FOOD_FIND_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()) {
                return resultSetToFood(rs);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Food findByCode(String slug) {
        try{
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SqlConstant.FOOD_FIND_BY_CODE);
            preparedStatement.setString(1, slug);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()) {
                return resultSetToFood(rs);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Food> findAll() {
        List<Food> foods = new ArrayList<>();
        try{
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SqlConstant.FOOD_FIND_ALL);
            preparedStatement.setInt(1, FoodStatus.ON_SALE.getValue());
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()) {
                Food food = resultSetToFood(rs);
                if(food != null) {
                    foods.add(food);
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return foods;
    }

    public Food resultSetToFood(ResultSet rs)
    {
        try{
            int id = Integer.parseInt(rs.getString(SqlConstant.FOOD_FIELD_ID));
            String name = rs.getString(SqlConstant.FOOD_FIELD_NAME);
            String code = rs.getString(SqlConstant.FOOD_FIELD_CODE);
            String thumbnail = rs.getString(SqlConstant.FOOD_FIELD_THUMBNAIL);
            String description = rs.getString(SqlConstant.FOOD_FIELD_DESCRIPTION);
            double price = rs.getDouble(SqlConstant.FOOD_FIELD_PRICE);
            int categoryId = rs.getInt(SqlConstant.FOOD_FIELD_CATEGORY_ID);
            LocalDateTime createdAt = rs.getTimestamp(SqlConstant.FIELD_CREATED_AT).toLocalDateTime();
            LocalDateTime updatedAt = rs.getTimestamp(SqlConstant.FIELD_UPDATED_AT).toLocalDateTime();
            LocalDateTime deletedAt = null;
            if(rs.getTimestamp(SqlConstant.FIELD_DELETED_AT) != null) {
                deletedAt = rs.getTimestamp(SqlConstant.FIELD_DELETED_AT).toLocalDateTime();
            }
            int createdBy = rs.getInt(SqlConstant.FIELD_CREATED_BY);
            int updatedBy = rs.getInt(SqlConstant.FIELD_UPDATED_BY);
            int deletedBy = rs.getInt(SqlConstant.FIELD_DELETED_BY);
            FoodStatus status = FoodStatus.of(rs.getInt(SqlConstant.CATEGORY_FIELD_STATUS));
            return Food.FoodBuilder.aFood()
                    .withId(id)
                    .withName(name)
                    .withCode(code)
                    .withCategoryId(categoryId)
                    .withDescription(description)
                    .withPrice(price)
                    .withThumbnail(thumbnail)
                    .withCreatedAt(createdAt)
                    .withUpdatedAt(updatedAt)
                    .withDeletedAt(deletedAt)
                    .withCreatedBy(createdBy)
                    .withUpdatedBy(updatedBy)
                    .withDeletedBy(deletedBy)
                    .withStatus(status)
                    .build();
        }catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
}