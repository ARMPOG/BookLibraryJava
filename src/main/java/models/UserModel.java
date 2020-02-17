package models;

import lombok.*;

import java.util.Objects;

//@ToString(of = {"name","email","password"})
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString()
@Builder
public class UserModel {
    private String name;
    private String email;
    private String password;

    public UserModel(UserModel user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.password = user.getPassword();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserModel userModel = (UserModel) o;
        return Objects.equals(email, userModel.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}
