package cas.ibc.ubc.ca.zipkin.pojos;

import java.util.Collections;
import java.util.List;

public class ZipkinTracePojo {
	public final List<ZipkinSpanPojo> spans;
	
	public ZipkinTracePojo(List<ZipkinSpanPojo> spans) {
		this.spans = Collections.unmodifiableList(spans);
	}

	@Override
	public String toString() {
		return "ZipkinTracePojo [spans=" + spans + "]";
	}
	
	
}
