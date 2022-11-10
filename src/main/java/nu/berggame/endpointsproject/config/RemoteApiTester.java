package nu.berggame.endpointsproject.config;

import nu.berggame.endpointsproject.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class RemoteApiTester implements CommandLineRunner {

    @Autowired
    PersonService personService;

    @Override
    public void run(String... args) throws Exception {
        System.out.println(personService.GetPersonByName("Kim"));
    }

    private Mono<String> callSlowEndpoint() {
        Mono<String> slowResponse = WebClient.create()
                .get()
                .uri("http://localhost:8080/random-string-slow")
                .retrieve()
                .bodyToMono(String.class)
                .doOnError(e -> System.out.println("An error occurred: " + e.getMessage()));
        return slowResponse;
    }

    private void callSlowEndpointBlocking() {
        long start = System.currentTimeMillis();
        List<String> randomStrings = new ArrayList<>();

        randomStrings.add(callSlowEndpoint().block());
        randomStrings.add(callSlowEndpoint().block());
        randomStrings.add(callSlowEndpoint().block());

        long end = System.currentTimeMillis();
        randomStrings.add("Time spent: " +  (end - start));

        System.out.println(randomStrings.stream().collect(Collectors.joining(",")));
    }

    private void callSlowEndpointNonBlocking() {
        long start = System.currentTimeMillis();
        Mono<String> firstString = callSlowEndpoint();
        Mono<String> secondString = callSlowEndpoint();
        Mono<String> thirdString = callSlowEndpoint();


        var response = Mono.zip(firstString, secondString, thirdString).map(t -> {
            List<String> randomStrings = new ArrayList<>();
            randomStrings.add(t.getT1());
            randomStrings.add(t.getT2());
            randomStrings.add(t.getT3());

            long end = System.currentTimeMillis();
            randomStrings.add("Time spent: " +  (end - start));


            return randomStrings;
        });

        System.out.println(response.block().stream().collect(Collectors.joining(",")));
    }

}
