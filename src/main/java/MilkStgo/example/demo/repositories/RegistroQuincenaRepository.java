package MilkStgo.example.demo.repositories;

import MilkStgo.example.demo.entities.RegistroQuincenaEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistroQuincenaRepository extends JpaRepository<RegistroQuincenaEntity, String> {

    @Query(value = "select * from registro_quincena as r where r.codigo = :codigo",
            nativeQuery = true)
    RegistroQuincenaEntity getByCodigo(@Param("codigo") String codigo);


    @Transactional
    void deleteByCodigo(String codigo);

   
    /*@Modifying
    @Transactional
    @Query("update porcentaje set kilos = :kilos where codigo = :codigo")
    void updateKilosQuincena(@Param("codigo") String codigo,@Param("kilos") int kilos);

    @Modifying
    @Transactional
    @Query("update porcentaje set st = :stActual where codigo = :codigo")
    void updateStQuincena(@Param("codigo") String codigo,@Param("stActual") int stActual);

    @Modifying
    @Transactional
    @Query("update porcentaje set grasa = :grasaActual where codigo = :codigo")
    void updateGrasaQuincena(@Param("codigo") String codigo,@Param("grasaActual") int grasaActual);*/

}
