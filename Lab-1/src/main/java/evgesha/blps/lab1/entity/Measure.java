package evgesha.blps.lab1.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

@Entity
@Table(name = "measure")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Measure {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Override
    public String toString() {
        return "Measure{" +
                "id=" + id +
                ", name= '" + name + '\'' +
                '}';
    }
}
