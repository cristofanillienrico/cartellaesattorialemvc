package it.prova.cartellaesattorialemvc.repository.cartellaesattoriale;

import it.prova.cartellaesattorialemvc.model.CartellaEsattoriale;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CartellaEsattorialeRepository extends CrudRepository<CartellaEsattoriale, Long>,CustomCartellaEsattorialeRepository {
	@Query("from CartellaEsattoriale c join fetch c.contribuente where c.id = ?1")
	CartellaEsattoriale findSingleCartellaEsattorialeEager(Long id);

}
