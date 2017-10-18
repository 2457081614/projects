package com.meession.market.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

import javax.sql.rowset.serial.SerialBlob;


import com.meession.market.bulletin.entity.Bulletin;

public class RDU {

	static Random r = new Random();
	private static final String[] CourseNamestr = { "数学分析", "高等代数", "数值分析", "离散数学", "概率论", "数据库", "使用软件工程", "Java与web", "拓扑学",
			"运筹学", "大学英语1", "大学英语2", "大学英语3", "大学英语4", "大学物理1", "大学物理1", "大学物理2", "高等代数" };
	private static final String[] MajorNamestr = { "应用数学", "统计学", "计算数学", "英语", "德语", "法语", "日语", "物理", "药理学", "临床医学", "软件工程",
			"计算机", "计算机网络", "法学", "哲学", "心理学", "教育学", "文学", "经济学", "计算机" };

	private static final String[] BULLETIN_STATUSES = {
		Bulletin.SAVED_AND_DISPUBLISHED,
		Bulletin.PUBLISHED,
	};
	
	
	/*From DXL*/
	public static String randomPurpose(){
		String [] purpose={"了解客户对此产品的看法",
				"收集客户的意见",
				"调查客户对新产品的购买程度",
				"了解客户的生活",
				"了解客户之前有没有买过此类产品",
				"增强客户对身体素质的认知",
				"给客户带来生活的方便"};
		
		return purpose[random(purpose.length)];
		
	}
	public static String randomStage(){
		String [] stage={"第一阶段","第二阶段","第三阶段","第四阶段"};
		return stage[random(stage.length)];
		
	}
	
	/*From YLX*/
	private static final String[] INTEREST = { "球类运动", "电子竞技", "户外运动", "阅读"

	};

	public static String ranInterest() {
		return INTEREST[random(INTEREST.length)];
	}

	public static String randomQuestion(){
		final String[] oprs = new String[]{"加","减","乘","除"};
		String result = RDU.random(10000) + oprs[RDU.random(oprs.length)] +  RDU.random(10000) + "等于几?";
		return result;
	}
	
	public static String randomAnswer(){
		return "很充实，但有点小累";
	}
	
	public static final String[] HOMETOWN = { "湖南", "北京", "新疆", "安徽", "福建",
			"广州", "吉林", "甘肃", "陕西", "山西", "广西", "云南", "江西", "湖北", "黑龙江" };

	public static String ranHometown() {
		return HOMETOWN[random(HOMETOWN.length)];
	}

	public static final String[] SCHOOL = { "湘潭大学", "湖南科技大学", "湖南软件学院",
			"湖南师范大学", "中南大学", "湖南大学", "湖南工程学院" };

	public static String ranSchool() {

		return SCHOOL[random(SCHOOL.length)];
	}
	public static Blob ranPhoto() {
		Blob photoBlob = null;
		try {
			File photo = new File(
					"../mitsm/WebContent/photos/test"
							+ random(11) + ".jpg");
			InputStream is = new FileInputStream(photo);
			byte[] bytes = new byte[(int) photo.length() + 1];
			is.read(bytes);
			photoBlob = new SerialBlob(bytes);
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return photoBlob;
	}
	/*From YLX*/
	
	public static String ranTaskStatus(){
		return BULLETIN_STATUSES[random(BULLETIN_STATUSES.length)];
	}
	
	public static String ranStudentNumber() {
		String str = "2014";
		for (int i = 0; i < 6; i++) {
			str += r.nextInt(10);
		}
		return str;
	}

	public static String randQq() {
		// TODO Auto-generated method stub
		String lRet = "" + (r.nextInt(9) + 1);
		String lHead[] = { "13", "15", "18", "14" };

		lRet = lHead[r.nextInt(lHead.length)];
		for (int i = 0; i < (6 + r.nextInt(4)); i++)
			lRet += r.nextInt(10);

		return lRet;
	}

	public static final String randomGender(){
		String[] genders = {"男","女"};
		return genders[r.nextInt(genders.length)];
	}
	
	
	public final static String randomName() {

		String[] firstName = { "赵", "钱", "孙", "李", "周", "吴", "郑", "王", "冯", "陈", "诸", "卫", "蒋", "沈", "韩", "杨", "朱", "秦",
				"尤", "许", "何", "吕", "施", "张", "孔", "曹", "严", "华", "金", "魏", "陶", "姜", "戚", "谢", "邹", "喻", "柏", "水", "窦",
				"章", "云", "苏", "潘", "葛", "奚", "范", "彭", "郎", "鲁", "韦", "昌", "马", "苗", "凤", "花", "方", "俞", "任", "袁", "柳",
				"酆", "鲍", "史", "唐", "费", "廉", "岑", "薛", "雷", "贺", "倪", "汤", "滕", "殷", "罗", "毕", "郝", "邬", "安", "常", "乐",
				"于", "时", "傅", "皮", "卡", "齐", "康", "伍", "余", "元", "卜", "顾", "孟", "平", "黄", "和", "穆", "萧", "尹", "姚", "邵",
				"堪", "汪", "祁", "毛", "禹", "狄", "米", "贝", "明", "臧", "计", "伏", "成", "戴", "谈", "宋", "茅", "庞", "熊", "纪", "舒",
				"屈", "项", "祝", "董", "粱", "杜", "阮", "蓝", "闵", "席", "季", "麻", "强", "贾", "路", "娄", "危", "江", "童", "颜", "郭",
				"梅", "盛", "林", "刁", "钟", "徐", "邱", "骆", "高", "夏", "蔡", "田", "樊", "胡", "凌", "霍", "虞", "万", "支", "柯", "咎",
				"管", "卢", "莫", "经", "房", "裘", "缪", "干", "解", "应", "宗", "丁", "宣", "贲", "邓", "郁", "单", "杭", "洪", "包", "诸",
				"左", "石", "崔", "吉", "钮", "龚", "程", "嵇", "邢", "滑", "裴", "陆", "荣", "翁", "荀", "羊", "於", "惠", "甄", "魏", "家",
				"封", "芮", "羿", "储", "靳", "汲", "邴", "糜", "松", "井", "段", "富", "巫", "乌", "焦", "巴", "弓", "牧", "隗", "山", "谷",
				"车", "侯", "宓", "蓬", "全", "郗", "班", "仰", "秋", "仲", "伊", "宫", "宁", "仇", "栾", "暴", "甘", "钭", "厉", "戎", "祖",
				"武", "符", "刘", "景", "詹", "束", "龙", "司马", "上官", "欧阳", "夏侯", "诸葛", "东方", "尉迟", "公孙", "长孙", "慕容", "司徒",
				"西门" };

		String[] lastname = { "超", "媛", "念", "立", "思", "嘉", "雨", "伟", "权", "秋", "佩", "雅", "联", "如", "渠", "保", "室", "铜",
				"梧", "胤", "昱", "佳", "伊", "校", "诗", "节", "如", "阁", "耕", "宫", "古", "谷", "观", "桂", "贵", "国", "广", "冠", "汉",
				"翰", "航", "杭", "海", "豪", "浩", "皓", "和", "河", "贺", "恒", "弘", "虹", "宏", "红", "厚", "鹄", "虎", "华", "欢", "怀",
				"辉", "惠", "会", "奇", "吉", "骥", "嘉", "佳", "甲", "稼", "江", "坚", "剑", "锦", "经", "镜", "界", "竞", "介", "京", "建",
				"净", "精", "敬", "静", "靖", "津", "进", "菁", "景", "炯", "驹", "举", "炬", "君", "俊", "军", "骏", "郡", "峻", "恺", "楷",
				"康", "可", "克", "珂", "逵", "魁", "阔", "昆", "奎", "宽", "况", "乐", "雷", "岭", "廉", "霖", "麟", "灵", "利", "良", "联",
				"烈", "罗", "陵", "梁", "立", "礼", "力", "莉", "烁", "隆", "龙", "禄", "璐", "露", "律", "茂", "梦", "密", "铭", "明", "绵",
				"妙", "默", "木", "能", "年", "宁", "念", "怒", "庞", "佩", "培", "朋", "鹏", "屏", "平", "魄", "珀", "璞", "奇", "琦", "齐",
				"启", "谦", "乾", "茜", "倩", "芹", "琴", "青", "卿", "秋", "权", "求", "情", "渠", "全", "荃", "群", "泉", "然", "让", "仁",
				"任", "荣", "儒", "锐", "若", "瑞", "三", "瑟", "森", "韶", "绍", "尚", "商", "珊", "善", "生", "升", "声", "晟", "胜", "盛",
				"诗", "时", "石", "实", "凇", "慎", "设", "守", "随", "世", "寿", "仕", "余", "帅", "双", "朔", "硕", "水", "誓", "适", "书",
				"舒", "殊", "顺", "思", "嗣", "似", "松", "颂", "素", "岁", "棠", "泰", "腾", "添", "铁", "同", "桐", "童", "彤", "团", "涂",
				"图", "土", "万", "旺", "望", "王", "闻", "威", "薇", "嵬", "伟", "卫", "蔚", "文", "微", "巍", "玮", "为", "畏", "吾", "午",
				"西", "熙", "玺", "仙", "先", "孝", "湘", "祥", "行", "献", "享", "效", "兴", "夏", "宣", "协", "向", "校", "轩", "瑕", "衔",
				"筱", "羡", "相", "香", "贤", "翔", "杏", "新", "信", "幸", "心", "星", "绣", "秀", "欣", "鑫", "兴", "行", "雄", "许", "炫",
				"雪", "学", "旭", "璇", "勋", "萱", "迅", "亚", "雅", "扬", "耀", "彦", "延", "岩", "炎", "永", "砚", "演", "焱", "洋", "阳",
				"曜", "耀", "夜", "译", "逸", "伊", "义", "艺", "意", "异", "怡", "翼", "毅", "银", "瑛", "仪", "易", "寅", "印", "苡", "野",
				"业", "英", "璎", "盈", "颖", "影", "雍", "勇", "悠", "由", "游", "佑", "友", "瑜", "遇", "玉", "岳", "元", "宇", "愚", "钰",
				"裕", "郁", "于"};

		return firstName[r.nextInt(firstName.length)] + lastname[r.nextInt(lastname.length)]
				+ lastname[r.nextInt(lastname.length)];
	}

	public static String ranMajor() {
		// TODO Auto-generated method stub
		return MajorNamestr[r.nextInt(MajorNamestr.length)];
	}

	public static String ranCourseName() {

		// TODO Auto-generated method stub
		return CourseNamestr[r.nextInt(CourseNamestr.length)];
	}

	public static int ranScore() {
		// TODO Auto-generated method stub
		return r.nextInt(3) + 3;
	}

	public static int ranGrade() {
		// TODO Auto-generated method stub
		return r.nextInt(100);
	}

	public static String randomCardNumber(int length) {
		String lRet = "";

		for (int i = 0; i < length; i++)
			lRet += r.nextInt(10);

		return lRet;

	}

	public static String randomCellPhoneNumber() {
		String lRet = "";
		String lHead[] = { "13", "15", "18", "14", "17" };

		lRet = lHead[r.nextInt(lHead.length)];
		for (int i = 0; i < 9; i++)
			lRet += r.nextInt(10);

		return lRet;

	}

	public static int randomBalance() {
		return r.nextInt(100000) + r.nextInt(100) / 100;
	}

	public static int random(int x) {
		return r.nextInt(x);
	}

	@SuppressWarnings("static-access")
	public static Date randomDate() {

		GregorianCalendar gc = new GregorianCalendar();

		int year = randBetween(1900, 2020);

		gc.set(gc.YEAR, year);
		int dayOfYear = randBetween(1, gc.getActualMaximum(gc.DAY_OF_YEAR));

		gc.set(gc.DAY_OF_YEAR, dayOfYear);

		// System.out.println(gc.get(gc.YEAR) + "-" + gc.get(gc.MONTH) + "-" +
		// gc.get(gc.DAY_OF_MONTH));
		return gc.getTime();
	}

	public static int randBetween(int start, int end) {
		return start + (int) Math.round(Math.random() * (end - start));
	}

	public static final String[] ENGLISH_NAMES = { "Aaron", "Abbott", "Abel", "Abner", "Abraham", "Adam", "Addison",
			"Adler", "Adley", "Adrian", "Adrien", "Aedan", "Aiden", "Aiken", "Alan", "Allan", "Alastair", "Albern",
			"Albert", "Albion", "Alden", "Aldis", "Aldrich", "Alexander", "Alfie", "Alfred", "Algernon", "Alston",
			"Alton", "Alvin", "Ambrose", "Amery", "Amos", "Andrew", "Angus", "Ansel", "Anthony", "Archer", "Archibald",
			"Arlen", "Arnold", "Arthur", "Art", "Arvel", "Atwater", "Atwood", "Aubrey", "Austin", "Avery", "Axel",
			"Baird", "Baldwin", "Barclay", "Barnaby", "Baron", "Barrett", "Barry", "Bartholomew", "Basil", "Benedict",
			"Benjamin", "Benton", "Bernard", "Bert", "Bevis", "Blaine", "Blair", "Blake", "Bond", "Boris", "Bowen",
			"Braden", "Bradley", "Brandan", "Brendan", "Brendon", "Brent", "Bret", "Brett", "Brian", "Brice", "Brigham",
			"Brock", "Broderick", "Brooke", "Bruce", "Bruno", "Bryant", "Buck", "Bud", "Burgess", "Burton", "Byron",
			"Cadman", "Calvert", "Caldwell", "Caleb", "Calvin", "Carrick", "Carl", "Carlton", "Carney", "Carroll",
			"Carter", "Carver", "Cary", "Casey", "Casper", "Cecil", "Cedric", "Chad", "Chadwick", "Chalmers",
			"Chandler", "Channing", "Chapman", "Charles", "Chatwin", "Chester", "Christian", "Christopher", "Clarence",
			"Claude", "Clayton", "Clay", "Clifford", "Cliff", "Clive", "Clyde", "Coleman", "Colin", "Collier", "Conan",
			"Connell", "Connor", "Conrad", "Conroy", "Conway", "Corwin", "Crispin", "Crosby", "Culbert", "Culver",
			"Curt", "Curtis", "Cuthbert", "Craig", "Cyril", "Dale", "Daley", "Dalton", "Damon", "Daniel", "Darcy",
			"Darian", "Darell", "Darrel", "David", "Davin", "Dean", "Declan", "Delmar", "Denley", "Dennis", "Derek",
			"Dermot", "Derwin", "Des", "Desmond", "Dexter", "Dillon", "Dion", "Dirk", "Dixon", "Dominic", "Donald",
			"Dorian", "Douglas", "Doyle", "Drake", "Drew", "Driscoll", "Dudley", "Duncan", "Durwin", "Dwayne", "Dwight",
			"Dylan", "Earl", "Eaton", "Ebenezer", "Edan", "Edgar", "Edric", "Edmond", "", "Edmund", "Edward", "",
			"Eddie", "Edwin", "Efrain", "Egan", "Egbert", "Egerton", "Egil", "Elbert", "Eldon", "Eldwin", "Eli", "Ely",
			"Elijah", "Elias", "Eliot", "Elliott", "Ellery", "Elmer", "Elroy", "Elton", "Elvis", "Emerson", "Emery",
			"Emmanuel", "Emmett", "Emrick", "Enoch", "Eric", "Erik", "Ernest", "Erronl", "Erskine", "Erwin", "Esmond",
			"Ethan", "Ethanael", "Ethen", "Eugene", "Evan", "Everett", "Ezra", "Fabian", "Fairfax", "Falkner", "Farley",
			"Farrell", "Felix", "Fenton", "Ferdinand", "Fergal", "Fergus", "Ferguson", "Ferris", "Finbar", "Fitzgerald",
			"Fleming", "Fletcher", "Floyd", "Forbes", "Forrest", "Foster", "Fox", "Francis", "Frank", "Frasier",
			"Frederick", "Freeman", "Gabriel", "Gale", "Galvin", "Gardner", "Garret", "Garrick", "Garth", "Gavin",
			"George", "Gerald", "Gerard", "Gerret", "Gideon", "Gifford", "Gilbert", "Giles", "Gilroy", "Glenn",
			"Goddard", "Godfrey", "Godwin", "Graham", "Grant", "Grayson", "Gregory", "Gresham", "Griswald", "Griswold",
			"Grover", "Guy", "Hadden", "Hadley", "Hadwin", "Hal", "Halbert", "Halden", "Hale", "Hall", "Halsey",
			"Hamlin", "Hanley", "Hardy", "Harlan", "Harland", "Harley", "Harold", "Harry", "Harris", "Harrison",
			"Hartley", "Heath", "Heathcliff", "Hector", "Henry", "Herbert", "Herman", "Homer", "Horace", "Horatio",
			"Howard", "Hubert", "Hugh", "Hugo", "Humphrey", "Hunter", "Ian", "Igor", "Irvin", "Irving", "Isaac",
			"Isaiah", "Ivan", "Iver", "Ivar", "Ives", "Jack", "Jacob", "James", "Jimmy", "Jarvis", "Jason", "Jasper",
			"Jed", "Jeffrey", "Jeremiah", "Jeremy", "Jerome", "Jesse", "John", "Jonathan", "Joseph", "Joey", "Joe",
			"Joshua", "Justin", "Kane", "Keene", "Keegan", "Keaton", "Keith", "Kelsey", "Kelvin", "Kendall", "Kendrick",
			"Kenneth", "Ken", "Kent", "Kenway", "Kenyon", "Kerry", "Kerwin", "Kevin", "Kiefer", "Kilby", "Kilian",
			"Kim", "Kimball", "Kingsley", "Kirby", "Kirk", "Kit", "Kody", "Konrad", "Kurt", "Kyle", "Lambert", "Lamont",
			"Lancelot", "Landon", "Landry", "Lane", "Lars", "Laurence", "Lee", "Leith", "Leonard", "Leo", "Leon",
			"Leroy", "Leslie", "Lester", "Lincoln", "Lionel", "Lloyd", "Logan", "Lombard", "Louis", "Lewis", "Lowell",
			"Lucas", "Luke", "Luther", "Lyndon", "Maddox", "Magnus", "Malcolm", "Melvin", "Marcus", "Mark", "Marc",
			"Marlon", "Martin", "Marvin", "Matthew", "Maurice", "Max", "Maxwell", "Medwin", "Melville", "Merlin",
			"Michael", "Milburn", "Miles", "Monroe", "Montague", "Montgomery", "Morgan", "Morris", "Morton", "Murray",
			"Nathaniel", "Nathan", "Neal", "Neville", "Nicholas", "Nigel", "Noel", "Norman", "Norris", "Olaf", "Olin",
			"Oliver", "Orson", "Oscar", "Oswald", "Otis", "Owen", "Paul", "Paxton", "Percival", "Percy", "Perry",
			"Peter", "Peyton", "Philbert", "Philip", "Phineas", "Pierce", "Quade", "Quenby", "Quillan", "Quimby",
			"Quentin", "Quinby", "Quincy", "Quinlan", "Quinn", "Ralph", "Ramsey", "Randolph", "Raymond", "Reginald",
			"Renfred", "Rex", "Rhett", "Richard", "Ridley", "Riley", "Robert", "Robin", "Roderick", "Rodney", "Roger",
			"Roland", "Rolf", "Ronald", "Rory", "Ross", "Roswell", "Roy", "Royce", "Rufus", "Rupert", "Russell",
			"Ryan" };

	public static final String EMAIL_DOMAINS[] = { "@111mail.com", "@123iran.com", "@1-usa.com", "@2die4.com",
			"@37.com", "@420email.com", "@4degreez.com", "@4-music-today.com", "@5.am", "@5005.lv", "@8.am",
			"@a.org.ua", "@abha.cc", "@accountant.com", "@actingbiz.com", "@adexec.com", "@africamail.com",
			"@agadir.cc", "@ahsa.ws", "@ajman.cc", "@ajman.us", "@ajman.ws", "@albaha.cc", "@alex4all.com",
			"@alexandria.cc", "@algerie.cc", "@allergist.com", "@allhiphop.com", "@alriyadh.cc", "@alumnidirector.com",
			"@amman.cc", "@anatomicrock.com", "@animeone.com", "@anjungcafe.com", "@aqaba.cc", "@arar.ws",
			"@archaeologist.com", "@arcticmail.com", "@artlover.com", "@asia.com", "@asiancutes.com", "@aswan.cc",
			"@a-teens.net", "@ausi.com", "@australiamail.com", "@autoindia.com", "@autopm.com", "@baalbeck.cc",
			"@bahraini.cc", "@banha.cc", "@barriolife.com", "@b-boy.com", "@beautifulboy.com", "@berlin.com",
			"@bgay.com", "@bicycledata.com", "@bicycling.com", "@bigheavyworld.com", "@bigmailbox.net",
			"@bikerheaven.net", "@bikerider.com", "@bikermail.com", "@billssite.com", "@bizerte.cc", "@bk.ru",
			"@blackandchristian.com", "@blackcity.net", "@blackvault.com", "@blida.info", "@bmx.lv", "@bmxtrix.com",
			"@boarderzone.com", "@boatnerd.com", "@bolbox.com", "@bongmail.com", "@bowl.com", "@buraydah.cc",
			"@butch-femme.org", "@byke.com", "@calle22.com", "@cameroon.cc", "@cannabismail.com", "@catlover.com",
			"@catlovers.com", "@certifiedbitches.com", "@championboxing.com", "@chatway.com", "@cheerful.com",
			"@chemist.com", "@chillymail.com", "@classprod.com", "@classycouples.com", "@clerk.com", "@cliffhanger.com",
			"@columnist.com", "@comic.com", "@company.org.ua", "@congiu.net", "@consultant.com", "@coolmail.ru",
			"@coolshit.com", "@corpusmail.com", "@counsellor.com", "@cutey.com", "@cyberunlimited.org",
			"@cycledata.com", "@darkfear.com", "@darkforces.com", "@deliveryman.com", "@dhahran.cc", "@dhofar.cc",
			"@dino.lv", "@diplomats.com", "@dirtythird.com", "@djibouti.cc", "@doctor.com", "@doglover.com",
			"@dominican.cc", "@dopefiends.com", "@dr.com", "@draac.com", "@drakmail.net", "@dr-dre.com",
			"@dreamstop.com", "@dublin.com", "@earthling.net", "@earthling.net", "@eclub.lv", "@egypt.net",
			"@e-mail.am", "@email.com", "@e-mail.ru", "@emailfast.com", "@emails.ru", "@e-mails.ru", "@eminemfans.com",
			"@envirocitizen.com", "@eritrea.cc", "@eritrea.cc", "@escapeartist.com", "@europe.com", "@execs.com",
			"@ezsweeps.com", "@falasteen.cc", "@famous.as", "@farts.com", "@feelingnaughty.com", "@financier.com",
			"@firemyst.com", "@fit.lv", "@freeonline.com", "@fromru.com", "@front.ru", "@fudge.com", "@fujairah.cc",
			"@fujairah.us", "@fujairah.ws", "@funkytimes.com", "@gabes.cc", "@gafsa.cc", "@gala.net",
			"@gamerssolution.com", "@gardener.com", "@gawab.com", "@gazabo.net", "@geologist.com", "@giza.cc",
			"@glittergrrrls.com", "@gmail.com", "@goatrance.com", "@goddess.com", "@gohip.com", "@goldenmail.ru",
			"@goldmail.ru", "@gospelcity.com", "@gothicgirl.com", "@gotomy.com", "@grapemail.net",
			"@graphic-designer.com", "@greatautos.org", "@guinea.cc", "@guinea.cc", "@guy.com", "@hacker.am",
			"@hairdresser.net", "@haitisurf.com", "@hamra.cc", "@happyhippo.com", "@hasakah.com", "@hateinthebox.com",
			"@hebron.tv", "@hiphopmail.com", "@homs.cc", "@hotbox.ru", "@hotmail.com", "@hotmail.ru", "@hot-shot.com",
			"@houseofhorrors.com", "@hugkiss.com", "@hullnumber.com", "@human.lv", "@ibra.cc", "@idunno4recipes.com",
			"@ihatenetscape.com", "@iname.com", "@inbox.ru", "@inorbit.com", "@insurer.com", "@intimatefire.com",
			"@iphon.biz", "@irbid.ws", "@irow.com", "@ismailia.cc", "@jadida.cc", "@jadida.org", "@japan.com",
			"@jazzemail.com", "@jerash.cc", "@jizan.cc", "@jouf.cc", "@journalist.com", "@juanitabynum.com",
			"@kairouan.cc", "@kanoodle.com", "@karak.cc", "@khaimah.cc", "@khartoum.cc", "@khobar.cc",
			"@kickboxing.com", "@kidrock.com", "@kinkyemail.com", "@kool-things.com", "@krovatka.net", "@kuwaiti.tv",
			"@kyrgyzstan.cc", "@land.ru", "@latakia.cc", "@latchess.com", "@latinabarbie.com", "@latinogreeks.com",
			"@lawyer.com", "@lebanese.cc", "@leesville.com", "@legislator.com", "@list.ru", "@lobbyist.com",
			"@london.com", "@loveable.com", "@loveemail.com", "@loveis.lv", "@lovers-mail.com", "@lowrider.com",
			"@lubnan.cc", "@lubnan.ws", "@lucky7lotto.net", "@lv-inter.net", "@mad.scientist.com", "@madeniggaz.net",
			"@madinah.cc", "@madrid.com", "@maghreb.cc", "@mail.com", "@mail.ru", "@mail15.com", "@mail333.com",
			"@mailbomb.com", "@manama.cc", "@mansoura.tv", "@marillion.net", "@marrakesh.cc", "@mascara.ws",
			"@megarave.com", "@meknes.cc", "@mesra.net", "@mindless.com", "@minister.com", "@mofa.com",
			"@moscowmail.com", "@motley.com", "@munich.com", "@muscat.tv", "@muscat.ws", "@music.com", "@musician.net",
			"@musician.org", "@musicsites.com", "@myself.com", "@nabeul.cc", "@nabeul.info", "@nablus.cc", "@nador.cc",
			"@najaf.cc", "@narod.ru", "@netbroadcaster.com", "@netfingers.com", "@net-surf.com", "@nettaxi.com",
			"@newmail.ru", "@nicedriveway.com", "@nightmail.ru", "@nm.ru", "@nocharge.com", "@nycmail.com", "@omani.ws",
			"@omdurman.cc", "@operationivy.com", "@optician.com", "@oran.cc", "@oued.info", "@oued.org", "@oujda.biz",
			"@oujda.cc", "@paidoffers.net", "@pakistani.ws", "@palmyra.cc", "@palmyra.ws", "@pcbee.com",
			"@pediatrician.com", "@persian.com", "@petrofind.com", "@phunkybitches.com", "@pikaguam.com",
			"@pinkcity.net", "@pisem.net", "@pitbullmail.com", "@planetsmeg.com", "@playful.com", "@pochta.ru",
			"@pochtamt.ru", "@poetic.com", "@pookmail.com", "@poop.com", "@poormail.com", "@pop3.ru", "@popstar.com",
			"@portsaid.cc", "@post.com", "@potsmokersnet.com", "@presidency.com", "@priest.com", "@primetap.com",
			"@programmer.net", "@project420.com", "@prolife.net", "@publicist.com", "@puertoricowow.com",
			"@puppetweb.com", "@qassem.cc", "@quds.cc", "@rabat.cc", "@rafah.cc", "@ramallah.cc", "@rambler.ru",
			"@rapstar.com", "@rapworld.com", "@rastamall.com", "@ratedx.net", "@ravermail.com", "@rbcmail.ru",
			"@realtyagent.com", "@registerednurses.com", "@relapsecult.com", "@remixer.com", "@repairman.com",
			"@representative.com", "@rescueteam.com", "@rockeros.com", "@romance106fm.com", "@rome.com",
			"@saveourplanet.org", "@safat.biz", "@safat.info", "@safat.us", "@safat.ws", "@saintly.com", "@salalah.cc",
			"@salmiya.biz", "@samerica.com", "@sanaa.cc", "@sanfranmail.com", "@scientist.com", "@seductive.com",
			"@seeb.cc", "@sexriga.lv", "@sfax.ws", "@sharm.cc", "@sinai.cc", "@singalongcenter.com", "@singapore.com",
			"@siria.cc", "@sketchyfriends.com", "@slayerized.com", "@smartstocks.com", "@smtp.ru", "@sociologist.com",
			"@sok.lv", "@soon.com", "@soulja-beatz.org", "@sousse.cc", "@spam.lv", "@specialoperations.com",
			"@speedymail.net", "@spells.com", "@streetracing.com", "@subspacemail.com", "@sudanese.cc", "@suez.cc",
			"@sugarray.com", "@superbikeclub.com", "@superintendents.net", "@supermail.ru", "@surfguiden.com",
			"@sweetwishes.com", "@tabouk.cc", "@tajikistan.cc", "@tangiers.cc", "@tanta.cc", "@tattoodesign.com",
			"@tayef.cc", "@teamster.net", "@techie.com", "@technologist.com", "@teenchatnow.com", "@tetouan.cc",
			"@the5thquarter.com", "@theblackmarket.com", "@timor.cc", "@tokyo.com", "@tombstone.ws", "@troamail.org",
			"@tunisian.cc", "@tunisian.cc", "@tut.by", "@tx.am", "@u2tours.com", "@ua.fm", "@uaix.info", "@umpire.com",
			"@urdun.cc", "@usa.com", "@vipmail.ru", "@vitalogy.org", "@whatisthis.com", "@whoever.com", "@winning.com",
			"@witty.com", "@wrestlezone.com", "@writeme.com", "@writeme.com", "@yahoo.com", "@yanbo.cc", "@yandex.ru",
			"@yemeni.cc", "@yemeni.cc", "@yogaelements.com", "@yours.com", "@yunus.cc", "@zabor.lv", "@zagazig.cc",
			"@zambia.cc", "@zarqa.cc", "@zerogravityclub.com" };

	public final static String randomEmail() {
		return ENGLISH_NAMES[r.nextInt(ENGLISH_NAMES.length)] + EMAIL_DOMAINS[r.nextInt(EMAIL_DOMAINS.length)];
	}

	public final static String randomChineseName() {

		String[] firstName = { "赵", "钱", "孙", "李", "周", "吴", "郑", "王", "冯", "陈", "诸", "卫", "蒋", "沈", "韩", "杨", "朱", "秦",
				"尤", "许", "何", "吕", "施", "张", "孔", "曹", "严", "华", "金", "魏", "陶", "姜", "戚", "谢", "邹", "喻", "柏", "水", "窦",
				"章", "云", "苏", "潘", "葛", "奚", "范", "彭", "郎", "鲁", "韦", "昌", "马", "苗", "凤", "花", "方", "俞", "任", "袁", "柳",
				"酆", "鲍", "史", "唐", "费", "廉", "岑", "薛", "雷", "贺", "倪", "汤", "滕", "殷", "罗", "毕", "郝", "邬", "安", "常", "乐",
				"于", "时", "傅", "皮", "卡", "齐", "康", "伍", "余", "元", "卜", "顾", "孟", "平", "黄", "和", "穆", "萧", "尹", "姚", "邵",
				"堪", "汪", "祁", "毛", "禹", "狄", "米", "贝", "明", "臧", "计", "伏", "成", "戴", "谈", "宋", "茅", "庞", "熊", "纪", "舒",
				"屈", "项", "祝", "董", "粱", "杜", "阮", "蓝", "闵", "席", "季", "麻", "强", "贾", "路", "娄", "危", "江", "童", "颜", "郭",
				"梅", "盛", "林", "刁", "钟", "徐", "邱", "骆", "高", "夏", "蔡", "田", "樊", "胡", "凌", "霍", "虞", "万", "支", "柯", "咎",
				"管", "卢", "莫", "经", "房", "裘", "缪", "干", "解", "应", "宗", "丁", "宣", "贲", "邓", "郁", "单", "杭", "洪", "包", "诸",
				"左", "石", "崔", "吉", "钮", "龚", "程", "嵇", "邢", "滑", "裴", "陆", "荣", "翁", "荀", "羊", "於", "惠", "甄", "魏", "家",
				"封", "芮", "羿", "储", "靳", "汲", "邴", "糜", "松", "井", "段", "富", "巫", "乌", "焦", "巴", "弓", "牧", "隗", "山", "谷",
				"车", "侯", "宓", "蓬", "全", "郗", "班", "仰", "秋", "仲", "伊", "宫", "宁", "仇", "栾", "暴", "甘", "钭", "厉", "戎", "祖",
				"武", "符", "刘", "景", "詹", "束", "龙", "司马", "上官", "欧阳", "夏侯", "诸葛", "东方", "尉迟", "公孙", "长孙", "慕容", "司徒",
				"西门" };

		String[] lastname = { "超", "媛", "念", "立", "思", "嘉", "雨", "伟", "权", "秋", "佩", "雅", "联", "如", "渠", "保", "室", "铜",
				"梧", "胤", "昱", "佳", "伊", "校", "诗", "节", "如", "阁", "耕", "宫", "古", "谷", "观", "桂", "贵", "国", "广", "冠", "汉",
				"翰", "航", "杭", "海", "豪", "浩", "皓", "和", "河", "贺", "恒", "弘", "虹", "宏", "红", "厚", "鹄", "虎", "华", "欢", "怀",
				"辉", "惠", "会", "奇", "吉", "骥", "嘉", "佳", "甲", "稼", "江", "坚", "剑", "锦", "经", "镜", "界", "竞", "介", "京", "建",
				"净", "精", "敬", "静", "靖", "津", "进", "菁", "景", "炯", "驹", "举", "炬", "君", "俊", "军", "骏", "郡", "峻", "恺", "楷",
				"康", "可", "克", "珂", "逵", "魁", "阔", "昆", "奎", "宽", "况", "乐", "雷", "岭", "廉", "霖", "麟", "灵", "利", "良", "联",
				"烈", "罗", "陵", "梁", "立", "礼", "力", "莉", "烁", "隆", "龙", "禄", "璐", "露", "律", "茂", "梦", "密", "铭", "明", "绵",
				"妙", "默", "木", "能", "年", "宁", "念", "怒", "庞", "佩", "培", "朋", "鹏", "屏", "平", "魄", "珀", "璞", "奇", "琦", "齐",
				"启", "谦", "乾", "茜", "倩", "芹", "琴", "青", "卿", "秋", "权", "求", "情", "渠", "全", "荃", "群", "泉", "" + "然", "让",
				"仁", "任", "荣", "儒", "锐", "若", "瑞", "三", "瑟", "森", "韶", "绍", "尚", "商", "珊", "善", "生", "升", "声", "晟", "胜",
				"盛", "诗", "时", "石", "实", "凇", "慎", "设", "守", "随", "世", "寿", "仕", "余", "帅", "双", "朔", "硕", "水", "誓", "适",
				"书", "舒", "殊", "顺", "思", "嗣", "似", "松", "颂", "素", "岁", "棠", "泰", "腾", "添", "铁", "同", "桐", "童", "彤", "团",
				"涂", "图", "土", "万", "旺", "望", "王", "闻", "威", "薇", "嵬", "伟", "卫", "蔚", "文", "微", "巍", "玮", "为", "畏", "吾",
				"午", "西", "熙", "玺", "仙", "先", "孝", "湘", "祥", "行", "献", "享", "效", "兴", "夏", "宣", "协", "向", "校", "轩", "瑕",
				"衔", "筱", "羡", "相", "香", "贤", "翔", "杏", "新", "信", "幸", "心", "星", "绣", "秀", "欣", "鑫", "兴", "行", "雄", "许",
				"炫", "雪", "学", "旭", "璇", "勋", "萱", "迅", "亚", "雅", "扬", "耀", "彦", "延", "岩", "炎", "永", "砚", "演", "焱", "洋",
				"阳", "曜", "耀", "夜", "译", "逸", "伊", "义", "艺", "意", "异", "怡", "翼", "毅", "银", "瑛", "仪", "易", "寅", "印", "苡",
				"野", "业", "英", "璎", "盈", "颖", "影", "雍", "勇", "悠", "由", "游", "佑", "友", "瑜", "遇", "玉", "岳", "元", "宇", "愚",
				"钰", "裕", "郁", "于", };

		return firstName[r.nextInt(firstName.length)] + lastname[r.nextInt(lastname.length)]
				+ lastname[r.nextInt(lastname.length)];
	}

	public static int ranSimester() { // 学期
		// TODO Auto-generated method stub
		return r.nextInt(8) + 1;
	}

	static final String[] courseType = { "必修", "选修" };

	public static String ranCourseType() {

		// TODO Auto-generated method stub
		return courseType[r.nextInt(courseType.length)];
	}

	public static int ranNum(int size) {
		// TODO Auto-generated method stub

		return r.nextInt(size);
	}

	public static String ranNums(int size) {
		String lRet = "";
		for (int i = 0; i < size; i++)
			lRet += r.nextInt(size);
		return lRet;
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

	public static String ranClass() {
		return randomMajor() + r.nextInt(5) + 1 + "班";
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

	public static final String randStatus() { // 在校状态
		int i = r.nextInt(100);
		if (i % 2 == 0)
			return "是";
		else
			return "否";
	}

	public static final String randMessageStatus() { // 消息状态
		int i = r.nextInt(100);
		if (i % 2 == 0)
			return "已读";
		else
			return "未读";
	}

	public static final Integer randDuration() { // 学制
		int i = r.nextInt(90);
		if (i / 30 < 1)
			return 3;
		else if (i / 60 < 1)
			return 4;
		else
			return 5;
	}

	public static final String[] HonorType = { "班级", "院级", "校级", "市级", "省级", "国家级" };

	public static String randomHonorType() { // 获奖类型
		return HonorType[r.nextInt(HonorType.length)];
	}

	public static final String[] DEGREE_TYPE = { "小学", "初中", "高中", "学士", "硕士", "博士" };

	public static String randomDegreType() {// 学历类型
		return DEGREE_TYPE[r.nextInt(DEGREE_TYPE.length)];
	}

	public static String randomIcon() { // 头像
		String iconPath = "../../images/icon" + r.nextInt(8) + ".jpg";
		return iconPath;
	}

	public static final String[] MAJORITY_TYPE = { "汉族", "阿昌族", "白 族", "保安族", "布朗族", "布依族", "朝鲜族", "达斡尔族", "傣族", "德昂族",
			"侗族", "东乡族", "独龙族", "鄂伦春族", "俄罗斯族", "鄂温克族", "高山族", "仡佬族", "哈尼族", "哈萨克族", "赫哲族", "回族", "基诺族", "京族", "景颇族",
			"柯尔克孜族", "拉祜族", "黎族", "傈僳族", "珞巴族", "满族", "毛南族", "门巴族", "蒙古族", "苗族", "仫佬族", "纳西族", "怒族", "普米族", "羌族", "撒拉族",
			"畲族", "水族", "塔吉克族", "塔塔尔族", "土族", "土家族", "佤族", "维吾尔族", "乌兹别克族", "锡伯族", "瑶族", "彝族", "裕固族", "藏族", "壮族" };

	public static String randomMajority() {// 民族
		return MAJORITY_TYPE[r.nextInt(MAJORITY_TYPE.length)];
	}

	public static final String[] CARD_TYPE = { "身份证", "护照", "驾照", "军人证" };

	public static String randomCardType() { // 证件类型
		return CARD_TYPE[r.nextInt(CARD_TYPE.length)];
	}

	public static final String[] Active = { "新生才艺大赛", "新生杯", "奋进杯", "英语演讲比赛", "公寓杯", "新生越野大赛", "数独大赛", "数学建模大赛", "化学竞赛",
			"主持人大赛", "supr-star", "金话筒", "街舞大赛" };

	public static String randomActive() { // 活动类型
		return Active[r.nextInt(Active.length)];
	}

	public static final String[] MessageTitle = { "放假通知", "春节拜年", "五一节祝福", "考试通知", "例行检查", "活动通知", "联系方式确认" };

	public static String randomMessageTitle() { // 消息标题
		return MessageTitle[r.nextInt(MessageTitle.length)];
	}

	public static final String[] MessageType = { "短信", "微信", "QQ", "E-mail", };

	public static String randomMessageType() { // 消息类型
		return MessageType[r.nextInt(MessageType.length)];
	}

	public static final String[] MAILPOSTCODE = { "100000", "101100", "101200", "101300", "101400", "101500", "102100",
			"102200", "102500", "102400", "102300", "102600" };

	public static String randomMailPostCode() {// 邮政编码
		return MAILPOSTCODE[r.nextInt(MAILPOSTCODE.length)];
	}

	public static final String[] SKILLNAME = { "Java", "HTML+JS+CSS", "C++", "C语言", "C#", "沟通能力及协调能力", "领导能力" };

	public static String randomSkillName() {// 个人技能名称
		return SKILLNAME[r.nextInt(SKILLNAME.length)];
	}

	public static final String[] PROFICENCYRANK = { "精通", "熟练", "掌握", "一般", "了解" };

	public static String randomProficencyRank() { // 技能熟练程度
		return PROFICENCYRANK[r.nextInt(PROFICENCYRANK.length)];
	}

	public static final String[] PROJECTS = { "使用mvc思想、oop面向对象思想、ThinkPHP模板框架、基于jQuery的EasyUI框架、Smarty模板引擎等技术来做项目开发",
			"分别用到Myeclipse开发工具、orcal数据库、ssh、javascript、jquery等开发技术，现几大功能运行稳定，运算速度明显变快", "在这里我不仅锻炼了吃苦耐劳的品行，更懂了很多为人处事的方法",
			"参与其中一项画图实习项目，制作桥位平面图，桥梁立面图共6套", "对法律关系界定不明导致的商业银行破产时理财产品的处置问题进行探析" };

	public static String randomProjects() {// 项目获得的知识
		return PROJECTS[r.nextInt(PROJECTS.length)];
	}

	public static final String[] PROJECTNAME = { "集团信息化管理平台项目", "集团ERP项目", "人才培训", "湘潭大学教务管理平台", "密训校友交流平台",
			"湘潭大学好好吃电商平台" };

	public static String randomProjectName() { // 项目名称
		return PROJECTNAME[r.nextInt(PROJECTNAME.length)];
	}

	public static final String[] PROJECTROLE = { "项目成员", "项目带头人 ", "项目经理 ", "项目集经理 ", "项目总管", "临时成员", "项目测试人员", "项目分析员",
			"项目设计员" };

	public static String randomProjectRole() {
		return PROJECTROLE[r.nextInt(PROJECTROLE.length)];
	}

	public static int randomUsedDuration() { // 使用多长时间（月）
		return r.nextInt(48) + 1;
	}

	public static int randomYear() { // 年份
		return 1900 + r.nextInt(200);
	}

	public static String num2String(int num, String format) {

		DecimalFormat df1 = new DecimalFormat(format);

		return df1.format(num);
	}

	public static final String randIDNum() {
		Date d = randomDate();
		return (r.nextInt(9) + 1) + ranNums(5) + "" + randomYear() + "" + num2String(d.getMonth() + 1, "00") + ""
				+ num2String(d.getDate(), "00") + "" + ranNums(4);
	}
	
	
	
	
	public static final String randomBulletinTitle(){
		String[] titles = {"习近平拉美之行 打造中秘自由贸易协定升级版",
				"全球健康促进大会开幕 李克强出席并致辞",		
				"静待特朗普施展",		
				"张德江出席美国",		
				"2016年11月21日外交部发言人耿爽主持例行记者会",		
				"2016年11月18日王宝强召开记者会",		
				"王宝强出现大卖场",		
				"全球健康促进大会开幕 李克强出席并致辞",		
				"八达岭老虎伤人当事人起诉动物园索赔154万",		
				"菲律宾总统将黄岩岛列为自然保护区 外交部回应",		
				"外交部：目前约有3000名缅甸边民进入中国境内",		
				"她花85万把自己整成芭比 还想让12岁女儿去丰胸",		
				"辽宁省政协原副主席受贿2195万 判刑13年9个月",		
				"奥迪宝马奔驰引领欧洲紧凑型豪华车销量辞",		
				"曝iPhone 8取消Home键！操作方式竟然是这样",		
				"全球健康促进大会开幕 李克强出席并致辞",		
				"人工智能重大进展！全球首个光电子神经网络问世",		
				"寒潮橙色预警：江淮及以南大部地区大风降温6-10℃",		
				"中国天气网讯 中央气象台11月22日18时继续发布寒潮橙色预警",		
				"10大巨头力挺 小米互联网音箱仅售399元",		
		};
		
		return titles[random(titles.length)];
	}
	
	public static String randomBulletinContent(){
		
		String[] strings = {"<p style='font-size:20px;'>Photojournalist "
				+ "Ayman Oghanna has been embedded "
				+ "for weeks with an Iraqi special "
				+ "forces unit seeking to retake "
				+ "the city of Mosul from so-called "
				+ "Islamic State. Here, he recalls "
				+ "his time with a member of the unit who died in a bomb attack."
				+ "Ahmed was involved in all of the wars of his generation and he was one of the bravest, kindest, funniest men I have known."
				+ "That in itself is a telling fact about the state of Iraq more than 13 years after the US-led invasion.</p>",
				"<p style='font-size:20px;'>央视网消息：中国国家主席习近平在结束出席亚太经合组织第二十四次领导人非正式会议系列活动之后，开始对秘鲁进行国事访问。在访问中，习近平主席强调，未来五年是中秘两国各自发展的关键时期。那么，在新形势下，中秘可以在哪些方面开展合作促进两国发展？对此，央视记者专访了现代国际关系研究院拉美所所长吴洪英。"
				+ "吴洪英表示，秘鲁是拉美国家中拥有华裔人口最多的国家，秘鲁人把习主席此次访问称作“老乡走亲戚”，秘鲁人民也对此次访问给予了很高的评价。访问期间，中国和秘鲁签署了多项合作协议，未来五年中秘间的合作在许多方面将会有很大的提升。在政治领域，中秘之间将会加强对话，尤其在地区事务、国际事务方面会加强合作和立场的协调。在贸易方面，随着中秘间贸易协定的全面升级，会有越来越多的秘鲁商品进入中国市场，中国制造的商品也会更多的进入秘鲁。同时，双方在投资领域的合作也会有较大提升，未来，中国对秘鲁在能源、矿业、农业等方面的投资将会得到加强。在文化交流方面，中国和秘鲁都是文明古国，互学互鉴对双方都非常重要。所以，未来的中秘关系将是一个全面战略伙伴关系的升级版。</p>"};
		return strings[random(strings.length)];
	}
}
