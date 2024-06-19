package bg.sogtuni.mobilele24.model;

import bg.sogtuni.mobilele24.model.enums.EngineTypeEnum;

public class OfferSummaryDTO {
    private Long id;
    private String description;
    private Integer mileage;
    private EngineTypeEnum engineType;

    public OfferSummaryDTO setEngineType(EngineTypeEnum engineType) {
        this.engineType = engineType;
        return this;
    }

    public Long getId() {
        return id;
    }

    public OfferSummaryDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public OfferSummaryDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public Integer getMileage() {
        return mileage;
    }

    public OfferSummaryDTO setMileage(Integer mileage) {
        this.mileage = mileage;
        return this;
    }

    public EngineTypeEnum getEngineType() {
        return engineType;
    }
}
