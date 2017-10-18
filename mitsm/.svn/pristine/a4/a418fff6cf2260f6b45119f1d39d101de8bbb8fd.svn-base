//package com.meession.market.common.util;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.text.ParseException;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//import com.meession.market.common.RDU;
//import com.meession.market.student.entity.MessageInfo;
//import com.meession.market.student.entity.StudentInfo;
//
//import jxl.Sheet;
//import jxl.Workbook;
//import jxl.read.biff.BiffException;
//
//public class GetAllByExcelUtils {
//	public static List<MessageInfo> getAllByExcel(InputStream input) {
//		List<MessageInfo> list = new ArrayList<MessageInfo>();
//		try {
//			Workbook rwb = Workbook.getWorkbook(input);
//
//			Sheet[] rss = rwb.getSheets();// 或者rwb.getSheet(0)
//			for (Sheet rs : rss) {
//				int clos = rs.getColumns();// 得到所有的列
//				int rows = rs.getRows();// 得到所有的行
//
//				System.out.println(clos + " rows:" + rows);
//				for (int i = 1; i < rows; i++) {
//					for (int j = 0; j < clos; j++) {
//						// 第一个是列数，第二个是行数
//						// String id=rs.getCell(j++,
//						// i).getContents();//默认最左边编号也算一列 所以这里得j++
//						String messageTitle = rs.getCell(j++, i).getContents();
//						String sender = rs.getCell(j++, i).getContents();
//						String content = rs.getCell(j++, i).getContents();
//						Date send_date = DateUtils.parse(rs.getCell(j++, i).getContents());
//						String type = rs.getCell(j++, i).getContents();
//						String state = rs.getCell(j++, i).getContents();
//						String sno = rs.getCell(j++, i).getContents();
//
//						list.add(new MessageInfo(messageTitle, sender, content, send_date, type, state, sno));
//					}
//				}
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return list;
//	}
//
//	public static List<StudentInfo> getStudentByExcel(InputStream input) throws ParseException, BiffException, IOException {
//		List<StudentInfo> list = new ArrayList<StudentInfo>();
//			Workbook rwb = Workbook.getWorkbook(input);
//
//			Sheet[] rss = rwb.getSheets();// 或者rwb.getSheet(0)
//			for (Sheet rs : rss) {
//				int clos = rs.getColumns();// 得到所有的列
//				int rows = rs.getRows();// 得到所有的行
//
//				System.out.println(clos + " rows:" + rows);
//				for (int i = 1; i < rows; i++) {
//					for (int j = 0; j < clos; j++) {
//						// 第一个是列数，第二个是行数
//						// String id=rs.getCell(j++,
//						// i).getContents();//默认最左边编号也算一列 所以这里得j++
//						String name = rs.getCell(j++, i).getContents();
//						String sno = rs.getCell(j++, i).getContents();
//						String sex = rs.getCell(j++, i).getContents();
//						String academy = rs.getCell(j++, i).getContents();
//						String depart = rs.getCell(j++, i).getContents();
//						String family_tel = rs.getCell(j++, i).getContents();
//						String major = rs.getCell(j++, i).getContents();
//						String company_tel = rs.getCell(j++, i).getContents();
//						String cname = rs.getCell(j++, i).getContents();
//						String email = rs.getCell(j++, i).getContents();
//						String tel = rs.getCell(j++, i).getContents();
//						String status = rs.getCell(j++, i).getContents();
//						String family_addr = rs.getCell(j++, i).getContents();
//						String WeChat = rs.getCell(j++, i).getContents();
//						Date start_date = DateUtils.parse(rs.getCell(j++, i).getContents());
//						String company_addr = rs.getCell(j++, i).getContents();
//						int duration = Integer.parseInt(rs.getCell(j++, i).getContents());
//						Date end_date = DateUtils.parse(rs.getCell(j++, i).getContents());
//						String dormitory = rs.getCell(j++, i).getContents();
//						String QQ = rs.getCell(j++, i).getContents();
//
//						list.add(new StudentInfo(sno, "cc", name, sex, academy, depart, major, cname, status, start_date,
//								end_date, duration, tel, family_tel, company_tel, email, family_addr, company_addr,
//								dormitory, QQ, WeChat));
//					}
//				}
//			}
//		
//		return list;
//	}
//
//}
