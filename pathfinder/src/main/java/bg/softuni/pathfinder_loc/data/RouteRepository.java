package bg.softuni.pathfinder_loc.data;

import bg.softuni.pathfinder_loc.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {

}
