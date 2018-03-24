package com.mbr.upload.manager.impl;

import com.mbr.upload.domain.Upload;
import com.mbr.upload.manager.UploadManager;
import com.mbr.upload.repository.UploadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UploadManagerImpl implements UploadManager {

    @Autowired
    private UploadRepository uploadRepository;

    @Override
    public void save(Upload upload) {
        uploadRepository.save(upload);
    }

    @Override
    public Upload queryById(Long id) {
        return uploadRepository.queryById(id);
    }
}
