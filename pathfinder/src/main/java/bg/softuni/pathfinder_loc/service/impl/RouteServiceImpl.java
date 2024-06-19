package bg.softuni.pathfinder_loc.service.impl;

import bg.softuni.pathfinder_loc.data.RouteRepository;
import bg.softuni.pathfinder_loc.model.Picture;
import bg.softuni.pathfinder_loc.model.Route;
import bg.softuni.pathfinder_loc.service.RouteService;
import bg.softuni.pathfinder_loc.service.dto.RouteShortInfoDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class RouteServiceImpl implements RouteService {

    private final RouteRepository routeRepository;
    private Random random;
    private ModelMapper mapper;
    public RouteServiceImpl(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
        this.random = new Random();
        this.mapper = new ModelMapper();
    }
    @Transactional
    public List<RouteShortInfoDTO> getAll(){
       return this.routeRepository.findAll()
                .stream()
                .map(this::mapToShortInfo)
                .toList();
    }

    private RouteShortInfoDTO mapToShortInfo(Route route) {
        RouteShortInfoDTO dto = this.mapper.map(route, RouteShortInfoDTO.class);

        Optional<Picture> picture = route.getPictures().stream().findFirst();
        dto.setImageUrl(picture.get().getUrl());

        return dto;
    }

    @Override
    @Transactional
    public RouteShortInfoDTO getRandomRoute() throws NullPointerException {
        long routeCount = this.routeRepository.count();
        long randomId = this.random.nextLong(routeCount) + 1;

        Optional<Route> routeById = this.routeRepository.findById(randomId);

        if(routeById.isEmpty()){
            throw new NullPointerException("A route with ID: " + randomId + " doesn't exist.");// Map to DTO
        }

        return mapToShortInfo(routeById.get());
    }
}
