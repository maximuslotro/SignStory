package com.maximuslotro.mc.signpic.plugin.search;

import com.maximuslotro.mc.signpic.attr.AttrReaders;
import com.maximuslotro.mc.signpic.attr.prop.SizeData;

public class SizeDataAttrFilterProperty implements AttrFilterProperty<SizeData> {

	public static final SizeDataAttrFilterProperty SIZE = new SizeDataAttrFilterProperty();

	private SizeDataAttrFilterProperty() {
	}

	@Override
	public SizeData get(final AttrReaders attr) {
		return attr.sizes.getMovie().get();
	}

}
