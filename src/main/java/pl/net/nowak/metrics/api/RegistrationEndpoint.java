package pl.net.nowak.metrics.api;

import com.codahale.metrics.annotation.Timed;
import com.google.common.collect.Lists;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(path = "registration")
public class RegistrationEndpoint {

	private static final List<String> items = Lists.newArrayList();

	@Timed(absolute = true, name = "registration.register")
	@RequestMapping(method = RequestMethod.POST, path = "/{item}")
	public void register(@PathVariable("item") String item) {
		log.info("new registration {} ", item);
		items.add(item);
	}

	@Timed(absolute = true, name = "registration.list")
	@RequestMapping(method = RequestMethod.GET)
	public List<String> getItems() {
		return items;
	}

}
