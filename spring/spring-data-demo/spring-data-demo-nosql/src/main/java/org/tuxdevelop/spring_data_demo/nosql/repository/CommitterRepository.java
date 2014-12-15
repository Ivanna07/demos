package org.tuxdevelop.spring_data_demo.nosql.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.tuxdevelop.spring_data_demo.nosql.domain.Committer;

public interface CommitterRepository extends MongoRepository<Committer,String>{
}
