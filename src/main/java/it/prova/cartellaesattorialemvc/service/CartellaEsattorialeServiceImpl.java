package it.prova.cartellaesattorialemvc.service;

import it.prova.cartellaesattorialemvc.model.CartellaEsattoriale;
import it.prova.cartellaesattorialemvc.repository.cartellaesattoriale.CartellaEsattorialeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CartellaEsattorialeServiceImpl implements CartellaEsattorialeService {
    @Autowired
    private CartellaEsattorialeRepository repositoryCartellaEsattoriale;

    @Transactional(readOnly = true)
    public List<CartellaEsattoriale> listAllElements() {
        return (List<CartellaEsattoriale>) repositoryCartellaEsattoriale.findAll();
    }

    @Transactional(readOnly = true)
    public CartellaEsattoriale caricaSingoloElemento(Long id) {
        return repositoryCartellaEsattoriale.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    public CartellaEsattoriale caricaSingoloElementoEager(Long id) {
        return repositoryCartellaEsattoriale.findSingleCartellaEsattorialeEager(id);
    }

    @Transactional
    public void aggiorna(CartellaEsattoriale cartellaEsattorialeInstance) {
        repositoryCartellaEsattoriale.save(cartellaEsattorialeInstance);
    }

    @Transactional
    public void inserisciNuovo(CartellaEsattoriale cartellaEsattorialeInstance) {
        repositoryCartellaEsattoriale.save(cartellaEsattorialeInstance);
    }

    @Transactional
    public void rimuovi(CartellaEsattoriale cartellaEsattorialeInstance) {
        repositoryCartellaEsattoriale.delete(cartellaEsattorialeInstance);
    }

    @Transactional(readOnly = true)
    public List<CartellaEsattoriale> findByExample(CartellaEsattoriale example) {
        return repositoryCartellaEsattoriale.findByExample(example);
    }

}
