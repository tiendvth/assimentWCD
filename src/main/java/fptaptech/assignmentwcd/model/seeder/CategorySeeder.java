package fptaptech.assignmentwcd.model.seeder;

import fptaptech.assignmentwcd.entity.Category;
import fptaptech.assignmentwcd.entity.entityEnum.CategoryStatus;
import fptaptech.assignmentwcd.model.MySqlCategoryModel;
import fptaptech.assignmentwcd.model.interfaceModel.CategoryModel;

public class CategorySeeder {
    private CategoryModel categoryModel;

    public CategorySeeder() {
        categoryModel = new MySqlCategoryModel();
    }

    public void seedData() {
        categoryModel.create(Category.CategoryBuilder.aCategory()
                .withName("Category")
                .withStatus(CategoryStatus.ACTIVE)
                .build());
    }
}
