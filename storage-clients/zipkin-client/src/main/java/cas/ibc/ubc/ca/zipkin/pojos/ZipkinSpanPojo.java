package cas.ibc.ubc.ca.zipkin.pojos;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ZipkinSpanPojo {

	public List<ZipkinAnnotationPojo> annotations;

	public final Boolean debug;
	public final Long duration;
	public final String id;
	public final String name;
	public final String parentId;
	public final Long timestamp;
	public final String traceId;

	public ZipkinSpanPojo(Boolean debug, Long duration, String id, String name, String parentId, Long timestamp,
			String traceId) {
		super();
		annotations = new LinkedList<ZipkinAnnotationPojo>();

		this.debug = debug;
		this.duration = duration;
		this.id = id;
		this.name = name;
		this.parentId = parentId;
		this.timestamp = timestamp;
		this.traceId = traceId;
	}

	public List<ZipkinAnnotationPojo> getAnnotations() {
		return Collections.unmodifiableList(annotations);
	}

	public void addAnnotations(ZipkinAnnotationPojo annotation) {
		this.annotations.add(annotation);
	}

	public Boolean getDebug() {
		return debug;
	}

	public Long getDuration() {
		return duration;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getParentId() {
		return parentId;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public String getTraceId() {
		return traceId;
	}

	@Override
	public String toString() {
		return "ZipkinSpanPojo [annotations=" + annotations + ", debug=" + debug + ", duration=" + duration + ", id="
				+ id + ", name=" + name + ", parentId=" + parentId + ", timestamp=" + timestamp + ", traceId=" + traceId
				+ "]";
	}

	
	
}
