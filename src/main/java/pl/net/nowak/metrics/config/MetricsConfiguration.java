package pl.net.nowak.metrics.config;

import com.codahale.metrics.Metric;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.MetricSet;
import com.codahale.metrics.Slf4jReporter;
import com.codahale.metrics.graphite.Graphite;
import com.codahale.metrics.graphite.GraphiteReporter;
import com.codahale.metrics.jvm.GarbageCollectorMetricSet;
import com.codahale.metrics.jvm.MemoryUsageGaugeSet;
import com.ryantenney.metrics.spring.config.annotation.EnableMetrics;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.annotation.PostConstruct;
import org.elasticsearch.metrics.ElasticsearchReporter;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableMetrics
public class MetricsConfiguration {

	@Autowired
	private MetricRegistry metricRegistry;

	@Value(value = "${elasticsearch.host}")
	private String elasticHost;

	@Value(value = "${graphite.host}")
	private String graphiteHost;

	@Value(value = "${system.nodeId}")
	private String nodeId;

	@PostConstruct
	public void startConsoleReporter() throws IOException {
		final Slf4jReporter reporter = Slf4jReporter.forRegistry(metricRegistry)
			.outputTo(LoggerFactory.getLogger("metrics"))
			.convertRatesTo(TimeUnit.SECONDS)
			.convertDurationsTo(TimeUnit.MILLISECONDS)
			.build();
		reporter.start(10, TimeUnit.SECONDS);

		final ElasticsearchReporter elReporter = ElasticsearchReporter
			.forRegistry(metricRegistry)
			.hosts(elasticHost)
			.index("metrics")
			.indexDateFormat(null) //no date suffix
			.prefixedWith(nodeId + "-")
			.build();
		elReporter.start(1, TimeUnit.MINUTES);

		final GraphiteReporter graphiteReporter = GraphiteReporter
			.forRegistry(metricRegistry)
			.prefixedWith(nodeId)
			.build(new Graphite(graphiteHost, 2003));
		graphiteReporter.start(10, TimeUnit.SECONDS);

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
