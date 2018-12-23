package com.digital.dance.common.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Random;

public interface VerifyCodeUtilsService {
    public final String V_CODE = "_CODE";
    /**
     * 验证码对象
     * @author xinyuliu
     *
     */
    public static class Verify{
    	
    	private String code;//如 1 + 2
    	
    	private Integer value;//如  3

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public Integer getValue() {
			return value;
		}

		public void setValue(Integer value) {
			this.value = value;
		}
    }
  
    /** 
     * 使用系统默认字符源生成验证码 

     * @return 
     */  
    public Verify generateVerify();
    
    /** 
     * 使用系统默认字符源生成验证码 
     * @param verifySize    验证码长度 
     * @return 
     */  
    public String generateVerifyCode(int verifySize);
    
    /** 
     * 使用指定源生成验证码 
     * @param verifySize    验证码长度 
     * @param sources   验证码字符源 
     * @return 
     */  
    public String generateVerifyCode(int verifySize, String sources);
      
    /** 
     * 生成随机验证码文件,并返回验证码值 
     * @param w 
     * @param h 
     * @param outputFile 
     * @param verifySize 
     * @return 
     * @throws IOException 
     */  
    public String outputVerifyImage(int w, int h, File outputFile, int verifySize) throws IOException;
      
    /** 
     * 输出随机验证码图片流,并返回验证码值 
     * @param w 
     * @param h 
     * @param os 
     * @param verifySize 
     * @return 
     * @throws IOException 
     */  
    public String outputVerifyImage(int w, int h, OutputStream os, int verifySize) throws IOException;
      
    /** 
     * 生成指定验证码图像文件 
     * @param w 
     * @param h 
     * @param outputFile 
     * @param code 
     * @throws IOException 
     */  
    public void outputImage(int w, int h, File outputFile, String code) throws IOException;
      
    /** 
     * 输出指定验证码图片流 
     * @param w 
     * @param h 
     * @param os 
     * @param code 
     * @throws IOException 
     */  
    public void outputImage(int w, int h, OutputStream os, String code) throws IOException;
      

    public void main(String[] args) throws IOException;
   
}  
