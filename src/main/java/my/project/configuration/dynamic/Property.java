package my.project.configuration.dynamic;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Property {
    SIGNATURE("signature");

    private final String name;
}
