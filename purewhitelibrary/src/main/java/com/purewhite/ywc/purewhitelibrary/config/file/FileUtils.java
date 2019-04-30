package com.purewhite.ywc.purewhitelibrary.config.file;

import android.os.Environment;
import android.text.TextUtils;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;


/**
 * @author yuwenchao
 * Environment.getExtemalStorageState() // 获取SDcard(手机存储)的状态
 * Environment.MEDIA_MOUNTED //手机装有SDCard(手机存储),并且可以进行读写
 * Environment.isExternalStorageRemovable() 是否可移除的sdcard
 *
 *getCacheDir()方法用于获取/data/data/<application package>/cache目录
 *getFilesDir()方法用于获取/data/data/<application package>/files目录
 * 二个目录分别对应 设置->应用->应用详情里面的”清除缓存“与”清除数据“选项
 *
 *getExternalCacheDir()方法可以获取到 SDCard/Android/data/你的应用包名/cache/目录，一般存放临时缓存数据
 *getExternalFilesDir()方法可以获取到 SDCard/Android/data/你的应用的包名/files/ 目录，一般放一些长时间保存的数据
 *当你的应用在被用户卸载后，SDCard/Android/data/你的应用的包名/ 这个目录下的所有文件都会被删除，不会留下垃圾信息。
 *
 * 手机内存：其实是内部存储的根目录，在ES文件浏览器对应的是根目录，路径为：Environment.getDataDirectory().getParentFile()
 * SD卡：这里的SD卡是指内置的SD卡，路径为：Environment.getExternalStorageDirectory()
 *
 *
 */
public final class FileUtils {

    private FileUtils() {
        throw new UnsupportedOperationException("you can not create object");
    }

    /**
     * 判断sdcrad是否可以使用
     * @return true sdcard存在可以读写并且不可以移除的时候才使用
     */
    public static boolean sdCan()
    {
       return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
               &&!Environment.isExternalStorageRemovable();
    }

    /**
     * 获取文件大小
     * @param file
     * @return
     * @throws Exception
     */
    public static long getFileSize(File file) throws Exception {
        long size = 0;
        try {
            File[] fileList = file.listFiles();
            for (int i = 0; i < fileList.length; i++) {
                // 如果下面还有文件 
                if (fileList[i].isDirectory()) {
                    size = size + getFileSize(fileList[i]);
                } else {
                    size = size + fileList[i].length();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return size;
    }

    /**
     * 删除文件或者目录下所有数据
     * @param dir
     * @return
     */
    private static boolean deleteDir(File dir) {
        /**
         * 判断是否不是目录，如果是目录就寻找目录下的文件删除，如果不是就删除
         */
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }

    /**
     * 根据文件路径获取文件
     *
     * @param filePath 文件路径
     * @return 文件
     */
    public static File getFilePath(String filePath) {
        return TextUtils.isEmpty(filePath) ? null : new File(filePath);
    }

    /**
     * 判断目录是否存在，不存在则判断是否创建成功
     *
     * @param file 文件
     * @return {@code true}: 存在或创建成功<br>{@code false}: 不存在或创建失败
     */
    public static boolean createOrExistsDir(File file) {
        /**
         * file.exists() 测试文件是否存在
         * true file.isDirectory()判断是否为文件目录
         *false file.mkdirs()判断创建目录是否成功
         */
        return file != null && (file.exists() ? file.isDirectory() : file.mkdirs());
    }

    /**
     * 判断目录是否存在，不存在则判断是否创建成功
     *
     * @param dirPath 目录路径
     * @return {@code true}: 存在或创建成功<br>{@code false}: 不存在或创建失败
     */
    public static boolean createOrExistsDir(String dirPath) {
        return createOrExistsDir(getFilePath(dirPath));
    }


    /**
     * 判断文件是否存在，不存在则判断是否创建成功
     *
     * @param file 文件
     * @return {@code true}: 存在或创建成功<br>{@code false}: 不存在或创建失败
     */
    public static boolean createOrExistsFile(File file) {
        if (file == null)
            return false;
        /**
         * 如果文件存在，则判断是否是文件
         */
        if (file.exists())
            return file.isFile();
        /**
         * 判断文件更目录是不是目录，如果不是目录返回 false
         */
        if (!createOrExistsDir(file.getParentFile()))
            return false;
        /**
         * 如果是就创建新文件，返回创建是否成功，如果出现异常就返回false
         */
        try
        {
            return file.createNewFile();
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 文件大小转换
     * @param size
     * @return
     */
    public static String getFileSizeShift(double size) {
        double kiloByte = size / 1024;
        if (kiloByte < 1) {
            return "0K";
        }
        double megaByte = kiloByte / 1024;
        if (megaByte < 1)
        {
            BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
            return result1.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "KB";
        }
        double gigaByte = megaByte / 1024;
        if (gigaByte < 1) {
            BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "MB";
        }
        double teraBytes = gigaByte / 1024;
        if (teraBytes < 1) {
            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "GB";
        }
        BigDecimal result4 = new BigDecimal(teraBytes);
        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "TB";
    }
}
