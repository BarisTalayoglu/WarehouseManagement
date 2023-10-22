package com.baris.Controller;

import com.baris.Entity.Truck;
import com.baris.Services.TruckService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import java.util.List;

@RestController
@RequestMapping("/trucks")
@RequiredArgsConstructor
public class TruckController {
    private final TruckService truckService;

    @GetMapping
    public List<Truck> getAllTrucks() {
        return truckService.getAllTrucks();
    }

    @GetMapping("/{id}")
    public Truck getTruckById(@PathVariable Long id) {
        return truckService.getTruckById(id).orElse(null);
    }

    @PostMapping
    public Truck createTruck(@RequestBody Truck truck) {
        return truckService.createTruck(truck);
    }

    @PutMapping("/{id}")
    public Truck updateTruck(@PathVariable Long id, @RequestBody Truck truck) {
        if (truckService.getTruckById(id).isPresent()) {
            truck.setId(id);
            return truckService.updateTruck(truck);
        } else {
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public void deleteTruck(@PathVariable Long id) {
        truckService.deleteTruck(id);
    }
}
