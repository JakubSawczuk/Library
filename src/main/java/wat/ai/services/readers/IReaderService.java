package wat.ai.services.readers;

import wat.ai.controllers.readers.dtos.ReaderBasicInfo;
import wat.ai.controllers.readers.dtos.ReaderDetalis;

import java.util.List;

public interface IReaderService {

    List<ReaderBasicInfo> getAllAtciveUsers();

    ReaderDetalis getUserDetails(int readerId);
}
