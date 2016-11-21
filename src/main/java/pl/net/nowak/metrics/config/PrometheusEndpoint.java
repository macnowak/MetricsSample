package pl.net.nowak.metrics.config;

import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.exporter.common.TextFormat;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.AbstractEndpoint;
import org.springframework.stereotype.Component;

/**
 * Created by Maciek on 21.11.2016.
 */
@Slf4j
@Component
class PrometheusEndpoint extends AbstractEndpoint<String> {

	private CollectorRegistry registry;

	@Autowired
	PrometheusEndpoint(CollectorRegistry registry) {
		super("prometheus", false, true);
		this.registry = registry;
	}

	@Override
	public String invoke() {
		try {
			Writer writer = new StringWriter();
			TextFormat.write004(writer, registry.metricFamilySamples());
			return writer.toString();
		} catch (IOException e) {
			log.error(e.getMessage(), e);
			return "";
		}
	}
}
