package ru.webapp.vinogradiya.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.webapp.vinogradiya.models.Selection;
import ru.webapp.vinogradiya.repositories.SelectionsRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SelectionsService {
    SelectionsRepository selectionsRepository;

    @Autowired
    public SelectionsService(SelectionsRepository selectionsRepository) {
        this.selectionsRepository = selectionsRepository;
    }

    public List<Selection> findAll() {
        return selectionsRepository.findAll();
    }

    public Selection findById(Long id) {
        return selectionsRepository.findById(id).orElse(null);
    }

    @Transactional
    public void create(Selection newSelection) {
        selectionsRepository.save(newSelection);
    }

    @Transactional
    public void update(Long id, Selection updatedSelection) {
        updatedSelection.setId(id);
        selectionsRepository.save(updatedSelection);
    }

    @Transactional
    public void delete(Long id) {
        selectionsRepository.deleteById(id);
    }
}
