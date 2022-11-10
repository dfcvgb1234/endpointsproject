package nu.berggame.endpointsproject.DTO.internal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class PersonDTO {

    private String name;
    private String gender;
    private int genderProbability;
    private int age;
    private int ageCount;
    private String country;
    private int countryProbability;

    @Override
    public String toString() {
        return "PersonDTO{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", genderProbability=" + genderProbability +
                ", age=" + age +
                ", ageCount=" + ageCount +
                ", country='" + country + '\'' +
                ", countryProbability=" + countryProbability +
                '}';
    }
}
