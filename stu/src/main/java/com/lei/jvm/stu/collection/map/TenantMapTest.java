package com.lei.jvm.stu.collection.map;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author leihaoyuan
 * @Date 2021/3/29 11:20
 * @Version 1.0 @Description map操作
 */
@Slf4j
public class TenantMapTest {

    public static void main(String[] args) {
        buildTenantMap();
    }


    private static void buildTenantList() {
        List<String> tenantList = Lists.newArrayList();
        tenantList.add("	anta	");
        tenantList.add("	cts	");
        tenantList.add("	jlg	");
        tenantList.add("	zjky	");
        tenantList.add("	familymart	");
        tenantList.add("	pre	");
        tenantList.add("	dml	");
        tenantList.add("	ps	");
        tenantList.add("	jfjt	");
        tenantList.add("	exetest	");
        tenantList.add("	qiaodan	");
        tenantList.add("	ctrip	");
        tenantList.add("	heytea	");
        tenantList.add("	atu	");
        tenantList.add("	nesc	");
        tenantList.add("	generalmills	");
        tenantList.add("	sanfu	");
        tenantList.add("	sinochem	");
        tenantList.add("	novavision	");
        tenantList.add("	langjiu	");
        tenantList.add("	bcs	");
        tenantList.add("	sn	");
        tenantList.add("	xcyfw	");
        tenantList.add("	jinzhoubank	");
        tenantList.add("	pandora	");
        tenantList.add("	ncarzone	");
        tenantList.add("	hs	");
        tenantList.add("	linde	");
        tenantList.add("	fila	");
        tenantList.add("	sw	");
        tenantList.add("	kukahome	");
        tenantList.add("	tpv	");
        tenantList.add("	yuanji	");
        tenantList.add("	darry	");
        tenantList.add("	fuyao	");
        tenantList.add("	hithium	");
        tenantList.add("	swatch	");
        tenantList.add("	pankoo	");
        tenantList.add("	qlbchina	");
        tenantList.add("	linsy	");
        tenantList.add("	fotile	");
        tenantList.add("	hebbank	");
        tenantList.add("	scc	");
        tenantList.add("	mh	");
        tenantList.add("	minor	");
        tenantList.add("	kltn	");
        tenantList.add("	21188	");
        tenantList.add("	xinhee	");
        tenantList.add("	qipai	");
        tenantList.add("	qzbank	");
        tenantList.add("	hh	");
        tenantList.add("	aimer	");
        tenantList.add("	bhaf	");
        tenantList.add("	zmp	");
        tenantList.add("	hsh	");
        tenantList.add("	qdkids	");
        tenantList.add("	robeca	");
        tenantList.add("	zgsh	");
        tenantList.add("	shenzhou	");
        tenantList.add("	gxlq	");
        tenantList.add("	peacebird	");
        tenantList.add("	bsd	");
        tenantList.add("	autoprod	");
        tenantList.add("	carpoly	");
        tenantList.add("	hdbp	");
        tenantList.add("	ddgroup	");
        tenantList.add("	hek	");
        tenantList.add("	tkgj	");
        tenantList.add("	xtep	");
        tenantList.add("	zoesoft	");
        tenantList.add("	eversun	");
        tenantList.add("	sbpc	");
        tenantList.add("	w1000	");
        tenantList.add("	xmbank	");
        tenantList.add("	ccnew	");
        tenantList.add("	jnc	");
        tenantList.add("	ea	");
        tenantList.add("	sjf	");
        tenantList.add("	zhishan	");
        tenantList.add("	mars	");
        tenantList.add("	dali	");
        tenantList.add("	xfxb	");
        tenantList.add("	tata	");
        tenantList.add("	swsc	");
        tenantList.add("	exe	");
        tenantList.add("	jfpx	");
        tenantList.add("	themslan	");
        tenantList.add("	marssenger	");
        tenantList.add("	horoy	");
        tenantList.add("	yaopharma	");
        tenantList.add("	yinlu	");
        tenantList.add("	zuoyou	");
        tenantList.add("	kls	");
        tenantList.add("	autotest	");
        tenantList.add("	liberty	");
        tenantList.add("	huafeng796	");
        tenantList.add("	jinglang	");
        tenantList.add("	baozun	");
        tenantList.add("	chhcu	");
        tenantList.add("	vanward	");
        tenantList.add("	clpc	");
        tenantList.add("	htbroker	");
        tenantList.add("	novastar	");
        tenantList.add("	shcell	");
        tenantList.add("	sunner	");
        tenantList.add("	pre_1	");
        tenantList.add("	swu	");
        tenantList.add("	jianzhongbang	");
        tenantList.add("	anpx	");
        tenantList.add("	demo	");
        tenantList.add("	yzw	");
        tenantList.add("	osn	");
        tenantList.add("	vcredit	");
        tenantList.add("	hlzq	");
        tenantList.add("	vitasoy	");
        tenantList.add("	qiudashu	");
        tenantList.add("	genecast	");
        tenantList.add("	tefang	");
        tenantList.add("	t3go	");
        tenantList.add("	lami	");
        tenantList.add("	fjhx	");
        tenantList.add("	dmtest	");
        tenantList.add("	ninebot	");
        tenantList.add("	yabang	");
        tenantList.add("	goodfarmer	");
        tenantList.add("	dev	");
        tenantList.add("	modernavenue	");
        tenantList.add("	haday	");
        tenantList.add("	zy	");
        tenantList.add("	ylcn	");
        tenantList.add("	hengan	");
        tenantList.add("	huate	");
        tenantList.add("	qunar	");
        tenantList.add("	horizon	");
        tenantList.add("	yananju	");
        tenantList.add("	qwwzyxt	");
        tenantList.add("	snowman	");
        tenantList.add("	hq	");
        tenantList.add("	iro	");
        tenantList.add("	convertlab	");
        tenantList.add("	beneunder	");
        tenantList.add("	lotus	");
        tenantList.add("	dmjf	");
        tenantList.add("	artgri	");
        tenantList.add("	semir	");
        tenantList.add("	kh	");
        tenantList.add("	pocfuyao1	");
        tenantList.add("	sanan	");
        tenantList.add("	tnong	");
        tenantList.add("	24hl	");
        tenantList.add("	dmyy	");
        tenantList.add("	dwy	");
        tenantList.add("	ywjtjt	");
        tenantList.add("	dongbai	");
        tenantList.add("	htwo	");
        tenantList.add("	nanputuo	");
        tenantList.add("	market	");
        tenantList.add("	spbs	");
        tenantList.add("	lbxx	");
        tenantList.add("	eroad	");
        tenantList.add("	skx	");
        tenantList.add("	crcc	");
        tenantList.add("	seadyke	");
        tenantList.add("	hiwango	");
        tenantList.add("	hqxf	");
        tenantList.add("	dreame	");
        tenantList.add("	dmcs1	");
        tenantList.add("	dkt	");
        tenantList.add("	xyxs	");
        tenantList.add("	jzsxy	");
        tenantList.add("	hnyh	");
        tenantList.add("	xinri	");
        tenantList.add("	gyyh	");
        tenantList.add("	xzb	");
        tenantList.add("	wjl	");
        tenantList.add("	dmyh	");
        tenantList.add("	xcec	");
        tenantList.add("	zgz	");
        tenantList.add("	xjyy	");
        tenantList.add("	shch	");
        tenantList.add("	zj	");
        tenantList.add("	dmxs	");
        tenantList.add("	yqxiachu	");
        tenantList.add("	dmzb	");
        tenantList.add("	course	");
        tenantList.add("	dmzzy	");
        tenantList.add("	dmdgs	");
        tenantList.add("	fzwater	");
        tenantList.add("	yanshou	");
        tenantList.add("	yanzhi	");
        tenantList.add("	honma	");
        tenantList.add("	cnte	");
        tenantList.add("	baona	");
        tenantList.add("	dmcy	");
        tenantList.add("	dmsc	");
        tenantList.add("	jjfazx	");
        tenantList.add("	bdc	");
        tenantList.add("	zhkj	");
        tenantList.add("	dmkx	");
        tenantList.add("	dmjt	");
        tenantList.add("	dmxf	");
        tenantList.add("	dmfdc	");
        tenantList.add("	mibp	");
        tenantList.add("	sdgwj	");
        tenantList.add("	zkst	");
        tenantList.add("	sunrise	");
        tenantList.add("	hlxy	");
        tenantList.add("	dmsdl	");
        tenantList.add("	ajl	");
        tenantList.add("	djs	");
        tenantList.add("	txx	");
        tenantList.add("	yxx	");
        tenantList.add("	huayi	");
        tenantList.add("	dmzq	");
        tenantList.add("	dmqc	");
        tenantList.add("	demo_cate	");
        tenantList.add("	dmzx	");
        tenantList.add("	dmyl	");
        tenantList.add("	dmcs2	");
        tenantList.add("	ylz	");
        tenantList.add("	dmwy	");
        tenantList.add("	xmgdyh	");
        tenantList.add("	dangjian	");
        tenantList.add("	supx	");
        tenantList.add("	xdkjt	");
        tenantList.add("	smdata	");
        tenantList.add("	xmwg	");
        tenantList.add("	ykhy	");
        tenantList.add("	dmnxp	");
        tenantList.add("	dmbx	");
        tenantList.add("	dmea	");
        tenantList.add("	dmsq	");
        tenantList.add("	dmys	");
        tenantList.add("	dmjr	");
        tenantList.add("	jomoo	");
        tenantList.add("	yunganxun	");
        tenantList.add("	coach	");
        tenantList.add("	xintongxue	");
        tenantList.add("	jenmeway	");
        tenantList.add("	dmcs3	");
        tenantList.add("	jczh	");
        tenantList.add("	sclm	");
        tenantList.add("	yskids	");
        tenantList.add("	rookie	");
        tenantList.add("	shws	");
        tenantList.add("	yx	");
        tenantList.add("	qggyl	");
        tenantList.add("	jjl	");
        tenantList.add("	xtepgyl	");
        tenantList.add("	vmax	");
        tenantList.add("	dmdj	");
        tenantList.add("	default	");
        tenantList.add("	dmqs	");
        tenantList.add("	yujing	");
        tenantList.add("	dmzp	");
        tenantList.add("	v3demo	");
        tenantList.add("	dmmr	");
        tenantList.add("	dmds	");
        tenantList.add("	dmwe	");
        tenantList.add("	gxzxl	");
        tenantList.add("	dmmh	");
        tenantList.add("	71ekbn21	");
        tenantList.add("	qiyou	");
        tenantList.add("	fjep	");
        tenantList.add("	cherydemo	");
        tenantList.add("	monya	");
        tenantList.add("	93	");
        tenantList.add("	boli	");
        tenantList.add("	linsysleep	");
        tenantList.add("	kw	");
        tenantList.add("	gjzx	");
        tenantList.add("	dmtest	");
        tenantList.add("	prod	");
        tenantList.add("	inm	");
        tenantList.add("	pocstarbucks1	");

        List<String> tenantCodeList = Lists.newArrayList();
        for (String tenantId : tenantList) {
            tenantCodeList.add(tenantId.trim());
        }
        log.info("租户列表={}", JSON.toJSONString(tenantCodeList.stream().distinct().collect(Collectors.toList())));
    }


    private static void buildTenantMap() {
        Map<String, String> tenantMap = new HashMap<>();
        tenantMap.put("	21188	", "	争鲜	");
        tenantMap.put("	24hl	", "	希望24热线租户	");
        tenantMap.put("	71ekbn21	", "	yongyou_71ekbn21	");
        tenantMap.put("	93	", "	九三	");
        tenantMap.put("	aimer	", "	爱慕	");
        tenantMap.put("	ajl	", "	安杰莱科技（杭州）有限公司	");
        tenantMap.put("	anpx	", "	福建傲农生物科技集团股份有限公司	");
        tenantMap.put("	anta	", "	安踏零售	");
        tenantMap.put("	artgri	", "	厦门雅瑞光学有限公司	");
        tenantMap.put("	atu	", "	安踏企业大学	");
        tenantMap.put("	autoprod	", "	自动化测试	");
        tenantMap.put("	autotest	", "	自动化测试	");
        tenantMap.put("	baona	", "	中山市小榄镇海馨美容店	");
        tenantMap.put("	baozun	", "	上海宝尊电子商务有限公司	");
        tenantMap.put("	bcs	", "	长沙银行	");
        tenantMap.put("	bdc	", "	北数咨询科技（深圳）有限公司	");
        tenantMap.put("	beneunder	", "	深圳减字科技有限公司	");
        tenantMap.put("	bhaf	", "	北京现代汽车金融	");
        tenantMap.put("	bsd	", "	波司登羽绒服装有限公司	");
        tenantMap.put("	bxn	", "	报喜鸟	");
        tenantMap.put("	carpoly	", "	嘉宝莉	");
        tenantMap.put("	ccnew	", "	中原证券	");
        tenantMap.put("	cdn	", "	西顿照明	");
        tenantMap.put("	cherydemo	", "	奇瑞汽车测试平台	");
        tenantMap.put("	chhcu	", "	创合汇云课堂	");
        tenantMap.put("	clpc	", "	中国人寿养老保险股份有限公司	");
        tenantMap.put("	cnte	", "	福建时代星云科技有限公司	");
        tenantMap.put("	coach	", "	代理商	");
        tenantMap.put("	convertlab	", "	上海欣兆阳信息科技有限公司	");
        tenantMap.put("	course	", "	课程模板	");
        tenantMap.put("	crcc	", "	中铁房地产集团西南有限公司	");
        tenantMap.put("	ctrip	", "	携程大学	");
        tenantMap.put("	cts	", "	周大生	");
        tenantMap.put("	dali	", "	达利食品集团有限公司	");
        tenantMap.put("	dangjian	", "	党建	");
        tenantMap.put("	darry	", "	迪阿股份有限公司	");
        tenantMap.put("	ddgroup	", "	当知当学	");
        tenantMap.put("	default	", "	预设值租户	");
        tenantMap.put("	demo	", "	演示	");
        tenantMap.put("	demo_cate	", "	餐饮行业demo	");
        tenantMap.put("	dev	", "	外部测试	");
        tenantMap.put("	djs	", "	DJS	");
        tenantMap.put("	dkt	", "	学呗（上海）教育科技有限公司	");
        tenantMap.put("	dmbx	", "	保险行业demo	");
        tenantMap.put("	dmcs1	", "	实施专用测试租户	");
        tenantMap.put("	dmcs2	", "	dmcs2	");
        tenantMap.put("	dmcs3	", "	dmcs3	");
        tenantMap.put("	dmcy	", "	餐饮	");
        tenantMap.put("	dmdgs	", "	全行业租户	");
        tenantMap.put("	dmdj	", "	党政机关平台	");
        tenantMap.put("	dmds	", "	直销行业demo	");
        tenantMap.put("	dmea	", "	教育机构演示租户	");
        tenantMap.put("	dmfdc	", "	房地产行业demo	");
        tenantMap.put("	dmjf	", "	dmjf	");
        tenantMap.put("	dmjr	", "	北京国家会计学院	");
        tenantMap.put("	dmjt	", "	中国交通建设	");
        tenantMap.put("	dmkx	", "	快销demo	");
        tenantMap.put("	dml	", "	达美乐	");
        tenantMap.put("	dmmh	", "	内蒙古民航机场集团公司	");
        tenantMap.put("	dmmr	", "	美容美发行业demo	");
        tenantMap.put("	dmnxp	", "	demo耐用消费品	");
        tenantMap.put("	dmqc	", "	汽车行业demo	");
        tenantMap.put("	dmqs	", "	轻食行业demo	");
        tenantMap.put("	dmsc	", "	商超连锁demo	");
        tenantMap.put("	dmsdl	", "	dmsdl	");
        tenantMap.put("	dmsq	", "	dmsq	");
        tenantMap.put("	dmtest	", "	内部人员测试后台使用	");
        tenantMap.put("	dmwe	", "	潍柴集团-demo	");
        tenantMap.put("	dmwy	", "	卫浴行业demo	");
        tenantMap.put("	dmxf	", "	鞋服 demo	");
        tenantMap.put("	dmxs	", "	dmxs	");
        tenantMap.put("	dmyh	", "	银行行业demo	");
        tenantMap.put("	dmyl	", "	企鹅杏仁	");
        tenantMap.put("	dmys	", "	养生行业demo	");
        tenantMap.put("	dmyy	", "	银联demo	");
        tenantMap.put("	dmzb	", "	珠宝行业demo	");
        tenantMap.put("	dmzp	", "	醉品集团	");
        tenantMap.put("	dmzq	", "	证券行业demo	");
        tenantMap.put("	dmzx	", "	咨询行业demo	");
        tenantMap.put("	dmzzy	", "	制造业demo	");
        tenantMap.put("	dongbai	", "	东百集团	");
        tenantMap.put("	dreame	", "	追觅科技(苏州)科技有限公司	");
        tenantMap.put("	dscn	", "	第一三共（中国）投资有限公司	");
        tenantMap.put("	ea	", "	深圳怡亚通	");
        tenantMap.put("	eroad	", "	上海易路软件有限公司（客户自用）	");
        tenantMap.put("	eversun	", "	永荣控股集团	");
        tenantMap.put("	exe	", "	职行力	");
        tenantMap.put("	exetest	", "	exetest	");
        tenantMap.put("	familymart	", "	全家e学堂	");
        tenantMap.put("	fila	", "	斐乐零售	");
        tenantMap.put("	fjep	", "	福建电子口岸	");
        tenantMap.put("	fjhx	", "	福建海峡银行股份有限公司	");
        tenantMap.put("	fotile	", "	宁波方太	");
        tenantMap.put("	fuyao	", "	福耀玻璃工业集团股份有限公司	");
        tenantMap.put("	fzwater	", "	福州市自来水有限公司	");
        tenantMap.put("	genecast	", "	臻和（北京）生物科技有限公司	");
        tenantMap.put("	generalmills	", "	哈根达斯	");
        tenantMap.put("	goodfarmer	", "	佳农食品控股（集团）有限公司	");
        tenantMap.put("	gxlq	", "	路桥学堂	");
        tenantMap.put("	gxzxl	", "	高校职行力	");
        tenantMap.put("	gyyh	", "	贵阳银行爽爽E学堂	");
        tenantMap.put("	haday	", "	佛山市海天调味食品股份有限公司	");
        tenantMap.put("	hdbp	", "	宏大爆破工程集团有限责任公司	");
        tenantMap.put("	hebbank	", "	河北银行股份有限公司	");
        tenantMap.put("	hek	", "	厦门惠尔康食品有限公司	");
        tenantMap.put("	hengan	", "	福建恒安集团有限公司	");
        tenantMap.put("	heytea	", "	喜茶	");
        tenantMap.put("	hh	", "	华人运通	");
        tenantMap.put("	hithium	", "	厦门海辰储能科技股份有限公司	");
        tenantMap.put("	hiwango	", "	泉州嗨玩购集团有限公司	");
        tenantMap.put("	hlxy	", "	云南华联锌铟股份	");
        tenantMap.put("	hlzq	", "	华龙智慧学院	");
        tenantMap.put("	hnyh	", "	海南银行股份有限公司	");
        tenantMap.put("	honma	", "	世力国际贸易（上海）有限公司	");
        tenantMap.put("	horizon	", "	北京地平线信息技术有限公司	");
        tenantMap.put("	horoy	", "	鸿荣源	");
        tenantMap.put("	hq	", "	华勤技术股份有限公司	");
        tenantMap.put("	hqxf	", "	泉州寰球鞋服有限公司	");
        tenantMap.put("	hs	", "	北京恒信玺利实业股份有限公司	");
        tenantMap.put("	hsh	", "	煌上煌	");
        tenantMap.put("	htbroker	", "	海通证券经纪人	");
        tenantMap.put("	htsec	", "	海通财富管理学院	");
        tenantMap.put("	htwo	", "	现代汽车氢燃料电池系统（广州）有限公司	");
        tenantMap.put("	huafeng796	", "	华点通	");
        tenantMap.put("	huate	", "	华特集团	");
        tenantMap.put("	huayi	", "	中山市华艺灯饰照明股份有限公司	");
        tenantMap.put("	inm	", "	浙江一鸣食品股份有限公司	");
        tenantMap.put("	iro	", "	依诺时尚(深圳)服饰有限公司——歌力思集团	");
        tenantMap.put("	jczh	", "	君诚众合有限公司	");
        tenantMap.put("	jfjt	", "	建发学堂	");
        tenantMap.put("	jfpx	", "	一汽解放汽车有限公司	");
        tenantMap.put("	jianzhongbang	", "	广州建众互联网科技有限公司	");
        tenantMap.put("	jinglang	", "	四川领跑体育用品有限公司	");
        tenantMap.put("	jinzhoubank	", "	优学悠享	");
        tenantMap.put("	jjfazx	", "	2022多公司租户demo新平台	");
        tenantMap.put("	jjl	", "	辰诺（上海）国际贸易有限公司	");
        tenantMap.put("	jlg	", "	内蒙古伊利实业集团股份有限公司北京分公司	");
        tenantMap.put("	jomoo	", "	九牧集团	");
        tenantMap.put("	jzsxy	", "	建众	");
        tenantMap.put("	kh	", "	科华数据股份有限公司	");
        tenantMap.put("	kls	", "	凯乐石	");
        tenantMap.put("	kltn	", "	克丽缇娜	");
        tenantMap.put("	kukahome	", "	顾家家居	");
        tenantMap.put("	kw	", "	孩子王儿童用品股份有限公司	");
        tenantMap.put("	lami	", "	上海万骊家居有限公司	");
        tenantMap.put("	langjiu	", "	郎学堂	");
        tenantMap.put("	lbxx	", "	蜡笔小新	");
        tenantMap.put("	lds	", "	立达信	");
        tenantMap.put("	lh	", "	广州力航培训有限公司	");
        tenantMap.put("	liberty	", "	利宝保险有限公司	");
        tenantMap.put("	linde	", "	晓德	");
        tenantMap.put("	linsy	", "	广东林氏家居有限公司	");
        tenantMap.put("	lotus	", "	路特斯	");
        tenantMap.put("	lsh	", "	广州粒上皇食品有限公司	");
        tenantMap.put("	market	", "	职行力	");
        tenantMap.put("	mars	", "	玛氏	");
        tenantMap.put("	marssenger	", "	火星人厨具股份有限公司	");
        tenantMap.put("	mh	", "	重庆嘉年名华商务服务有限公司	");
        tenantMap.put("	mibp	", "	习筹	");
        tenantMap.put("	midea	", "	广东美的厨卫电器制造有限公司	");
        tenantMap.put("	minor	", "	美诺	");
        tenantMap.put("	modernavenue	", "	摩登大道	");
        tenantMap.put("	monya	", "	盟亚	");
        tenantMap.put("	nanputuo	", "	南普陀	");
        tenantMap.put("	ncarzone	", "	天猫养车	");
        tenantMap.put("	nesc	", "	东证商学院	");
        tenantMap.put("	ninebot	", "	九号科技有限公司	");
        tenantMap.put("	novastar	", "	西安诺瓦星云科技股份有限公司	");
        tenantMap.put("	novavision	", "	星知视	");
        tenantMap.put("	osn	", "	佛山欧神诺云商科技有限公司	");
        tenantMap.put("	pandora	", "	潘多拉	");
        tenantMap.put("	pankoo	", "	盘古餐饮	");
        tenantMap.put("	peacebird	", "	宁波太平鸟时尚服饰股份有限公司	");
        tenantMap.put("	pocfuyao1	", "	福耀玻璃工业集团股份有限公司	");
        tenantMap.put("	pocstarbucks1	", "	星巴克企业管理（中国）有限公司	");
        tenantMap.put("	pre	", "	预发布	");
        tenantMap.put("	pre_1	", "	预发布1	");
        tenantMap.put("	ps	", "	宝胜学院	");
        tenantMap.put("	qdkids	", "	乔丹儿童	");
        tenantMap.put("	qggyl	", "	厦门轻工集团供应链有限公司	");
        tenantMap.put("	qiaodan	", "	乔丹	");
        tenantMap.put("	qipai	", "	柒牌	");
        tenantMap.put("	qiudashu	", "	广州山丘餐饮管理有限公司	");
        tenantMap.put("	qiyou	", "	企友	");
        tenantMap.put("	qlbchina	", "	齐鲁银行e学院	");
        tenantMap.put("	qunar	", "	北京趣拿软件科技有限公司	");
        tenantMap.put("	qwwzyxt	", "	青蛙王子	");
        tenantMap.put("	qzbank	", "	泉州银行	");
        tenantMap.put("	robeca	", "	乐贝家	");
        tenantMap.put("	rookie	", "	永兴东润	");
        tenantMap.put("	sanan	", "	三安光电	");
        tenantMap.put("	sanfu	", "	三福	");
        tenantMap.put("	sbpc	", "	DE学堂	");
        tenantMap.put("	scc	", "	大连中远海运	");
        tenantMap.put("	sclm	", "	三草两木	");
        tenantMap.put("	sdgwj	", "	首都公务机	");
        tenantMap.put("	seadyke	", "	厦门茶叶进出口有限公司	");
        tenantMap.put("	semir	", "	上海森马服饰有限公司	");
        tenantMap.put("	seng	", "	森歌学习平台	");
        tenantMap.put("	shcell	", "	细胞集团	");
        tenantMap.put("	shenzhou	", "	申洲	");
        tenantMap.put("	shws	", "	上海纬申	");
        tenantMap.put("	sinochem	", "	未来学院	");
        tenantMap.put("	sjf	", "	四川水井坊股份有限公司	");
        tenantMap.put("	skx	", "	斯凯奇鞋业(东莞)有限公司	");
        tenantMap.put("	smdata	", "	城市服务治理队伍内训平台	");
        tenantMap.put("	sn	", "	内蒙古伊利实业集团股份有限公司金山分公司	");
        tenantMap.put("	snowman	", "	雪人股份	");
        tenantMap.put("	spbs	", "	尚品本色智能家居有限公司	");
        tenantMap.put("	sunhohi	", "	佛山市新豪轩智能家居科技有限公司	");
        tenantMap.put("	sunner	", "	福建圣农食品有限公司	");
        tenantMap.put("	sunrise	", "	厦门日上集团有限公司	");
        tenantMap.put("	supx	", "	厦门超级形健康科技有限公司	");
        tenantMap.put("	sw	", "	七匹狼	");
        tenantMap.put("	swatch	", "	瑞表中国学院	");
        tenantMap.put("	swsc	", "	西南证券	");
        tenantMap.put("	swu	", "	七匹狼管理学院	");
        tenantMap.put("	t3go	", "	T3出行	");
        tenantMap.put("	tata	", "	北京闼闼同创工贸有限公司	");
        tenantMap.put("	tefang	", "	厦门市特房信息技术有限公司	");
        tenantMap.put("	themslan	", "	嘉哲美学	");
        tenantMap.put("	tkgj	", "	天坤大学	");
        tenantMap.put("	tnong	", "	广东天农食品集团股份有限公司	");
        tenantMap.put("	tpv	", "	冠捷科技	");
        tenantMap.put("	txx	", "	天下行租车	");
        tenantMap.put("	v3demo	", "	v3demo	");
        tenantMap.put("	vanward	", "	广东万和新电气股份有限公司	");
        tenantMap.put("	vcredit	", "	维信学院	");
        tenantMap.put("	vitasoy	", "	维他奶有限公司	");
        tenantMap.put("	vmax	", "	厦门薇格整形外科医院	");
        tenantMap.put("	w1000	", "	万仟宝典	");
        tenantMap.put("	wjl	", "	广东万家乐燃气具有限公司	");
        tenantMap.put("	xcec	", "	西安康明斯发动机有限公司	");
        tenantMap.put("	xcyfw	", "	新城悦服务	");
        tenantMap.put("	xdkjt	", "	西点/课匠堂	");
        tenantMap.put("	xfxb	", "	深圳市幸福商城科技股份有限公司	");
        tenantMap.put("	xinhee	", "	欣贺股份有限公司	");
        tenantMap.put("	xinri	", "	无锡新日电动车销售服务有限公司	");
        tenantMap.put("	xintongxue	", "	深圳市深爱你人力咨询有限公司	");
        tenantMap.put("	xjyy	", "	湘江运营	");
        tenantMap.put("	xmbank	", "	厦门银行	");
        tenantMap.put("	xmgdyh	", "	鹭岛阳光学堂	");
        tenantMap.put("	xmwg	", "	厦门文广	");
        tenantMap.put("	xtep	", "	特步集团	");
        tenantMap.put("	xtepgyl	", "	特步供应链	");
        tenantMap.put("	xyxs	", "	仙居制药	");
        tenantMap.put("	xyzfedu	", "	福建国通星驿网络科技有限公司	");
        tenantMap.put("	xzb	", "	训战宝	");
        tenantMap.put("	yabang	", "	久久丫食品集团有限公司	");
        tenantMap.put("	yananju	", "	厦门燕安居连锁经营有限公司	");
        tenantMap.put("	yanshou	", "	验收用的租户	");
        tenantMap.put("	yanzhi	", "	研职	");
        tenantMap.put("	yaopharma	", "	药友移动学习平台	");
        tenantMap.put("	yinlu	", "	厦门银鹭食品集团有限公司	");
        tenantMap.put("	ylcn	", "	日邮物流	");
        tenantMap.put("	ylz	", "	易联众	");
        tenantMap.put("	yqxiachu	", "	一起下厨	");
        tenantMap.put("	yskids	", "	以纯童装	");
        tenantMap.put("	yuanji	", "	袁记云饺	");
        tenantMap.put("	yujing	", "	瑜境	");
        tenantMap.put("	yunganxun	", "	云干训	");
        tenantMap.put("	yunyi	", "	杭州云衣软件科技有限公司	");
        tenantMap.put("	yx	", "	易行peoplus	");
        tenantMap.put("	yxx	", "	云学习	");
        tenantMap.put("	yzw	", "	燕之屋	");
        tenantMap.put("	zgsh	", "	河南双汇投资发展股份有限公司	");
        tenantMap.put("	zgz	", "	追光者	");
        tenantMap.put("	zhishan	", "	四川至膳品牌管理有限公司	");
        tenantMap.put("	zhkj	", "	众华会计师事务所	");
        tenantMap.put("	zj	", "	紫金矿业（试用）	");
        tenantMap.put("	zjky	", "	紫金矿业	");
        tenantMap.put("	zkst	", "	武汉中科瑞华生态科技股份有限公司	");
        tenantMap.put("	zmp	", "	周麻婆	");
        tenantMap.put("	zoesoft	", "	智业软件股份有限公司	");
        tenantMap.put("	zuoyou	", "	左右家私	");
        tenantMap.put("	zy	", "	济南三立企业管理咨询有限公司	");


        Map<String, String> metaMap = new HashMap<>();
        for (Map.Entry<String, String> entry : tenantMap.entrySet()) {
            metaMap.put(entry.getKey().trim(), entry.getValue().trim());
        }
        log.info("预制数据-总数=【{}】数据={}", metaMap.size(), JSON.toJSONString(metaMap));
    }


}