package com.bonc.businesscircle.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class FileUtil {
    private static Logger logger = LoggerFactory.getLogger(FileUtil.class);
//    public static File TEMP_PATH = null;
//    @Value("${tempFilePath}")
//    static String tempFilePath;
//
//    static {
//        try {
//            TEMP_PATH = new File(tempFilePath);
//        } catch (Exception e) {
//            File f = new File(FileUtil.class.getResource("/").getPath());
//            TEMP_PATH = new File(f, "tempFilePath");
//        }
//        mkdirs(TEMP_PATH);
//    }

//    /**
//     * 创建不重复的临时目录
//     *
//     * @return
//     */
//    public static File createTempPath() {
//        File tempPath = new File(TEMP_PATH, UUID.randomUUID().toString());
//        FileUtil.mkdirs(tempPath);
//        return tempPath;
//    }

    /**
     * 读取文件内容为字符串
     *
     * @param file
     * @return
     */
    public static String readFile2Str(File file) {
        byte[] bytes = readFile2Bytes(file);
        if (bytes == null) {
            return null;
        } else {
            try {
                return new String(bytes, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    /**
     * 读取文件内容为字符串
     *
     * @param file
     * @return
     */
    public static byte[] readFile2Bytes(File file) {
        InputStream jsonIn = null;
        try {
            jsonIn = new FileInputStream(file);
            byte[] ch = new byte[jsonIn.available()];
            jsonIn.read(ch);
            return ch;
        } catch (Exception e) {
            logger.error("read file to string error", e);
        } finally {
            closeStream(jsonIn);
        }
        return null;
    }

    public static void closeStream(Closeable stream) {
        if (stream != null) {
            try {
                stream.close();
            } catch (IOException e) {
                logger.error("close InputStream/OutputStream error.", e);
            }
        }
    }

    /**
     * 输入流写文件
     *
     * @param file
     * @param in
     */
    public static File wirteFile(File file, InputStream in, boolean closeInput) {
        FileOutputStream out = null;
        byte[] ch = new byte[1024];
        try {
            out = new FileOutputStream(file);
            int i = 0;
            while ((i = in.read(ch)) != -1) {
                out.write(ch, 0, i);
            }
            return file;
        } catch (Exception e) {
            logger.error("wirteFile error.", e);
            return null;
        } finally {
            if (closeInput) {
                closeStream(in);
            }
            closeStream(out);
        }
    }

    /**
     * 获得指定文件的byte数组
     */
    private byte[] getBytes(String filePath) {
        byte[] buffer = null;
        try {
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }

    /**
     * 根据byte数组，生成文件
     */
    public static File getFile(byte[] bfile, String filePath, String fileName) {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try {
            File dir = new File(filePath);
            //判断文件目录是否存在
            if (!dir.exists()) {
                dir.mkdirs();
            }
            file = new File(filePath + fileName);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bfile);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return file;
    }

    /**
     * 创建目录
     *
     * @param file
     */
    public static void mkdirs(File file) {
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    /**
     * 删除文件或文件夹（包含子文件）
     *
     * @param file
     */
    public static void deleteFile(File file) {
        if (file.isDirectory()) {
            for (File childFile : file.listFiles()) {
                deleteFile(childFile);
            }
            file.delete();
            logger.info("删除文件夹成功：" + file.getName());
        } else {
            file.delete();
            logger.info("删除文件成功：" + file.getName());
        }
    }

    /**
     * 根据文件名（完整路径或纯文件名）截取后缀名
     *
     * @param fullName
     * @return
     */
    public static String getSuffix(String fullName) {
        if (SequenceTool.isEmpty(fullName)) {
            return "";
        }
        int index = fullName.lastIndexOf("\\");
        int index1 = fullName.lastIndexOf("/");
        if (index1 > index) {
            index = index1;
        }
        fullName = fullName.substring(index + 1);
        index = fullName.lastIndexOf(".");
        if (index > 0) {
            return fullName.substring(index + 1, fullName.length());
        } else {
            return "";
        }
    }

    public static boolean httpDownload(String httpUrl, File file) {
        // 下载网络文件  
        int bytesum = 0;
        int byteread = 0;

        URL url = null;
        try {
            url = new URL(httpUrl);
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
            return false;
        }
        try {
            URLConnection conn = url.openConnection();
            InputStream inStream = conn.getInputStream();
            FileOutputStream fs = new FileOutputStream(file);

            byte[] buffer = new byte[1204];
            while ((byteread = inStream.read(buffer)) != -1) {
                bytesum += byteread;
                fs.write(buffer, 0, byteread);
            }
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) throws Exception {
        File f = new File(FileUtil.class.getResource("/").getPath());
        System.out.println(f.getPath());
    }
}
