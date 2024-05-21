package app.repository;

import app.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalesRepository extends JpaRepository<Sale, Integer> {
    List<Sale> findTop7ByOrderByDateDesc();
}