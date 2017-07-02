package com.example.admin_xc.basemodule.entry;

import android.text.TextUtils;

/**
 * User:    Xiaoc
 * 项目名:  WZB3.0
 * Package: baizhuan.hangzhou.com.wzb30.Model
 * Date:    2016-07-08
 * Time:    09:36
 * 类描述：产品类
 */
public class Product {


    /**
     * Zt : 2
     * carlogoid : 3
     * carurl : http://wzb-dev.oss-cn-hangzhou.aliyuncs.com/201609/qiandao.png
     * cpbh : 225
     * cpje : 100000
     * cpmc : 大额返现测试
     * qdje : 500
     * qx : 30
     * releasedate : 1477929600000
     * syl : 12
     * tag : 2,
     * tx : 满2万返20
     * xszm : 0
     * ytje : 20500
     */

    private String Zt;
    private String carlogoid;
    private String imgurl;
    private int cpbh;
    private String cpje;
    private String cpmc;
    private int qdje;
    private int qx;
    private long releasedate;
    private String syl;
    private String tag;
    private String tx;
    private int xszm;
    private int ytje;

//    {"Msg":"产品首页显示成功","Totalpage":7,"List":[{"Zt":"2","carlogoid":11,"cpbh":240,"cpje":"50000","cpmc":"马自达稳赚新手标14期","imgurl":"http://img-dev.52wzb.com/car-logo/mazda.png","qdje":100,"qx":7,"releasedate":1478361600000,"syl":15.00,"tag":"1,2,","tx":"满5千返5元，满10万返120元","xszm":1,"ytje":31100},{"Zt":"2","carlogoid":6,"cpbh":242,"cpje":"120000","cpmc":"奔驰过桥贷17期","imgurl":"http://img-dev.52wzb.com/car-logo/benz.png","qdje":500,"qx":3,"releasedate":1478361600000,"syl":7.00,"tag":"","tx":"红包不可用","xszm":0,"ytje":54094},{"Zt":"2","carlogoid":"","cpbh":243,"cpje":"480000","cpmc":"路虎车贷宝18期","imgurl":"","qdje":500,"qx":90,"releasedate":1478361600000,"syl":14.00,"tag":"2,4,","tx":"满2万返15元，多投多返；可用红包；","xszm":0,"ytje":391367},{"Zt":"2","carlogoid":"","cpbh":237,"cpje":"280000","cpmc":"英菲尼迪车贷宝17期","imgurl":"","qdje":500,"qx":35,"releasedate":1478102400000,"syl":12.00,"tag":"2,4,","tx":"满2万返5元，多投多返；可用红包；","xszm":0,"ytje":168434},{"Zt":"3","carlogoid":3,"cpbh":239,"cpje":"250000","cpmc":"宝马周末特惠赚8期","imgurl":"http://img-dev.52wzb.com/car-logo/bmw.png","qdje":500,"qx":50,"releasedate":1478361600000,"syl":12.50,"tag":"2,3,","tx":"满3万返30，多投多返","xszm":0,"ytje":250013},{"Zt":"3","carlogoid":"","cpbh":238,"cpje":"100000","cpmc":"北京现代过桥贷16期","imgurl":"","qdje":500,"qx":3,"releasedate":1478275200000,"syl":7.00,"tag":"","tx":"红包不可用","xszm":0,"ytje":119757},{"Zt":"3","carlogoid":11,"cpbh":236,"cpje":"80000","cpmc":"马自达过桥贷15期","imgurl":"http://img-dev.52wzb.com/car-logo/mazda.png","qdje":500,"qx":3,"releasedate":1478016000000,"syl":7.00,"tag":"","tx":"红包不可用","xszm":0,"ytje":83174},{"Zt":"3","carlogoid":"","cpbh":234,"cpje":"650000","cpmc":"阿斯顿车贷宝16期","imgurl":"","qdje":500,"qx":90,"releasedate":1477929600000,"syl":14.00,"tag":"4,","tx":"红包可用","xszm":0,"ytje":650162},{"Zt":"3","carlogoid":"","cpbh":235,"cpje":"80000","cpmc":"路虎稳赚新手标13期","imgurl":"","qdje":100,"qx":7,"releasedate":1477929600000,"syl":15.00,"tag":"1,","tx":"新手专享","xszm":1,"ytje":80090},{"Zt":"3","carlogoid":9,"cpbh":233,"cpje":"380000","cpmc":"雷克萨斯车贷宝15期","imgurl":"http://img-dev.52wzb.com/car-logo/lexus.png","qdje":500,"qx":75,"releasedate":1477756800000,"syl":14.00,"tag":"4,","tx":"红包可用","xszm":0,"ytje":392147}],"Result":"01"}

    public String getZt() {
        return Zt;
    }

    public void setZt(String Zt) {
        this.Zt = Zt;
    }

    public String getCarlogoid() {
        return carlogoid;
    }

    public void setCarlogoid(String carlogoid) {
        this.carlogoid = carlogoid;
    }

    public String getImgurl() {
        if (TextUtils.isEmpty(imgurl)) return "";
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public int getCpbh() {
        return cpbh;
    }

    public void setCpbh(int cpbh) {
        this.cpbh = cpbh;
    }

    public String getCpje() {
        return cpje;
    }

    public void setCpje(String cpje) {
        this.cpje = cpje;
    }

    public String getCpmc() {
        return cpmc;
    }

    public void setCpmc(String cpmc) {
        this.cpmc = cpmc;
    }

    public int getQdje() {
        return qdje;
    }

    public void setQdje(int qdje) {
        this.qdje = qdje;
    }

    public int getQx() {
        return qx;
    }

    public void setQx(int qx) {
        this.qx = qx;
    }

    public long getReleasedate() {
        return releasedate;
    }

    public void setReleasedate(long releasedate) {
        this.releasedate = releasedate;
    }

    public String getSyl() {
        return syl;
    }

    public void setSyl(String syl) {
        this.syl = syl;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTx() {
        return tx;
    }

    public void setTx(String tx) {
        this.tx = tx;
    }

    public int getXszm() {
        return xszm;
    }

    public void setXszm(int xszm) {
        this.xszm = xszm;
    }

    public int getYtje() {
        return ytje;
    }

    public void setYtje(int ytje) {
        this.ytje = ytje;
    }
}
