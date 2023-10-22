package com.baris.Controller;

import com.baris.Entity.Material;
import com.baris.Services.MaterialService;
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
@RequestMapping("/materials")
@RequiredArgsConstructor
public class MaterialController {
    private final MaterialService materialService;

    @GetMapping
    public List<Material> getAllMaterials() {
        return materialService.getAllMaterials();
    }

    @GetMapping("/{id}")
    public Material getMaterialById(@PathVariable Long id) {
        return materialService.getMaterialById(id).orElse(null);
    }

    @PostMapping
    public Material createMaterial(@RequestBody Material material) {
        return materialService.createMaterial(material);
    }

    @PutMapping("/{id}")
    public Material updateMaterial(@PathVariable Long id, @RequestBody Material material) {
        if (materialService.getMaterialById(id).isPresent()) {
            material.setId(id);
            return materialService.updateMaterial(material);
        } else {
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public void deleteMaterial(@PathVariable Long id) {
        materialService.deleteMaterial(id);
    }
}
