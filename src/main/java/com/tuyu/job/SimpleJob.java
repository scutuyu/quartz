package com.tuyu.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <pre>
 * ////////////////////////////////////////////////////////////////////
 * //                          _ooOoo_                               //
 * //                         o8888888o                              //
 * //                         88" . "88                              //
 * //                         (| ^_^ |)                              //
 * //                         O\  =  /O                              //
 * //                      ____/`---'\____                           //
 * //                    .'  \\|     |//  `.                         //
 * //                   /  \\|||  :  |||//  \                        //
 * //                  /  _||||| -:- |||||-  \                       //
 * //                  |   | \\\  -  /// |   |                       //
 * //                  | \_|  ''\---/''  |   |                       //
 * //                  \  .-\__  `-`  ___/-. /                       //
 * //                ___`. .'  /--.--\  `. . ___                     //
 * //              ."" '<  `.___\_<|>_/___.'  >'"".                  //
 * //            | | :  `- \`.;`\ _ /`;.`/ - ` : | |                 //
 * //            \  \ `-.   \_ __\ /__ _/   .-` /  /                 //
 * //      ========`-.____`-.___\_____/___.-`____.-'========         //
 * //                           `=---='                              //
 * //      ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^        //
 * //             佛祖保佑       永无BUG     永不修改                   //
 * ////////////////////////////////////////////////////////////////////
 * </pre>
 * <p>
 * tuyu于5/29/18祈祷...
 *
 * @author tuyu
 * @date 5/29/18
 * Stay Hungry, Stay Foolish.
 */
public class SimpleJob implements Job {

    private String name;
    private String age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S")
                .format(new Date());

        // 获取DataMap数据方法一
        String name1 = context.getJobDetail().getJobDataMap().get("name").toString();
        String age1 = context.getTrigger().getJobDataMap().get("age").toString();

        // 获取DataMap数据方法二
        String name2 = context.getMergedJobDataMap().get("name").toString();
        String age2 = context.getMergedJobDataMap().get("age").toString();

        // 获取DateMap数据的方法三
        String name3 = name;
        String age3 = age;
        StringBuilder stringBuilder = new StringBuilder("-----> \nThread: ");
        stringBuilder.append(Thread.currentThread().getName())
                .append("\nStatus: Hello World")
                .append("\nTime: ").append(time)
                .append("\nname1: ").append(name1)
                .append("\nage1: ").append(age1)
                .append("\nname2: ").append(name2)
                .append("\nage2: ").append(age2)
                .append("\nname3: ").append(name3)
                .append("\nage3: ").append(age3);
        System.out.println(stringBuilder.toString());
    }
}
