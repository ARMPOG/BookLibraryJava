package models;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString(of = {"name", "genre"})
@Builder
public class Book {
    private UserModel author;
    private String name;
    private String genre;
}
