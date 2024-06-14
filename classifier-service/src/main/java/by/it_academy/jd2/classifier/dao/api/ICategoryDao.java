package by.it_academy.jd2.classifier.dao.api;

import by.it_academy.jd2.classifier.dao.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ICategoryDao extends JpaRepository<CategoryEntity, UUID> {
}
