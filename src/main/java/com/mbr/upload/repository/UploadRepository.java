package com.mbr.upload.repository;

import com.mbr.upload.domain.Upload;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component
public interface UploadRepository extends MongoRepository<Upload,Long> {

    Upload queryById(Long id);
}
