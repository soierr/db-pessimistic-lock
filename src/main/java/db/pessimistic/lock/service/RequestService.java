package db.pessimistic.lock.service;

import db.pessimistic.lock.model.Request;
import db.pessimistic.lock.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RequestService {
    @Autowired
    private RequestRepository requestRepository;
    public void incrementSla(int requestCode) {
        Request requestFound = requestRepository.findByCode(requestCode);
        requestFound.setSla(requestFound.getSla()+1);
        //requestRepository.save(requestFound);
    }
}
