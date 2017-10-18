package com.meession.education.common.util;

import java.util.Random;

/**
 * 随机生成的类
 * 
 * @author zy
 * 
 *         产生一个a到b的随机整数 Math.random()*(b-a+1)+a new Random().nextInt(b-a+1)+a
 *
 */
public class RANDOM {

	static Random r = new Random();

	private static final String[] courseName = { "数学分析", "高等代数", "数值分析", "离散数学", "概率论", "数据库", "使用软件工程", "Java与web",
			"拓扑学", "运筹学", "大学英语1", "大学英语2", "大学英语3", "大学英语4", "大学物理1", "大学物理1", "大学物理2", "高等代数", "C++", "C语言", "英语视听说1",
			"英语视听说2", "英语视听说3","数据结构","算法分析","图形学","人工只能","毛泽东思想","思修","大学体育1","大学体育2","大学体育3" };

	private static final String[] userHomeTown = { "湖南", "北京", "新疆", "安徽", "福建", "广州", "吉林", "甘肃", "陕西", "山西", "广西",
			"云南", "江西", "湖北", "黑龙江" };

	private static final String[] firstName = { "赵", "钱", "孙", "李", "周", "吴", "郑", "王", "冯", "陈", "诸", "卫", "蒋", "沈",
			"韩", "杨", "朱", "秦", "尤", "许", "何", "吕", "施", "张", "孔", "曹", "严", "华", "金", "魏", "陶", "姜", "戚", "谢", "邹",
			"喻", "柏", "水", "窦", "章", "云", "苏", "潘", "葛", "奚", "范", "彭", "郎", "鲁", "韦", "昌", "马", "苗", "凤", "花", "方",
			"俞", "任", "袁", "柳", "酆", "鲍", "史", "唐", "费", "廉", "岑", "薛", "雷", "贺", "倪", "汤", "滕", "殷", "罗", "毕", "郝",
			"邬", "安", "常", "乐", "于", "时", "傅", "皮", "卡", "齐", "康", "伍", "余", "元", "卜", "顾", "孟", "平", "黄", "和", "穆",
			"萧", "尹", "姚", "邵", "堪", "汪", "祁", "毛", "禹", "狄", "米", "贝", "明", "臧", "计", "伏", "成", "戴", "谈", "宋", "茅",
			"庞", "熊", "纪", "舒", "屈", "项", "祝", "董", "粱", "杜", "阮", "蓝", "闵", "席", "季", "麻", "强", "贾", "路", "娄", "危",
			"江", "童", "颜", "郭", "梅", "盛", "林", "刁", "钟", "徐", "邱", "骆", "高", "夏", "蔡", "田", "樊", "胡", "凌", "霍", "虞",
			"万", "支", "柯", "咎", "管", "卢", "莫", "经", "房", "裘", "缪", "干", "解", "应", "宗", "丁", "宣", "贲", "邓", "郁", "单",
			"杭", "洪", "包", "诸", "左", "石", "崔", "吉", "钮", "龚", "程", "嵇", "邢", "滑", "裴", "陆", "荣", "翁", "荀", "羊", "於",
			"惠", "甄", "魏", "家", "封", "芮", "羿", "储", "靳", "汲", "邴", "糜", "松", "井", "段", "富", "巫", "乌", "焦", "巴", "弓",
			"牧", "隗", "山", "谷", "车", "侯", "宓", "蓬", "全", "郗", "班", "仰", "秋", "仲", "伊", "宫", "宁", "仇", "栾", "暴", "甘",
			"钭", "厉", "戎", "祖", "武", "符", "刘", "景", "詹", "束", "龙", "司马", "上官", "欧阳", "夏侯", "诸葛", "东方", "尉迟", "公孙", "长孙",
			"慕容", "司徒", "西门" };

	private static final String[] lastName = { "超", "媛", "念", "立", "思", "嘉", "雨", "伟", "权", "秋", "佩", "雅", "联", "如",
			"渠", "保", "室", "铜", "梧", "胤", "昱", "佳", "伊", "校", "诗", "节", "如", "阁", "耕", "宫", "古", "谷", "观", "桂", "贵",
			"国", "广", "冠", "汉", "翰", "航", "杭", "海", "豪", "浩", "皓", "和", "河", "贺", "恒", "弘", "虹", "宏", "红", "厚", "鹄",
			"虎", "华", "欢", "怀", "辉", "惠", "会", "奇", "吉", "骥", "嘉", "佳", "甲", "稼", "江", "坚", "剑", "锦", "经", "镜", "界",
			"竞", "介", "京", "建", "净", "精", "敬", "静", "靖", "津", "进", "菁", "景", "炯", "驹", "举", "炬", "君", "俊", "军", "骏",
			"郡", "峻", "恺", "楷", "康", "可", "克", "珂", "逵", "魁", "阔", "昆", "奎", "宽", "况", "乐", "雷", "岭", "廉", "霖", "麟",
			"灵", "利", "良", "联", "烈", "罗", "陵", "梁", "立", "礼", "力", "莉", "烁", "隆", "龙", "禄", "璐", "露", "律", "茂", "梦",
			"密", "铭", "明", "绵", "妙", "默", "木", "能", "年", "宁", "念", "怒", "庞", "佩", "培", "朋", "鹏", "屏", "平", "魄", "珀",
			"璞", "奇", "琦", "齐", "启", "谦", "乾", "茜", "倩", "芹", "琴", "青", "卿", "秋", "权", "求", "情", "渠", "全", "荃", "群",
			"泉", "然", "让", "仁", "任", "荣", "儒", "锐", "若", "瑞", "三", "瑟", "森", "韶", "绍", "尚", "商", "珊", "善", "生", "升",
			"声", "晟", "胜", "盛", "诗", "时", "石", "实", "凇", "慎", "设", "守", "随", "世", "寿", "仕", "余", "帅", "双", "朔", "硕",
			"水", "誓", "适", "书", "舒", "殊", "顺", "思", "嗣", "似", "松", "颂", "素", "岁", "棠", "泰", "腾", "添", "铁", "同", "桐",
			"童", "彤", "团", "涂", "图", "土", "万", "旺", "望", "王", "闻", "威", "薇", "嵬", "伟", "卫", "蔚", "文", "微", "巍", "玮",
			"为", "畏", "吾", "午", "西", "熙", "玺", "仙", "先", "孝", "湘", "祥", "行", "献", "享", "效", "兴", "夏", "宣", "协", "向",
			"校", "轩", "瑕", "衔", "筱", "羡", "相", "香", "贤", "翔", "杏", "新", "信", "幸", "心", "星", "绣", "秀", "欣", "鑫", "兴",
			"行", "雄", "许", "炫", "雪", "学", "旭", "璇", "勋", "萱", "迅", "亚", "雅", "扬", "耀", "彦", "延", "岩", "炎", "永", "砚",
			"演", "焱", "洋", "阳", "曜", "耀", "夜", "译", "逸", "伊", "义", "艺", "意", "异", "怡", "翼", "毅", "银", "瑛", "仪", "易",
			"寅", "印", "苡", "野", "业", "英", "璎", "盈", "颖", "影", "雍", "勇", "悠", "由", "游", "佑", "友", "瑜", "遇", "玉", "岳",
			"元", "宇", "愚", "钰", "裕", "郁", "于" };

	/**
	 * get user's hometown
	 * 
	 * @return
	 */
	public static String randomHometown() {
		String hometown = "";
		hometown += userHomeTown[r.nextInt(userHomeTown.length)];
		return hometown;
	}

	/**
	 * get a random qq number
	 * 
	 * @return
	 */
	public static String randomQQ() {
		String qq = "";
		qq += r.nextInt(9) + 1;
		for (int i = 1; i < 10; i++)
			qq += r.nextInt(10);
		return qq;
	}

	/**
	 * 随机产生一个姓名
	 * 
	 * @return 姓名
	 */
	public static String randomName() {
		String name = "";
		int random = r.nextInt(2) + 1;
		name += firstName[r.nextInt(firstName.length)];
		for (int i = 0; i < random; i++)
			name += lastName[r.nextInt(lastName.length)];
		return name;
	}

	/**
	 * get a random course name method.
	 * 
	 * @return
	 */
	public static String randomCourse() {
		String course = "";
		course += courseName[r.nextInt(courseName.length)];
		return course;
	}

	private static final String[] MAJORS = { "汉语言文学", "新闻学", "广播电视新闻学", "广告学", "艺术设计", "动画", "历史学", "中国革命史与中国共产党党史",
			"文化产业管理", "哲学", "社会学", "国际政治", "思想政治教育", "人力资源管理", "旅游管理", "编辑出版学", "行政管理", "公共事业管理", "信息管理与信息系统", "档案学",
			"图书馆学", "政治学与行政学", "法学", "经济学", "国际经济与贸易", "金融学", "工商管理", "市场营销", "会计学", "财务管理", "电子商务", "审计学", "英语", "德语",
			"法语", "日语", "对外汉语", "翻译", "西班牙语", "数学与应用数学", "信息与计算科学", "统计学", "物理学", "微电子学", "材料物理", "测控技术与仪器", "材料科学与工程",
			"新能源材料与器件", "化学", "应用化学", "材料化学", "药学", "高分子材料与工程", "环境科学", "环境工程", "化学工程与工艺", "制药工程", "食品科学与工程", "生物工程",
			"环保设备工程", "金属材料工程", "机械设计制造及其自动化", "材料成型及控制工程", "工业设计", "过程装备与控制工程", "热能与动力工程", "自动化", "电子信息工程", "通信工程",
			"计算机科学与技术", "网络工程", "软件工程", "建筑电气与智能化", "土木工程", "建筑环境与设备工程", "工程力学", "采矿工程", "电子信息科学与技术", "测绘工程", "安全工程" };

	public static String randomMajor() {
		return MAJORS[r.nextInt(MAJORS.length)];
	}

	public static final String[] DPTMS = { "艺术学院", "哲学与历史文化学院", "旅游管理学院", "公共管理学院", "法学院", "商学院", "外国语学院", "数学与计算科学学院",
			"材料与光电物理学院", "化学学院", "化工学院", "机械工程学院", "信息工程学院", "土木工程与力学学院", "能源工程学院" };

	public static final String randDepartment() {
		return DPTMS[r.nextInt(DPTMS.length)];
	}

	public static final String randGender() {// 性别
		int i = r.nextInt(100);
		if (i % 2 == 0)
			return "男";
		else
			return "女";
	}

	public static void main(String[] args) {

		System.out.println(randomName());
	}

}
