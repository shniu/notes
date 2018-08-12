package com.digcredit.shniu.hadoop.kpi;

/**
 * Created by shniu on 2017/8/8.
 *
 * kpi实体类,每个kpi对象包含了对应日志的每个属性
 * 提供parse方法对每行的日志进行解析
 * 重写toString方法
 * main方法中提供了demo数据进行测试
 */
public class Kpi {

    private Boolean isValidate = true;

    private String remoteAddr;  // 用户ip 0
    private String remoteUser;  // 客户端用户名 1
    private String requestTime;  // 请求时间 3
    private String requestMethod;  // 请求方法 5
    private String requestPage;  // 请求页面 6
    private String requestHttp;  // http协议信息 7
    private String requestStatus;  // 返回的状态码 8
    private String sentBytes;  // 发送的页面字节数 9
    private String httpReferrer;  // 从什么页面跳转进来,10

    public Boolean getValidate() {
        return isValidate;
    }

    public void setValidate(Boolean validate) {
        isValidate = validate;
    }

    public String getRemoteAddr() {
        return remoteAddr;
    }

    public void setRemoteAddr(String remoteAddr) {
        this.remoteAddr = remoteAddr;
    }

    public String getRemoteUser() {
        return remoteUser;
    }

    public void setRemoteUser(String remoteUser) {
        this.remoteUser = remoteUser;
    }

    public String getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(String requestTime) {
        this.requestTime = requestTime;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public String getRequestPage() {
        return requestPage;
    }

    public void setRequestPage(String requestPage) {
        this.requestPage = requestPage;
    }

    public String getRequestHttp() {
        return requestHttp;
    }

    public void setRequestHttp(String requestHttp) {
        this.requestHttp = requestHttp;
    }

    public String getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    public String getSentBytes() {
        return sentBytes;
    }

    public void setSentBytes(String sentBytes) {
        this.sentBytes = sentBytes;
    }

    public String getHttpReferrer() {
        return httpReferrer;
    }

    public void setHttpReferrer(String httpReferrer) {
        this.httpReferrer = httpReferrer;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    private String userAgent;  // 用户使用的客户端信息, 数组剩下的部分

    /**
     * 解析每行日志数据, 包括验证数据的合法性, 将各个所需要的属性填充到kpi对象中
     * @param line 一行日志数据
     * @return 返回的kpi对象包含了该行日志的所有信息
     **/
    public static Kpi parse(String line) {
        String[] items = line.split(" ");
        Kpi kpi = new Kpi();

        // 标准数据格式为23个元素
        if (items.length > 13) {
            // 根据每行日志的数据位置进行解析
            kpi.setRemoteAddr(items[0]);
            kpi.setRemoteUser(items[1]);
            kpi.setRequestTime(items[3].substring(1));
            kpi.setRequestMethod(items[5].substring(1));
            kpi.setRequestPage(items[6]);
            if (items[7].length() < 1) {
                kpi.setRequestHttp("http");
            } else {
                kpi.setRequestHttp(items[7].substring(0, items[7].length() - 1));
            }
            kpi.setRequestStatus(items[8]);
            kpi.setSentBytes(items[9]);
            kpi.setHttpReferrer(items[10]);
            kpi.setUserAgent(items[11]);

            // 过滤响应码>400的错误请求
            if (!kpi.getRequestStatus().equals("") && Integer.parseInt(kpi.getRequestStatus()) > 400) {
                kpi.setValidate(false);
            }
        } else {
            kpi.setValidate(false);
        }

        return kpi;
    }

    @Override
    public String toString() {
        return "Kpi{" +
                "isValidate=" + isValidate +
                ", remoteAddr='" + remoteAddr + '\'' +
                ", remoteUser='" + remoteUser + '\'' +
                ", requestTime='" + requestTime + '\'' +
                ", requestMethod='" + requestMethod + '\'' +
                ", requestPage='" + requestPage + '\'' +
                ", requestHttp='" + requestHttp + '\'' +
                ", requestStatus='" + requestStatus + '\'' +
                ", sentBytes='" + sentBytes + '\'' +
                ", httpReferrer='" + httpReferrer + '\'' +
                ", userAgent='" + userAgent + '\'' +
                '}';
    }

    public static void main(String[] args) {
        //数据格式:
        String line = "66.102.12.84 - - " +
                "[04/Jan/2012:23:18:32 +0800] " +
                "\"GET /ctp080113.php?tid=1495366 HTTP/1.1\" " +
                "200 " +
                "31 " +
                "\"http://www.itpub.net/thread-1495366-1-1.html\" " +
                "\"Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/534.51 (KHTML, like Gecko; Google Web Preview) Chrome/12.0.742 Safari/534.51\"";
        Kpi kpi = Kpi.parse(line);
        System.out.println(kpi.toString());
    }
}
