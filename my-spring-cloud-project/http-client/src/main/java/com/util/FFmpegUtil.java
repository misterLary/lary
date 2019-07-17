package com.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 对视频、音频文件进行处理的工具类
 *
 * @author lx
 * @since 2016年7月25日 13:41:14
 * @version 1.0
 * @description 主要是处理视频、音频的处理；
 *  1.
 *
 */
public class FFmpegUtil {

    private static final Logger LOG = LoggerFactory.getLogger(FFmpegUtil.class);

    private static String FFMPEG_EXE_PATH = FFmpegUtil.class.getResource("/").getPath()+"ffmpeg/bin/ffmpeg.exe";


    public static void main(String[] args) {
        String inputFilePath = "https://1252093142.vod2.myqcloud.com/4704461fvodcq1252093142/48c8a9475285890781000441992/playlist.m3u8";
        String outputFilePath = "D:\\测试资料\\test9.jpg";
        List<String> command = new ArrayList<String>();
        command.add(FFMPEG_EXE_PATH);
        command.add("-i");
        command.add(inputFilePath);
        command.add("-y");
        command.add("-f");
        command.add("image2");
        command.add("-t");
        command.add("0.001");
        command.add("-s");
        command.add("600*460");
        command.add(outputFilePath);
        ProcessBuilder builder = new ProcessBuilder();
        builder.command(command);
        builder.redirectErrorStream(true);//正常信息和错误信息合并输出
        try {
            Process process = builder.start();//开始执行命令
            StringBuffer sbf = new StringBuffer();//如果你想获取到执行完后的信息，那么下面的代码也是需要的
            String line = null;
            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
            while ((line = br.readLine()) != null) {
                sbf.append(line);
                sbf.append(" ");
            }
            String resultInfo = sbf.toString();
            LOG.info("视频截图获取:",resultInfo);
        } catch (IOException e) {
            e.printStackTrace();
            LOG.error("视频截图获取失败",e.getMessage());
        }
    }
}

