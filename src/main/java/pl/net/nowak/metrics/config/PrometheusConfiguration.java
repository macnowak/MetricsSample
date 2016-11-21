package pl.net.nowak.metrics.config;

import com.codahale.metrics.MetricRegistry;
import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.dropwizard.DropwizardExports;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Maciek on 16.11.2016.
 */
@Configuration
public class PrometheusConfiguration {

	@Autowired
	private MetricRegistry metricRegistry;

	@Bean
	public CollectorRegistry collectorRegistry() {
		CollectorRegistry collectorRegistry = new CollectorRegistry();
		collectorRegistry.register(new DropwizardExports(metricRegistry));
		return collectorRegistry;
	}
}