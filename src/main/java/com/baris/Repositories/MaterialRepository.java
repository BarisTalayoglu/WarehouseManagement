package com.baris.Repositories;
import com.baris.Entity.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Long> {

    // Find materials with stock greater than or equal to a given quantity
    List<Material> findByCurrentStockGreaterThanEqual(int quantity);

}
