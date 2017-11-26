package com.maple.guideserver.controller;

import com.maple.guideserver.Common.ActType;
import com.maple.guideserver.Common.Result;
import com.maple.guideserver.dao.ScenicDao;
import com.maple.guideserver.dao.TeamDao;
import com.maple.guideserver.dao.UserDao;
import com.maple.guideserver.entity.Team;
import com.maple.guideserver.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class AllController {
    @Autowired
    private UserDao userDao;
    @Autowired
    private TeamDao teamDao;
    @Autowired
    private ScenicDao scenicDao;

    @RequestMapping(value = "/")
    @ResponseBody
    public String test(){
        return "hello world";
    }

    /**
     * 登陆
     * @param param
     * @return
     */
    @RequestMapping(value = "login",method = RequestMethod.POST)
    @ResponseBody
    public Result Login(@RequestBody ActType param){
        Result result = new Result();
        try {
            if (param.getAccount() == null || param.equals("") || param.getPassword() == null || param.getPassword().equals("")){
                result.setResult(Result.FAIL);
                result.setMessage("请输入账号密码！");
                return  result;
            }

            String account = param.getAccount();
            String password = param.getPassword();
            User user = userDao.selectUserByAccAndPw(account,password);
            if (user == null){
                result.setMessage("账号密码错误！");
                result.setResult(Result.FAIL);
                return result;
            }
            result.setResult(Result.SUCCESS);
            result.setValue(user);
            return result;
        } catch (Exception e){
            result.setResult(Result.FAIL);
            result.setMessage(e.toString());
            return result;
        }

    }

    /**
     * 注册
     * @param param
     * @return
     */
    @RequestMapping(value = "register",method = RequestMethod.POST)
    @ResponseBody
    public Result Register(@RequestBody ActType param){
        Result result = new Result();
        try {
            if (param.getAccount() == null || param.getAccount().equals("") || param.getPassword() == null || param.getPassword().equals("")){
                result.setResult(Result.FAIL);
                result.setMessage("请输入账号密码！");
                return  result;
            }

            String account = param.getAccount();
            String password = param.getPassword();
            User user = userDao.selectUserByAcc(account);
            if (user == null){
                userDao.insertUser(account,password);
            } else {
                result.setMessage("账号已注册！");
                result.setResult(Result.FAIL);
                return result;
            }
            result.setResult(Result.SUCCESS);
            return result;
        } catch (Exception e){
            result.setResult(Result.FAIL);
            result.setMessage(e.toString());
            return result;
        }
    }

    /**
     * 更新位置信息
     * @param param
     * @return
     */
    @RequestMapping(value = "location",method = RequestMethod.POST)
    @ResponseBody
    public Result location(@RequestBody ActType param){
        Result result = new Result();
        try {
            if (param.getLongitude() == null || param.getLongitude().equals("") || param.getLatitude() == null || param.getLatitude().equals("") || param.getUser_id() == null || param.getUser_id().equals("")){
                result.setResult(Result.FAIL);
                return  result;
            }
            Double longitude = Double.parseDouble(param.getLongitude());
            Double latitude = Double.parseDouble(param.getLatitude());
            Integer user_id = Integer.parseInt(param.getUser_id());
            userDao.updateUserPlace(user_id,longitude,latitude);
            result.setResult(Result.SUCCESS);
            return result;
        } catch (Exception e){
            result.setResult(Result.FAIL);
            result.setMessage(e.toString());
            return result;
        }
    }

    /**
     * 创建队伍(导游专用)
     * @param param
     * @return
     */
    @RequestMapping(value = "createTeam",method = RequestMethod.POST)
    @ResponseBody
    public Result createTeam(@RequestBody ActType param){
        Result result = new Result();
        try {
            if (param.getTeam_name() == null || param.getTeam_name().equals("") || param.getTeam_pw() == null || param.getTeam_pw().equals("") || param.getUser_id() == null || param.getUser_id().equals("0")){
                result.setResult(Result.FAIL);
                result.setMessage("请输入队名口令！");
                return  result;
            }

            String team_name = param.getTeam_name();
            String team_pw = param.getTeam_pw();
            Integer user_id = Integer.parseInt(param.getUser_id());
            Team team = new Team();
            team.setTeam_name(team_name);
            team.setTeam_pw(team_pw);
            team.setUser_id(user_id);
            Team check = teamDao.selectTeamByCondition(team_name,null);
            if (check == null){
                teamDao.insertTeam(team);
                teamDao.addMember(team.getUser_id(),team.getTeam_id(),1);
            } else {
                result.setMessage("队伍已存在！");
                result.setResult(Result.FAIL);
                return result;
            }
            result.setResult(Result.SUCCESS);
            result.setValue(team);
            return result;
        } catch (Exception e){
            result.setResult(Result.FAIL);
            result.setMessage(e.toString());
            return result;
        }
    }

    /**
     * 加入队伍(游客专用)
     * @param param
     * @return
     */
    @RequestMapping(value = "joinTeam",method = RequestMethod.POST)
    @ResponseBody
    public Result joinTeam(@RequestBody ActType param){
        Result result = new Result();
        try {
            if (param.getTeam_name() == null || param.getTeam_name().equals("") || param.getTeam_pw() == null || param.getTeam_pw().equals("") || param.getUser_id() == null || param.getUser_id().equals("")){
                result.setResult(Result.FAIL);
                result.setMessage("请输入队名口令！");
                return  result;
            }

            String team_name = param.getTeam_name();
            String team_pw = param.getTeam_pw();
            Integer user_id = Integer.parseInt(param.getUser_id());
            Team team = teamDao.selectTeamByCondition(team_name,team_pw);
            if (team == null){
                result.setMessage("队名或口令错误！");
                result.setResult(Result.FAIL);
                return result;
            } else {
                teamDao.addMember(user_id,team.getTeam_id(),2);
            }
            result.setResult(Result.SUCCESS);
            result.setValue(team);
            return result;
        } catch (Exception e){
            result.setResult(Result.FAIL);
            result.setMessage(e.toString());
            return result;
        }
    }

    /**
     * 退出队伍(游客专用)
     * @param param
     * @return
     */
    @RequestMapping(value = "exitTeam",method = RequestMethod.POST)
    @ResponseBody
    public Result exitTeam(@RequestBody ActType param){
        Result result = new Result();
        try {
            if (param.getTeam_id() == null || param.getTeam_id().equals("") || param.getUser_id() == null || param.getUser_id().equals("")){
                result.setResult(Result.FAIL);
                return  result;
            }

            Integer user_id = Integer.parseInt(param.getUser_id());
            Integer team_id = Integer.parseInt(param.getTeam_id());
            teamDao.exitTeam(user_id,team_id);
            result.setResult(Result.SUCCESS);
            return result;
        } catch (Exception e){
            result.setResult(Result.FAIL);
            result.setMessage(e.toString());
            return result;
        }
    }

    /**
     * 销毁队伍(导游专用)
     * @param param
     * @return
     */
    @RequestMapping(value = "deleteTeam",method = RequestMethod.POST)
    @ResponseBody
    public Result deleteTeam(@RequestBody ActType param){
        Result result = new Result();
        try {
            if (param.getTeam_id() == null || param.getTeam_id().equals("")){
                result.setResult(Result.FAIL);
                return  result;
            }
            Integer team_id = Integer.parseInt(param.getTeam_id());
            teamDao.deleteTeam(team_id);
            result.setResult(Result.SUCCESS);
            return result;
        } catch (Exception e){
            result.setResult(Result.FAIL);
            result.setMessage(e.toString());
            return result;
        }
    }

    /**
     * 获取所在队伍
     * @param param
     * @return
     */
    @RequestMapping(value = "getTeam",method = RequestMethod.POST)
    @ResponseBody
    public Result getTeam(@RequestBody ActType param){
        Result result = new Result();
        try {
            if (param.getUser_id() == null || param.getUser_id().equals("")){
                result.setResult(Result.FAIL);
                return  result;
            }
            Integer user_id = Integer.parseInt(param.getUser_id());
            Team team = teamDao.selectTeamByUser(user_id);
            result.setValue(team);
            result.setResult(Result.SUCCESS);
            return result;
        } catch (Exception e){
            result.setResult(Result.FAIL);
            result.setMessage(e.toString());
            return result;
        }
    }

    /**
     * 获取导游信息
     * @param param
     * @return
     */
    @RequestMapping(value = "getGuide",method = RequestMethod.POST)
    @ResponseBody
    public Result getGuide(@RequestBody ActType param){
        Result result = new Result();
        try {
            if (param.getTeam_id() == null || param.getTeam_id().equals("")){
                result.setResult(Result.FAIL);
                return  result;
            }
            Integer team_id = Integer.parseInt(param.getTeam_id());
            User guide = userDao.selectGuide(team_id);
            result.setValue(guide);
            result.setResult(Result.SUCCESS);
            return result;
        } catch (Exception e){
            result.setResult(Result.FAIL);
            result.setMessage(e.toString());
            return result;
        }
    }

    /**
     * 获取团队成员信息
     * @param param
     * @return
     */
    @RequestMapping(value = "getTeammate",method = RequestMethod.POST)
    @ResponseBody
    public Result getTeammate(@RequestBody ActType param){
        Result result = new Result();
        try {
            if (param.getTeam_id() == null || param.getTeam_id().equals("")){
                result.setResult(Result.FAIL);
                return  result;
            }
            Integer team_id = Integer.parseInt(param.getTeam_id());
            List<User> mates = userDao.selectAllTeammate(team_id);
            result.setValue(mates);
            result.setResult(Result.SUCCESS);
            return result;
        } catch (Exception e){
            result.setResult(Result.FAIL);
            result.setMessage(e.toString());
            return result;
        }
    }

    /**
     * 获取游客信息
     * @param param
     * @return
     */
    @RequestMapping(value = "getTourise",method = RequestMethod.POST)
    @ResponseBody
    public Result getTourise(@RequestBody ActType param){
        Result result = new Result();
        try {
            if (param.getTeam_id() == null || param.getTeam_id().equals("")){
                result.setResult(Result.FAIL);
                return  result;
            }
            Integer team_id = Integer.parseInt(param.getTeam_id());
            List<User> mates = userDao.selectTourise(team_id);
            result.setValue(mates);
            result.setResult(Result.SUCCESS);
            return result;
        } catch (Exception e){
            result.setResult(Result.FAIL);
            result.setMessage(e.toString());
            return result;
        }
    }
}
