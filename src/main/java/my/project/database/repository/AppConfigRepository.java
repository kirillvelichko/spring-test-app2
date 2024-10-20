package my.project.database.repository;

import my.project.database.entity.AppConfigEntity;
import org.springframework.data.repository.CrudRepository;

public interface AppConfigRepository extends CrudRepository<AppConfigEntity, Long> {
    AppConfigEntity getByName(String name);
}
