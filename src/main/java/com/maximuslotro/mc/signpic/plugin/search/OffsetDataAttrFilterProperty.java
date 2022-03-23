package com.maximuslotro.mc.signpic.plugin.search;

import com.maximuslotro.mc.signpic.attr.AttrReaders;
import com.maximuslotro.mc.signpic.attr.prop.OffsetData;

public class OffsetDataAttrFilterProperty implements AttrFilterProperty<OffsetData> {

	public static final OffsetDataAttrFilterProperty OFFSET = new OffsetDataAttrFilterProperty();

	private OffsetDataAttrFilterProperty() {
	}

	@Override
	public OffsetData get(final AttrReaders attr) {
		return attr.offsets.getMovie().get();
	}

}
