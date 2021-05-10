package it.prova.cartellaesattorialemvc.service;

import it.prova.cartellaesattorialemvc.model.Contribuente;

import java.util.List;


public interface ContribuenteService {

    public List<Contribuente> listAllElements();

    public Contribuente caricaSingoloElemento(Long id);

    public Contribuente caricaSingoloElementoConCartelleEsattoriali(Long id);

    public void aggiorna(Contribuente contribuenteInstance);

    public void inserisciNuovo(Contribuente contribuenteInstance);

    public void rimuovi(Contribuente contribuenteInstance);

    public List<Contribuente> findByExample(Contribuente example);

    public List<Contribuente> cercaByCognomeENomeILike(String term);

}
