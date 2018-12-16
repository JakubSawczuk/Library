package wat.ai.services.readers;

import wat.ai.models.Reader;
import wat.ai.services.readers.dtos.AddReaderDTO;
import wat.ai.services.readers.dtos.ReaderBasicInfo;
import wat.ai.services.readers.dtos.ReaderDetails;

import java.util.List;

public interface IReaderService {

    List<ReaderBasicInfo> getAllActiveUsers();

    ReaderDetails getReaderDetails(int readerId);

    ReaderDetails updateReader(ReaderDetails readerDetails);

    ReaderDetails deleteReader(ReaderDetails readerDetails);

    Reader addReader(AddReaderDTO addReaderDTO);
}
