package io.github.amuse.sims_server_spring.exceptions.users;

public class DuplicateUserIdException extends RuntimeException {
    public DuplicateUserIdException(String userId) {
        super("duplicate user id " + userId);
    }
}
