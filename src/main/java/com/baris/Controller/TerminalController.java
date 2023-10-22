package com.baris.Controller;

import com.baris.Entity.Terminal;
import com.baris.Services.TerminalService;
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
@RequestMapping("/terminals")
@RequiredArgsConstructor
public class TerminalController {
    private final TerminalService terminalService;

    @GetMapping
    public List<Terminal> getAllTerminals() {
        return terminalService.getAllTerminals();
    }

    @GetMapping("/{id}")
    public Terminal getTerminalById(@PathVariable Long id) {
        return terminalService.getTerminalById(id).orElse(null);
    }

    @PostMapping
    public Terminal createTerminal(@RequestBody Terminal terminal) {
        return terminalService.createTerminal(terminal);
    }

    @PutMapping("/{id}")
    public Terminal updateTerminal(@PathVariable Long id, @RequestBody Terminal terminal) {
        if (terminalService.getTerminalById(id).isPresent()) {
            terminal.setId(id);
            return terminalService.updateTerminal(terminal);
        } else {
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public void deleteTerminal(@PathVariable Long id) {
        terminalService.deleteTerminal(id);
    }
}
