package fptaptech.assignmentwcd.entity;

import fptaptech.assignmentwcd.constant.ValidationConstant;
import fptaptech.assignmentwcd.entity.base.BaseEntity;
import fptaptech.assignmentwcd.entity.entityEnum.FoodStatus;
import fptaptech.assignmentwcd.model.MySqlCategoryModel;
import fptaptech.assignmentwcd.model.interfaceModel.CategoryModel;

import java.time.LocalDateTime;
import java.util.HashMap;

public class Food extends BaseEntity {
    private int id;
    private String name;
    private String code;
    private int categoryId;
    private String description;
    private String thumbnail;
    private double price;
    private FoodStatus status;
    private HashMap<String, String> errors;
    private CategoryModel categoryModel;

    public Food() {
        errors = new HashMap<>();
        categoryModel = new MySqlCategoryModel();
    }

    public Food(String name, String code, int categoryId, String description, String thumbnail, double price, FoodStatus status) {
        this.name = name;
        this.code = code;
        this.categoryId = categoryId;
        this.description = description;
        this.thumbnail = thumbnail;
        this.price = price;
        this.status = status;
    }

    public Food(LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime deletedAt, int createdBy, int updatedBy, int deletedBy, int id, String name, String code, int categoryId, String description, String thumbnail, double price, FoodStatus status) {
        super(createdAt, updatedAt, deletedAt, createdBy, updatedBy, deletedBy);
        this.id = id;
        this.code = code;
        this.name = name;
        this.categoryId = categoryId;
        this.description = description;
        this.thumbnail = thumbnail;
        this.price = price;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public FoodStatus getStatus() {
        return status;
    }

    public void setStatus(FoodStatus status) {
        this.status = status;
    }

    public HashMap<String, String> getErrors() {
        return errors;
    }

    public Category getCategory() {
        return categoryModel.findById(this.categoryId);
    }

    public static final class FoodBuilder {
        public LocalDateTime createdAt;
        public LocalDateTime updatedAt;
        public LocalDateTime deletedAt;
        public int createdBy;
        public int updatedBy;
        public int deletedBy;
        private int id;
        private String name;
        private String code;
        private int categoryId;
        private String description;
        private String thumbnail;
        private double price;
        private FoodStatus status;

        private FoodBuilder() {
            this.name = "";
            this.code = "";
            this.description = "";
            this.thumbnail = "";
            this.createdAt = LocalDateTime.now();
            this.updatedAt = LocalDateTime.now();
            this.status = FoodStatus.ON_SALE;
        }

        public static FoodBuilder aFood() {
            return new FoodBuilder();
        }

        public FoodBuilder withId(int id) {
            this.id = id;
            return this;
        }

        public FoodBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public FoodBuilder withCode(String code) {
            this.code = code;
            return this;
        }

        public FoodBuilder withCategoryId(int categoryId) {
            this.categoryId = categoryId;
            return this;
        }

        public FoodBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        public FoodBuilder withThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
            return this;
        }

        public FoodBuilder withPrice(double price) {
            this.price = price;
            return this;
        }

        public FoodBuilder withStatus(FoodStatus status) {
            this.status = status;
            return this;
        }

        public FoodBuilder withCreatedAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public FoodBuilder withUpdatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public FoodBuilder withDeletedAt(LocalDateTime deletedAt) {
            this.deletedAt = deletedAt;
            return this;
        }

        public FoodBuilder withCreatedBy(int createdBy) {
            this.createdBy = createdBy;
            return this;
        }

        public FoodBuilder withUpdatedBy(int updatedBy) {
            this.updatedBy = updatedBy;
            return this;
        }

        public FoodBuilder withDeletedBy(int deletedBy) {
            this.deletedBy = deletedBy;
            return this;
        }

        public Food build() {
            Food food = new Food();
            food.setId(id);
            food.setName(name);
            food.setCode(code);
            food.setCategoryId(categoryId);
            food.setDescription(description);
            food.setThumbnail(thumbnail);
            food.setPrice(price);
            food.setStatus(status);
            food.setCreatedAt(createdAt);
            food.setUpdatedAt(updatedAt);
            food.setDeletedAt(deletedAt);
            food.setCreatedBy(createdBy);
            food.setUpdatedBy(updatedBy);
            food.setDeletedBy(deletedBy);
            return food;
        }
    }

    public boolean isValid() {
        this.checkValid();
        return this.errors.size() == 0;
    }

    public void checkValid() {
        if(name.length() <= 7 || name == null) {
            errors.put(ValidationConstant.FOOD_ERROR_KEY_NAME, "Name cannot be empty");
        }
        if(categoryId == 0) {
            errors.put(ValidationConstant.FOOD_ERROR_KEY_CATEGORY_ID, "CategoryId cannot be empty");
        }
        if(price == 0) {
            errors.put(ValidationConstant.FOOD_ERROR_KEY_PRICE, "Price must be greater than 0");
        }
        if(thumbnail.length() == 0 || thumbnail == null) {
            errors.put(ValidationConstant.FOOD_ERROR_KEY_THUMBNAIL, "Thumbnail cannot be empty");
        }
        if(description.length() == 0 || description == null) {
            errors.put(ValidationConstant.FOOD_ERROR_KEY_DESCRIPTION, "Description cannot be empty");
        }
        if(code.length() == 0 || code == null) {
            errors.put(ValidationConstant.FOOD_ERROR_KEY_CODE, "Code cannot be empty");
        }
    }
}
