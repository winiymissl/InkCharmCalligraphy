package com.example.module_character.ui.adapter.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author winiymissl
 * @Date 2024-04-18 11:13
 * @Version 1.0
 */
public class MatchItem {
    Integer id;
    String name;
    String data_time;
    String end_data_time;
    String duration;
    String rewards_info;
    String location;
    String info;


    String organizer;

    public String getName() {
        return name;
    }

    public String getData_time() {
        return data_time;
    }

    public String getEnd_data_time() {
        return end_data_time;
    }

    public String getOrganizer() {
        return organizer;
    }

    public String getDuration() {
        return duration;
    }

    public String getRewards_info() {
        return rewards_info;
    }

    public String getLocation() {
        return location;
    }

    public String getInfo() {
        return info;
    }

    public static final String[] organizers = {
            "中国书法协会",
            "上海书法协会",
            "北京书法协会",
            "广州书法协会",
            "深圳书法协会"
    };

    public static final String[] names = {
            "第一届 北京书法大赛",
            "第二届 上海书法大赛",
            "第三届 广州书法大赛",
            "第四届 深圳书法大赛",
            "第五届 南京书法大赛"
    };

    public static final String[] data_times = {
            "2023-04-18 09:00:00",
            "2023-05-15 10:00:00",
            "2023-07-20 08:30:00",
            "2023-09-10 09:30:00",
            "2023-11-05 11:00:00"
    };

    public static final String[] end_data_times = {
            "2023-04-18 18:00:00",
            "2023-05-15 18:00:00",
            "2023-07-20 18:30:00",
            "2023-09-10 18:30:00",
            "2023-11-05 18:00:00"
    };

    public static final String[] durations = {
            "1天",
            "1天",
            "1天",
            "1天",
            "1天"
    };

    public static final String[] rewards_infos = {
            "一等奖：1000元\n二等奖：500元\n三等奖：300元\n四等奖：100元\n五等奖：50元\n六等奖：10元",
            "一等奖：1500元\n二等奖：800元\n三等奖：500元\n四等奖：200元\n五等奖：100元\n六等奖：50元",
            "一等奖：1200元\n二等奖：600元\n三等奖：400元\n四等奖：150元\n五等奖：80元\n六等奖：30元",
            "一等奖：1100元\n二等奖：550元\n三等奖：350元\n四等奖：120元\n五等奖：60元\n六等奖：20元",
            "一等奖：1300元\n二等奖：700元\n三等奖：450元\n四等奖：130元\n五等奖：70元\n六等奖：25元"
    };

    public static final String[] locations = {
            "中国·北京",
            "中国·上海",
            "中国·广州",
            "中国·深圳",
            "中国·南京"
    };

    public static final String[] infos = {
            "比赛主题：中国书法艺术传承与创新\n" +
                    "参赛资格：不限年龄，全国范围内的书法爱好者均可参加\n" +
                    "报名方式：参赛者需于6月1日至6月20日之间填写在线报名表格，并缴纳100元报名费用。报名表格和费用缴纳链接将在官方网站上公布。\n" +
                    "评审标准：书法作品将根据字体规范、笔墨结合、艺术表现力等方面进行评审。评审团将由资深书法家和艺术专家组成。\n" +
                    "奖项设置：设立金奖1名、银奖3名、铜奖5名，优秀奖若干名，另设最佳创意奖和最具传承价值奖各1名。获奖者将获得荣誉证书和奖金。\n" +
                    "赛制安排：比赛采用单轮赛制，每位参赛者需在比赛当天完成一幅书法作品。评审团将在比赛结束后进行评选。",
            "比赛主题：上海书法新风尚\n" +
                    "参赛资格：不限年龄，全国范围内的书法爱好者均可参加\n" +
                    "报名方式：参赛者需于6月1日至6月20日之间填写在线报名表格，并缴纳150元报名费用。报名表格和费用缴纳链接将在官方网站上公布。\n" +
                    "评审标准：书法作品将根据字体规范、笔墨结合、艺术表现力等方面进行评审。评审团将由资深书法家和艺术专家组成。\n" +
                    "奖项设置：设立金奖1名、银奖3名、铜奖5名，优秀奖若干名，另设最佳创意奖和最具传承价值奖各1名。获奖者将获得荣誉证书和奖金。\n" +
                    "赛制安排：比赛采用单轮赛制，每位参赛者需在比赛当天完成一幅书法作品。评审团将在比赛结束后进行评选。",
            "比赛主题：上海书法新风尚\n" +
                    "参赛资格：不限年龄，全国范围内的书法爱好者均可参加\n" +
                    "报名方式：参赛者需于6月1日至6月20日之间填写在线报名表格，并缴纳150元报名费用。报名表格和费用缴纳链接将在官方网站上公布。\n" +
                    "评审标准：书法作品将根据字体规范、笔墨结合、艺术表现力等方面进行评审。评审团将由资深书法家和艺术专家组成。\n" +
                    "奖项设置：设立金奖1名、银奖3名、铜奖5名，优秀奖若干名，另设最佳创意奖和最具传承价值奖各1名。获奖者将获得荣誉证书和奖金。\n" +
                    "赛制安排：比赛采用单轮赛制，每位参赛者需在比赛当天完成一幅书法作品。评审团将在比赛结束后进行评选。",
            "比赛主题：上海书法新风尚\n" +
                    "参赛资格：不限年龄，全国范围内的书法爱好者均可参加\n" +
                    "报名方式：参赛者需于6月1日至6月20日之间填写在线报名表格，并缴纳150元报名费用。报名表格和费用缴纳链接将在官方网站上公布。\n" +
                    "评审标准：书法作品将根据字体规范、笔墨结合、艺术表现力等方面进行评审。评审团将由资深书法家和艺术专家组成。\n" +
                    "奖项设置：设立金奖1名、银奖3名、铜奖5名，优秀奖若干名，另设最佳创意奖和最具传承价值奖各1名。获奖者将获得荣誉证书和奖金。\n" +
                    "赛制安排：比赛采用单轮赛制，每位参赛者需在比赛当天完成一幅书法作品。评审团将在比赛结束后进行评选。",
            "比赛主题：上海书法新风尚\n" +
                    "参赛资格：不限年龄，全国范围内的书法爱好者均可参加\n" +
                    "报名方式：参赛者需于6月1日至6月20日之间填写在线报名表格，并缴纳150元报名费用。报名表格和费用缴纳链接将在官方网站上公布。\n" +
                    "评审标准：书法作品将根据字体规范、笔墨结合、艺术表现力等方面进行评审。评审团将由资深书法家和艺术专家组成。\n" +
                    "奖项设置：设立金奖1名、银奖3名、铜奖5名，优秀奖若干名，另设最佳创意奖和最具传承价值奖各1名。获奖者将获得荣誉证书和奖金。\n" +
                    "赛制安排：比赛采用单轮赛制，每位参赛者需在比赛当天完成一幅书法作品。评审团将在比赛结束后进行评选。",
            // 添加其他比赛的信息
    };

    public MatchItem(String name, String data_time, String end_data_time, String duration, String rewards_info, String location, String info, String organizer) {
        this.name = name;
        this.data_time = data_time;
        this.end_data_time = end_data_time;
        this.duration = duration;
        this.rewards_info = rewards_info;
        this.location = location;
        this.info = info;
        this.organizer = organizer;
    }

    public static List<MatchItem> getData() {
        List<MatchItem> list = new ArrayList<>();
        for (int i = 0; i < names.length; i++) {
            list.add(new MatchItem(names[i], data_times[i], end_data_times[i], durations[i], rewards_infos[i], locations[i], infos[i], organizers[i]));
        }
        return list;
    }
}