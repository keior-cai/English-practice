package com.english.listening.controller.student;

import com.english.listening.constants.FileConstants;
import com.english.listening.exception.CheckException;
import com.english.listening.mapper.ClassInfoMapper;
import com.english.listening.mapper.HomeworkMapper;
import com.english.listening.mapper.ListeningSubjectMapper;
import com.english.listening.mapper.OralLanguageMapper;
import com.english.listening.pojo.*;
import com.english.listening.result.AudioBean;
import com.english.listening.result.ResultBean;
import com.english.listening.utils.*;
import com.english.listening.vo.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;


/**
 * @Description:
 * @Author: Gentle
 * @date 2018/12/31 13:04
 */
@RequestMapping(value = "/api/student/")
@CrossOrigin("*")
@RestController
public class HomeworkController {


    @Autowired
    HomeworkMapper homeworkMapper;
    @Autowired
    ListeningSubjectMapper listeningSubjectMapper;
    @Autowired
    ClassInfoMapper classInfoMapper;

    @Autowired
    OralLanguageMapper oralLanguageMapper;

    /**
     * 获取随机10道听力题目
     *
     * @return
     */
    @GetMapping(value = "getListeningSubjectProblem")
    public ResultBean<List<ListeningSubject>> getTenProblem() {
        return new ResultBean<>(listeningSubjectMapper.selectRandomListeningSubject(10));
    }

    /**
     * 查看班级作业
     *
     * @return
     */
    @GetMapping(value = "selectClassHomeWork")
    public ResultBean<AllHomeWrokVo> selectClassHomeWork() {
        StudentInfo studentInfo = (StudentInfo) RequestAndResponseUtils.getRequest().getAttribute("studentInfo");

        StudentClassInfo studentClassInfo = classInfoMapper.selectStudentClassInfoByStudentId(studentInfo.getStudentId());

        List<ClassHomeWork> classHomeWorks = homeworkMapper.selectClassHomeWorkByClassId(studentClassInfo.getClassId());
        if (classHomeWorks == null || classHomeWorks.isEmpty()) {
            throw new CheckException("没有找到作业");
        }

        for (ClassHomeWork classHomeWork:classHomeWorks){

            classHomeWork.getHomeworkId();

        }
        //作业总和
        AllHomeWrokVo allHomeWrokVo = new AllHomeWrokVo();
        //多个听力练习作业
        List<ListeningHomeWorkVo> listeningHomeWorkVos = new ArrayList<>();

       List<OralLanguageIdVo > languageIdVos = new ArrayList<>();

        System.out.println(classHomeWorks);
        for (ClassHomeWork classHomeWork : classHomeWorks) {

            if (classHomeWork.getHomeworkType() == 1) {
                ListeningHomeWorkVo listeningHomeWorkVo = new ListeningHomeWorkVo();
                listeningHomeWorkVo.setNum(classHomeWork.getNum());
                listeningHomeWorkVo.setHomeworkId(classHomeWork.getHomeworkId());
                listeningHomeWorkVo.setHomeworkType(classHomeWork.getHomeworkType());
                listeningHomeWorkVos.add(listeningHomeWorkVo);
            } if (classHomeWork.getHomeworkType() == 0){
                OralLanguageIdVo oralLanguageIdVo = new OralLanguageIdVo();
                oralLanguageIdVo.setHomeworkId(classHomeWork.getHomeworkId());
                oralLanguageIdVo.setHomeworkType(classHomeWork.getHomeworkType());
                OralLanguage oralLanguageByOralLanguageId = oralLanguageMapper.getOralLanguageByOralLanguageId(classHomeWork.getOralLanguageId());
                oralLanguageIdVo.setList(oralLanguageByOralLanguageId);
                languageIdVos.add(oralLanguageIdVo);
            }
        }

        allHomeWrokVo.setListeningHomeWorkVo(listeningHomeWorkVos);
        allHomeWrokVo.setOralLanguageIdVo(languageIdVos);
        return new ResultBean<>(allHomeWrokVo);
    }

    /**
     * 用户获取历史听力训练
     *
     * @return
     */
    @GetMapping(value = "getHistoryHearingTraining")
    public ResultBean<ListeningHomeWorkHistoryVo> getHistoryHearingTraining() {

        StudentInfo studentInfo = (StudentInfo) RequestAndResponseUtils.getRequest().getAttribute("studentInfo");
        System.out.println(studentInfo.getStudentId());
        List<HomeWorkHistory> homeWorkHistories = homeworkMapper.selectAllListeningHomeWorkHistoryByStudentId(studentInfo.getStudentId());
        System.out.println(homeWorkHistories);

        if (homeWorkHistories.isEmpty()) {
            throw new CheckException("没有历史听力题目");
        }

        StudentClassInfo studentClassInfo = classInfoMapper.selectStudentClassInfoByStudentId(studentInfo.getStudentId());

        List<ClassHomeWork> classHomeWorks = homeworkMapper.selectClassHomeWorkByClassId(studentClassInfo.getClassId());

        if (classHomeWorks == null || classHomeWorks.isEmpty()) {
            return new ResultBean<>();
        }

        //总的显示VO
        ListeningHomeWorkHistoryVo listeningHomeWorkHistoryVo = new ListeningHomeWorkHistoryVo();
        //临时存储集合
        List<StudentListeningSubjectHistoryVo> listeningSubjectHistoryVos = new ArrayList<>();

        StudentListeningSubjectHistoryVo studentListeningSubjectHistoryVo = null;
        //遍历所有题目
        for (HomeWorkHistory homeWorkHistory : homeWorkHistories) {
            //构建Vo
            studentListeningSubjectHistoryVo = new StudentListeningSubjectHistoryVo();
            //查询听力题目
            ListeningSubject listeningSubject = listeningSubjectMapper.selectListeningSubjectById(homeWorkHistory.getListeningId());

            BeanUtils.copyProperties(listeningSubject, studentListeningSubjectHistoryVo);
            studentListeningSubjectHistoryVo.setUserAnswer(homeWorkHistory.getUserAnswer());

            listeningSubjectHistoryVos.add(studentListeningSubjectHistoryVo);
        }
        System.out.println("listeningSubjectHistoryVos:"+listeningSubjectHistoryVos);
        //将题目加入
        listeningHomeWorkHistoryVo.setListeningSubjectHistoryVos(listeningSubjectHistoryVos);
        listeningHomeWorkHistoryVo.setTotal(homeWorkHistories.size());

        List<Integer> list = homeworkMapper.selectAllListeningHomeWorkScoreByStudentId(studentInfo.getStudentId());
        if (list==null||list.isEmpty()){
            throw new CheckException("没有作业");
        }
        System.out.println("list:"+list);
        int temp = 0;
        for (Integer l : list) {
            temp = l + temp;
        }

        //总得分
        listeningHomeWorkHistoryVo.setScope(temp * 10);

        return new ResultBean<>(listeningHomeWorkHistoryVo);
    }
    /**
     * 用户获取历史口语训练
     *
     * @return
     */
    @GetMapping(value = "getHistoryOralLanguage")
    public ResultBean<List<OralLanguageIdHistoryVo>> getHistoryOralLanguage(){

        StudentInfo studentInfo = (StudentInfo) RequestAndResponseUtils.getRequest().getAttribute("studentInfo");

        List<StudentHomeWork> studentHomeWorks = homeworkMapper.selectAllOralLanguageHomeWorkHistoryByStudentId(studentInfo.getStudentId());

        System.out.println("学生作业："+studentHomeWorks);

        if (studentHomeWorks.isEmpty()){
            return new ResultBean<>();
        }

        List<OralLanguageIdHistoryVo> list = new ArrayList<>();
        for (StudentHomeWork studentHomeWork :studentHomeWorks){
            OralLanguageIdHistoryVo oralLanguageIdHistoryVo = new OralLanguageIdHistoryVo();

            oralLanguageIdHistoryVo.setHomeWorkPath(studentHomeWork.getHomeWorkPath());
            oralLanguageIdHistoryVo.setLongText(studentHomeWork.getLongText());
            oralLanguageIdHistoryVo.setHomeWorkPath(studentHomeWork.getHomeWorkPath());
            ClassHomeWork classHomeWork = homeworkMapper.selectClassHomeWorkByHomeWorkkId(studentHomeWork.getClassHomeWorkId());
            System.out.println(classHomeWork);

            OralLanguage oralLanguageByOralLanguageId = oralLanguageMapper.getOralLanguageByOralLanguageId(classHomeWork.getOralLanguageId());
            if (oralLanguageByOralLanguageId==null){
                continue;
            }

            System.out.println(oralLanguageByOralLanguageId);

            oralLanguageIdHistoryVo.setOralLanguageId(oralLanguageByOralLanguageId.getOralLanguageId());

            oralLanguageIdHistoryVo.setHomeworkId(classHomeWork.getHomeworkId());

            oralLanguageIdHistoryVo.setOralLanguageText(oralLanguageByOralLanguageId.getOralLanguageText());
            list.add(oralLanguageIdHistoryVo);
        }
        return new ResultBean<>(list);
    }

    @PostMapping(value = "uploadOralLanguage")
    @Transactional
    public ResultBean<Integer> uploadOralLanguage(@RequestParam("file") MultipartFile file,@RequestParam(value = "homeWorkId") Integer homeWorkId){
        StudentInfo studentInfo = (StudentInfo) RequestAndResponseUtils.getRequest().getAttribute("studentInfo");
        String s = FileUpload.fileUp(file, FileConstants.DEFAULT_ROOT_PATH, UuidUtil.get32UUID());
        String pcmPath = FileConstants.DEFAULT_ROOT_PATH + "/test.pcm";
        AudioUtils.convertMP32Pcm(s, pcmPath);
        AudioBean pcm = AudioUtils.getBaiduMessage(pcmPath, "pcm");

        AudioUtils.CheckAudio(pcm);

        StudentHomeWork studentHomeWork  = new StudentHomeWork();
        studentHomeWork.setClassHomeWorkId(homeWorkId);
        studentHomeWork.setHomeWorkPath("http://39.108.125.57:8080/image/"+s.substring(s.lastIndexOf("/")+1));
        studentHomeWork.setLongText(pcm.getResult());
        studentHomeWork.setStatus(1);
        studentHomeWork.setStudentId(studentInfo.getStudentId());

        homeworkMapper.insertStudentHomework2(studentHomeWork);

        return new ResultBean<>(1);
    }


    @PostMapping(value = "/submitTopic")
    @Transactional
     public ResultBean<String> submitTopic(@RequestParam("jsonData") String jsonData){

        System.out.println("1231312");
        List<StudentListeningSubjectSubmit> list1 = JsonUtil.jsonTolist(jsonData, StudentListeningSubjectSubmit.class);
        System.out.println(list1);
        StudentInfo studentInfo = (StudentInfo) RequestAndResponseUtils.getRequest().getAttribute("studentInfo");
        if (list1==null||list1.isEmpty()){
            throw  new CheckException("提交题目不可以为空");
        }
        int score =0;
        for (StudentListeningSubjectSubmit studentListeningSubjectSubmit :list1){
            studentListeningSubjectSubmit.setStudentId(studentInfo.getStudentId());
            if(studentListeningSubjectSubmit.getAnswer().equalsIgnoreCase(studentListeningSubjectSubmit.getUserAnswer())){
                score+=10;
            }

            homeworkMapper.inserthomeWorkHistory(studentListeningSubjectSubmit);
        }

        StudentHomeWork studentHomeWork = new StudentHomeWork();
        studentHomeWork.setStudentId(studentInfo.getStudentId());
        studentHomeWork.setStatus(1);
        studentHomeWork.setClassHomeWorkId(list1.get(0).getClassHomeWorkId());
        studentHomeWork.setStudentHomeWorkScore(score);
        homeworkMapper.insertStudentHomework(studentHomeWork);

        return  new ResultBean<>(0,"作业提交成功");
     }
















}

