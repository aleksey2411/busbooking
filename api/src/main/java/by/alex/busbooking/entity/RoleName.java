package by.alex.busbooking.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RoleName {
    ROLE_USER("USER"), ROLE_ADMIN("ADMIN");

    private final String shortName;
}
