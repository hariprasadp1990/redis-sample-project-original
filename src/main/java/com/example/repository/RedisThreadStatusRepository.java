package com.example.repository;
import com.example.model.RedisThreadStatus;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import org.springframework.data.redis.core.HashOperations;
import javax.annotation.Resource;

@Repository("redisThreadStatusRepository")
public interface RedisThreadStatusRepository extends JpaRepository<RedisThreadStatus, Long> {

   
    public List<RedisThreadStatus> findById(Integer id);


}
