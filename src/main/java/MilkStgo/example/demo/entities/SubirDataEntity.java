package MilkStgo.example.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "data")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SubirDataEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Integer ID;
    private String fecha;
    private String turno;
    private String proveedor;
    private String kls_leche;

}
