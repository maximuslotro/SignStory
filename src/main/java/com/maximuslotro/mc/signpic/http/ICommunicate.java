package com.maximuslotro.mc.signpic.http;

import javax.annotation.Nonnull;

import com.maximuslotro.mc.signpic.ILoadCancelable;

public interface ICommunicate extends ILoadCancelable {
	void communicate();

	void setCallback(@Nonnull ICommunicateCallback callback);
}
