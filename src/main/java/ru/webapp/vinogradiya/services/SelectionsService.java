package ru.webapp.vinogradiya.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.webapp.vinogradiya.models.Selection;
import ru.webapp.vinogradiya.repositories.SelectionsRepository;

@Service
public class SelectionsService {
    SelectionsRepository selectionsRepository;

    @Autowired
    public SelectionsService(SelectionsRepository selectionsRepository) {
        this.selectionsRepository = selectionsRepository;
    }

    public void create(Selection newSelection) {
        selectionsRepository.save(newSelection);
    }
}
