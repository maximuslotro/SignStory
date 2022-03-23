package com.maximuslotro.mc.signpic.http.upload;

import javax.annotation.Nullable;

import com.maximuslotro.mc.signpic.http.ICommunicate;

public interface IUploader extends ICommunicate {
	@Nullable
	String getLink();
}
