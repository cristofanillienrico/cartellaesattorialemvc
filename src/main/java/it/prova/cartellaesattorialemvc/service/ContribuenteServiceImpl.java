package it.prova.cartellaesattorialemvc.service;

import it.prova.cartellaesattorialemvc.model.Contribuente;
import it.prova.cartellaesattorialemvc.repository.contribuente.ContribuenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ContribuenteServiceImpl implements ContribuenteService{

    @Autowired
    private ContribuenteRepository contribuenteRepository;

    @Transactional(readOnly = true)
    public List<Contribuente> listAllElements() {
        return (List<Contribuente>) contribuenteRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Contribuente caricaSingoloElemento(Long id) {
        return contribuenteRepository.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    public Contribuente caricaSingoloElementoConCartelleEsattoriali(Long id) {
        return contribuenteRepository.caricaSingoloElementoConCartelleEsattoriali(id);
    }

    @Transactional
    public void aggiorna(Contribuente contribuenteInstance) {
        contribuenteRepository.save(contribuenteInstance);
    }

    @Transactional
    public void inserisciNuovo(Contribuente contribuenteInstance) {
        contribuenteRepository.save(contribuenteInstance);
    }

    @Transactional
    public void rimuovi(Contribuente contribuenteInstance) {
        contribuenteRepository.delete(contribuenteInstance);
    }

    @Transactional(readOnly = true)
    public List<Contribuente> findByExample(Contribuente example) {
        return contribuenteRepository.findByExample(example);
    }

    @Transactional(readOnly = true)
    public List<Contribuente> cercaByCognomeENomeILike(String term) {
        System.out.println("da scrivere");
        return null;

    }
}
