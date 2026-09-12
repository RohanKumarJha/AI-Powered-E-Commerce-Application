package com.ecommerce.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MongoSequenceService {

    private final MongoOperations mongoOperations;

    public long getNextSequence(String sequenceName) {

        Query query = new Query(
                Criteria.where("_id").is(sequenceName)
        );

        Update update = new Update()
                .inc("sequence", 1);

        FindAndModifyOptions options =
                new FindAndModifyOptions()
                        .returnNew(true)
                        .upsert(true);

        MongoSequence sequence =
                mongoOperations.findAndModify(
                        query,
                        update,
                        options,
                        MongoSequence.class
                );

        return sequence.getSequence();
    }

    private static class MongoSequence {

        private String id;
        private long sequence;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public long getSequence() {
            return sequence;
        }

        public void setSequence(long sequence) {
            this.sequence = sequence;
        }
    }
}