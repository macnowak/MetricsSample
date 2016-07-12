package pl.net.nowak.metrics.config;

import com.codahale.metrics.Metric;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.MetricSet;
import com.codahale.metrics.Slf4jReporter;
import com.codahale.metrics.jvm.GarbageCollectorMetricSet;
import com.codahale.metrics.jvm.MemoryUsageGaugeSet;
import com.ryantenney.metrics.spring.config.annotation.EnableMetrics;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.annotation.PostConstruct;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableMetrics
public class MetricsConfiguration {

	@Autowired
	private MetricRegistry metricRegistry;

	@PostConstruct
	public void startConsoleReporter() {
		final Slf4jReporter reporter = Slf4jReporter.forRegistry(metricRegistry)
				.outputTo(LoggerFactory.getLogger("metrics"))
				.convertRatesTo(TimeUnit.SECONDS)
				.convertDurationsTo(TimeUnit.MILLISECONDS)
				.build();
		reporter.start(10, TimeUnit.SECONDS);
	}

	@PostConstruct
	public void registerJvmMetrics() {
		registerAll("gc", new GarbageCollectorMetricSet(), metricRegistry);
		registerAll("memory", new MemoryUsageGaugeSet(), metricRegistry);
	}

	private void registerAll(String prefix, MetricSet metricSet, MetricRegistry registry) {
		for (Map.Entry<String, Metric> entry : metricSet.getMetrics().entrySet()) {
			if (entry.getValue() instanceof MetricSet) {
				registerAll(prefix + "." + entry.getKey(), (MetricSet) entry.getValue(), registry);
			} else {
				registry.register(prefix + "." + entry.getKey(), entry.getValue());
			}
		}
	}

}
