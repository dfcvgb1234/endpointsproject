package nu.berggame.endpointsproject.service;

import nu.berggame.endpointsproject.DTO.external.AgifyDTO;
import nu.berggame.endpointsproject.DTO.external.GenderizeDTO;
import nu.berggame.endpointsproject.DTO.external.NationalizeDTO;
import nu.berggame.endpointsproject.DTO.internal.PersonDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Arrays;

@Service
public class PersonService {

    private Mono<GenderizeDTO> GetGenderByName(String name) {
        return WebClient.create()
                .get()
                .uri("https://api.genderize.io?name=" + name)
                .retrieve()
                .bodyToMono(GenderizeDTO.class);
    }

    private Mono<AgifyDTO> GetAgeByName(String name) {
        return WebClient.create()
                .get()
                .uri("https://api.agify.io?name=" + name)
                .retrieve()
                .bodyToMono(AgifyDTO.class);
    }

    private Mono<NationalizeDTO> GetNationalityByName(String name) {
        return WebClient.create()
                .get()
                .uri("https://api.nationalize.io?name=" + name)
                .retrieve()
                .bodyToMono(NationalizeDTO.class);
    }

    public PersonDTO GetPersonByName(String name) {

        Mono<GenderizeDTO> genderMono = GetGenderByName(name);
        Mono<AgifyDTO> ageMono = GetAgeByName(name);
        Mono<NationalizeDTO> nationalityMono = GetNationalityByName(name);

        return Mono.zip(genderMono, ageMono, nationalityMono).map(t -> {

            // Sort the countries, so we get the most likely in the front of the array
            Arrays.sort(t.getT3().getCountry());

            PersonDTO person = new PersonDTO();
            person.setName(name);
            person.setGender(t.getT1().getGender());
            person.setGenderProbability((int)(t.getT1().getProbability()*100));
            person.setAge(t.getT2().getAge());
            person.setAgeCount(t.getT2().getCount());
            person.setCountry(t.getT3().getCountry()[0].getCountry_id());
            person.setCountryProbability((int)(t.getT3().getCountry()[0].getProbability()*100));

            return person;

        }).block();
    }

}
