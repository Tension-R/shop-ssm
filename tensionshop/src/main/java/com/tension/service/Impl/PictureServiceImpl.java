package com.tension.service.Impl;

import com.tension.bean.PictureResult;
import com.tension.service.PictureService;
import com.tension.util.FastDFSClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * 图片上传Service
 */
@Service
public class PictureServiceImpl implements PictureService{

    @Value("${IMAGE_SERVER_BASE_URL}")
    private String IMAGE_SERVER_BASE_URL;

    public PictureResult uploadPic(MultipartFile picFile) {
        PictureResult result = new PictureResult();
        if (picFile.isEmpty()){
            result.setError(1);
            result.setMessage("图片不存在");
            return result;
        }
        try {
            //取扩展名
            String originaFileName = picFile.getOriginalFilename();
            String extName = originaFileName.substring(originaFileName.indexOf(".") + 1);

            FastDFSClient client = new FastDFSClient("properties/client.conf");
            String url = client.uploadFile(picFile.getBytes(),extName);
            url = IMAGE_SERVER_BASE_URL + url;

            result.setError(0);
            result.setUrl(url);
        } catch (Exception e) {
            result.setError(1);
            result.setMessage("图片上传失败");
        }
        return result;
    }
}
