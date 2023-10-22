package com.baris.Services;
import com.baris.Repositories.TerminalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import com.baris.Entity.Terminal;

@Service
@RequiredArgsConstructor
public class TerminalService {
    private final TerminalRepository terminalRepository;

    public List<Terminal> getAllTerminals() {
        return terminalRepository.findAll();
    }

    public Optional<Terminal> getTerminalById(Long id) {
        return terminalRepository.findById(id);
    }

    public Terminal createTerminal(Terminal terminal) {
        return terminalRepository.save(terminal);
    }

    public Terminal updateTerminal(Terminal terminal) {
        return terminalRepository.save(terminal);
    }

    public void deleteTerminal(Long id) {
        terminalRepository.deleteById(id);
    }
}
