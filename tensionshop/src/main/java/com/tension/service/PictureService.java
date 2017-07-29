package com.tension.service;

import com.tension.bean.PictureResult;
import org.springframework.web.multipart.MultipartFile;

public interface PictureService {
    PictureResult uploadPic(MultipartFile picFile);
}
