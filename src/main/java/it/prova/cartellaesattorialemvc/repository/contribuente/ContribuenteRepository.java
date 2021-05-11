package it.prova.cartellaesattorialemvc.repository.contribuente;

import it.prova.cartellaesattorialemvc.model.Contribuente;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ContribuenteRepository extends CrudRepository<Contribuente, Long>, CustomContribuenteRepository {
    List<Contribuente> findByCognomeIgnoreCaseContainingOrNomeIgnoreCaseContainingOrderByNomeAsc(String cognome, String nome);

    @Query("from Contribuente c left join fetch c.cartelleEsattoriali where c.id=?1")
    Contribuente caricaSingoloElementoConCartelleEsattoriali(Long id);


}

