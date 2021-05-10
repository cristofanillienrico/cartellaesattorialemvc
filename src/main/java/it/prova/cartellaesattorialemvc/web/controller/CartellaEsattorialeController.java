package it.prova.cartellaesattorialemvc.web.controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import it.prova.cartellaesattorialemvc.model.CartellaEsattoriale;
import it.prova.cartellaesattorialemvc.model.Contribuente;
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
        model.addAttribute("insert_cartellaEsattoriale_attr", new CartellaEsattoriale());
        return "cartellaesattoriale/insert";
    }

    @PostMapping("/save")
    public String saveCartellaEsattoriale(@Valid @ModelAttribute("insert_cartellaEsattoriale_attr") CartellaEsattoriale cartellaEsattoriale, BindingResult result,
                                          RedirectAttributes redirectAttrs) {

// se il contribuente è valorizzato dobbiamo provare a caricarlo perché
// ci aiuta in pagina. Altrimenti devo fare rejectValue 'a mano' altrimenti
// comunque viene fatta una new durante il binding, anche se arriva stringa vuota
        if (cartellaEsattoriale.getContribuente() != null && cartellaEsattoriale.getContribuente().getId() != null)
            cartellaEsattoriale.setContribuente(contribuenteService.caricaSingoloElemento(cartellaEsattoriale.getContribuente().getId()));
        else
            result.rejectValue("contribuente", "contribuente.notnull");

        if (result.hasErrors()) {
            return "cartellaesattoriale/insert";
        }
        cartellaEsattorialeService.inserisciNuovo(cartellaEsattoriale);

        redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
        return "redirect:/cartellaesattoriale";
    }

    @GetMapping("/search")
    public String searchCartellaEsattoriale(Model model) {
        model.addAttribute("contribuenti_list_attribute", contribuenteService.listAllElements());
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

    @GetMapping("/delete/{idCartellaEsattoriale}")
    public String prepareDeleteCartellaEsattoriale(@PathVariable(required = true) Long idCartellaEsattoriale, Model model) {
        model.addAttribute("elimina_cartellaEsattoriale_attr", cartellaEsattorialeService.caricaSingoloElemento(idCartellaEsattoriale));
        return "cartellaesattoriale/delete";
    }

    @PostMapping("/delete/executedelete")
    public String executeDeleteCartellaEsattoriale(@Valid @ModelAttribute("idCartellaEsattoriale") Long idCartellaEsattoriale, RedirectAttributes redirectAttrs) {

        cartellaEsattorialeService.rimuovi(cartellaEsattorialeService.caricaSingoloElementoEager(idCartellaEsattoriale));

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