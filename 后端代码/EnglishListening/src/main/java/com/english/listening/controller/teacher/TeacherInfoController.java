package com.english.listening.controller.teacher;

import com.english.listening.common.RedisService;
import com.english.listening.exception.CheckException;
import com.english.listening.mapper.*;
import com.english.listening.pojo.*;
import com.english.listening.result.ResultBean;
import com.english.listening.utils.JsonUtil;
import com.english.listening.utils.RequestAndResponseUtils;
import com.english.listening.vo.OralLanguageHomeWorkVO;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @ClassName TeacherInfoController
 * @Description
 * @Author CCJ
 * @Date 2018/12/31 22:20
 **/
@RestController
@CrossOrigin("*")
@RequestMapping("/api/teacher")
public class TeacherInfoController {

    @Autowired
    private RedisService redisService;

    @Autowired
    private StudentClassInfoMapper studentClassInfoMapper;

    @Autowired
    private StudentInfoMapper studentInfoMapper;

    @Autowired
    private HomeworkMapper homeworkMapper;

    @Autowired
    private ClassHomeWorkMapper classHomeWorkMapper;

    @Autowired
    private ClassInfoMapper classInfoMapper;

    @Autowired
    private OralLanguageMapper oralLanguageMapper;

    @Autowired
    private ListeningSubjectMapper listeningSubjectMapper;

    @Autowired
    private TeacherInfoMapper teacherInfoMapper;


    @GetMapping("/getInfo")
    public ResultBean getInfo(){
        String token =  RequestAndResponseUtils.getRequest().getHeader("token");
        if(StringUtils.isEmpty(token)){
            throw new CheckException("token为空");
        }
        TeacherInfo teacherInfo = JsonUtil.jsonToObject(redisService.get(token),TeacherInfo.class);
        teacherInfo.setTeacherPassword(null);
        return new ResultBean(teacherInfo);
    }

    @GetMapping("/getStudents")
    public ResultBean getStudents(@RequestParam("classId")Integer classId){
        String token = RequestAndResponseUtils.getRequest().getHeader("token");
        TeacherInfo teacherInfo =  JsonUtil.jsonToObject(redisService.get(token),TeacherInfo.class);
        List<StudentClassInfo> studentClassInfoList =  studentClassInfoMapper.selectAllByClassIdAndTeacherId(classId,teacherInfo.getTeacherId());
        Set<Integer> studentNumberSet =  studentClassInfoList.stream().map(e->e.getStudentId()).collect(Collectors.toSet());
        if (studentNumberSet.size() == 0){
            throw new CheckException("该班级学生数量为空");
        }
        List<StudentInfo> studentInfoList =  studentInfoMapper.selectAllByIdList(new ArrayList(studentNumberSet));
        if (studentInfoList.size() <= 0){
            throw new CheckException("该班级学生数量为空");
        }
        studentInfoList.forEach(e->e.setStudentPassword(null));
        return new ResultBean(studentInfoList);
    }

    @GetMapping("/getClassAll")
    public ResultBean getClassAll(){
        String token =  RequestAndResponseUtils.getRequest().getHeader("token");
        TeacherInfo teacherInfo = JsonUtil.jsonToObject(redisService.get(token),TeacherInfo.class);
        List<StudentClassInfo> studentClassInfoList = studentClassInfoMapper.selectAllByTeacherId(teacherInfo.getTeacherId());
        if (studentClassInfoList.isEmpty()){
            throw new CheckException("没有班级信息");
        }
        List<ClassInfo> classInfoList =  classInfoMapper.selectByClassIdList(studentClassInfoList.stream().map(e->e.getClassId()).collect(Collectors.toList()));
        return new ResultBean(classInfoList,classInfoList.size());
    }

    @GetMapping("/getStudentHomeWork")
    public ResultBean getStudentHomeWork(@RequestParam("classId")Integer classId,
                                         @RequestParam("homeWorkType")Integer homeWorkType){
        List<OralLanguageHomeWorkVO> oralLanguageHomeWorkVOList = classHomeWorkMapper.selectOralLanguageHomeWorkByClass(classId,homeWorkType);
        return new ResultBean(oralLanguageHomeWorkVOList,oralLanguageHomeWorkVOList.size());
    }

    @PostMapping("/addOralLanguage")
    public ResultBean addOralLanguage(@RequestParam("oralLanguageText") String text){

        if (StringUtils.isEmpty(text)){
            throw new CheckException("参数为空");
        }

        if (oralLanguageMapper.selectOralLanguageByText(text) != null){
            throw new CheckException("听力文本已存在,请更换一个");
        }

        if (oralLanguageMapper.addOraLanguage(text) >0 ){
            return new ResultBean(0,"添加成功");
        }
        return new ResultBean(1,"添加失败");
    }

    @PostMapping("/addOralLanguageWork")
    public ResultBean addOralLanguageWork(@RequestParam("oralLanguageId") Integer oralLanguageId,
                                          @RequestParam("classId")Integer classId){
        ClassHomeWork classHomeWork = new ClassHomeWork();
        classHomeWork.setHomeworkType(0);
        classHomeWork.setOralLanguageId(oralLanguageId);
        classHomeWork.setClassId(classId);
        if (oralLanguageMapper.selectById(oralLanguageId) == null){
            throw new CheckException("听力题目不存在");
        }
        if (classHomeWorkMapper.addClassHomeWork(classHomeWork) > 0){
            return new ResultBean(0,"添加成功");
        }
        return new ResultBean(0,"添加失败");
    }

    @GetMapping("/getOralLanguageWork")
    public ResultBean getOralLanguageWork(){
        List<OralLanguage> oralLanguageList =  oralLanguageMapper.selectAll();
        return new ResultBean(oralLanguageList,oralLanguageList.size());
    }

    @PostMapping("/addClassWork")
    public ResultBean addClassWork(@RequestParam("classId")Integer classId,
                                   @RequestParam("num")Integer num){
        if (num <=0){
            throw new CheckException("作业题目不能为0");
        }
        ClassHomeWork classHomeWork = new ClassHomeWork();
        classHomeWork.setClassId(classId);
        classHomeWork.setHomeworkType(1);
        classHomeWork.setNum(num);
        if(classHomeWorkMapper.addClassHomeWork(classHomeWork) >0){
            return new ResultBean(0,"添加成功");
        }
        return new ResultBean(0,"添加失败");
    }

    @PostMapping("/updatePwd")
    public ResultBean updatePwd(@RequestParam("pwd1")String pwd1,
                                @RequestParam("pwd2")String pwd2){
        String token =  RequestAndResponseUtils.getRequest().getHeader("token");
        TeacherInfo teacherInfo = JsonUtil.jsonToObject(redisService.get(token),TeacherInfo.class);
        if (teacherInfo == null){
            throw new CheckException("教师不存在");
        }
        if (teacherInfo.getTeacherPassword().equals(pwd1)){
            if (teacherInfoMapper.updatePwd(teacherInfo.getTeacherNumber(),pwd2) > 0){
                return new ResultBean(0,"修改密码成功");
            }
        }else{
            return new ResultBean(0,"原密码错误");
        }
        return new ResultBean(0,"修改密码失败");
    }

    @GetMapping("/dataOut")
    public void dataOut(HttpServletResponse response,@RequestParam("classId")Integer classId) throws IOException{
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("信息表");
        HSSFCellStyle setBorder = workbook.createCellStyle();
        //居中
        setBorder.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        setBorder.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        setBorder.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        setBorder.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        setBorder.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
        HSSFFont font = workbook.createFont();
        //加粗
        font.setFontName("Arial");
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        //设置字体大小
        font.setFontHeightInPoints((short) 12);
        setBorder.setFont(font);
        sheet.setColumnWidth(0, 5800);
        sheet.setColumnWidth(1, 3000);
        sheet.setColumnWidth(2, 2000);
        sheet.setColumnWidth(3, 8000);
        sheet.setColumnWidth(4, 8000);
        sheet.setColumnWidth(5, 5000);

        List<OralLanguageHomeWorkVO> oralLanguageHomeWorkVOList =  classHomeWorkMapper.selectOralLanguageHomeWorkByClass(classId,0);
        //设置要导出的文件的名字
        String fileName = "readName.xls";
        //新增数据行，并且设置单元格数据
        int rowNum = 1;
        String[] headers = {"学号", "姓名", "性别", "朗读文本", "朗读路径", "状态"};
        //headers表示excel表中第一行的表头
        HSSFRow row = sheet.createRow(0);
        //在excel表中添加表头
        for (int i = 0; i < headers.length; i++) {
            HSSFCell cell = row.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
            cell.setCellStyle(setBorder);
        }
        for (OralLanguageHomeWorkVO oralLanguageHomeWorkVO : oralLanguageHomeWorkVOList) {
            getCell(sheet, rowNum, 0, oralLanguageHomeWorkVO.getSno(), setBorder);
            getCell(sheet, rowNum, 1, oralLanguageHomeWorkVO.getName(), setBorder);
            getCell(sheet, rowNum, 2, oralLanguageHomeWorkVO.getSex() == 0 ? "男" : "女", setBorder);
            getCell(sheet, rowNum, 3, oralLanguageHomeWorkVO.getOralLanguageText(), setBorder);
            getCell(sheet, rowNum, 4, oralLanguageHomeWorkVO.getHomeWorkPath(), setBorder);
            getCell(sheet, rowNum, 5, (oralLanguageHomeWorkVO.getStatus() == 0) ? "未完成" : "已完成", setBorder);
            //设置格式
            rowNum++;
        }
        CellRangeAddress region = new CellRangeAddress(rowNum, rowNum, (short) 1, (short) 5);
        //合并格子
        sheet.addMergedRegion(region);
        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        response.flushBuffer();
        workbook.write(response.getOutputStream());
    }

    /**
     * 方法封装。。别问我为什么。。我懒
     * Gentle
     *
     * @param sheet     哪一页
     * @param row       行
     * @param col       列
     * @param content   要塞进去去的值
     * @param cellStyle 显示的文本格式
     * @return Gentle 编写
     */
    protected HSSFCell getCell(HSSFSheet sheet, int row, int col, String content, HSSFCellStyle cellStyle) {
        HSSFRow sheetRow = sheet.getRow(row);
        if (sheetRow == null) {
            sheetRow = sheet.createRow(row);
        }

        HSSFCell cell = sheetRow.getCell(col);
        if (cell == null) {
            cell = sheetRow.createCell(col);
        }
        cell.setCellStyle(cellStyle);
        cell.setCellValue(content);

        return cell;
    }

}
