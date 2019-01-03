package wat.ai.securities.services.librarians;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wat.ai.securities.services.IUserService;
import wat.ai.securities.services.librarians.dtos.LibrarianDetails;
import wat.ai.security2.repositories.SecurityRepository;

@Service
public class LibrarianServiceImpl implements IUserService {

    final SecurityRepository securityRepository;

    @Autowired
    public LibrarianServiceImpl(SecurityRepository securityRepository) {
        this.securityRepository = securityRepository;
    }

    @Override
    public LibrarianDetails loginLibrarian(String username, String password) {
//        Librarian librarian = securityRepository.findLibrarianByUsernameAndPasswordHash(username, Base64.getEncoder().encodeToString(password.getBytes()));

//        if(librarian == null)
//            return null;
//
//
//        ModelMapper modelMapper = new ModelMapper();
//
//        LibrarianDetails librarianDetails =  modelMapper.map(librarian, LibrarianDetails.class);
//        librarianDetails.setToken("TESTTOKENA");

        return new LibrarianDetails();
    }
}
