package bg.sogtuni.mobilele24.model;

import bg.sogtuni.mobilele24.model.enums.EngineTypeEnum;

public record OfferDetailsDTO(Long id,
                              String description,
                              Integer mileage,
                              EngineTypeEnum engineType) {
}
