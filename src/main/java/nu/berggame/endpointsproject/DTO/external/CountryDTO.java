package nu.berggame.endpointsproject.DTO.external;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class CountryDTO implements Comparable<CountryDTO> {

    private String country_id;
    private double probability;

    @Override
    public int compareTo(CountryDTO o) {
        return (int)((o.getProbability()*100) - (this.probability*100));
    }
}
