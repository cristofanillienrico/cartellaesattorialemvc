package it.prova.cartellaesattorialemvc.repository.contribuente;

import it.prova.cartellaesattorialemvc.model.Contribuente;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomContribuenteRepository {
    List<Contribuente> findByExample(Contribuente example);


}
