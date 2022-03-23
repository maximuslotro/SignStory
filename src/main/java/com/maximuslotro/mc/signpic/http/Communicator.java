package com.maximuslotro.mc.signpic.http;

import java.util.concurrent.ExecutorService;

import javax.annotation.Nonnull;

import com.maximuslotro.mc.bnnwidget.util.NotifyCollections.NotifyArrayDeque;
import com.maximuslotro.mc.signpic.Config;
import com.maximuslotro.mc.signpic.state.Progressable;
import com.maximuslotro.mc.signpic.util.ThreadUtils;

public class Communicator {
	public static @Nonnull Communicator instance = new Communicator();

	private final @Nonnull NotifyArrayDeque<Progressable> tasks = new NotifyArrayDeque<Progressable>();
	private final @Nonnull ExecutorService threadpool = ThreadUtils.newFixedCachedThreadPool(Config.getConfig().communicateThreads.get(), "signpic-http-%d");

	public @Nonnull NotifyArrayDeque<Progressable> getTasks() {
		return this.tasks;
	}

	public void communicate(final @Nonnull ICommunicate communicate) {
		if (communicate instanceof Progressable)
			synchronized (this.tasks) {
				this.tasks.offer((Progressable) communicate);
			}
		this.threadpool.execute(new Runnable() {
			@SuppressWarnings("unlikely-arg-type")
			@Override
			public void run() {
				try {
					communicate.communicate();
				} finally {
					synchronized (Communicator.this.tasks) {
						Communicator.this.tasks.remove(communicate);
					}
				}
			}
		});
	}
}
