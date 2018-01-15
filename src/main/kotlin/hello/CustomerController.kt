package hello

import io.micrometer.core.annotation.Timed
import io.micrometer.core.instrument.Metrics
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@Timed
class CustomerController(private val repository: CustomerRepository) {

	@GetMapping("/customers")
	fun findAll(): MutableIterable<Customer> {
		Metrics.counter("views.customer").increment()
		Metrics.gauge("gaugeSample", Math.random())
		return repository.findAll()
	}

	@GetMapping("/customers/{lastName}")
	fun findByLastName(@PathVariable lastName:String)
			= repository.findByLastName(lastName)

}