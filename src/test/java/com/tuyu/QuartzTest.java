package com.tuyu;

import com.tuyu.job.SimpleJob;
import org.junit.Before;
import org.junit.Test;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

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
 * 简单作业调度和基于日历的作业调度
 * @author tuyu
 * @date 5/29/18
 * Stay Hungry, Stay Foolish.
 */
public class QuartzTest {

    private static final long RUNNING_TIMES = 20000L;// 20秒钟
    protected SchedulerFactory factory;
    protected Scheduler scheduler;
    protected JobDetail jobDetail;

    @Before
    public void before() throws Exception{
        // 实例化标准的调度器工厂
        factory = new StdSchedulerFactory();
        // 由调度器工厂实例化一个调度器
        scheduler = scheduler = factory.getScheduler();

        // 由任务构建器实例化一个任务
        jobDetail = JobBuilder.newJob(SimpleJob.class)
                .withIdentity("job1", "jobGroup1")
                .usingJobData("name", "scutuyu") // 为任务传递数据
                .build();
    }

    /**
     * 定时任务
     * <p>每秒钟执行任务一次</p>
     */
    @Test
    public void testSimpleQuartz() throws Exception{
        // 实例化标准的调度器工厂
        // 由触发器构建器实例化一个触发器
        // 由调度器工厂实例化一个调度器
        // 由任务构建器实例化一个任务

        SimpleTrigger simpleTrigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "triggerGroup1")
                .usingJobData("age", "24") // 为任务传递数据
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(1).repeatForever()) // 每秒执行一次，永远执行下去
                .build();

        // 由调度器调度执行任务
        scheduler.scheduleJob(jobDetail, simpleTrigger);
        scheduler.start();
        // 主线程睡眠，不然主线程结束，子线程也会结束
        Thread.sleep(RUNNING_TIMES);
    }

    /**
     * 定时任务[从0秒开始，没5秒执行一次]
     * <p>cron风格的定时任务</p>
     * <p>基于日历的作业调度</p>
     * <p>cron表达式：[秒] [分] [小时] [日] [月] [周] [年]</p>
     */
    @Test
    public void testCronTrigger() throws Exception {

        // 实例化标准的调度器工厂
        // 由触发器构建器实例化一个触发器
        // 由调度器工厂实例化一个调度器
        // 由任务构建器实例化一个任务

        CronTrigger cronTrigger = TriggerBuilder.newTrigger()
                .withIdentity("cronTrigger1", "cronTriggerGroup1")
                .usingJobData("age", "24")
                .startNow()
                .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ? "))
                .build();

        // 由调度器调度执行任务
        scheduler.scheduleJob(jobDetail, cronTrigger);
        scheduler.start();
        // 主线程睡眠，不然主线程结束，子线程也会结束
        Thread.sleep(120000L);
    }
}
