package wat.ai.services.readers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wat.ai.controllers.readers.dtos.ReaderBasicInfo;
import wat.ai.controllers.readers.dtos.ReaderDetails;
import wat.ai.models.Reader;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


@Service
public class ReaderServiceImpl implements IReaderService {
    private static final Logger LOGGER = Logger.getLogger(ReaderServiceImpl.class.getName());

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<ReaderBasicInfo> getAllActiveUsers() {
        List<ReaderBasicInfo> readerBasicInfoList = new ArrayList<>();
        List<Reader> readerList = (List<Reader>) entityManager.createQuery("SELECT r FROM Reader r WHERE r.isActive = true")
                                                                .getResultList();
        ModelMapper modelMapper = new ModelMapper();

        readerList.forEach(reader -> {
            ReaderBasicInfo readerBasicInfo = modelMapper.map(reader, ReaderBasicInfo.class);
            readerBasicInfoList.add(readerBasicInfo);
        });

        return readerBasicInfoList;
    }

    @Override
    public ReaderDetails getReaderDetails(int readerId) {
        Reader reader = (Reader) entityManager.createQuery("SELECT r from Reader r where r.readerId = :pReaderId")
                                                .setParameter("pReaderId", readerId)
                                                .getSingleResult();

        ModelMapper modelMapper = new ModelMapper();
        ReaderDetails readerDetails = modelMapper.map(reader, ReaderDetails.class);

        return readerDetails;
    }

    @Override
    public ReaderDetails updateReader(ReaderDetails readerDetails) {
        ModelMapper modelMapper = new ModelMapper();

        Reader beforeUpdateReader = entityManager.find(Reader.class, readerDetails.getReaderId());
        Reader afterUpdateReader = modelMapper.map(readerDetails, Reader.class);

        afterUpdateReader.setActive(beforeUpdateReader.isActive());
        afterUpdateReader.setPasswordHash(beforeUpdateReader.getPasswordHash());

        entityManager.merge(afterUpdateReader);
        return readerDetails;
    }


}
