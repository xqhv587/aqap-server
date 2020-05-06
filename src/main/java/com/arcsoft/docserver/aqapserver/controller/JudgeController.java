package com.arcsoft.docserver.aqapserver.controller;

import com.arcsoft.docserver.aqapserver.entity.model.ResponseBean;
import com.arcsoft.docserver.aqapserver.entity.model.SwaggerConstant;
import com.arcsoft.docserver.aqapserver.entity.pojo.*;
import com.arcsoft.docserver.aqapserver.entity.pojo.Dictionary;
import com.arcsoft.docserver.aqapserver.entity.vo.JudgeListVo;
import com.arcsoft.docserver.aqapserver.entity.vo.JudgeVo;
import com.arcsoft.docserver.aqapserver.entity.vo.UserInfoVo;
import com.arcsoft.docserver.aqapserver.service.*;
import com.arcsoft.docserver.aqapserver.util.ChineseAndEnglish;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
/**
 * @author xqh3622
 */
@RestController
public class JudgeController {
    @Autowired
    private UserService userService;
    @Autowired
    private TeamService teamService;
    @Autowired
    private ScoreService scoreService;
    @Autowired
    private DictionaryService dictionaryService;
    @Autowired
    private FeedbackService feedbackService;
    @Autowired
    private JudgeSelectService judgeSelectService;
    @Autowired
    private DictionarySelectService dictionarySelectService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Value("${AQAP.functionId}")
    private String functionId;

    @ApiOperation(value = "获取用户详情列表")
    @ApiResponses({
            @ApiResponse(code = 200, message = "true"),
            @ApiResponse(code = 400, message = "参数错误"),
            @ApiResponse(code = 404, message = "Judge不存在"),
            @ApiResponse(code = 500, message = "全局异常")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "用户名", required = false, dataType = SwaggerConstant.STRING, paramType = "query",defaultValue = "牛占斌"),
            @ApiImplicitParam(name = "team", value = "团队", required = false, dataType = SwaggerConstant.STRING, paramType = "query",defaultValue = "QA Photo Team"),
            @ApiImplicitParam(name = "levelId", value = "星级id", required = false, dataType = SwaggerConstant.INTEGER, paramType = "query",defaultValue = "2"),
            @ApiImplicitParam(name = "pageIndex", value = "当前页", required = false, dataType = SwaggerConstant.INTEGER, paramType = "query",defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页面size", required = false, dataType = SwaggerConstant.INTEGER, paramType = "query",defaultValue = "10"),
            @ApiImplicitParam(name = "sort", value = "排序字段", required = false, dataType = SwaggerConstant.STRING, paramType = "query",defaultValue = "level_id"),
            @ApiImplicitParam(name = "order", value = "排序方式", required = false, dataType = SwaggerConstant.STRING, paramType = "query",defaultValue = "asc")

    })
    @PostMapping("getJudgeList")
    public ResponseBean<JudgeListVo> getJudgeList(HttpServletRequest request ,String sort,String order,String userName, String team, Integer levelId, Integer pageIndex, Integer pageSize){
        //TODO @CookieValue("currentloginusername") Integer currentloginusername ,
        String currentloginusername = "2064";
        Cookie[] cookies =  request.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("usertoken")){
                    currentloginusername =  cookie.getValue();
                }
            }
        }else{
            System.out.println("未获取到cookie中的值");
            return  null;
        }
        System.out.println("获取到用户名"+currentloginusername);

        String test = (String) stringRedisTemplate.opsForValue().get(currentloginusername);
        System.out.println(functionId+"这是functionid");
        int permission = judgeSelectService.getPermission(functionId,test);
        //permission = 101;
        System.out.println(permission+"权限值");

        //List<Judge> list = judgeService.getJudgeList();

        if(!sort.isEmpty()){

            if(sort.equals("team")){
                sort = "b.dept_id";
            }else if(sort.equals("userName")){
                //sort = "a.user_id";
                sort = "b.real_name+'('+b.last_name+' '+b.first_name+')'";
            }else if(sort.equals("level")){
                sort = "a.level_id";
            }else if(sort.equals("updateTime")){
                sort = "a.update_time";
            }
        }else{
            sort = "b.dept_id";
        }
        if(!userName.isEmpty()){
                userName = "%"+userName+"%";
        }
        List<Judge> list = judgeSelectService.getJudgeList1(userName,team,levelId,permission,test,sort,order);
        List<Judge> listp = new ArrayList<>();
        List<Judge> list2 = new ArrayList<>();
        int total = list.size()+1;

        if(pageSize!=null){
            int cur = (pageIndex-1)*pageSize;
            if(cur<list.size()){
                if(cur+pageSize<=total) {
                    listp.addAll(list.subList(cur , cur+pageSize));
                }
                if(cur+pageSize>total){
                    listp.addAll(list.subList(cur , list.size()));
                }
            }else{
                List<JudgeVo> judgeVos = new ArrayList<>();
                JudgeListVo judgeListVo =JudgeListVo.builder().total(list.size())
                        .judgeVos(judgeVos)
                        .build();
                return  new ResponseBean<>(judgeListVo);
                //return null;
            }
        }
        if(pageIndex!=null){
            list2 = listp;
        }else{
            list2 = list;
        }

        List<JudgeVo> judgeVos = new ArrayList<>();

        for (Judge judge:list2) {

            //TODO 根据levelId查询星级名称
            //Dictionary  levelVo = dictionaryService.getLevelByDictionaryId(judge.getLevelId());
            Dictionary  levelVo = dictionarySelectService.getLevelByDictionaryId(judge.getLevelId());
            double levelNum = levelVo.getEndLevel()==1 ? levelVo.getPreLevel()+0.5 : levelVo.getPreLevel();
            String levelRemark = levelVo.getRemark();
            //levelMap.put(judge.getLevelId(),levelVo.getItemName());
            //TODO 查每个用户
            User user =  userService.getUserByUserId(judge.getUserId());
            //userNameList.add(user.getRealName());

            //TODO 查到用户组
            Team teams = teamService.getTeamByDeptId(user.getDeptId());
            //groupList.add(teams.getGroupName());

            JudgeVo judgeVo = JudgeVo.builder().team(teams.getGroupName())
                    //.userName(user.getRealName()+"("+user.getUserName()+")")
                    .userName(judge.getComPoseName())
                    .userId(judge.getUserId())
                    .level(levelVo.getItemName())
                    .levelNum(levelNum)
                    .levelRemark(levelRemark)
                    .updateTime(judge.getUpdateTime()).build();
            judgeVos.add(judgeVo);
        }
        JudgeListVo judgeListVo =JudgeListVo.builder().total(list.size())
                .judgeVos(judgeVos)
                .build();
        return new ResponseBean<>(judgeListVo);
    }

    @ApiOperation(value = "获取用户信息")
    @ApiResponses({
            @ApiResponse(code = 200, message = "true"),
            @ApiResponse(code = 400, message = "参数错误"),
            @ApiResponse(code = 404, message = "Judge不存在"),
            @ApiResponse(code = 500, message = "全局异常")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, dataType = SwaggerConstant.STRING, paramType = "query",defaultValue = "9586")
    })
    @PostMapping("getUserInfo")
    public ResponseBean<UserInfoVo> getUserInfo(String userId) {
        //TODO 根据userId查询feedback
        Feedback feedback = feedbackService.getFeedbackByUserId(userId);
        List<Score> scores= scoreService.getScoreByUserId(userId);
        //TODO 遍历scores得到scope名称
        double meanValue = 0;
        Map<String,Integer> map = new HashMap<>();
        for (Score score:scores) {
            System.out.println(score.getScope()+"能力值");
            Dictionary_l dictionary = dictionaryService.getScopeByDictionaryId(score.getDictionaryId());

            meanValue += score.getScope();
            map.put(dictionary.getItemName().trim(),score.getScope());
        }

        UserInfoVo userInfoVo = UserInfoVo.builder().feedback(feedback).map(map).meanValue(meanValue/scores.size()).build();
        return new ResponseBean<>(userInfoVo);
    }
    @ApiOperation(value = "获取group下拉框信息")
    @ApiResponses({
            @ApiResponse(code = 200, message = "true"),
            @ApiResponse(code = 400, message = "参数错误"),
            @ApiResponse(code = 404, message = "Judge不存在"),
            @ApiResponse(code = 500, message = "全局异常")
    })

    @PostMapping("getGroupSelectList")
    public ResponseBean<JudgeListVo> getGroupSelectList() {
        Set<Team> teamSet  = new HashSet<>();

        int permission = 101;
//        List<Team> teams = teamService.getTeams();
//
//
//        System.out.println(permission+"权限值");
//
//        for (Team team:teams) {
//            groupList.add(team.getGroupName());
//        }
//
//        List<Dictionary_l> levelVo = dictionaryService.getLevelList();
//        for (Dictionary_l dic:levelVo) {
//            levelMap.put(dic.getID(),dic.getItemName());
//        }
//        List<User> userlist = userService.getUserList();
//        for (User user:userlist) {
//            userNameList.add(user.getRealName());
//        }
        String sort =  "a.user_id";
        List<Judge> list = judgeSelectService.getJudgeList1("","",null,permission,"",sort,"");

        for (Judge judge:list) {

            //TODO 根据levelId查询星级名称
            //Dictionary  levelVo = dictionaryService.getLevelByDictionaryId(judge.getLevelId());
            //Dictionary  levelVo = dictionarySelectService.getLevelByDictionaryId(judge.getLevelId());

            //System.out.println(levelVo+"星级信息1");
            //TODO 查每个用户
             User user =  userService.getUserByUserId(judge.getUserId());

            //TODO 查到用户组
            Team teams = teamService.getTeamByDeptId(user.getDeptId());

            teamSet.add(teams);
        }
        JudgeListVo judgeListVo =JudgeListVo.builder()
                .groupMap(teamSet)
                .build();
        return new ResponseBean<>(judgeListVo);
    }

    @ApiOperation(value = "获取level下拉框信息")
    @ApiResponses({
            @ApiResponse(code = 200, message = "true"),
            @ApiResponse(code = 400, message = "参数错误"),
            @ApiResponse(code = 404, message = "Judge不存在"),
            @ApiResponse(code = 500, message = "全局异常")
    })
    @PostMapping("getLevelSelectList")
    public ResponseBean<JudgeListVo> getLevelSelectList() {

        List<Dictionary_l> levelVo = dictionaryService.getLevelList();

        JudgeListVo judgeListVo = JudgeListVo.builder()
                .levelMap(levelVo)
                .build();
        return new ResponseBean<>(judgeListVo);

    }



}
