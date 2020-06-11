package ua.kpi.model.entity;

import ua.kpi.model.enums.Role;

public class User {
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

    private User() {}

    public Builder getBuilder() {
        return new Builder();
    }

    public static User.Builder newBuilder() {
        return new User().new Builder();
    }

    public class Builder {
        private Builder() {}

        public User build() {
            return User.this;
        }

        public User.Builder setId(Long id) {
            User.this.id = id;
            return this;
        }

        public User.Builder setUsername(String username) {
            User.this.username = username;
            return this;
        }

        public User.Builder setPassword(String password) {
            User.this.password = password;
            return this;
        }

        public User.Builder setRole(Role role) {
            User.this.role = role;
            return this;
        }
    }
}
