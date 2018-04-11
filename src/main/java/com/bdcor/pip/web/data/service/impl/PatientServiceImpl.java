package com.bdcor.pip.web.data.service.impl;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import net.sf.jxls.reader.ReaderBuilder;
import net.sf.jxls.reader.ReaderConfig;
import net.sf.jxls.reader.XLSReadStatus;
import net.sf.jxls.reader.XLSReader;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.dom4j.Document;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.bdcor.pip.core.utils.DateUtil;
import com.bdcor.pip.core.utils.Securitys;
import com.bdcor.pip.core.utils.StringUtils;
import com.bdcor.pip.data.util.CryptoUtil;
import com.bdcor.pip.data.util.FileUtils;
import com.bdcor.pip.web.data.dao.PipCommPatientDropLogMapper;
import com.bdcor.pip.web.data.dao.PipCommPatientHistoryMapper;
import com.bdcor.pip.web.data.dao.PipCommPatientInputMapper;
import com.bdcor.pip.web.data.dao.PipCommPatientMapper;
import com.bdcor.pip.web.data.dao.PipCommPatientQaMapper;
import com.bdcor.pip.web.data.dao.PipCommPatientQcDropMapper;
import com.bdcor.pip.web.data.dao.PipCommPatientQcLogMapper;
import com.bdcor.pip.web.data.dao.PipCommPatientQcMapper;
import com.bdcor.pip.web.data.dao.PipCommPatientUpdateLogMapper;
import com.bdcor.pip.web.data.dao.PipFeeInfoMapper;
import com.bdcor.pip.web.data.dao.PipScmFrozenTubeMapper;
import com.bdcor.pip.web.data.dao.UqsAnswerDropMapper;
import com.bdcor.pip.web.data.dao.UqsAnswerMapper;
import com.bdcor.pip.web.data.domain.PipCommPatient;
import com.bdcor.pip.web.data.domain.PipCommPatientDropLog;
import com.bdcor.pip.web.data.domain.PipCommPatientExample;
import com.bdcor.pip.web.data.domain.PipCommPatientHistory;
import com.bdcor.pip.web.data.domain.PipCommPatientInput;
import com.bdcor.pip.web.data.domain.PipCommPatientInputExample;
import com.bdcor.pip.web.data.domain.PipCommPatientKey;
import com.bdcor.pip.web.data.domain.PipCommPatientQa;
import com.bdcor.pip.web.data.domain.PipCommPatientQaExample;
import com.bdcor.pip.web.data.domain.PipCommPatientQc;
import com.bdcor.pip.web.data.domain.PipCommPatientQcDrop;
import com.bdcor.pip.web.data.domain.PipCommPatientQcDropExample;
import com.bdcor.pip.web.data.domain.PipCommPatientQcDropKey;
import com.bdcor.pip.web.data.domain.PipCommPatientQcExample;
import com.bdcor.pip.web.data.domain.PipCommPatientQcKey;
import com.bdcor.pip.web.data.domain.PipCommPatientQcLog;
import com.bdcor.pip.web.data.domain.PipCommPatientUpdateLog;
import com.bdcor.pip.web.data.domain.PipFeeInfo;
import com.bdcor.pip.web.data.domain.PipFeeInfoExample;
import com.bdcor.pip.web.data.domain.PipScmFrozenTube;
import com.bdcor.pip.web.data.domain.PipScmFrozenTubeExample;
import com.bdcor.pip.web.data.domain.UqsAnswer;
import com.bdcor.pip.web.data.domain.UqsAnswerDrop;
import com.bdcor.pip.web.data.domain.UqsAnswerDropExample;
import com.bdcor.pip.web.data.domain.UqsAnswerExample;
import com.bdcor.pip.web.data.filter.PatientFilter;
import com.bdcor.pip.web.data.service.PatientService;

@Service
public class PatientServiceImpl implements PatientService {
	@Autowired
	PipCommPatientInputMapper pipCommPatientInputMapper;

	@Autowired
	PipCommPatientMapper pipCommPatientMapper;

	@Autowired
	PipCommPatientQcMapper pipCommPatientQcMapper;

	@Autowired
	PipCommPatientQaMapper pipCommPatientQaMapper;

	@Autowired
	PipCommPatientQcDropMapper pipCommPatientQcDropMapper;

	@Autowired
	PipFeeInfoMapper pipFeeInfoMapper;

	@Autowired
	UqsAnswerMapper uqsAnswerMapper;

	@Autowired
	UqsAnswerDropMapper uqsAnswerDropMapper;

	@Autowired
	PipScmFrozenTubeMapper pipScmFrozenTubeMapper;

	@Autowired
	PipCommPatientHistoryMapper pipCommPatientHistoryMapper;

	@Autowired
	PipCommPatientDropLogMapper pipCommPatientDropLogMapper;

	@Autowired
	PipCommPatientQcLogMapper pipCommPatientQcLogMapper;

	@Autowired
	PipCommPatientUpdateLogMapper pipCommPatientUpdateLogMapper;

	private CellStyle redStyle;
	private Workbook cbook;

	private CellStyle getRedCellStyle(Workbook book) {
		if (redStyle == null
				|| (redStyle != null && (cbook == null || !cbook.equals(book)))) {
			cbook = book;
			redStyle = book.createCellStyle();
			redStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
			redStyle.setFillForegroundColor(HSSFColor.RED.index);
			redStyle.setFillBackgroundColor(HSSFColor.RED.index);
		}
		return redStyle;
	}

	/**
	 * @param file
	 *            上传的数据文件
	 * @param template
	 *            模板文件
	 * @param xml
	 *            模板列与对象的绑定
	 */
	@Override
	public Map<String, Object> checkData(File file, File template, File xml) {

		List<String> msg = new ArrayList<String>();
		Map<String, Object> map = new HashMap<String, Object>();
		Workbook wb = this.getExcelData(file);
		Workbook tb = this.getExcelData(template);

		int headerRow = 0;

		// 检查模板
		if (this.checkTemplate(wb, tb, headerRow)) {
			try {
				// 导入excle
				InputStream inputXML = new BufferedInputStream(
						new FileInputStream(xml));
				XLSReader mainReader = ReaderBuilder.buildFromXML(inputXML);
				InputStream inputXLS = new BufferedInputStream(
						new FileInputStream(file));
				PipCommPatientInput p = new PipCommPatientInput();
				List<PipCommPatientInput> list = new ArrayList<PipCommPatientInput>();

				ReaderConfig.getInstance().setSkipErrors(true);

				Map beans = new HashMap();
				beans.put("pat", p);
				beans.put("pats", list);
				XLSReadStatus readStatus = mainReader.read(inputXLS, beans);
				if (list != null && list.size() > 0) {
					if (list.size() > 20000) {
						throw new Exception("数据表格需要限制在20000行以内");
					}

					int i = 1;
					int mm = 0;
					for (PipCommPatientInput po : list) {
						// System.out.println(po.getPatientName());
						if (wb.getSheetAt(0).getRow(i).getCell(0) == null)
							wb.getSheetAt(0).getRow(i).createCell(0);
						if (wb.getSheetAt(0).getRow(i).getCell(1) == null)
							wb.getSheetAt(0).getRow(i).createCell(1);
						if (wb.getSheetAt(0).getRow(i).getCell(2) == null)
							wb.getSheetAt(0).getRow(i).createCell(2);
						if (wb.getSheetAt(0).getRow(i).getCell(3) == null)
							wb.getSheetAt(0).getRow(i).createCell(3);
						if (wb.getSheetAt(0).getRow(i).getCell(4) == null)
							wb.getSheetAt(0).getRow(i).createCell(4);
						if (wb.getSheetAt(0).getRow(i).getCell(5) == null)
							wb.getSheetAt(0).getRow(i).createCell(5);
						if (wb.getSheetAt(0).getRow(i).getCell(6) == null)
							wb.getSheetAt(0).getRow(i).createCell(6);
						if (wb.getSheetAt(0).getRow(i).getCell(7) == null)
							wb.getSheetAt(0).getRow(i).createCell(7);
						if (wb.getSheetAt(0).getRow(i).getCell(8) == null)
							wb.getSheetAt(0).getRow(i).createCell(8);
						if (wb.getSheetAt(0).getRow(i).getCell(9) == null)
							wb.getSheetAt(0).getRow(i).createCell(9);

						StringBuffer sb = new StringBuffer();
						sb.append("");
						if (StringUtils.isBlank(po.getPatientName())) {
							sb.append("姓名不能为空；");
							wb.getSheetAt(0).getRow(i).getCell(0)
									.setCellStyle(getRedCellStyle(wb));
						} else if (po.getPatientName().length() > 40) {
							sb.append("姓名数据超长；");
							wb.getSheetAt(0).getRow(i).getCell(0)
									.setCellStyle(getRedCellStyle(wb));
						}

						if (StringUtils.isBlank(po.getIdNumber())) {
							if (StringUtils.isBlank(po.getSex())) {
								sb.append("没有身份证号时性别不能为空；");
								wb.getSheetAt(0).getRow(i).getCell(1)
										.setCellStyle(getRedCellStyle(wb));
							}
							if (StringUtils.isNotBlank(po.getSex())
									&& !po.getSex().equals("男")
									&& !po.getSex().equals("女")) {
								sb.append("性别填写错误；");
								wb.getSheetAt(0).getRow(i).getCell(1)
										.setCellStyle(getRedCellStyle(wb));
							}
							if (StringUtils.isBlank(po.getBirthdayTxt())) {
								sb.append("没有身份证号时出生日期不能为空；");
								wb.getSheetAt(0).getRow(i).getCell(3)
										.setCellStyle(getRedCellStyle(wb));
							} else {
								try {
									Pattern pad = Pattern
											.compile("((^((1[8-9]\\d{2})|([2-9]\\d{3}))([-\\/\\._])(10|12|0?[13578])([-\\/\\._])(3[01]|[12][0-9]|0?[1-9])$)|(^((1[8-9]\\d{2})|([2-9]\\d{3}))([-\\/\\._])(11|0?[469])([-\\/\\._])(30|[12][0-9]|0?[1-9])$)|(^((1[8-9]\\d{2})|([2-9]\\d{3}))([-\\/\\._])(0?2)([-\\/\\._])(2[0-8]|1[0-9]|0?[1-9])$)|(^([2468][048]00)([-\\/\\._])(0?2)([-\\/\\._])(29)$)|(^([3579][26]00)([-\\/\\._])(0?2)([-\\/\\._])(29)$)|(^([1][89][0][48])([-\\/\\._])(0?2)([-\\/\\._])(29)$)|(^([2-9][0-9][0][48])([-\\/\\._])(0?2)([-\\/\\._])(29)$)|(^([1][89][2468][048])([-\\/\\._])(0?2)([-\\/\\._])(29)$)|(^([2-9][0-9][2468][048])([-\\/\\._])(0?2)([-\\/\\._])(29)$)|(^([1][89][13579][26])([-\\/\\._])(0?2)([-\\/\\._])(29)$)|(^([2-9][0-9][13579][26])([-\\/\\._])(0?2)([-\\/\\._])(29)$))");
									Matcher md = pad.matcher(po
											.getBirthdayTxt());
									if (!md.matches()) {
										throw new Exception();
									}
								} catch (Exception ex) {
									sb.append("出生日期格式错误；");
									wb.getSheetAt(0).getRow(i).getCell(3)
											.setCellStyle(getRedCellStyle(wb));
								}
							}
							if (StringUtils.isBlank(po.getCredentialsType())
									|| StringUtils.isBlank(po
											.getCredentialsCode())) {
								sb.append("没有身份证号时证件号和类型不能为空；");
								wb.getSheetAt(0).getRow(i).getCell(7)
										.setCellStyle(getRedCellStyle(wb));
								wb.getSheetAt(0).getRow(i).getCell(8)
										.setCellStyle(getRedCellStyle(wb));
							}

						} else {
							if (po.getIdNumber().length() != 15
									&& po.getIdNumber().length() != 18) {
								sb.append("身份证号长度错误；");
								wb.getSheetAt(0).getRow(i).getCell(2)
										.setCellStyle(getRedCellStyle(wb));
							} else {
								for (int j = 0; j < i - 1; j++) {
									if (list.get(j).getIdNumber() != null
											&& !list.get(j).getIdNumber()
													.equals("")
											&& list.get(j).getIdNumber()
													.equals(po.getIdNumber())) {
										sb.append("身份证号与第" + (j + 1) + "行重复；");
										wb.getSheetAt(0)
												.getRow(i)
												.getCell(2)
												.setCellStyle(
														getRedCellStyle(wb));
										break;
									}
								}

								po.setIdNumber(po.getIdNumber().replaceAll("x",
										"X"));

								PipCommPatientInputExample example = new PipCommPatientInputExample();
								example.createCriteria().andIdNumberEqualTo(
										po.getIdNumber());
								example.or().andIdNumberEqualTo(
										po.getIdNumber().toLowerCase());
								List plist = this.pipCommPatientInputMapper
										.selectByExample(example);
								if (plist != null && plist.size() > 0) {
									sb.append("身份证号已经存在；");
									wb.getSheetAt(0).getRow(i).getCell(2)
											.setCellStyle(getRedCellStyle(wb));
								} else {
									Pattern pa = Pattern
											.compile("(\\d{15})|(\\d{17}[0-9X])");
									Matcher m = pa.matcher(po.getIdNumber());
									if (!m.matches()) {
										sb.append("身份证号格式错误；");
										wb.getSheetAt(0)
												.getRow(i)
												.getCell(2)
												.setCellStyle(
														getRedCellStyle(wb));
									} else {
										String dtxt = po.getIdNumber()
												.substring(6, 14);
										if (po.getIdNumber().length() == 15) {
											dtxt = "19"
													+ po.getIdNumber()
															.substring(6, 12);
										}
										try {
											Pattern pad = Pattern
													.compile("((^((1[8-9]\\d{2})|([2-9]\\d{3}))([-\\/\\._])(10|12|0?[13578])([-\\/\\._])(3[01]|[12][0-9]|0?[1-9])$)|(^((1[8-9]\\d{2})|([2-9]\\d{3}))([-\\/\\._])(11|0?[469])([-\\/\\._])(30|[12][0-9]|0?[1-9])$)|(^((1[8-9]\\d{2})|([2-9]\\d{3}))([-\\/\\._])(0?2)([-\\/\\._])(2[0-8]|1[0-9]|0?[1-9])$)|(^([2468][048]00)([-\\/\\._])(0?2)([-\\/\\._])(29)$)|(^([3579][26]00)([-\\/\\._])(0?2)([-\\/\\._])(29)$)|(^([1][89][0][48])([-\\/\\._])(0?2)([-\\/\\._])(29)$)|(^([2-9][0-9][0][48])([-\\/\\._])(0?2)([-\\/\\._])(29)$)|(^([1][89][2468][048])([-\\/\\._])(0?2)([-\\/\\._])(29)$)|(^([2-9][0-9][2468][048])([-\\/\\._])(0?2)([-\\/\\._])(29)$)|(^([1][89][13579][26])([-\\/\\._])(0?2)([-\\/\\._])(29)$)|(^([2-9][0-9][13579][26])([-\\/\\._])(0?2)([-\\/\\._])(29)$))");

											dtxt = dtxt.substring(0, 4) + "-"
													+ dtxt.substring(4, 6)
													+ "-"
													+ dtxt.substring(6, 8);
											Matcher md = pad.matcher(dtxt);
											if (md.matches()) {
												SimpleDateFormat sdf = new SimpleDateFormat(
														"yyyy-MM-dd");
												Date dd = sdf.parse(dtxt);
												Date nd = new Date();
												if (Math.abs(dd.getYear()
														- nd.getYear()) > 150) {
													throw new Exception();
												}
											} else {
												throw new Exception();
											}
										} catch (Exception ex) {
											sb.append("身份证号出生日期格式错误；");
											wb.getSheetAt(0)
													.getRow(i)
													.getCell(2)
													.setCellStyle(
															getRedCellStyle(wb));
										}
									}
								}
							}
						}

						if (StringUtils.isNotBlank(po.getPhone())
								&& po.getPhone().length() > 100) {
							sb.append("电话数据超长；");
							wb.getSheetAt(0).getRow(i).getCell(4)
									.setCellStyle(getRedCellStyle(wb));
						}

						if (StringUtils.isNotBlank(po.getMobile())) {

							try {
								po.setMobile(po.getMobile().replaceAll("-", "")
										.replace("+", ""));
								if (po.getMobile().length() != 11) {
									throw new Exception();
								}
								Long l = Long.parseLong(po.getMobile());
								if (!l.toString().equals(po.getMobile())) {
									throw new Exception();
								}
							} catch (Exception ex) {
								sb.append("手机格式错误；");
								wb.getSheetAt(0).getRow(i).getCell(5)
										.setCellStyle(getRedCellStyle(wb));
							}
						}

						if (StringUtils.isNotBlank(po.getAddress())
								&& po.getAddress().length() > 200) {
							sb.append("地址数据超长；");
							wb.getSheetAt(0).getRow(i).getCell(6)
									.setCellStyle(getRedCellStyle(wb));
						}

						if (StringUtils.isNotBlank(po.getCredentialsType())) {
							String tys = ",居民身份证,居民户口簿,护照,军官证,驾驶证,港涣居民来往内地通行证,台湾居民来往内地通行证,其他法定有效证件,";
							if (tys.indexOf("," + po.getCredentialsType() + ",") < 0) {
								sb.append("证件类型错误；");
								wb.getSheetAt(0).getRow(i).getCell(7)
										.setCellStyle(getRedCellStyle(wb));
							}
						}

						if (StringUtils.isNotBlank(po.getCredentialsCode())
								&& po.getCredentialsCode().length() > 100) {
							sb.append("证件号数据超长；");
							wb.getSheetAt(0).getRow(i).getCell(8)
									.setCellStyle(getRedCellStyle(wb));
						}

						wb.getSheetAt(0).getRow(i).getCell(9)
								.setCellValue(sb.toString());
						i++;
						if (!sb.toString().equals("")) {
							msg.add("第" + i + "行数据：" + sb.toString());
							mm++;
						}
						/*
						 * if ( mm > 1000 ){ break; }
						 */
					}

					if (msg.size() == 0) {
						for (PipCommPatientInput po : list) {
							po.setProjectId(Securitys.getUser()
									.getCurrent_projectId());
							po.setLccCode(Securitys.getUser().getLccCode());
							Date cd = new Date();
							po.setCreateDate(cd);
							Date cd2 = new Date();
							cd2.setTime(cd.getTime() + 24L * 3600 * 1000);
							po.setUpdateDate(cd2);
							po.setIsRemoved(new Short("0"));

							if (StringUtils.isNotBlank(po.getCredentialsCode())) {
								if (po.getCredentialsType().equals("居民户口簿")) {
									po.setCredentialsType("02");
								} else if (po.getCredentialsType().equals("护照")) {
									po.setCredentialsType("03");
								} else if (po.getCredentialsType()
										.equals("军官证")) {
									po.setCredentialsType("04");
								} else if (po.getCredentialsType()
										.equals("驾驶证")) {
									po.setCredentialsType("05");
								} else if (po.getCredentialsType().equals(
										"港涣居民来往内地通行证")) {
									po.setCredentialsType("06");
								} else if (po.getCredentialsType().equals(
										"台湾居民来往内地通行证")) {
									po.setCredentialsType("07");
								} else if (po.getCredentialsType().equals(
										"其他法定有效证件")) {
									po.setCredentialsType("99");
								}
							}

							if (StringUtils.isNotBlank(po.getIdNumber())) {
								// 性别
								String ns = po.getIdNumber().substring(
										po.getIdNumber().length() - 2,
										po.getIdNumber().length() - 1);
								if (po.getIdNumber().length() == 15)
									ns = po.getIdNumber().substring(14, 15);
								Integer n = Integer.parseInt(ns);
								if (n % 2 == 0) {
									po.setSex("2");
								} else {
									po.setSex("1");
								}
								// 生日
								String dtxt = po.getIdNumber().substring(6, 14);
								if (po.getIdNumber().length() == 15) {
									dtxt = "19"
											+ po.getIdNumber().substring(6, 12);
								}
								try {
									SimpleDateFormat sdf = new SimpleDateFormat(
											"yyyyMMdd");
									Date dd = sdf.parse(dtxt);
									po.setBirthday(dd);
								} catch (Exception ex) {
								}

							} else {
								if (po.getSex() != null
										&& po.getSex().equals("男")) {
									po.setSex("1");
								} else if (po.getSex() != null
										&& po.getSex().equals("女")) {
									po.setSex("2");
								}
								if (StringUtils.isNotBlank(po.getBirthdayTxt())) {
									try {
										po.setBirthday(DateUtils.parseDate(
												po.getBirthdayTxt(),
												new String[] { "yyyy-MM-dd" }));
									} catch (Exception ex) {
									}
								}
							}

							List<Map<String, Object>> keys = this.pipCommPatientInputMapper
									.getEntityId();
							if (keys != null && keys.size() == 1) {
								po.setId(((BigDecimal) keys.get(0).get("KEY"))
										.toString());
							} else {
								po.setId("1");
							}
							this.pipCommPatientInputMapper.insert(po);
						}
						PipCommPatientHistory his = new PipCommPatientHistory();
						his.setId(System.currentTimeMillis());
						his.setCreateDate(new Date());
						his.setCreateBy(Securitys.getUser().getLoginName());
						his.setCreaterName(Securitys.getUser().getName());
						his.setFileName(file.getName());
						his.setFilePath(file.getPath());
						his.setLccCode(Securitys.getUser().getLccCode());
						his.setProjectId(Securitys.getUser()
								.getCurrent_projectId());
						his.setRowNum(list.size());
						this.pipCommPatientHistoryMapper.insert(his);
					}
				} else {
					msg.add("无解析上传文件的数据或数据为空！");
				}
			} catch (Exception e) {
				e.printStackTrace();
				msg.add(e.getMessage());
			}
		} else {
			msg.add("模板格式错误！");
		}
		map.put("msg", msg);
		map.put("book", wb);
		return map;
	}

	@Override
	public boolean checkTemplate(Workbook wb, Workbook tb, int headerRow) {

		Row header = wb.getSheetAt(0).getRow(headerRow);
		Row templateHeader = tb.getSheetAt(0).getRow(headerRow);
		for (int j = 0; j < templateHeader.getLastCellNum(); j++) {
			Cell hc = header.getCell(j);
			Cell tc = templateHeader.getCell(j);
			if (hc != null && tc != null
					&& hc.getCellType() == Cell.CELL_TYPE_STRING
					&& tc.getCellType() == Cell.CELL_TYPE_STRING
					&& !hc.getStringCellValue().equals("")
					&& hc.getStringCellValue().equals(tc.getStringCellValue())) {

			} else {
				return false;
			}
		}
		return true;
	}

	public Workbook getExcelMultipart(MultipartFile file) {
		Workbook book = null;
		InputStream is = null;
		try {
			is = file.getInputStream();
			book = new HSSFWorkbook(is);
		} catch (Exception ex) {
			try {
				is = file.getInputStream();
				book = new XSSFWorkbook(is);
			} catch (Exception exx) {
				exx.printStackTrace();
			}
		} finally {
			try {
				if (is != null)
					is.close();
			} catch (Exception ex) {

			}
		}
		return book;
	}

	@Override
	public Workbook getExcelData(File file) {
		Workbook book = null;
		FileInputStream is = null;
		try {
			is = new FileInputStream(file);
			book = new HSSFWorkbook(is);
		} catch (Exception ex) {
			try {
				is = new FileInputStream(file);
				book = new XSSFWorkbook(is);
			} catch (Exception exx) {
				// throw new ServiceException("不兼容的Excel数据格式！");
			}
		} finally {
			try {
				if (is != null)
					is.close();
			} catch (Exception ex) {

			}
		}
		return book;
	}

	private static Map<String, String> itemMap = new HashMap<String, String>();
	static {
		itemMap.put("001001", "001001-基本信息登记表");
		itemMap.put("001002", "001002-初筛调查表");
		itemMap.put("001003", "001003-高危调查表");
		itemMap.put("001004", "001004-高危核查表");
		itemMap.put("001005", "001005-干预调查表");
		itemMap.put("001006", "001006-随访调查表");
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public void dropPatient(String projectId, String patientId)
			throws Exception {

		if (StringUtils.isBlank(patientId))
			throw new Exception("作废条码不能为空");
		PipCommPatient pat = null;
		boolean isRisk = false;
		if (patientId.substring(0, 1).equals("G")) {
			isRisk = true;
			// 高危
			PipCommPatientExample example = new PipCommPatientExample();
			example.createCriteria().andProjectIdEqualTo(projectId)
					.andRiskCodeEqualTo(patientId);
			List<PipCommPatient> list = this.pipCommPatientMapper
					.selectByExample(example);
			if (list != null && list.size() == 1) {
				pat = list.get(0);
			} else {
				throw new Exception("该条码被重复绑定，请手工处理");
			}
		} else {
			// 初筛
			PipCommPatientKey key = new PipCommPatientKey();
			key.setPatientId(patientId);
			key.setProjectId(Securitys.getUser().getCurrent_projectId());
			pat = this.pipCommPatientMapper.selectByPrimaryKey(key);
		}

		if (pat == null) {
			throw new Exception("无法作废不存在的条码");
		}

		PipCommPatientDropLog log = new PipCommPatientDropLog();
		log.setPatientId(patientId);
		log.setCreateBy(Securitys.getUserName());
		log.setIdNumber(pat.getIdNumber());
		log.setCreateDate(new Date());
		log.setType("清空");

		// 复制 drop
		this.movePatientQc(projectId, patientId, pat, log);

		this.pipCommPatientDropLogMapper.insert(log);

		// drop answer
		this.moveAnswer(projectId, patientId, pat);

		// 修改 patient
		if (isRisk) {
			this.removeRiskCode(projectId, patientId, pat);
		} else {
			this.deletePatient(projectId, patientId, pat);
		}

		// 删除 feeinfo
		this.deleteFeeInfo(projectId, patientId, pat);

		// 删除进度 qa
		this.deletePatientQa(projectId, patientId, pat);

		if (isRisk) {
			this.emptyFrozenTube(projectId, patientId, pat);
		}
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public void dropPatientUqs(String projectId, String patientId,
			String itemCode) throws Exception {

		Date d = new Date();

		String lccCode = patientId.replace("G", "").substring(0, 4);

		PipCommPatientQcKey key = new PipCommPatientQcKey();
		key.setProjectId(projectId);
		key.setItemCode(itemCode);
		key.setPatientId(patientId);
		key.setLccCode(lccCode);
		PipCommPatientQc qc = this.pipCommPatientQcMapper
				.selectByPrimaryKey(key);
		if (qc != null) {
			this.deletePatientQc(projectId, lccCode, patientId, itemCode, d);

			this.moveAnswer(projectId, patientId, itemCode, d);

			PipCommPatientQcLog log = new PipCommPatientQcLog();
			log.setCreateBy(Securitys.getUserName());
			log.setCreateDate(new Date());
			log.setEndDate(qc.getEndTime());
			log.setItemCode(itemCode);
			log.setPatientId(patientId);
			// log.setSourceId(sourceId);
			log.setStartDate(qc.getStartTime());
			log.setType("删除");

			pipCommPatientQcLogMapper.insert(log);
		} else {
			throw new Exception("删除问卷不存在");
		}

	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public void copyPatientUqs(String projectId, String patientId,
			String sourceId, String itemCode, String dropDate) throws Exception {

		String lccCode = patientId.replace("G", "").substring(0, 4);
		PipCommPatientQcKey key = new PipCommPatientQcKey();
		key.setProjectId(projectId);
		key.setItemCode(itemCode);
		key.setPatientId(patientId);
		key.setLccCode(lccCode);
		PipCommPatientQc qc = this.pipCommPatientQcMapper
				.selectByPrimaryKey(key);

		if (qc != null)
			this.dropPatientUqs(projectId, patientId, itemCode);

		String lccCode2 = sourceId.replace("G", "").substring(0, 4);
		if (StringUtils.isNotBlank(dropDate)) {
			PipCommPatientQcDrop qd = this.copyPatientQcDrop(projectId,
					lccCode2, patientId, sourceId, itemCode, dropDate);
			this.copyAnswerDrop(projectId, lccCode2, patientId, sourceId,
					itemCode, dropDate);

			PipCommPatientQcLog log = new PipCommPatientQcLog();
			log.setCreateBy(Securitys.getUserName());
			log.setCreateDate(new Date());
			log.setEndDate(qd.getEndTime());
			log.setItemCode(itemCode);
			log.setPatientId(patientId);
			log.setSourceId(sourceId);
			log.setStartDate(qd.getStartTime());
			log.setType("复制");

			pipCommPatientQcLogMapper.insert(log);

		} else {

			PipCommPatientQc qc2 = this.copyPatientQc(projectId, lccCode2,
					patientId, sourceId, itemCode);
			this.copyAnswer(projectId, lccCode2, patientId, sourceId, itemCode);

			PipCommPatientQcLog log = new PipCommPatientQcLog();
			log.setCreateBy(Securitys.getUserName());
			log.setCreateDate(new Date());
			log.setEndDate(qc2.getEndTime());
			log.setItemCode(itemCode);
			log.setPatientId(patientId);
			log.setSourceId(sourceId);
			log.setStartDate(qc2.getStartTime());
			log.setType("复制");

			pipCommPatientQcLogMapper.insert(log);
		}

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public void updatePatientCode(String projectId, String idNumber,
			String patientId, String riskCode, String delPat, String delRisk)
			throws Exception {
		System.out.println(idNumber + "|" + patientId + "|" + riskCode + "|"
				+ delPat + "|" + delRisk);

		PipCommPatientUpdateLog log = new PipCommPatientUpdateLog();
		log.setCreateBy(Securitys.getUserName());
		log.setCreateDate(new Date());
		log.setIdNumber(idNumber);
		log.setNewPatientId(patientId);
		log.setNewRiskCode(riskCode);
		log.setPatientIdType(delPat);
		log.setRiskCodeType(delRisk);
		log.setPatientIds("");
		log.setRiskCodes("");

		PipCommPatientExample example = new PipCommPatientExample();
		example.createCriteria().andUpdateDateIsNotNull()
				.andProjectIdEqualTo(projectId).andIdNumberEqualTo(idNumber);

		List<PipCommPatient> list = this.pipCommPatientMapper
				.selectByExample(example);
		PipCommPatientInput input = null;
		if (list == null || list.size() == 0) {
			PipCommPatientInputExample exa = new PipCommPatientInputExample();
			exa.createCriteria().andUpdateDateIsNotNull()
					.andProjectIdEqualTo(projectId)
					.andIdNumberEqualTo(idNumber);
			List<PipCommPatientInput> inputs = this.pipCommPatientInputMapper
					.selectByExample(exa);
			if (inputs == null || inputs.size() == 0) {
				throw new Exception("无此身份证号");
			} else if (inputs != null && inputs.size() > 1) {
				throw new Exception("身份证号重复");
			} else {
				input = inputs.get(0);
			}
		}

		if (StringUtils.isNotBlank(patientId)) {
			PipCommPatient _p = getPatientByKey(projectId, patientId);
			if (_p != null && !_p.getIdNumber().equals(idNumber)) {
				throw new Exception("指定条码已被使用");
			}
			if (this.getPatientQcDrop(projectId, patientId)) {
				throw new Exception("指定条码曾经被删除");
			}
		}

		if (StringUtils.isNotBlank(riskCode)) {
			PipCommPatient _p = getPatientByKey(projectId, riskCode);
			if (_p != null && !_p.getIdNumber().equals(idNumber)) {
				throw new Exception("指定条码已被使用");
			}
			if (this.getPatientQcDrop(projectId, patientId)) {
				throw new Exception("指定条码曾经被删除");
			}
		}

		if (list.size() == 1) {
			// 只绑定一个初筛
			PipCommPatient pat = list.get(0);
			log.setPatientIds(pat.getPatientId());
			log.setRiskCodes(pat.getRiskCode());

			if (delRisk.equals("1")) {
				this.updateRiskCode(pat, null);
			} else if (StringUtils.isNotBlank(riskCode)) {
				this.updateRiskCode(pat, riskCode);
			}

			if (delPat.equals("1")) {
				this.updatePatientId(pat, null);
			} else if (StringUtils.isNotBlank(patientId)
					&& !pat.getPatientId().equals(patientId)) {
				this.updatePatientId(pat, patientId);
			}

		} else if (input != null) {
			// 只存在花名册中
			if (delRisk.equals("1")) {
				input.setRiskCode(null);
			} else if (StringUtils.isNotBlank(riskCode)) {
				input.setRiskCode(riskCode);
			}

			if (delPat.equals("1")) {
				input.setPatientId(null);
			} else if (StringUtils.isNotBlank(patientId)) {
				input.setPatientId(patientId);
			}

			this.pipCommPatientInputMapper.updateByPrimaryKey(input);

			if (input.getPatientId() != null) {
				PipCommPatient p = new PipCommPatient();
				p = new PipCommPatient();
				p.setProjectId(projectId);
				p.setPatientId(input.getPatientId());
				p.setPatientCode(input.getId());
				p.setLccCode(input.getLccCode());

				p.setBirthday(input.getBirthday());
				// p.setBloodCode(patient.get);
				p.setCreateDate(new Date());
				p.setMobile(input.getMobile());
				p.setIdNumber(input.getIdNumber());
				p.setNation(input.getNation());
				p.setPatientName(input.getPatientName());
				p.setPhone(input.getPhone());
				p.setSex(input.getSex());

				p.setIsFollowrisk(input.getIsFollowrisk());
				p.setIsFollowview(input.getIsFollowview());
				p.setFollowriskDate(input.getFollowriskDate());
				p.setFollowviewDate(input.getFollowviewDate());

				p.setRiskCode(input.getRiskCode());
				if (StringUtils.isNotBlank(input.getRiskCode())) {
					p.setRiskDate(new Date());
				}

				p.setCredentialsCode(input.getCredentialsCode());
				p.setCredentialsType(input.getCredentialsType());

				p.setUpdateDate(new Date());
				p.setIsSpecial(input.getIsSpecial());

				this.pipCommPatientMapper.insert(p);
			}

		} else {
			// 绑定多个
			for (PipCommPatient pat : list) {
				log.setPatientIds(log.getPatientIds() + pat.getPatientId()
						+ " ");
				if (StringUtils.isNotBlank(pat.getRiskCode())) {
					log.setRiskCodes(log.getRiskCodes() + pat.getRiskCode()
							+ " ");
				}
			}

			if (delRisk.equals("1")) {
				for (PipCommPatient pat : list) {
					this.updateRiskCode(pat, null);
				}
			} else if (StringUtils.isNotBlank(riskCode)) {
				for (PipCommPatient pat : list) {
					this.updateRiskCode(pat, riskCode);
				}
			}

			if (delPat.equals("1")) {
				for (PipCommPatient pat : list) {
					this.updatePatientId(pat, null);
				}
			} else if (StringUtils.isNotBlank(patientId)) {
				boolean match = false;
				for (PipCommPatient pat : list) {
					if (pat.getPatientId().equals(patientId)) {
						match = true;
						break;
					}
				}
				if (match) {
					// 指定的初筛，是其中之一，删除其他
					for (PipCommPatient pat : list) {
						if (!pat.getPatientId().equals(patientId)) {
							this.updatePatientId(pat, null);
						}
					}
				} else {
					// 不是 , 删除所有新建
					// PipCommPatient p = list.get(0);
					for (PipCommPatient pat : list) {
						this.updatePatientId(pat, null);
					}
					PipCommPatientInputExample exa = new PipCommPatientInputExample();
					example.createCriteria().andUpdateDateIsNotNull()
							.andIdNumberEqualTo(idNumber);
					List<PipCommPatientInput> inputs = this.pipCommPatientInputMapper
							.selectByExample(exa);
					if (inputs != null && inputs.size() == 1) {
						PipCommPatientInput pinput = inputs.get(0);
						pinput.setPatientId(patientId);
						this.pipCommPatientInputMapper
								.updateByPrimaryKey(pinput);

						PipCommPatient p = new PipCommPatient();
						p = new PipCommPatient();
						p.setProjectId(projectId);
						p.setPatientId(pinput.getPatientId());
						p.setPatientCode(pinput.getId());
						p.setLccCode(pinput.getLccCode());

						p.setBirthday(pinput.getBirthday());
						// p.setBloodCode(patient.get);
						p.setCreateDate(new Date());
						p.setMobile(pinput.getMobile());
						p.setIdNumber(pinput.getIdNumber());
						p.setNation(pinput.getNation());
						p.setPatientName(pinput.getPatientName());
						p.setPhone(pinput.getPhone());
						p.setSex(pinput.getSex());

						p.setIsFollowrisk(pinput.getIsFollowrisk());
						p.setIsFollowview(pinput.getIsFollowview());
						p.setFollowriskDate(pinput.getFollowriskDate());
						p.setFollowviewDate(pinput.getFollowviewDate());

						p.setRiskCode(pinput.getRiskCode());
						if (StringUtils.isNotBlank(pinput.getRiskCode())) {
							p.setRiskDate(new Date());
						}

						p.setCredentialsCode(pinput.getCredentialsCode());
						p.setCredentialsType(pinput.getCredentialsType());

						p.setUpdateDate(new Date());
						p.setIsSpecial(pinput.getIsSpecial());

						this.pipCommPatientMapper.insert(p);
					} else if (inputs != null && inputs.size() > 1) {
						throw new Exception("身份证号重复");
					} else {
						throw new Exception("身份证号不存在");
					}

				}
			}
		}

		this.pipCommPatientUpdateLogMapper.insert(log);
	}

	private void updatePatientId(PipCommPatient pat, String patid)
			throws Exception {
		try {

			PipCommPatientKey key = new PipCommPatientKey();
			key.setProjectId(pat.getProjectId());
			key.setPatientId(pat.getPatientId());
			this.pipCommPatientMapper.deleteByPrimaryKey(key);
			if (patid != null) {
				pat.setPatientId(patid);
				this.pipCommPatientMapper.insert(pat);
			}
			PipCommPatientInputExample example = new PipCommPatientInputExample();
			example.createCriteria().andUpdateDateIsNotNull()
					.andIdNumberEqualTo(pat.getIdNumber())
					.andPatientIdEqualTo(pat.getPatientId());
			List<PipCommPatientInput> list = this.pipCommPatientInputMapper
					.selectByExample(example);
			if (list != null && list.size() > 0) {
				for (PipCommPatientInput p : list) {
					p.setPatientId(patid);
					this.pipCommPatientInputMapper.updateByPrimaryKey(p);
				}
			}
		} catch (Exception ex) {
			throw new Exception("修改初筛码异常：" + ex.getMessage());
		}
	}

	private void updateRiskCode(PipCommPatient pat, String riskCode)
			throws Exception {
		try {

			String pid = pat.getPatientId();

			pat.setRiskCode(riskCode);
			pat.setRiskDate(new Date());
			this.pipCommPatientMapper.updateByPrimaryKey(pat);

			PipCommPatientInputExample example = new PipCommPatientInputExample();
			example.createCriteria().andUpdateDateIsNotNull()
					.andIdNumberEqualTo(pat.getIdNumber())
					.andPatientIdEqualTo(pid);
			List<PipCommPatientInput> list = this.pipCommPatientInputMapper
					.selectByExample(example);
			for (PipCommPatientInput p : list) {
				p.setRiskCode(riskCode);
				this.pipCommPatientInputMapper.updateByPrimaryKey(p);
			}
		} catch (Exception ex) {
			throw new Exception("修改初筛码异常：" + ex.getMessage());
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public void updatePatient(String projectId, String patientId,
			String targetPatId, String sourceId, String items,
			Map<String, Object> params) throws Exception {
		if (StringUtils.isBlank(patientId))
			throw new Exception("作废条码不能为空");

		PipCommPatient pat = null;
		boolean isRisk = false;
		if (patientId.substring(0, 1).equals("G")) {
			isRisk = true;
			// 高危
			PipCommPatientExample example = new PipCommPatientExample();
			example.createCriteria().andProjectIdEqualTo(projectId)
					.andRiskCodeEqualTo(patientId);
			List<PipCommPatient> list = this.pipCommPatientMapper
					.selectByExample(example);
			if (list != null && list.size() == 1) {
				pat = list.get(0);
			} else {
				throw new Exception("该条码被重复绑定，请手工处理");
			}
		} else {
			// 初筛
			PipCommPatientKey key = new PipCommPatientKey();
			key.setPatientId(patientId);
			key.setProjectId(Securitys.getUser().getCurrent_projectId());
			pat = this.pipCommPatientMapper.selectByPrimaryKey(key);
		}

		if (pat == null) {
			throw new Exception("无法作废不存在的条码");
		}

		// 检查目标条码
		PipCommPatient target = this.getPatientByKey(projectId, targetPatId);
		if (target != null) {
			throw new Exception("修改后的条码已经被使用");
		}

		// 检查来源条码
		List<PipCommPatientQcDrop> qcdroplist = null;
		List<PipCommPatientQc> qclist = null;
		if (StringUtils.isNotBlank(sourceId)) {
			qcdroplist = this.getPipCommPatientQcDropList(projectId, patientId);
			if (qcdroplist == null || qcdroplist.size() == 0) {
				qclist = this.getPipCommPatientQcList(projectId, patientId);
				if (qclist == null || qclist.size() == 0) {
					throw new Exception("来源条码的问卷信息不存在");
				}
			}
		}

		PipCommPatientDropLog log = new PipCommPatientDropLog();
		log.setPatientId(patientId);
		log.setCreateBy(Securitys.getUserName());
		log.setIdNumber(pat.getIdNumber());
		log.setCreateDate(new Date());
		log.setType("修改");
		log.setTargetId(targetPatId);
		log.setSourceId(sourceId);

		// 复制 drop
		if (items != null && items.length() > 1)
			this.updatePatientQc(projectId, patientId, pat, targetPatId,
					sourceId, items, log);

		// udpate answer
		if (items != null && items.length() > 1)
			this.updateAnswer(projectId, patientId, pat, targetPatId, sourceId,
					items);

		this.pipCommPatientDropLogMapper.insert(log);

		// 修改 patient
		if (isRisk) {
			this.updateRiskCode(projectId, patientId, pat, targetPatId);
		} else {
			this.updatePatient(projectId, patientId, pat, targetPatId);
		}

		// 修改 feeinfo
		if (StringUtils.isBlank(sourceId)) {
			this.updateFeeInfo(projectId, patientId, pat, targetPatId);
		} else {
			this.updateFeeInfo(projectId, sourceId, pat, targetPatId);
		}
		// 修改进度 qa
		if (StringUtils.isBlank(sourceId)) {
			this.updatePatientQa(projectId, patientId, pat, targetPatId);
		} else {
			this.updatePatientQa(projectId, sourceId, pat, targetPatId);
		}
		// 修改scm tube
		if (isRisk) {
			if (StringUtils.isBlank(sourceId)) {
				this.updateFrozenTube(projectId, patientId, pat, targetPatId);
			} else {
				this.updateFrozenTube(projectId, sourceId, pat, targetPatId);
			}
		}

	}

	private List<PipCommPatientQc> getPipCommPatientQcList(String projectId,
			String patientId) {
		PipCommPatientQcExample example = new PipCommPatientQcExample();
		example.createCriteria().andProjectIdEqualTo(projectId)
				.andPatientIdEqualTo(patientId);
		return this.pipCommPatientQcMapper.selectByExample(example);
	}

	private List<PipCommPatientQcDrop> getPipCommPatientQcDropList(
			String projectId, String patientId) {
		PipCommPatientQcDropExample example = new PipCommPatientQcDropExample();
		example.createCriteria().andProjectIdEqualTo(projectId)
				.andPatientIdEqualTo(patientId);
		return this.pipCommPatientQcDropMapper.selectByExample(example);
	}

	private PipCommPatient getPatientByKey(String projectId, String patientId) {
		PipCommPatient ret = null;
		if (patientId.substring(0, 1).equals("G")) {
			// 高危
			PipCommPatientExample example = new PipCommPatientExample();
			example.createCriteria().andProjectIdEqualTo(projectId)
					.andRiskCodeEqualTo(patientId);
			List<PipCommPatient> list = this.pipCommPatientMapper
					.selectByExample(example);
			if (list != null && list.size() == 1) {
				ret = list.get(0);
			}
		} else {
			// 初筛
			PipCommPatientKey key = new PipCommPatientKey();
			key.setPatientId(patientId);
			key.setProjectId(Securitys.getUser().getCurrent_projectId());
			ret = this.pipCommPatientMapper.selectByPrimaryKey(key);
		}
		return ret;
	}

	private boolean getPatientQcDrop(String projectId, String patientId) {
		PipCommPatientQcDropExample example = new PipCommPatientQcDropExample();
		example.createCriteria().andPatientIdEqualTo(patientId)
				.andProjectIdEqualTo(projectId);
		List<PipCommPatientQcDrop> list = this.pipCommPatientQcDropMapper
				.selectByExample(example);
		if (list != null && list.size() > 0) {
			return true;
		}
		return false;
	}

	private void deleteFeeInfo(String projectId, String patientId,
			PipCommPatient pat) throws Exception {
		try {
			PipFeeInfoExample qaexa = new PipFeeInfoExample();
			qaexa.createCriteria().andProjectIdEqualTo(projectId)
					.andPatientIdEqualTo(patientId);
			this.pipFeeInfoMapper.deleteByExample(qaexa);
		} catch (Exception ex) {
			throw new Exception("删除费用信息异常：" + ex.getMessage());
		}
	}

	private void updateFeeInfo(String projectId, String patientId,
			PipCommPatient pat, String targetId) throws Exception {
		try {
			PipFeeInfoExample qaexa = new PipFeeInfoExample();
			qaexa.createCriteria().andProjectIdEqualTo(projectId)
					.andPatientIdEqualTo(patientId);

			List<PipFeeInfo> list = this.pipFeeInfoMapper
					.selectByExample(qaexa);
			for (PipFeeInfo qa : list) {
				qa.setLccCode(targetId.replace("G", "").substring(0, 4));
				qa.setPatientId(targetId);
				this.pipFeeInfoMapper.insert(qa);
			}
			this.pipFeeInfoMapper.deleteByExample(qaexa);
		} catch (Exception ex) {
			throw new Exception("修改费用信息异常：" + ex.getMessage());
		}
	}

	private void deletePatientQa(String projectId, String patientId,
			PipCommPatient pat) throws Exception {
		try {
			PipCommPatientQaExample qaexa = new PipCommPatientQaExample();
			qaexa.createCriteria().andProjectIdEqualTo(projectId)
					.andPatientIdEqualTo(pat.getPatientId());
			this.pipCommPatientQaMapper.deleteByExample(qaexa);
		} catch (Exception ex) {
			throw new Exception("删除完整度信息异常：" + ex.getMessage());
		}
	}

	private void updatePatientQa(String projectId, String patientId,
			PipCommPatient pat, String targetId) throws Exception {
		try {
			PipCommPatientQaExample qaexa = new PipCommPatientQaExample();
			qaexa.createCriteria().andProjectIdEqualTo(projectId)
					.andPatientIdEqualTo(pat.getPatientId());

			List<PipCommPatientQa> list = this.pipCommPatientQaMapper
					.selectByExample(qaexa);
			for (PipCommPatientQa qa : list) {
				qa.setLccCode(targetId.replace("G", "").substring(0, 4));
				qa.setPatientId(targetId);
				this.pipCommPatientQaMapper.insert(qa);
			}
			this.pipCommPatientQaMapper.deleteByExample(qaexa);
		} catch (Exception ex) {
			throw new Exception("修改完整度信息异常：" + ex.getMessage());
		}
	}

	private void updateRiskCode(String projectId, String patientId,
			PipCommPatient pat, String targetId) throws Exception {
		try {

			pat.setRiskCode(targetId);
			this.pipCommPatientMapper.updateByPrimaryKey(pat);

			PipCommPatientInputExample example = new PipCommPatientInputExample();
			example.createCriteria().andProjectIdEqualTo(projectId)
					.andRiskCodeEqualTo(patientId);
			List<PipCommPatientInput> list = this.pipCommPatientInputMapper
					.selectByExample(example);
			for (PipCommPatientInput pi : list) {
				pi.setRiskCode(targetId);
				// pi.setPatientId(null);
				pi.setUpdateDate(new Date());
				this.pipCommPatientInputMapper.updateByPrimaryKey(pi);
			}

		} catch (Exception ex) {
			throw new Exception("删除筛查对象异常：" + ex.getMessage());
		}
	}

	private void updatePatient(String projectId, String patientId,
			PipCommPatient pat, String targetId) throws Exception {
		try {

			PipCommPatient pat2 = pat;
			// BeanUtils.copyProperties(pat2, pat);

			PipCommPatientKey pkey = new PipCommPatientKey();
			pkey.setPatientId(patientId);
			pkey.setProjectId(projectId);
			this.pipCommPatientMapper.deleteByPrimaryKey(pkey);

			pat2.setPatientId(targetId);
			this.pipCommPatientMapper.insert(pat2);

			PipCommPatientInputExample example = new PipCommPatientInputExample();
			example.createCriteria().andProjectIdEqualTo(projectId)
					.andPatientIdEqualTo(patientId);
			List<PipCommPatientInput> list = this.pipCommPatientInputMapper
					.selectByExample(example);
			for (PipCommPatientInput pi : list) {
				// pi.setRiskCode(null);
				pi.setPatientId(targetId);
				pi.setUpdateDate(new Date());
				this.pipCommPatientInputMapper.updateByPrimaryKey(pi);
			}

		} catch (Exception ex) {
			throw new Exception("删除筛查对象异常：" + ex.getMessage());
		}
	}

	private void deletePatient(String projectId, String patientId,
			PipCommPatient pat) throws Exception {
		try {
			PipCommPatientKey pkey = new PipCommPatientKey();
			pkey.setPatientId(pat.getPatientId());
			pkey.setProjectId(projectId);
			this.pipCommPatientMapper.deleteByPrimaryKey(pkey);

			PipCommPatientInputExample example = new PipCommPatientInputExample();
			example.createCriteria().andProjectIdEqualTo(projectId)
					.andPatientIdEqualTo(patientId);
			List<PipCommPatientInput> list = this.pipCommPatientInputMapper
					.selectByExample(example);
			for (PipCommPatientInput pi : list) {
				pi.setRiskCode(null);
				pi.setPatientId(null);
				pi.setUpdateDate(new Date());
				this.pipCommPatientInputMapper.updateByPrimaryKey(pi);
			}

		} catch (Exception ex) {
			throw new Exception("删除筛查对象异常：" + ex.getMessage());
		}
	}

	private void removeRiskCode(String projectId, String patientId,
			PipCommPatient pat) throws Exception {
		try {
			pat.setRiskCode(null);
			pat.setRiskDate(null);
			this.pipCommPatientMapper.updateByPrimaryKey(pat);

			PipCommPatientInputExample example = new PipCommPatientInputExample();
			example.createCriteria().andProjectIdEqualTo(projectId)
					.andRiskCodeEqualTo(patientId);

			List<PipCommPatientInput> list = this.pipCommPatientInputMapper
					.selectByExample(example);
			for (PipCommPatientInput pi : list) {
				pi.setRiskCode(null);
				// pi.setPatientId(null);
				pi.setUpdateDate(new Date());
				this.pipCommPatientInputMapper.updateByPrimaryKey(pi);
			}
		} catch (Exception ex) {
			throw new Exception("删除高危条码异常：" + ex.getMessage());
		}
	}

	private void updatePatientQc(String projectId, String patientId,
			PipCommPatient pat, String targetId, String sourceId, String items,
			PipCommPatientDropLog log) throws Exception {
		try {

			List<PipCommPatientQc> qlist = null;
			PipCommPatientQcExample qex = new PipCommPatientQcExample();
			if (StringUtils.isNotBlank(sourceId)) {
				qex.createCriteria().andProjectIdEqualTo(projectId)
						.andPatientIdEqualTo(sourceId);
				qlist = this.pipCommPatientQcMapper.selectByExample(qex);

				this.movePatientQc(projectId, patientId, pat, log);
			} else {
				qex.createCriteria().andProjectIdEqualTo(projectId)
						.andPatientIdEqualTo(patientId);

				qlist = this.pipCommPatientQcMapper.selectByExample(qex);
			}
			if (qlist == null || qlist.size() == 0) {

				List<PipCommPatientQcDrop> dlist = null;
				PipCommPatientQcDropExample dex = new PipCommPatientQcDropExample();
				if (StringUtils.isNotBlank(sourceId)) {
					dex.createCriteria().andProjectIdEqualTo(projectId)
							.andPatientIdEqualTo(sourceId);
				} else {
					dex.createCriteria().andProjectIdEqualTo(projectId)
							.andPatientIdEqualTo(patientId);
				}
				dlist = this.pipCommPatientQcDropMapper.selectByExample(dex);
				if (dlist == null || dlist.size() == 0)
					throw new Exception("复制内容来源条码没有找到对应的问卷");

				StringBuffer sb = new StringBuffer();
				for (PipCommPatientQcDrop dqc : dlist) {
					if (items.indexOf("," + dqc.getItemCode() + "-") >= 0) {
						PipCommPatientQc qc = new PipCommPatientQc();
						BeanUtils.copyProperties(qc, dqc);

						sb.append(itemMap.get(qc.getItemCode()));
						sb.append(",");

						this.moveFile(projectId, targetId, qc);

						qc.setLccCode(targetId.replace("G", "").substring(0, 4));
						qc.setPatientId(targetId);
						this.pipCommPatientQcMapper.insert(qc);

					}
				}

			} else {
				StringBuffer sb = new StringBuffer();
				for (PipCommPatientQc qc : qlist) {
					if (items.indexOf("," + qc.getItemCode() + "-") >= 0) {
						if (StringUtils.isBlank(sourceId)) {
							PipCommPatientQcDrop qcdrop = new PipCommPatientQcDrop();
							BeanUtils.copyProperties(qcdrop, qc);
							qcdrop.setDropDate(new Date());
							qcdrop.setDropNum(new Short("0"));
							qcdrop.setOperatorName(Securitys.getUserName());
							this.pipCommPatientQcDropMapper.insert(qcdrop);
						}
						sb.append(itemMap.get(qc.getItemCode()));
						sb.append(",");

						this.moveFile(projectId, targetId, qc);
						qc.setLccCode(targetId.replace("G", "").substring(0, 4));
						qc.setPatientId(targetId);
						this.pipCommPatientQcMapper.insert(qc);

					}
				}

				log.setItems(sb.toString());
			}
			if (StringUtils.isBlank(sourceId)) {
				this.pipCommPatientQcMapper.deleteByExample(qex);
			}
		} catch (Exception ex) {
			throw new Exception("移动问卷：" + ex.getMessage());
		}
	}

	private void moveFile(String projectId, String targetId, PipCommPatientQc qc)
			throws Exception {
		String zippath = qc.getUqsZipfile();
		if (zippath.indexOf(projectId) == 1) {
			zippath = "/var/put" + zippath;
		}

		ZipInputStream zis = new ZipInputStream(new FileInputStream(zippath));
		ZipEntry entry = zis.getNextEntry();
		// List<BufferedInputStream> blist = new
		// ArrayList<BufferedInputStream>();
		while (entry != null) {
			if (entry.getName().equals(qc.getUqsFile())) {
				ByteArrayOutputStream output = new ByteArrayOutputStream();
				byte[] buffer = new byte[4096];
				int n = 0;
				while (-1 != (n = zis.read(buffer))) {
					output.write(buffer, 0, n);
				}

				SAXReader saxReader = new SAXReader();
				Document document = null;
				byte[] enput = output.toByteArray();
				try {
					document = saxReader.read(new ByteArrayInputStream(enput));
				} catch (Exception ex) {
					enput = CryptoUtil.decrypt(
							qc.getPatientId().replace("G", "").substring(0, 4),
							new String(enput, "UTF-8")).getBytes("UTF-8");
					document = saxReader.read(new ByteArrayInputStream(enput));
				}

				document.getRootElement().element("doctype")
						.setAttributeValue("PatientCode", targetId);
				String path = qc.getUqsZipfile().substring(0,
						qc.getUqsZipfile().indexOf("UQS") + 4)
						+ "update/";

				String txt = CryptoUtil.encrypt(targetId.replace("G", "")
						.substring(0, 4), document.asXML());

				File file = new File(path + qc.getUqsFile());
				FileUtils.write(file, txt, "UTF-8");
				List<File> files = new ArrayList<File>();
				files.add(file);
				String zipfile = FileUtils.zipFiles(files, path);
				qc.setUqsZipfile(path + zipfile);
				break;
			}
			entry = zis.getNextEntry();
		}
	}

	private void moveFile(String projectId, String targetId,
			PipCommPatientQcDrop qc) throws Exception {
		String zippath = qc.getUqsZipfile();
		if (zippath.indexOf(projectId) == 1) {
			zippath = "/var/put" + zippath;
		}

		ZipInputStream zis = new ZipInputStream(new FileInputStream(zippath));
		ZipEntry entry = zis.getNextEntry();
		// List<BufferedInputStream> blist = new
		// ArrayList<BufferedInputStream>();
		while (entry != null) {
			if (entry.getName().equals(qc.getUqsFile())) {
				ByteArrayOutputStream output = new ByteArrayOutputStream();
				byte[] buffer = new byte[4096];
				int n = 0;
				while (-1 != (n = zis.read(buffer))) {
					output.write(buffer, 0, n);
				}

				SAXReader saxReader = new SAXReader();
				Document document = null;
				byte[] enput = output.toByteArray();
				try {
					document = saxReader.read(new ByteArrayInputStream(enput));
				} catch (Exception ex) {
					enput = CryptoUtil.decrypt(
							qc.getPatientId().replace("G", "").substring(0, 4),
							new String(enput, "UTF-8")).getBytes("UTF-8");
					document = saxReader.read(new ByteArrayInputStream(enput));
				}

				document.getRootElement().element("doctype")
						.setAttributeValue("PatientCode", targetId);
				String path = qc.getUqsZipfile().substring(0,
						qc.getUqsZipfile().indexOf("UQS") + 4)
						+ "update/";

				String txt = CryptoUtil.encrypt(targetId.replace("G", "")
						.substring(0, 4), document.asXML());

				File file = new File(path + qc.getUqsFile());
				FileUtils.write(file, txt, "UTF-8");
				List<File> files = new ArrayList<File>();
				files.add(file);
				String zipfile = FileUtils.zipFiles(files, path);
				qc.setUqsZipfile(path + zipfile);
				break;
			}
			entry = zis.getNextEntry();
		}
	}

	private void deletePatientQc(String projectId, String lccCode,
			String patientId, String itemCode, Date d) throws Exception {
		try {
			PipCommPatientQcKey key = new PipCommPatientQcKey();
			key.setProjectId(projectId);
			key.setItemCode(itemCode);
			key.setPatientId(patientId);
			key.setLccCode(lccCode);
			PipCommPatientQc qc = this.pipCommPatientQcMapper
					.selectByPrimaryKey(key);
			if (qc != null) {
				PipCommPatientQcDrop qcdrop = new PipCommPatientQcDrop();
				BeanUtils.copyProperties(qcdrop, qc);
				qcdrop.setDropDate(d);
				qcdrop.setOperatorName(Securitys.getUserName());
				qcdrop.setDropNum(new Short("0"));
				this.pipCommPatientQcDropMapper.insert(qcdrop);

				this.pipCommPatientQcMapper.deleteByPrimaryKey(key);

			} else {
				// throw new Exception("该问卷不存在");
			}
		} catch (Exception ex) {
			throw new Exception(ex.getMessage());
		}
	}

	private PipCommPatientQc copyPatientQc(String projectId, String lccCode,
			String patientId, String sourceId, String itemCode)
			throws Exception {
		try {

			PipCommPatientQcKey key = new PipCommPatientQcKey();
			key.setProjectId(projectId);
			key.setItemCode(itemCode);
			key.setPatientId(sourceId);
			key.setLccCode(lccCode);
			PipCommPatientQc qc = this.pipCommPatientQcMapper
					.selectByPrimaryKey(key);

			if (qc == null)
				throw new Exception("数据来源条码对应的问卷信息不存在");

			this.moveFile(projectId, patientId, qc);

			qc.setLccCode(patientId.replace("G", "").substring(0, 4));
			qc.setPatientId(patientId);

			this.pipCommPatientQcMapper.insert(qc);
			return qc;
		} catch (Exception ex) {
			throw new Exception(ex.getMessage());
		}
	}

	private PipCommPatientQcDrop copyPatientQcDrop(String projectId,
			String lccCode, String patientId, String sourceId, String itemCode,
			String dropDate) throws Exception {
		try {

			PipCommPatientQcDropKey key = new PipCommPatientQcDropKey();
			key.setProjectId(projectId);
			key.setItemCode(itemCode);
			key.setPatientId(sourceId);
			key.setLccCode(lccCode);
			Date d = new Date();
			d.setTime(Long.valueOf(dropDate));
			key.setDropDate(d);
			PipCommPatientQcDrop qc = this.pipCommPatientQcDropMapper
					.selectByPrimaryKey(key);

			if (qc == null)
				throw new Exception("数据来源条码对应的问卷信息不存在");

			this.moveFile(projectId, patientId, qc);
			PipCommPatientQc q = new PipCommPatientQc();

			BeanUtils.copyProperties(q, qc);
			q.setLccCode(patientId.replace("G", "").substring(0, 4));
			q.setPatientId(patientId);

			this.pipCommPatientQcMapper.insert(q);
			return qc;
		} catch (Exception ex) {
			throw new Exception(ex.getMessage());
		}
	}

	private void copyAnswer(String projectId, String lccCode, String patientId,
			String sourceId, String itemCode) throws Exception {
		try {
			UqsAnswerExample uexa = new UqsAnswerExample();
			uexa.createCriteria().andQuestionnaireIdEqualTo(itemCode)
					.andProjectIdEqualTo(projectId)
					.andPatientIdEqualTo(sourceId);
			List<UqsAnswer> ulist = this.uqsAnswerMapper.selectByExample(uexa);
			for (UqsAnswer u : ulist) {
				UqsAnswer ud = new UqsAnswer();
				BeanUtils.copyProperties(ud, u);
				ud.setPatientId(patientId);
				this.uqsAnswerMapper.insert(ud);
			}

			// this.uqsAnswerMapper.deleteByExample(uexa);

		} catch (Exception ex) {
			throw new Exception("移动问卷答案异常：" + ex.getMessage());
		}
	}

	private void copyAnswerDrop(String projectId, String lccCode,
			String patientId, String sourceId, String itemCode, String dropDate)
			throws Exception {
		try {
			Date d = new Date();
			d.setTime(Long.valueOf(dropDate));
			UqsAnswerDropExample uexa = new UqsAnswerDropExample();
			uexa.createCriteria().andQuestionnaireIdEqualTo(itemCode)
					.andProjectIdEqualTo(projectId).andDropDateEqualTo(d)
					.andPatientIdEqualTo(sourceId);
			List<UqsAnswerDrop> ulist = this.uqsAnswerDropMapper
					.selectByExample(uexa);
			for (UqsAnswerDrop u : ulist) {
				UqsAnswer ud = new UqsAnswer();
				BeanUtils.copyProperties(ud, u);
				ud.setPatientId(patientId);
				this.uqsAnswerMapper.insert(ud);
			}

			// this.uqsAnswerMapper.deleteByExample(uexa);

		} catch (Exception ex) {
			throw new Exception("移动问卷答案异常：" + ex.getMessage());
		}
	}

	private void movePatientQc(String projectId, String patientId,
			PipCommPatient pat, PipCommPatientDropLog log) throws Exception {
		try {
			PipCommPatientQcExample qex = new PipCommPatientQcExample();
			qex.createCriteria().andProjectIdEqualTo(projectId)
					.andPatientIdEqualTo(patientId);
			List<PipCommPatientQc> qlist = this.pipCommPatientQcMapper
					.selectByExample(qex);
			StringBuffer sb = new StringBuffer();
			for (PipCommPatientQc qc : qlist) {
				PipCommPatientQcDrop qcdrop = new PipCommPatientQcDrop();
				BeanUtils.copyProperties(qcdrop, qc);
				qcdrop.setDropDate(new Date());
				qcdrop.setOperatorName(Securitys.getUserName());
				qcdrop.setDropNum(new Short("0"));
				this.pipCommPatientQcDropMapper.insert(qcdrop);
				/*
				 * PipCommPatientQcKey qkey = new PipCommPatientQcKey();
				 * qkey.setItemCode(qc.getItemCode());
				 * qkey.setLccCode(qc.getLccCode());
				 * qkey.setPatientId(qc.getPatientId());
				 * qkey.setProjectId(qc.getProjectId());
				 * this.pipCommPatientQcMapper.deleteByPrimaryKey(qkey);
				 */
				sb.append(itemMap.get(qc.getItemCode()));
				sb.append(",");
			}
			log.setItems(sb.toString());
			this.pipCommPatientQcMapper.deleteByExample(qex);
		} catch (Exception ex) {
			throw new Exception("移动问卷：" + ex.getMessage());
		}
	}

	private void moveAnswer(String projectId, String patientId,
			String itemCode, Date d) throws Exception {
		try {
			UqsAnswerExample uexa = new UqsAnswerExample();
			uexa.createCriteria().andQuestionnaireIdEqualTo(itemCode)
					.andProjectIdEqualTo(projectId)
					.andPatientIdEqualTo(patientId);
			List<UqsAnswer> ulist = this.uqsAnswerMapper.selectByExample(uexa);
			for (UqsAnswer u : ulist) {
				UqsAnswerDrop ud = new UqsAnswerDrop();
				BeanUtils.copyProperties(ud, u);
				ud.setDropDate(d);
				this.uqsAnswerDropMapper.insert(ud);
			}

			this.uqsAnswerMapper.deleteByExample(uexa);

		} catch (Exception ex) {
			throw new Exception("移动问卷答案异常：" + ex.getMessage());
		}
	}

	private void moveAnswer(String projectId, String patientId,
			PipCommPatient pat) throws Exception {
		try {
			UqsAnswerExample uexa = new UqsAnswerExample();
			uexa.createCriteria().andProjectIdEqualTo(projectId)
					.andPatientIdEqualTo(patientId);
			List<UqsAnswer> ulist = this.uqsAnswerMapper.selectByExample(uexa);
			for (UqsAnswer u : ulist) {
				UqsAnswerDrop ud = new UqsAnswerDrop();
				BeanUtils.copyProperties(ud, u);
				ud.setDropDate(new Date());
				this.uqsAnswerDropMapper.insert(ud);
			}

			this.uqsAnswerMapper.deleteByExample(uexa);

		} catch (Exception ex) {
			throw new Exception("移动问卷答案异常：" + ex.getMessage());
		}
	}

	private void updateAnswer(String projectId, String patientId,
			PipCommPatient pat, String targetId, String sourceId, String items)
			throws Exception {
		try {
			UqsAnswerExample uexa = new UqsAnswerExample();
			if (StringUtils.isNotBlank(sourceId)) {
				uexa.createCriteria().andProjectIdEqualTo(projectId)
						.andPatientIdEqualTo(sourceId);
			} else {
				uexa.createCriteria().andProjectIdEqualTo(projectId)
						.andPatientIdEqualTo(patientId);
			}
			List<UqsAnswer> ulist = this.uqsAnswerMapper.selectByExample(uexa);
			for (UqsAnswer u : ulist) {
				if (items.indexOf("," + u.getQuestionnaireId() + "-") >= 0) {
					if (StringUtils.isBlank(sourceId)) {
						UqsAnswerDrop ud = new UqsAnswerDrop();
						BeanUtils.copyProperties(ud, u);
						ud.setDropDate(new Date());
						this.uqsAnswerDropMapper.insert(ud);
					}

					u.setPatientId(targetId);
					this.uqsAnswerMapper.insert(u);

					/*
					 * UqsAnswerKey ukey = new UqsAnswerKey();
					 * BeanUtils.copyProperties(ukey, u);
					 * this.uqsAnswerMapper.deleteByPrimaryKey(ukey);
					 */
				}
			}
			if (StringUtils.isBlank(sourceId)) {
				this.uqsAnswerMapper.deleteByExample(uexa);
			}
		} catch (Exception ex) {
			throw new Exception("移动问卷答案异常：" + ex.getMessage());
		}
	}

	private void updateFrozenTube(String projectId, String patientId,
			PipCommPatient pat, String targetId) throws Exception {
		try {
			PipScmFrozenTubeExample example = new PipScmFrozenTubeExample();
			example.createCriteria().andProjectIdEqualTo(projectId)
					.andBloodCodeEqualTo(patientId);
			List<PipScmFrozenTube> list = this.pipScmFrozenTubeMapper
					.selectByExample(example);
			this.pipScmFrozenTubeMapper.deleteByExample(example);
			for (PipScmFrozenTube t : list) {
				t.setBloodCode(targetId);
				this.pipScmFrozenTubeMapper.insert(t);
			}

		} catch (Exception ex) {
			throw new Exception("修改冻存管数据异常：" + ex.getMessage());
		}
	}

	private void emptyFrozenTube(String projectId, String patientId,
			PipCommPatient pat) throws Exception {
		try {
			PipScmFrozenTubeExample example = new PipScmFrozenTubeExample();
			example.createCriteria().andProjectIdEqualTo(projectId)
					.andBloodCodeEqualTo(patientId);
			List<PipScmFrozenTube> list = this.pipScmFrozenTubeMapper
					.selectByExample(example);
			this.pipScmFrozenTubeMapper.deleteByExample(example);
			for (PipScmFrozenTube t : list) {
				t.setBloodCode("empty");
				this.pipScmFrozenTubeMapper.insert(t);
			}

		} catch (Exception ex) {
			throw new Exception("修改冻存管数据异常：" + ex.getMessage());
		}
	}

	@Override
	public void exchangePatient(String projectId, String patientId,
			String patientId2) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public List<PipCommPatient> queryPatientList(PatientFilter pf) {
		return pipCommPatientMapper.queryPatientList(pf);
	}

	@Override
	public void updateByPatientList(PatientFilter pf) {
		pipCommPatientMapper.updateByPatientList(pf);
	}

	@Override
	public int updateByPrimaryKey(PipCommPatient record) {
		return pipCommPatientMapper.updateByPrimaryKey(record);

	}
	@Override
	public int updateBySelevePrimaryKey(PipCommPatient record) {
		return pipCommPatientMapper.updateByPrimaryKeySelective(record);

	}
	@Override
	public int updateByPatientId(PipCommPatient record) {
		return pipCommPatientMapper.updateByPatientId(record);
	}

	public int insertSelective(PipCommPatient record) {
		return pipCommPatientMapper.insertSelective(record);
	}

	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * 
	 * @see com.bdcor.pip.web.data.service.PatientService#selectByKey(java.lang.String)
	 */
	@Override
	public PipCommPatient selectByKey(String patientId) {
		PipCommPatientKey key = new PipCommPatientKey();
		key.setPatientId(patientId);
		key.setProjectId(Securitys.getUser().getCurrent_projectId());
		PipCommPatient pipCommPatient = pipCommPatientMapper
				.selectByPrimaryKey(key);
		if (null != pipCommPatient) {
			pipCommPatient.setFollowviewDateStr(DateUtil.dateToString(
					pipCommPatient.getFollowviewDate(), "yyyy-MM-dd"));
		}
		return pipCommPatient;
	}
}
