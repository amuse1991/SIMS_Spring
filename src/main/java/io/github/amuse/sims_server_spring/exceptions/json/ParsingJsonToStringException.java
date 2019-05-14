package io.github.amuse.sims_server_spring.exceptions.json;

public class ParsingJsonToStringException extends RuntimeException {
    public ParsingJsonToStringException(Object sourceObj) {
        super("can't convert object "+ sourceObj.toString());
    }
}
