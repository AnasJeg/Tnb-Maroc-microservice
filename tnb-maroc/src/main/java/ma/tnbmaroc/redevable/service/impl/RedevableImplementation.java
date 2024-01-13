package ma.tnbmaroc.redevable.service.impl;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import ma.tnbmaroc.redevable.domain.Redevable;
import ma.tnbmaroc.redevable.repository.RedevableRepository;
import ma.tnbmaroc.redevable.service.RedevableService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Transactional
@Slf4j
public class RedevableImplementation implements RedevableService {

    private final RedevableRepository redevableRepository;



    public RedevableImplementation(RedevableRepository rp) {
        this.redevableRepository = rp;

    }

    @KafkaListener(topics = "authentication-service", groupId = "tnb-service", containerFactory = "kafkaListenerContainerFactory")
    public void consumeRedevableMessage(ConsumerRecord<String, Redevable> record) {
        try {
            Redevable redevable = record.value();
            log.info("Received and saved Redevable from Kafka: {}", redevable);
            this.save(redevable);
        } catch (Exception e) {
            log.error("Error processing Kafka message: {}", e.getMessage());
        }
    }

    @Override
    public Redevable save(Redevable redevable) {
        log.info("save redevable {}", redevable);
        return this.redevableRepository.save(redevable);
    }


    @Override
    public Page<Redevable> getAll(Pageable pageable) {
        log.info("get all redevable page {} size {}",pageable.getPageNumber(), pageable.getPageSize());
        return this.redevableRepository.findAll(pageable);
    }

    @Override
    public Redevable update(Redevable redevable) {
        log.info("update redevable {}",redevable);
        return this.redevableRepository.saveAndFlush(redevable);
    }

    @Override
    public boolean delete(Long id) {
        if(this.redevableRepository.findById(id).isPresent()){
            log.info("delete redevable {}",id);
            this.redevableRepository.delete(this.redevableRepository.findById(id).get());
            return  true;
        }
        log.error("error delete redevable {}",id);
        return false;
    }

    @Override
    public Redevable getById(Long id) {
        return this.redevableRepository.findById(id).get();
    }



    @Override
    public Redevable findByCin(String cin) {
        return this.redevableRepository.findByCin(cin);
    }


}
