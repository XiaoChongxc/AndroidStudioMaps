package com.example.admin_xc.basemodule.util;

import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * User:    Xiaoc
 * 项目名:  WZB3.0
 * Package: baizhuan.hangzhou.com.wzb30.util
 * Date:    2016-07-07
 * Time:    15:52
 * 类描述：文件操作类
 */
public class FileUtil {

    /***********
     * 保存升级APK的目录
     ***********/
    public static final String dir = "52wzb";

    public static final String fileName = "wzb";

    public static String filePath = "";

    public static boolean ExistSDCard() {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            return true;
        } else
            return false;
    }

    private static File getFileDie() {
        File updateDir = new File(Environment.getExternalStorageDirectory() + "/" + dir + "/");
        if (!updateDir.exists()) {
            updateDir.mkdirs();
        }
        return updateDir;
    }

    /**
     * 方法描述：createFile方法
     *
     * @return
     * @see FileUtil
     */
    public static File createFile() {

        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            File updateDir = getFileDie();
            File updateFile = new File(updateDir + "/" + fileName + ".apk");
            filePath = updateFile.getAbsolutePath();
            if (!updateFile.exists()) {
                try {
                    updateFile.createNewFile();
                    return updateFile;
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }
            return updateFile;
        } else {
            return null;
        }
    }

    public interface FileDownLinstener {
        void onProgress(float progress);

        void onSuccess();

        void onFailure();

    }

    /****
     * 流 保存为文件
     * <p/>
     * 如果保存成功， 就返回 文件的绝对路径，  失败返回 null
     */
    public static String saveFile(InputStream is, long totalsize, FileDownLinstener linstener) {
        byte[] buf = new byte[2048];
        int len = 0;
        int downSize = 0;
        FileOutputStream fos = null;
        try {
            File file = createFile();
            fos = new FileOutputStream(file);
            while ((len = is.read(buf)) != -1) {
                fos.write(buf, 0, len);
                downSize += len;
                linstener.onProgress((float) downSize / totalsize);
                L.e("totalsize:" + totalsize);
            }
            fos.flush();
            linstener.onSuccess();
            //如果下载文件成功，第一个参数为文件的绝对路径
            return file.getAbsolutePath();

        } catch (IOException e) {
            L.e("保存文件失败" + e.getMessage());
            linstener.onFailure();
            return null;
        } finally {
            try {
                if (is != null) is.close();
            } catch (IOException e) {
            }
            try {
                if (fos != null) fos.close();
            } catch (IOException e) {
            }
        }
    }
}
