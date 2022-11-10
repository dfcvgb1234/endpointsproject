package nu.berggame.endpointsproject.DTO.external;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class GenderizeDTO {

    private int count;
    private String gender;
    private String name;
    private double probability;
}
