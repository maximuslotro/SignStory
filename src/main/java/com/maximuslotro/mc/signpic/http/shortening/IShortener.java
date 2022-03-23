package com.maximuslotro.mc.signpic.http.shortening;

import javax.annotation.Nullable;

import com.maximuslotro.mc.signpic.http.ICommunicate;

public interface IShortener extends ICommunicate {
	@Nullable
	String getShortLink();
}
