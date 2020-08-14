package com.example.dyq.entity;


import com.example.dyq.exception.CustomerException;
import com.example.dyq.util.AesRsaUtil;
import com.example.dyq.util.BeanUtils;
import com.example.dyq.util.DesUtil;
import org.apache.commons.codec.digest.DigestUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;
import java.util.UUID;

public class RequestIndustrialDatagram {

    private String version;

    private String reqTrace;

    private String timestamp;

    private String agentNo;

    private String organizNo;

    private Object Data;

    private String Signature;

    private Object extras;

    public Object getExtras() {
        return extras;
    }

    public void setExtras(Object extras) {
        this.extras = extras;
    }

    public String getSignature() {
        return Signature;
    }

    public void setSignature(String signature) {
        Signature = signature;
    }

    public Object getData() {
        return Data;
    }

    public void setData(Object data) {
        Data = data;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getReqTrace() {
        return reqTrace;
    }

    public void setReqTrace(String reqTrace) {
        this.reqTrace = reqTrace;
    }

    public String getAgentNo() {
        return agentNo;
    }

    public void setAgentNo(String agentNo) {
        this.agentNo = agentNo;
    }

    public String getOrganizNo() {
        return organizNo;
    }

    public void setOrganizNo(String organizNo) {
        this.organizNo = organizNo;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public RequestIndustrialDatagram() {
        initDatagram();
    }

    private RequestIndustrialDatagram initDatagram() {
        this.setVersion("1.0");
        this.setReqTrace(UUID.randomUUID().toString().replaceAll("-", "").toUpperCase());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        this.setTimestamp(sdf.format(Calendar.getInstance().getTime()));
        return this;
    }

    public RequestIndustrialDatagram decryptRSA(String encryptString, String privateKey) throws Exception {
        try {
            RequestIndustrialDatagram rd = BeanUtils.json2Bean(encryptString, this.getClass());
            this.setReqTrace(rd.getReqTrace());
            this.setVersion(rd.getVersion());
            this.setTimestamp(rd.getTimestamp());
            byte[] aesKeyByte = AesRsaUtil.decryptKey(privateKey, rd.getSignature(), "UTF-8");
            this.setData(BeanUtils.json2Bean(AesRsaUtil.decryptData((String) rd.getData(), aesKeyByte, "UTF-8"), Map.class));
            this.setSignature(rd.getSignature());
            this.setExtras(rd.getExtras());
            return this;
        } catch (Exception e) {
            throw new CustomerException(e.getMessage(), e);
        }
    }

    public RequestIndustrialDatagram decryptRSA(String privateKey) throws Exception {
        try {
            byte[] aesKeyByte = AesRsaUtil.decryptKey(privateKey, this.getSignature(), "UTF-8");
            this.setData(BeanUtils.json2Bean(AesRsaUtil.decryptData((String) this.getData(), aesKeyByte, "UTF-8"), Map.class));
            return this;
        } catch (Exception e) {
            throw new CustomerException(e.getMessage(), e);
        }
    }

    public RequestIndustrialDatagram decryptDES(String encryptString, String key) throws Exception {
        try {
            RequestIndustrialDatagram rd = BeanUtils.json2Bean(encryptString, this.getClass());
            this.setReqTrace(rd.getReqTrace());
            this.setVersion(rd.getVersion());
            this.setTimestamp(rd.getTimestamp());
            this.setExtras(rd.getExtras());
            String signature = DigestUtils.md5Hex(rd.getData() + key);
            if (!rd.getSignature().equalsIgnoreCase(signature)) {// MD5验证忽略大小写
                throw new IllegalArgumentException("signature error!");
            }
            String dataStr = DesUtil.desDecode((String) rd.getData(), key);
            this.setData(BeanUtils.json2Bean(dataStr, Object.class));
            this.setSignature(rd.getSignature());
            return this;
        } catch (Exception e) {
            throw new CustomerException(e.getMessage(), e);
        }
    }

    public RequestIndustrialDatagram decryptDES(String key) throws Exception {
        try {
            String signature = DigestUtils.md5Hex(this.getData() + key);
            if (!this.getSignature().equalsIgnoreCase(signature)) {// MD5验证忽略大小写
                throw new IllegalArgumentException("signature error!");
            }
            String dataStr = DesUtil.desDecode((String) this.getData(), key);
            this.setData(BeanUtils.json2Bean(dataStr, Object.class));
            return this;
        } catch (Exception e) {
            throw new CustomerException(e.getMessage(), e);
        }
    }

    public String encryptDESString(String key) {
        try {
            String data = BeanUtils.bean2JSON(this.getData());
            String encodeData = DesUtil.desEncode(data, key);
            this.setData(encodeData);
            String signature = DigestUtils.md5Hex(encodeData + key);
            this.setSignature(signature);
        } catch (Exception var5) {
            var5.printStackTrace();
            throw new CustomerException(var5.getMessage(), var5.getCause());
        }
        return this.toString();
    }

    @Override
    public String toString() {
        try {
            return BeanUtils.bean2JSON(this);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomerException(e.getMessage(), e.getCause());
        }
    }
}
