package nu.berggame.endpointsproject.DTO.external;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class NationalizeDTO {

    private CountryDTO[] country;

    private String name;


}
