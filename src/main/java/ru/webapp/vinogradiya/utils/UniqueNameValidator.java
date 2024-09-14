package ru.webapp.vinogradiya.utils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.webapp.vinogradiya.repositories.SelectionsRepository;

@Component
public class UniqueNameValidator implements ConstraintValidator<UniqueName, String> {
    SelectionsRepository selectionsRepository;

    public UniqueNameValidator() {
    }

    @Autowired
    public UniqueNameValidator(SelectionsRepository selectionsRepository) {
        this.selectionsRepository = selectionsRepository;
    }

    @Override
    public boolean isValid(String name, ConstraintValidatorContext context) {
        return !selectionsRepository.existsByName(name);
    }
}
