package wat.ai.securities.services;

import wat.ai.securities.services.librarians.dtos.LibrarianDetails;

public interface IUserService {

    LibrarianDetails loginLibrarian(String username, String password);
}
