package com.maximuslotro.mc.signpic.image;

import javax.annotation.Nonnull;

import com.maximuslotro.mc.signpic.attr.prop.SizeData;

public interface ImageTexture {

	void bind();

	@Nonnull
	SizeData getSize();

	boolean hasMipmap();

}