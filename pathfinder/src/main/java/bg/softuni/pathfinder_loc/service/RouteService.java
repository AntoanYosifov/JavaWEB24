package bg.softuni.pathfinder_loc.service;

import bg.softuni.pathfinder_loc.service.dto.RouteShortInfoDTO;

import java.util.List;

public interface RouteService {
    RouteShortInfoDTO getRandomRoute();
    List<RouteShortInfoDTO> getAll();
}
