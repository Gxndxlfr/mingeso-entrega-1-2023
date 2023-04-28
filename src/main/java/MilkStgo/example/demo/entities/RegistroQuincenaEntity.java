package MilkStgo.example.demo.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "registro_quincena")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RegistroQuincenaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Integer ID;
    private String kilos;
    private String codigo;
    private String grasa;
    private String st;
}
