package bg.sogtuni.mobilele24.service.impl;

import bg.sogtuni.mobilele24.model.AddOfferDTO;
import bg.sogtuni.mobilele24.model.OfferDetailsDTO;
import bg.sogtuni.mobilele24.model.OfferSummaryDTO;
import bg.sogtuni.mobilele24.model.entity.OfferEntity;
import bg.sogtuni.mobilele24.repository.OfferRepository;
import bg.sogtuni.mobilele24.service.OfferService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfferServiceImpl implements OfferService {
    private final OfferRepository offerRepository;

    public OfferServiceImpl(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    @Override
    public long createOffer(AddOfferDTO addOfferDTO) {
       return this.offerRepository.save(map(addOfferDTO)).getId();
    }

    @Override
    public OfferDetailsDTO getOfferDetails(Long id) {
         return this.offerRepository.findById(id)
                .map(OfferServiceImpl::toOfferDetails)
                .orElseThrow();
    }

    @Override
    public void deleteOffer(Long offerId) {
        this.offerRepository.deleteById(offerId);
    }

    @Override
    public List<OfferSummaryDTO> getAllOffers() {
                                // paging must be done, not find all
        return this.offerRepository.findAll()
                .stream()
                .map(OfferServiceImpl::toOfferSummary)
                .collect(Collectors.toList());
    }
    private static OfferSummaryDTO toOfferSummary(OfferEntity offerEntity){
        // todo: use mapping library
        return new OfferSummaryDTO().setId(offerEntity.getId())
                        .setDescription(offerEntity.getDescription())
                .setMileage(offerEntity.getMileage())
                .setEngineType(offerEntity.getEngine());
                 // todo: mileage

    }


    private static OfferDetailsDTO toOfferDetails(OfferEntity offerEntity){
        // todo: use mapping library
        return new OfferDetailsDTO(offerEntity.getId(),
                offerEntity.getDescription(),
                offerEntity.getMileage(), // todo: mileage
                offerEntity.getEngine());
    }

    private static OfferEntity map(AddOfferDTO addOfferDTO){
        // todo: use mapped(e.g. ModelMapper)
        return new OfferEntity()
                .setDescription(addOfferDTO.description())
                .setEngine(addOfferDTO.engineType())
                .setMileage(addOfferDTO.mileage());
    }
}
