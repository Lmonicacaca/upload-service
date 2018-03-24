package com.mbr.upload.manager;


import com.mbr.upload.domain.Upload;

public interface UploadManager {

    void save(Upload upload);

    Upload queryById(Long id);

}
