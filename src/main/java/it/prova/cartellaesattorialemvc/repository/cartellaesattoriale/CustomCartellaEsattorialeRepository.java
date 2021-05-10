package it.prova.cartellaesattorialemvc.repository.cartellaesattoriale;


import it.prova.cartellaesattorialemvc.model.CartellaEsattoriale;

import java.util.List;

public interface CustomCartellaEsattorialeRepository {

	List<CartellaEsattoriale> findByExample(CartellaEsattoriale example);

}
