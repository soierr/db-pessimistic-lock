package db.pessimistic.lock.repository;

import db.pessimistic.lock.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;

@Repository
public interface RequestRepository extends JpaRepository<Request, Integer> {

    //it MUST be located here not in service layer. There it does not work
    //as test revealed it does not matter
    // whether PESSIMISTIC_READ OR PESSIMISTIC_WRITE is used
    @Lock(LockModeType.PESSIMISTIC_READ)
    Request findByCode(int code);
}
