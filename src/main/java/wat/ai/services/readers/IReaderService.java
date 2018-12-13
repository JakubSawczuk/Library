package wat.ai.services.readers;

import wat.ai.controllers.readers.dtos.ReaderBasicInfo;
import wat.ai.controllers.readers.dtos.ReaderDetails;

import java.util.List;

public interface IReaderService {

    List<ReaderBasicInfo> getAllActiveUsers();

    ReaderDetails getReaderDetails(int readerId);

    ReaderDetails updateReader(ReaderDetails readerDetails);
}
