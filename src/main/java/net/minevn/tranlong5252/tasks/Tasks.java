package net.minevn.tranlong5252.tasks;

import java.util.Objects;

import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

/**
 * EnchantmentAPI © 2017
 * com.sucy.enchant.api.Tasks
 *
 * Utility class providing ways to register tasks through EnchantmentAPI
 */
public class Tasks {

    /**
     * Schedules a task to run
     *
     * @param runnable runnable to execute
     * @return task handling the runnable
     */
    public static BukkitTask sync(final Runnable runnable, Plugin plugin) {
        return plugin.getServer().getScheduler().runTask(plugin, runnable);
    }

    /**
     * Schedules a task to run after the specified number of ticks
     *
     * @param runnable runnable to execute
     * @param delay number of ticks to wait
     * @return task handling the runnable
     */
    public static BukkitTask sync(final Runnable runnable, final int delay, Plugin plugin) {
        Objects.requireNonNull(runnable, "Runnable cannot be null");
        return plugin.getServer().getScheduler().runTaskLater(plugin, runnable, delay);
    }

    /**
     * Schedules a task to run continuously
     *
     * @param runnable runnable to execute
     * @param delay delay in ticks before running the task the first time
     * @param interval time in ticks between each subsequent execution
     * @return task handling the runnable
     */
    public static BukkitTask sync(final Runnable runnable, final int delay, final int interval, Plugin plugin) {
        Objects.requireNonNull(runnable, "Runnable cannot be null");
        return plugin.getServer().getScheduler().runTaskTimer(plugin, runnable, delay, interval);
    }

    /**
     * Schedules a task to run a given number of times
     *
     * @param runnable runnable to execute
     * @param delay delay in ticks before running the task the first time
     * @param interval time in ticks between each subsequent execution
     * @param repetitions number of times the task should run
     * @return task handling the runnable
     */
    public static BukkitTask sync(final Runnable runnable, final int delay, final int interval, int repetitions, Plugin plugin) {
        Objects.requireNonNull(runnable, "Runnable cannot be null");
        return new RepeatTask(runnable, repetitions).runTaskTimer(plugin, delay, interval);
    }

    /**
     * Schedules a async task to run
     *
     * @param runnable runnable to execute
     * @return task handling the runnable
     */
    public static BukkitTask async(final Runnable runnable, Plugin plugin) {
        return plugin.getServer().getScheduler().runTaskAsynchronously(plugin, runnable);
    }

    /**
     * Schedules a async task to run after the specified number of ticks
     *
     * @param runnable runnable to execute
     * @param delay number of ticks to wait
     * @return task handling the runnable
     */
    public static BukkitTask async(final Runnable runnable, final int delay, Plugin plugin) {
        Objects.requireNonNull(runnable, "Runnable cannot be null");
        return plugin.getServer().getScheduler().runTaskLaterAsynchronously(plugin, runnable, delay);
    }

    /**
     * Schedules a async task to run continuously
     *
     * @param runnable runnable to execute
     * @param delay delay in ticks before running the task the first time
     * @param interval time in ticks between each subsequent execution
     * @return task handling the runnable
     */
    public static BukkitTask async(final Runnable runnable, final int delay, final int interval, Plugin plugin) {
        Objects.requireNonNull(runnable, "Runnable cannot be null");
        return plugin.getServer().getScheduler().runTaskTimerAsynchronously(plugin, runnable, delay, interval);
    }

    /**
     * Schedules a async task to run a given number of times
     *
     * @param runnable runnable to execute
     * @param delay delay in ticks before running the task the first time
     * @param interval time in ticks between each subsequent execution
     * @param repetitions number of times the task should run
     * @return task handling the runnable
     */
    public static BukkitTask async(final Runnable runnable, final int delay, final int interval, int repetitions, Plugin plugin) {
        Objects.requireNonNull(runnable, "Runnable cannot be null");
        return new RepeatTask(runnable, repetitions).runTaskTimerAsynchronously(plugin, delay, interval);
    }


    private static class RepeatTask extends BukkitRunnable {
        private final Runnable runnable;
        private int repetitions;
        public RepeatTask(final Runnable runnable, final int repetitions) {
            this.runnable = runnable;
            this.repetitions = repetitions;
        }
        @Override
        public void run() {
            runnable.run();
            repetitions--;
            if (repetitions <= 0) {
                cancel();
            }
        }
    }
}