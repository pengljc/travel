package com.jxd.travel.controller;

import ch.qos.logback.core.util.FileUtil;
import com.jxd.travel.controller.ex.FileStateException;
import com.jxd.travel.controller.ex.FileUploadIOException;
import com.jxd.travel.util.JsonResult;
import com.jxd.travel.util.SystemAPI;
import org.apache.ibatis.annotations.Delete;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

/**
 * @ClassName FileController
 * @Description TODO
 * @Author liujianpeng
 * @Date 2022/9/29
 * @Version 1.0
 */
@RestController
@RequestMapping("/files")
public class FileController extends BaseController {

    /**
     * 上传文件
     * @param session session对象
     * @param file 接收前台传入的文件
     * @return 返回存入图片的url
     */
    @PostMapping("/upload")
    public JsonResult<String> upload(HttpSession session, @RequestParam("file") MultipartFile file) {
        //获取上传文件的原始文件名
        String originalFilename = file.getOriginalFilename();
        //获取到本项目的根目录

        //为了防止出现同名图片的覆盖情况。需要重新命名
        //生成新文件名
        String filename = UUID.randomUUID().toString().toUpperCase();
        //获取上传图片的扩展名
        //获取文件名中最后一个“.”的索引
        int index = originalFilename.lastIndexOf(".");
        //获取到扩展名
        String suffix = originalFilename.substring(index);
        //拼接形成图片的新名字
        String fullFilename = filename + suffix;
        //图片的目录路径：e:/img/tickets/
        String targetPath = SystemAPI.filePath;
        //在targetPath目录中，创建一个名字为图片新名字的文件。由dest对象指向它
        File dest = new File(targetPath, fullFilename);
        //把当前file文件中的数据，写入到dest文件中
        try {
            file.transferTo(dest);
        } catch (FileStateException e) {
            //正在读写的时候，文件状态改变了，被打开、移除
            throw new FileStateException("文件状态异常");
        } catch (IOException e) {
            throw new FileUploadIOException("文件读写异常");
        }
        //System.out.println(targetPath);
        //System.out.println(fullFilename);
        //把图像的路径返回
        String avatar = "http://localhost:8086/" + fullFilename;

        return new JsonResult<String>(OK,avatar);
    }

    /**
     * 批量删除图片
     * @param delImgUrl 存多张图片url的字符串数组
     * @return 无
     */
    @DeleteMapping
    public JsonResult<Void> delete(@RequestBody String[] delImgUrl) {
        //图片的目录路径：e:/img/tickets/
        String targetPath = SystemAPI.filePath;

        for (String s : delImgUrl) {
            //对传入url进行处理，去掉http之类的东西
            int index = s.lastIndexOf("/");
            String imgName = s.substring(index + 1);
            //图片完整路径
            String imgPath = targetPath + imgName;
            //创建file对象，进行删除操作
            File file = new File(imgPath);
            file.delete();
        }

        return new JsonResult<Void>(OK);
    }
}
