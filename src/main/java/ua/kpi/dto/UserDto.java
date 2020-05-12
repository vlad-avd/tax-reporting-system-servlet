package ua.kpi.dto;

import ua.kpi.model.enums.Role;

import java.util.Set;

public class UserDto {
    private Long id;
    private String username;
    private String password;

    private Role role;

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    private UserDto() {}

    public static Builder newBuilder() {
        return new UserDto().new Builder();
    }

    public Builder getBuilder() {
        return new Builder();
    }

    public class Builder {
        private Builder() {}

        public UserDto build() {
            return UserDto.this;
        }

        public Builder setId(Long id) {
            UserDto.this.id = id;
            return this;
        }

        public Builder setUsername(String username) {
            UserDto.this.username = username;
            return this;
        }

        public Builder setPassword(String password) {
            UserDto.this.password = password;
            return this;
        }

        public Builder setRole(Role role) {
            UserDto.this.role = role;
            return this;
        }
    }
}
