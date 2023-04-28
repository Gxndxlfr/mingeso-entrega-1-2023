package MilkStgo.example.demo.repositories;


import MilkStgo.example.demo.entities.ProveedorEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProveedorRepository extends JpaRepository<ProveedorEntity, String> {

    @Transactional
    void deleteByCodigo(String codigo);
}
