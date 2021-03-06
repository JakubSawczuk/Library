package wat.ai.services.readers;

import org.hibernate.exception.ConstraintViolationException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wat.ai.models.entities.Reader;
import wat.ai.repositories.ReaderRepository;
import wat.ai.services.readers.dtos.AddReaderDTO;
import wat.ai.services.readers.dtos.BookLoanReaderInfo;
import wat.ai.services.readers.dtos.ReaderBasicInfo;
import wat.ai.services.readers.dtos.ReaderDetails;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


@Service
public class ReaderServiceImpl implements IReaderService {
    private static final Logger LOGGER = Logger.getLogger(ReaderServiceImpl.class.getName());

    private final ReaderRepository readerRepository;

    @Autowired
    public ReaderServiceImpl(ReaderRepository readerRepository) {
        this.readerRepository = readerRepository;
    }

    @Override
    public List<ReaderBasicInfo> getAllActiveUsers() {
        ModelMapper modelMapper = new ModelMapper();
        List<Reader> activeReaderList = readerRepository.findByIsActive(true);

        List<ReaderBasicInfo> readerBasicInfoList = new ArrayList<>();

        activeReaderList.forEach(reader -> {
            ReaderBasicInfo readerBasicInfo = modelMapper.map(reader, ReaderBasicInfo.class);
            readerBasicInfoList.add(readerBasicInfo);
        });

        return readerBasicInfoList;
    }

    @Override
    public ReaderDetails getReaderDetails(int readerId) {
        Reader reader = readerRepository.findByReaderId(readerId);
        ModelMapper modelMapper = new ModelMapper();

        return modelMapper.map(reader, ReaderDetails.class);
    }

    @Override
    public ReaderDetails updateReader(ReaderDetails readerDetails) {
            ModelMapper modelMapper = new ModelMapper();

            Reader readerBeforeUpdate = readerRepository.findByReaderId(readerDetails.getReaderId());
            Reader readerAfterUpdate = modelMapper.map(readerDetails, Reader.class);

            readerAfterUpdate.setActive(readerBeforeUpdate.isActive());
            readerAfterUpdate.setPasswordHash(readerBeforeUpdate.getPasswordHash());

            try {
                readerRepository.save(readerAfterUpdate);
            }catch (ConstraintViolationException e){
                LOGGER.log(Level.SEVERE, e.toString(), e);
            }
            return readerDetails;
    }

    @Override
    public void deleteReader(int readerId) {
        Reader reader = readerRepository.findByReaderId(readerId);
        reader.setActive(false);

        try {
            readerRepository.save(reader);
        }catch (Exception e){
            LOGGER.log(Level.SEVERE, e.toString(), e);
        }
    }

    @Override
    public Reader addReader(AddReaderDTO theAddReaderDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Reader reader = modelMapper.map(theAddReaderDTO, Reader.class);

        try {
            reader.setPasswordHash(Base64.getEncoder().encodeToString(theAddReaderDTO.getPassword().getBytes()));
            reader.setActive(true);
            readerRepository.save(reader);
        } catch (ConstraintViolationException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
        }
        return reader;
    }

    @Override
    public List<BookLoanReaderInfo> readerInfoToLoans() {
        List<Reader> activeReaderList = readerRepository.findByIsActive(true);
        List<BookLoanReaderInfo> bookLoanReaderInfoList = new ArrayList<>();

        activeReaderList.forEach(reader -> {
            String displayValue = buildDisplayName(reader);
            bookLoanReaderInfoList.add(new BookLoanReaderInfo(displayValue, reader.getReaderId()));
        });

        return bookLoanReaderInfoList;
    }

    private String buildDisplayName(Reader reader){
        StringBuilder displayValue = new StringBuilder();
        return displayValue.append(reader.getFirstName())
                .append(" ")
                .append(reader.getLastName())
                .append(" (")
                .append(reader.getCardNumber())
                .append(")").toString();
    }

}
