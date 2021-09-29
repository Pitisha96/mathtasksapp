package by.itransition.mathtasksapp.services.Impl;

import by.itransition.mathtasksapp.models.Theme;
import by.itransition.mathtasksapp.repositories.ThemeRepository;
import by.itransition.mathtasksapp.services.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThemeServiceImpl implements ThemeService {
    private final ThemeRepository themeRepository;

    @Autowired
    public ThemeServiceImpl(ThemeRepository themeRepository) {
        this.themeRepository = themeRepository;
    }

    @Override
    public List<Theme> getAll() {
        return themeRepository.findAll();
    }
}
