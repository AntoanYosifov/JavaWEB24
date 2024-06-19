package bg.sogtuni.mobilele24.service;

import bg.sogtuni.mobilele24.model.AddOfferDTO;
import bg.sogtuni.mobilele24.model.OfferDetailsDTO;
import bg.sogtuni.mobilele24.model.OfferSummaryDTO;

import java.util.List;

public interface OfferService {
    long createOffer(AddOfferDTO addOfferDTO);
    OfferDetailsDTO getOfferDetails(Long id);
    void deleteOffer(Long offerId);

    List<OfferSummaryDTO> getAllOffers();
}
