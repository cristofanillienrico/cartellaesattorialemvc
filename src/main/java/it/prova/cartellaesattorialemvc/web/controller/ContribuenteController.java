package it.prova.cartellaesattorialemvc.web.controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import it.prova.cartellaesattorialemvc.model.Contribuente;
import it.prova.cartellaesattorialemvc.service.ContribuenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/contribuente")
public class ContribuenteController {

    @Autowired
    private ContribuenteService contribuenteService;

    @GetMapping
    public ModelAndView listAllContribuenti() {
        ModelAndView mv = new ModelAndView();
        List<Contribuente> contribuenti = contribuenteService.listAllElements();
        mv.addObject("contribuenti_list_attribute", contribuenti);
        mv.setViewName("contribuente/list");
        return mv;
    }

    @GetMapping("/insert")
    public String createContribuente(Model model) {
        model.addAttribute("insert_contribuente_attr", new Contribuente());
        return "contribuente/insert";
    }

    @PostMapping("/save")
    public String saveContribuente(@Valid @ModelAttribute("insert_contribuente_attr") Contribuente contribuente, BindingResult result,
                                   RedirectAttributes redirectAttrs) {
        if (result.hasErrors()) {
            return "contribuente/insert";
        }
        contribuenteService.inserisciNuovo(contribuente);

        redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
        return "redirect:/contribuente";
    }

    @GetMapping("/search")
    public String searchContribuente() {
        return "contribuente/search";
    }

    @PostMapping("/list")
    public String listContribuenti(Contribuente contribuenteExample, ModelMap model) {
        List<Contribuente> contribuenti = contribuenteService.findByExample(contribuenteExample);
        model.addAttribute("contribuenti_list_attribute", contribuenti);
        return "contribuente/list";
    }

    @GetMapping(value = "/searchContribuentiAjax", produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    String searchContribuente(@RequestParam String term) {

        List<Contribuente> listaContribuenteByTerm = contribuenteService.cercaByCognomeENomeILike(term);
        return buildJsonResponse(listaContribuenteByTerm);
    }

    private String buildJsonResponse(List<Contribuente> listaContribuenti) {
        JsonArray ja = new JsonArray();

        for (Contribuente contribuenteItem : listaContribuenti) {
            JsonObject jo = new JsonObject();
            jo.addProperty("value", contribuenteItem.getId());
            jo.addProperty("label", contribuenteItem.getNome() + " " + contribuenteItem.getCognome());
            ja.add(jo);
        }

        return new Gson().toJson(ja);
    }

    @GetMapping("/show/{idContribuente}")
    public String showContribuente(@PathVariable(required = true) Long idContribuente, Model model) {
        model.addAttribute("show_contribuente_attr", contribuenteService.caricaSingoloElementoConCartelleEsattoriali(idContribuente));
        return "contribuente/show";
    }

    @GetMapping("/delete/{idContribuente}")
    public String prepareDeleteContribuente(@PathVariable(required = true) Long idContribuente, Model model) {
        model.addAttribute("elimina_contribuente_attr", contribuenteService.caricaSingoloElemento(idContribuente));
        return "contribuente/delete";
    }

    @PostMapping("/delete/executedelete")
    public String executeDeleteContribuente(@Valid @ModelAttribute("idContribuente") Long idContribuente, RedirectAttributes redirectAttrs) {
        Contribuente contribuenteDaEliminare = contribuenteService.caricaSingoloElementoConCartelleEsattoriali(idContribuente);
        if (!contribuenteDaEliminare.getCartelleEsattoriali().isEmpty()) {
            redirectAttrs.addFlashAttribute("errorMessage", "Non posso eliminare contribuente che ha cartelle esattoriali collegate");

            return "redirect:/contribuente";
        }

        contribuenteService.rimuovi(contribuenteDaEliminare);

        redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");

        return "redirect:/contribuente";
    }

    @GetMapping("/edit/{idContribuente}")
    public String prepareEditContribuente(@PathVariable(required = true) Long idContribuente, Model model) {
        model.addAttribute("modifica_contribuente_attr", contribuenteService.caricaSingoloElemento(idContribuente));
        return "contribuente/edit";
    }

    @PostMapping("/edit/executeedit")
    public String executeEditContribuente(@Valid @ModelAttribute("modifica_contribuente_attr") Contribuente contribuente, BindingResult result,
                                          RedirectAttributes redirectAttrs) {
        if (result.hasErrors()) {
            return "contribuente/edit";
        }
        contribuenteService.aggiorna(contribuente);

        redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
        return "redirect:/contribuente";
    }


}
