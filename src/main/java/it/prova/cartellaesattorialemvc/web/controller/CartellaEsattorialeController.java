package it.prova.cartellaesattorialemvc.web.controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import it.prova.cartellaesattorialemvc.model.CartellaEsattoriale;
import it.prova.cartellaesattorialemvc.model.Contribuente;
import it.prova.cartellaesattorialemvc.model.Stato;
import it.prova.cartellaesattorialemvc.service.CartellaEsattorialeService;
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
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
@RequestMapping(value = "/cartellaesattoriale")
public class CartellaEsattorialeController {

    @Autowired
    private CartellaEsattorialeService cartellaEsattorialeService;
    @Autowired
    private ContribuenteService contribuenteService;

    @GetMapping
    public ModelAndView listAllCartellaEsattoriale() {
        ModelAndView mv = new ModelAndView();
        List<CartellaEsattoriale> cartellaEsattoriale = cartellaEsattorialeService.listAllElements();
        mv.addObject("cartellaEsattoriale_list_attribute", cartellaEsattoriale);
        mv.setViewName("cartellaesattoriale/list");
        return mv;
    }

    @GetMapping("/insert")
    public String createCartellaEsattoriale(Model model) {
        CartellaEsattoriale nuovaCartellaEsattoriale = new CartellaEsattoriale();
        nuovaCartellaEsattoriale.setStato(Stato.CREATA);
        model.addAttribute("insert_cartellaEsattoriale_attr", nuovaCartellaEsattoriale);
        return "cartellaesattoriale/insert";
    }

    @PostMapping("/save")
    public String saveCartellaEsattoriale(@Valid @ModelAttribute("insert_cartellaEsattoriale_attr") CartellaEsattoriale cartellaEsattoriale, BindingResult result,
                                          RedirectAttributes redirectAttrs) {


//        cartellaEsattoriale.setStato(Stato.CREATA);

        if (result.hasErrors()) {
            return "cartellaesattoriale/insert";
        }
        cartellaEsattorialeService.inserisciNuovo(cartellaEsattoriale);

        redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
        return "redirect:/cartellaesattoriale";
    }

    @GetMapping("/search")
    public String searchCartellaEsattoriale(Model model) {
        List<String> stati = Stream.of(Stato.values()).map(stato -> stato.name()).collect(Collectors.toList());
        model.addAttribute("contribuenti_list_attribute", contribuenteService.listAllElements());
        model.addAttribute("stati_list_attribute", stati);
        return "cartellaesattoriale/search";
    }

    @PostMapping("/list")
    public String listRegisti(CartellaEsattoriale cartellaEsattorialeExample, ModelMap model) {
        List<CartellaEsattoriale> cartellaEsattoriale = cartellaEsattorialeService.findByExample(cartellaEsattorialeExample);
        model.addAttribute("cartellaEsattoriale_list_attribute", cartellaEsattoriale);
        return "cartellaesattoriale/list";
    }

    @GetMapping("/show/{idCartellaEsattoriale}")
    public String showCartellaEsattoriale(@PathVariable(required = true) Long idCartellaEsattoriale, Model model) {
        model.addAttribute("show_cartellaEsattoriale_attr", cartellaEsattorialeService.caricaSingoloElementoEager(idCartellaEsattoriale));
        return "cartellaesattoriale/show";
    }

    @GetMapping("/cambiaStato/{idCartellaEsattoriale}")
    public String prepareCambiaStatoCartellaEsattoriale(@PathVariable(required = true) Long idCartellaEsattoriale, Model model) {
        model.addAttribute("cambiaStato_cartellaEsattoriale_attr", cartellaEsattorialeService.caricaSingoloElemento(idCartellaEsattoriale));
        return "cartellaesattoriale/cambiaStato";
    }

    @PostMapping("/cambiaStato/executecambiaStato")
    public String executeCambiaStatoCartellaEsattoriale(@Valid @ModelAttribute("idCartellaEsattoriale") Long idCartellaEsattoriale, RedirectAttributes redirectAttrs) {

        CartellaEsattoriale cartellaDaCambiareStato = cartellaEsattorialeService.caricaSingoloElementoEager(idCartellaEsattoriale);

        if (cartellaDaCambiareStato.getStato().toString() == "CANCELLATA") {
            cartellaDaCambiareStato.setStato(Stato.IN_VERIFICA);

        } else {
            cartellaDaCambiareStato.setStato(Stato.CANCELLATA);
        }

        cartellaEsattorialeService.aggiorna(cartellaDaCambiareStato);


        redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");

        return "redirect:/cartellaesattoriale";
    }


    @GetMapping("/edit/{idCartellaEsattoriale}")
    public String prepareEditCartellaEsattoriale(@PathVariable(required = true) Long idCartellaEsattoriale, Model model) {
            model.addAttribute("update_cartellaEsattoriale_attr", cartellaEsattorialeService.caricaSingoloElemento(idCartellaEsattoriale));
        return "cartellaesattoriale/edit";
    }

    @PostMapping("/edit/saveupdate")
    public String saveEditCartellaEsattoriale(@Valid @ModelAttribute("update_cartellaEsattoriale_attr") CartellaEsattoriale cartellaEsattoriale, BindingResult result,
                                              RedirectAttributes redirectAttrs) {

        if (cartellaEsattoriale.getContribuente() != null && cartellaEsattoriale.getContribuente().getId() != null)
            cartellaEsattoriale.setContribuente(contribuenteService.caricaSingoloElemento(cartellaEsattoriale.getContribuente().getId()));
        else
            result.rejectValue("contribuente", "contribuente.notnull");

        if (result.hasErrors()) {
            return "cartellaesattoriale/edit";
        }

        cartellaEsattorialeService.aggiorna(cartellaEsattoriale);

        redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");

        return "redirect:/cartellaesattoriale";
    }

    @GetMapping(value = "/searchContribuenteAjax", produces = {MediaType.APPLICATION_JSON_VALUE})
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


}