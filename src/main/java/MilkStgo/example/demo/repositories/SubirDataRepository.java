package MilkStgo.example.demo.repositories;

import MilkStgo.example.demo.entities.SubirDataEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface SubirDataRepository extends JpaRepository<SubirDataEntity, Integer> {

    @Query(value = "select * from data as a where a.proveedor = :codigo",
            nativeQuery = true)
    ArrayList<SubirDataEntity> getbyCodigo(@Param("codigo") String codigo);

    @Query(value = "select * from data as a where a.proveedor = :codigo and a.turno = :turno",
            nativeQuery = true)
    ArrayList<SubirDataEntity> getbyTurnoAndCodigo(@Param("turno") String turno,@Param("codigo") String codigo);

    @Transactional
    void deleteByProveedor(String proveedor);
}
