package my.project.database.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "app_config")
@Data
public class AppConfigEntity {
    @Id
    @Column("id")
    private Long id;

    @Column("name")
    private String name;

    @Column("value")
    private String value;

    @Column("description")
    private String description;
}
