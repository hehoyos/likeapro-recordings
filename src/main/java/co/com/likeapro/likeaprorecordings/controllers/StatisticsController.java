package co.com.likeapro.likeaprorecordings.controllers;

import co.com.likeapro.likeaprorecordings.models.Customer;
import co.com.likeapro.likeaprorecordings.models.Event;
import co.com.likeapro.likeaprorecordings.models.Statistics;
import co.com.likeapro.likeaprorecordings.services.StatisticsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/statistics")
@AllArgsConstructor
public class StatisticsController {

    private StatisticsService statisticsService;

    @PostMapping("/")
    public Mono<Statistics> createStatistics(@RequestBody Statistics statistics) {
        return statisticsService.saveStatistics(statistics);
    }

    @GetMapping("/{id}")
    public Mono<Statistics> readStatistics(@PathVariable Long id) {
        return statisticsService.findStatisticsById(id);
    }

    @GetMapping("/")
    public Flux<Statistics> readAllStatistics() {
        return statisticsService.findAllStatistics();
    }

    @PutMapping("/{id}")
    public Mono<Statistics> updateStatistics(@PathVariable Long id, @RequestBody Statistics statistics) {
        return statisticsService.updateStatistics(id, statistics);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteStatistics(@PathVariable Long id) {
        return statisticsService.deleteStatistics(id);
    }

    @GetMapping("/mvp")
    public Mono<Customer> getMvp() {
        return statisticsService.getMvp();
    }

    @GetMapping("/best-event")
    public Mono<Event> getBestEvent() {
        return statisticsService.getBestEvent();
    }
}
