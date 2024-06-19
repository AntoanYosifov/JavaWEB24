package bg.softuni.pathfinder_loc.web;

import bg.softuni.pathfinder_loc.service.RouteService;
import bg.softuni.pathfinder_loc.service.dto.RouteShortInfoDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class RouteController {

    private final RouteService routeService;

    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping("/routes")
    public String routes(Model model){

       // RouteShortInfoDTO randomRoute = this.routeService.getRandomRoute();

        List<RouteShortInfoDTO> routes = routeService.getAll();
        model.addAttribute("allRoutes", routes);
        return "routes";
    }
}
