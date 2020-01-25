package springwebfluxwebsocket.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.vavr.control.Try;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GreetingsService {
    private static final Logger log = LoggerFactory.getLogger(GreetingsService.class);
    private ObjectMapper jsonMapper = new ObjectMapper();
    public String greeting(String message) {
        return Try.of(() -> {
            final Greeting greeting = jsonMapper.readValue(message, Greeting.class);
            return jsonMapper.writeValueAsString(greeting);
        })
                .onFailure(parserException -> log.error("Could not parse JSON object", parserException))
                .getOrElse("");
    }
}