package MilkStgo.example.demo.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "porcentaje")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SubirPorcentajeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Integer ID;
    private String codigo;
    private String grasa;
    private String solidoTotal;


}
