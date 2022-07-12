package com.MS_Commissions.MS_Commissions.repository;

import com.MS_Commissions.MS_Commissions.model.Commissions;
import org.bson.types.ObjectId;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommissionRepository extends ReactiveCrudRepository<Commissions, ObjectId> {
}
