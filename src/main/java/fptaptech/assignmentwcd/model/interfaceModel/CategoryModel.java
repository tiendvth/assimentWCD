package fptaptech.assignmentwcd.model.interfaceModel;

import fptaptech.assignmentwcd.entity.Category;

import java.util.List;
import java.util.Locale;


public interface CategoryModel {
    boolean create(Category obj);
    boolean update(int id, Category obj);
    boolean delete(int id);
    Category findById(int id);
    Category findBySlug(String slug);
    List<Category> findAll();
}
