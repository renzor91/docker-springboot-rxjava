package pe.com.bcp.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.com.bcp.api.entidad.Moneda;

@Repository
public interface MonedaRepository extends JpaRepository<Moneda, String> {

	@Query("SELECT m.tipo FROM Moneda m where m.nombre = :nombre")
	String findByNombre(@Param("nombre") String nombre);

}
