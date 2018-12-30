package wat.ai.services.statisticts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wat.ai.models.views.TopReaders_V;
import wat.ai.repositories.StatisticRepository;

import java.util.List;
import java.util.logging.Logger;

@Service
public class StatisticServiceImpl implements IStatisticService{
    static final Logger LOGGER = Logger.getLogger(StatisticServiceImpl.class.getName());
    private final StatisticRepository statisticRepository;

    @Autowired
    public StatisticServiceImpl(StatisticRepository statisticRepository) {
        this.statisticRepository = statisticRepository;
    }

    @Override
    public List<TopReaders_V> getTopReaders_V() {
        return statisticRepository.readAllByCardNumberIsNotNull();
    }
}
