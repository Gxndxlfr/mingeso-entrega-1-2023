package MilkStgo.example.demo.repositories;

import MilkStgo.example.demo.entities.SubirPorcentajeEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SubirPorcentajeRepository extends JpaRepository<SubirPorcentajeEntity, Integer> {

    @Query(value = "select * from porcentaje as p where p.codigo = :codigo",
            nativeQuery = true)
    SubirPorcentajeEntity getbyCodigo(@Param("codigo") String codigo);

    @Transactional
    void deleteByCodigo(String codigo);
}
