package bg.sogtuni.mobilele24.web;

import bg.sogtuni.mobilele24.model.AddOfferDTO;
import bg.sogtuni.mobilele24.model.enums.EngineTypeEnum;
import bg.sogtuni.mobilele24.service.OfferService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/offers")
public class OfferController {
    private final OfferService offerService;

    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping("/add")
    public String newOffer(Model model) {

        if (!model.containsAttribute("addOfferDTO")) {
            model.addAttribute("addOfferDTO", AddOfferDTO.empty());
        }

        model.addAttribute("allEngineTypes", EngineTypeEnum.values());
        return "offer-add";
    }

    @PostMapping("/add")
    public String createOffer(@Valid AddOfferDTO addOfferDTO,
                              BindingResult bindingResult,
                              RedirectAttributes rAtt) {

        if (bindingResult.hasErrors()) {
            rAtt.addFlashAttribute("addOfferDTO", addOfferDTO);
            rAtt.addFlashAttribute("org.springframework.validation.BindingResult.addOfferDTO", bindingResult);
            return "redirect:/offers/add";
        }

        long newOffer =  this.offerService.createOffer(addOfferDTO);

        return "redirect:/offers/" + newOffer ;
    }

    @GetMapping("/{id}")
    public String offerDetails(@PathVariable("id") Long id, Model model){
        model.addAttribute("offerDetails", this.offerService.getOfferDetails(id));

        return "details";
    }

    @DeleteMapping("/{id}")
    public String deleteOffer(@PathVariable("id") Long id){
        this.offerService.deleteOffer(id);

        return "redirect:/offers/all";
    }
}
