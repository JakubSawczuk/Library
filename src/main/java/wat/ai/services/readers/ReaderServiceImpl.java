package wat.ai.services.readers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wat.ai.controllers.readers.dtos.ReaderBasicInfo;
import wat.ai.controllers.readers.dtos.ReaderDetalis;
import wat.ai.models.Reader;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReaderServiceImpl implements IReaderService {

    private final static String allActiveUsersQuery = "SELECT r FROM Reader r WHERE r.isActive = true";

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<ReaderBasicInfo> getAllAtciveUsers() {
        List<ReaderBasicInfo> readerBasicInfoList = new ArrayList<>();
        List<Reader> readerList = (List<Reader>) entityManager.createQuery(allActiveUsersQuery).getResultList();
        ModelMapper modelMapper = new ModelMapper();

        readerList.forEach(reader -> {
            ReaderBasicInfo readerBasicInfo = modelMapper.map(reader, ReaderBasicInfo.class);
            readerBasicInfoList.add(readerBasicInfo);
        });

        return readerBasicInfoList;
    }

    @Override
    public ReaderDetalis getUserDetails(int readerId) {
        Reader reader = (Reader) entityManager.createQuery("SELECT r from Reader r where r.readerId = :pReaderId")
                                                .setParameter("pReaderId", readerId)
                                                .getSingleResult();

        ModelMapper modelMapper = new ModelMapper();
        ReaderDetalis ReaderDetalis = modelMapper.map(reader, ReaderDetalis.class);

        return ReaderDetalis;
    }


}
