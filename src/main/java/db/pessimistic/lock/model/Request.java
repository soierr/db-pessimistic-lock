package db.pessimistic.lock.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;


@Data
@Entity
public class Request {
    @Id
    private int code;
    private String name;
    private long sla;
}
