package com.maximuslotro.mc.signpic.plugin.search;

import com.maximuslotro.mc.signpic.attr.AttrReaders;
import com.maximuslotro.mc.signpic.attr.prop.RotationData;

public class RotationDataAttrFilterProperty implements AttrFilterProperty<RotationData> {
	public static final RotationDataAttrFilterProperty ROTATION = new RotationDataAttrFilterProperty();

	public RotationDataAttrFilterProperty() {
	}

	@Override
	public RotationData get(final AttrReaders attr) {
		return attr.rotations.getMovie().get();
	}

}
