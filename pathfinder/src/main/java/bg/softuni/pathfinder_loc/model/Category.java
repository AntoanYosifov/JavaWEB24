package bg.softuni.pathfinder_loc.model;

import jakarta.persistence.*;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Enumerated(EnumType.STRING)
    private CategoryType name;
    @Column(columnDefinition = "TEXT")
    private String description;

    public Category() {
    }
}
